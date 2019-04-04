package com.mminf.mensafontenuova;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences_agg.edit().putString(campo, valore).commit();
    }


    private boolean connessioneok = false;

    public String leggi_str(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(this);
        String valore = "";
        return mPreferences_leg.getString(campo, valore);
    }

    public int leggi_int(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(this);
        int valore = 0;
        return mPreferences_leg.getInt(campo, valore);
    }

    private SharedPreferences mPreferences;
    private String username;
    private String password;
    int contatore;

    void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences3.edit().putInt(campo, valore).commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText n_user = findViewById(R.id.editText);
        EditText n_password = findViewById(R.id.editText2);
        RadioButton rad1 = findViewById(R.id.radioButton);
        RadioButton rad2 = findViewById(R.id.radioButton2);

        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        /* WebView myWebView =  findViewById(R.id.WEB);*/

        if (leggi_str("radio1") == "0") {
            rad1.setChecked(true);
            rad2.setChecked(false);
        }

        if (leggi_str("radio1") == "1") {
            rad1.setChecked(true);
            rad2.setChecked(false);
        }
        if (leggi_str("radio1") == "2") {
            rad1.setChecked(false);
            rad2.setChecked(true);
        }


        username = leggi_str("username");
        password = leggi_str("password");
        n_user.setText(username);
        n_password.setText(password);

    }

    public void connesso(String username_site, String password_site) {


        WebView myWebView = findViewById(R.id.WEB);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
        final String js = "javascript: document.getElementById('txtUsername').value='" + username_site + "';" + "document.getElementById('txtPassword').value='" + password_site + "';" + "document.getElementById('btnOK').click()";
        myWebView.loadUrl(url);

        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {
                if (url.contains("fontenuopre")) {
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            Log.e("html", s);
                        }
                    });
                }
                if (url.contains("login")) {

                    connessioneok = false;
                    scrivi_str("connesso", "NO");

                }
                if (url.contains("PWM_ChildrenList.aspx")) {
                    connessioneok = true;
                    scrivi_str("connesso", "ok");

                }

            }
        });
    }


    public void premuto(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        EditText n_user = findViewById(R.id.editText);
        EditText n_password = findViewById(R.id.editText2);
        TextView txtconnesso = findViewById(R.id.textView10);
        RadioButton rad1 = findViewById(R.id.radioButton);
        RadioButton rad2 = findViewById(R.id.radioButton2);
        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        scrivi_str("username", n_user.getText().toString());
        scrivi_str("password", n_password.getText().toString());
        scrivi_str("utenza", "SI");

        if (rad1.isChecked()) {
            scrivi_str("radio1", "1");
        }

        if (rad2.isChecked()) {
            scrivi_str("radio1", "2");
        }

        connesso(n_user.getText().toString(), n_password.getText().toString());
        txtconnesso.setText(leggi_str("connesso"));


    }


    public void sito1(View view) {
        scrivi_str("sito", "https://www.icsandropertinifontenuova.edu.it");
    }


    public void sito2(View view) {

        scrivi_str("sito", "http://www.istitutopirandello.it/public/");

    }


}
