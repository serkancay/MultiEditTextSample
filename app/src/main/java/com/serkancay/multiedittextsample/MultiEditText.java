package com.serkancay.multiedittextsample;

import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by serkan on 24.02.2016.
 */
public class MultiEditText {
    // Linear Layout için referansımız.
    private LinearLayout linearEnterText;
    // EditText sayısı
    int lineCount;
    // Veri girişini bunun sayesinde dinliyoruz.
    TextWatcher textWatcher;
    // EditTextlerimizin listesi
    private List<EditText> editTextList = new ArrayList<>();
    // EditTextlerden verileri bir liste halinde geri alacağız.
    private List<String> dataList;
    // Context için referans
    Activity activity;
    // Eğer EditText için ipucu belirlenmezse varsayılan olarak bu yazacak
    String editTextHint = "Type here..";

    // Yapıcı fonksiyonumuz. Parametre olarak context, yerleşim yeri olan Linear Layout'un id si
    // ve EditText için ipucu
    public MultiEditText(Activity activity, int linearId, String editTextHint){
        linearEnterText = (LinearLayout) activity.findViewById(linearId);
        this.activity = activity;
        this.editTextHint = editTextHint;
        // Yapıcı fonksiyon çağırıldığında ekrana ilk EditTextimiz ekleniyor.
        addNewEditText();
    }

    // Yeni EditText ekleyen fonksiyon
    private void addNewEditText(){
        // Yerleşimimize yeni EditText öğesi ekleniyor.
        linearEnterText.addView(editText(lineCount));
        // EditText sayımız bir artırılıyor.
        lineCount++;
    }

    // Parametre olarak aldığı EditText'i dinliyor.
    private void setTextListener(final EditText editText){
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Eğer son EditTexte bir veri girişi olmuşsa listener kaldırılıyor ve
                // yeni EditText ekleniyor.
                editText.removeTextChangedListener(textWatcher);
                addNewEditText();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        // EditTexte listener atanıyor.
        editText.addTextChangedListener(textWatcher);
    }

    // Bu metod EditText verilerini bir liste halinde dönderiyor.
    public List<String> getStringData(){
        // Liste oluşturuldu.
        dataList = new ArrayList<>();

        // EditText listesi taranıyor.
        for(int i=0; i<editTextList.size() ; i++){
            // Boş içeriğe sahip EditTextler listeye eklenmiyor.
            if( editTextList.get(i).length() > 0){
                dataList.add(editTextList.get(i).getText().toString());
            }
        }
        return dataList;

    }

    // EditText hazırlayıcı metod
    // Parametre olarak int alıyor bunlar EditText sayısıdır. 0,1,2,3...
    private EditText editText(int _id){
        EditText editText = new EditText(activity);
        // Id belirlendi
        editText.setId(_id);
        // Burada ipucuna ekleme yapılıyor
        // 1. Liste elemanı gibi...
        editText.setHint( _id+1 + ". " + editTextHint);
        // Genişliği Match_Parent olarak ayarlandı.
        editText.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        // Tek bir satır olması belirlendi.
        editText.setSingleLine();
        // Giriş yöntemi belirlendi. Burada Text ama isteğe göre number vs. değiştirilebilir.
        editText.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        // EditText listesine ekleniyor.
        editTextList.add(editText);
        // Listener atanıyor.
        setTextListener(editText);
        return editText;
    }
}
