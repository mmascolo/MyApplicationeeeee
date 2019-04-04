package com.mminf.mensafontenuova;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {

    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences_agg.edit().putString(campo, valore).commit();
    }


    void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences.edit().putInt(campo, valore).commit();
    }

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        EditText n_user =  findViewById(R.id.editText);
        EditText n_password = findViewById(R.id.editText2);

        SharedPreferences mPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        /* WebView myWebView =  findViewById(R.id.WEB);*/
        username = leggi_str("username");
        password = leggi_str("password");
        n_user.setText(username);
        n_password.setText(password);
        Log.e("user",username);
        Log.e("pass",password);


        contatore = 3;




    }

    public void premuto(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

        TextView output = findViewById(R.id.textView3);
        output.setText("Risultato connessione : test in corso...");
        SharedPreferences mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        EditText n_user = findViewById(R.id.editText);
        EditText n_password = findViewById(R.id.editText2);


        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        scrivi_str("username", n_user.getText().toString());
        scrivi_str("password", n_password.getText().toString());
//    	mPreferences.edit().putString("username",n_user.getText().toString()).commit();
//    	mPreferences.edit().putString("password",n_password.getText().toString()).commit();
//    	mPreferences.edit().putInt("conta",3).commit();
        WebView myWebView = findViewById(R.id.WEB);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);

        String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
        final String js = "javascript: document.getElementById('txtUsername').value='" + n_user.getText().toString() + "';" + "document.getElementById('txtPassword').value='" + n_password.getText().toString() + "';" + "document.getElementById('btnOK').click()";
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
                TextView output = findViewById(R.id.textView3);
                TextView output2 = findViewById(R.id.textView5);


                int conta;
                if (url.contains("login")) {
                    output.setText("Risultato connessione : ERRORE");
                    TextView output4 = findViewById(R.id.textView);
                    conta = Integer.valueOf(output4.getText().toString());
                    Log.e("SIAMO", "  	conta = Integer.valueOf(output4.getText().toString());");
                    conta = conta - 1;
                    output4.setText(Integer.toString(conta));
                    if (conta > 0) {
                        output2.setText("ATTENZIONE ANCORA " + conta + " al blocco utenza");
                    }
                    if (conta <= 0) {
                        output2.setText("UTENZA BLOCCATA COLLEGARSI AL SITO PER SBLOCCARLA");
                    }
                }
                if (url.contains("PWM_ChildrenList.aspx")) {
                    output2.setText("");


                    output.setText("Risultato connessione : effettuata correttamente");
                    Log.e("dove","dopo connessione effettuata")
                    view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                          @Override
//
                           public void onReceiveValue(String html1) {
                             String input = html1;
                             String input2 = html1;
                              Log.e("dove","demtro onreceive");
                             TextView saldo = findViewById(R.id.textView4);
                              Log.e("dove","dopo saldo");

                              input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                              Log.e("dove","dopo sub");
                             input = "Bambino: " + input.toString();
                             TextView bambino = findViewById(R.id.textView2);
                             bambino.setText(input.toString());
                            //                           	saldo.setText(input2.toString());
                            Log.e("input", input);
                            Log.e("input2", input2);
                        }
                    });
                    //********************************************************************************
                    //*******************************************saldo****************************
                    view.evaluateJavascript(
                            "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                @Override
                                public void onReceiveValue(String html1) {
                                    Log.e("html0", html1);
                                    String input = html1;
                                    String input2 = html1;
                                    TextView saldo = findViewById(R.id.textView4);
                                    input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                    input = "Saldo: " + input.toString();
                                    TextView bambino = findViewById(R.id.textView2);
                                    saldo.setText(input.toString());
                                    Log.e("input", input);
                                    Log.e("input2", input2);
                                }
                            });


                }

            }

        });
    }
}

