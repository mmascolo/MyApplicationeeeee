package com.mminf.mensafontenuova;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;



    public void scrivi_str(String campo, String valore) {
        SharedPreferences mPreferences_agg = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences_agg.edit().putString(campo, valore).commit();
    }

    public void riempidati() {



        TextView bambino = findViewById(R.id.textView4);
        bambino.setText("Aggiornamento dati saldo in corso");
        WebView myWebView = findViewById(R.id.WEB);
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.getSettings().setDomStorageEnabled(true);
        myWebView.setOverScrollMode(WebView.OVER_SCROLL_NEVER);
        String username_sito = leggi_str("username");
        String password_sito = leggi_str("password");


        String url = "https://www.schoolesuite.it/default1/NSC_Login.aspx?installation_code=fontenuopre";
        final String js = "javascript: document.getElementById('txtUsername').value='" + username_sito + "';" + "document.getElementById('txtPassword').value='" + password_sito + "';" + "document.getElementById('btnOK').click()";
        myWebView.loadUrl(url);
        myWebView.setWebViewClient(new WebViewClient() {
            public void onPageFinished(WebView view, String url) {

                if (url.contains("fontenuopre")) {
                    view.evaluateJavascript(js, new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {
                            Log.e("html", js);
                        }
                    });
                }


                if (url.contains("login")) {
                    TextView bambino = findViewById(R.id.textView2);
                    bambino.setText("errore login");
                }
                if (url.contains("PWM_ChildrenList.aspx")) {


                    view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows.length); })();", new ValueCallback<String>() {

                                @Override
                                public void onReceiveValue(String html21) {
//                    i= Integer.parseInt(html21);

                                    scrivi_int("bambini", Integer.parseInt(html21));
                                    Log.e("Contalinee", html21);

                                }
                            }
                    );

                    int contarow = leggi_int("bambini");

                    Log.e("contatore", Integer.toString(leggi_int("bambini")));







                    if (contarow==2) {
                        //                    CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                Log.e("bambino", input);
                                TextView bambino = findViewById(R.id.textView4);
                                bambino.setText(input);

                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        TextView saldo  = findViewById(R.id.textView2);
                                        saldo.setText(input2);
                                    }
                                });


                    }

// ************************************************************************* DUE BAMBINI ********************************************************
//                    ****************************************************************************************************************


                    if (contarow==3) {

                        //                    CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino1", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                Log.e("bambino", input);
                                TextView bambino = findViewById(R.id.textView4);
                                bambino.setText(input);

                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo1", input2);

                                        TextView saldo  = findViewById(R.id.textView2);
                                        saldo.setText(input2);
                                    }
                                });






//                       CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                scrivi_str("bambinoappo2", input);
                                Log.e("bambino", input);
                                TextView bambino2 = findViewById(R.id.textView6);
                                bambino2.setText(input);
                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo2", input2);
                                        TextView saldo2 = findViewById(R.id.textView7);
                                        saldo2.setText( input2);
                                    }
                                });


                    }
// *********************************************************************************************************************
//                    *****************************************************************************************************


                    // ************************************************************************* tre BAMBINI ********************************************************
//                    ****************************************************************************************************************


                    if (contarow==4) {

                        //                    CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino1", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                Log.e("bambino", input);
                                TextView bambino = findViewById(R.id.textView4);
                                bambino.setText(input);

                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo1", input2);

                                        TextView saldo  = findViewById(R.id.textView2);
                                        saldo.setText(input2);
                                    }
                                });






//                       CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                scrivi_str("bambinoappo2", input);
                                Log.e("bambino", input);
                                TextView bambino2 = findViewById(R.id.textView6);
                                bambino2.setText(input);
                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo2", input2);
                                        TextView saldo2 = findViewById(R.id.textView7);
                                        saldo2.setText( input2);
                                    }
                                });

                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                scrivi_str("bambinoappo2", input);
                                Log.e("bambino", input);
                                TextView bambino2 = findViewById(R.id.textView8);
                                bambino2.setText(input);
                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo2", input2);
                                        TextView saldo2 = findViewById(R.id.textView9);
                                        saldo2.setText( input2);
                                    }
                                });
                    }
// *********************************************************************************************************************
//                    *****************************************************************************************************

                    // ************************************************************************* quattro BAMBINI ********************************************************
//                    ****************************************************************************************************************


                    if (contarow==5) {

                        //                    CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino1", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                Log.e("bambino", input);
                                TextView bambino = findViewById(R.id.textView4);
                                bambino.setText(input);

                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[1].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo1", input2);

                                        TextView saldo  = findViewById(R.id.textView2);
                                        saldo.setText(input2);
                                    }
                                });






//                       CARICA NOME BAMBINO
                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                scrivi_str("bambinoappo2", input);
                                Log.e("bambino", input);
                                TextView bambino2 = findViewById(R.id.textView6);
                                bambino2.setText(input);
                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[2].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo2", input2);
                                        TextView saldo2 = findViewById(R.id.textView7);
                                        saldo2.setText( input2);
                                    }
                                });

                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                scrivi_str("bambinoappo2", input);
                                Log.e("bambino", input);
                                TextView bambino2 = findViewById(R.id.textView8);
                                bambino2.setText(input);
                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[3].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo2", input2);
                                        TextView saldo2 = findViewById(R.id.textView9);
                                        saldo2.setText( input2);
                                    }
                                });



                        view.evaluateJavascript("(function() { return (document.getElementById('tblChildrenList').rows[4].cells.item(0).innerHTML); })();", new ValueCallback<String>() {
                            @Override


                            public void onReceiveValue(String html1) {
                                String input = html1;
                                Log.e("bambino", html1);
                                input = input.substring(html1.indexOf(">") + 1, html1.lastIndexOf("\\"));
                                input = "Bambino: " + input;
                                scrivi_str("bambinoappo2", input);
                                Log.e("bambino", input);
                                TextView bambino2 = findViewById(R.id.textView11);
                                bambino2.setText(input);
                            }
                        });

//                        CARICA SALDO
                        view.evaluateJavascript(
                                "(function() { return (document.getElementById('tblChildrenList').rows[4].cells.item(1).innerHTML); })();", new ValueCallback<String>() {
                                    @Override
                                    public void onReceiveValue(String html2) {
                                        String input2 = html2;

                                        input2 = input2.substring(html2.indexOf(">") + 1, html2.lastIndexOf("\\"));
                                        input2 = "Saldo: " + input2;
                                        scrivi_str("saldoappo2", input2);
                                        TextView saldo2 = findViewById(R.id.textView12);
                                        saldo2.setText( input2);
                                    }
                                });





                    }
// *********************************************************************************************************************
//                    *****************************************************************************************************





















                }}



//
//            }

        });


    }

    public void scrivi_int(String campo, int valore) {
        SharedPreferences mPreferences3 = PreferenceManager.getDefaultSharedPreferences(this);
        mPreferences3.edit().putInt(campo, valore).commit();
    }

    public String leggi_str(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(this);
        String valore = "";
        return mPreferences_leg.getString(campo, valore);
    }

    public String leggi_sito(String campo, String valore) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(this);
        return mPreferences_leg.getString(campo, valore);
    }


    private SectionsPagerAdapter mSectionsPagerAdapter;

    public int leggi_int(String campo) {
        SharedPreferences mPreferences_leg = PreferenceManager.getDefaultSharedPreferences(this);
        int valore = 0;
        return mPreferences_leg.getInt(campo, valore);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        WebView myWebView =  findViewById(R.id.SCUOLA);
        myWebView.setInitialScale(80);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        myWebView.getSettings().setDomStorageEnabled(true);




            TextView bambino = findViewById(R.id.textView2);
            TextView bambino2 = findViewById(R.id.textView4);
            bambino2.setText("Per visualizzare il saldo attuale");
            bambino.setText("Impostare le credenziali nelle impostazioni");

        if (leggi_str("connesso").equals("ok")) {
            bambino.setText("");
            riempidati();
        }

        myWebView.loadUrl(leggi_sito("sito", "https://www.icsandropertinifontenuova.edu.it"));
        myWebView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        Date d = new Date();
        String date = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());
        String mese = new SimpleDateFormat("MM", Locale.getDefault()).format(new Date());

int posizione = Integer.parseInt(date);


        Log.e("mese", mese);
if (mese.equals("05")) {posizione= posizione+30;}

if (mese.equals("06")) {posizione= posizione+61;}

        mViewPager.setCurrentItem(posizione - 1);




    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent intent1 = new Intent(this,  Main2Activity.class);
            startActivity(intent1);


            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {




            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            String[] menu_cibo = {
                    ";;;;;;","01/04/2019;LUNEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","02/04/2019;MARTEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","03/04/2019;MERCOLEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert","04/04/2019;GIOVEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione","05/04/2019;VENERDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","06/04/2019;SABATO; ; ; ; ;","07/04/2019;DOMENICA; ; ; ; ;","08/04/2019;LUNEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","09/04/2019;MARTEDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","10/04/2019;MERCOLEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","11/04/2019;GIOVEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","12/04/2019;VENERDI;Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","13/04/2019;SABATO; ; ; ; ;","14/04/2019;DOMENICA; ; ; ; ;","15/04/2019;LUNEDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione","16/04/2019;MARTEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato","17/04/2019;MERCOLEDI;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta","18/04/2019;GIOVEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione","19/04/2019;VENERDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione","20/04/2019;SABATO; ; ; ; ;","21/04/2019;DOMENICA; ; ; ; ;","22/04/2019;LUNEDI; ; ; ; ;","23/04/2019;MARTEDI; ; ; ; ;","24/04/2019;MERCOLEDI; ; ; ; ;","25/04/2019;GIOVEDI; ; ; ; ;","26/04/2019;VENERDI; ; ; ; ;","27/04/2019;SABATO; ; ; ; ;","28/04/2019;DOMENICA; ; ; ; ;","29/04/2019;LUNEDI;Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione","30/04/2019;MARTEDI;Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione","01/05/2019;MERCOLEDI;1*Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione","02/05/2019;GIOVEDI;Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione","03/05/2019;VENERDI;Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert","04/05/2019;SABATO; ; ; ; ; ;","05/05/2019;DOMENICA; ; ; ; ;","06/05/2019;LUNEDI;Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione","07/05/2019;MARTEDI;Fettuccine al ragù;Mozzarella/Formaggio;Pomodori/finocchi;Frutta di stagione","08/05/2019;MERCOLEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Gelato","09/05/2019;GIOVEDI; Risotto con piselli;Fesa di tacchino;Carote;Frutta di stagione","10/05/2019;VENERDI; Pasta al pomodoro;Polpette Vitellone;Piselli al vapore;Frutta di stagione","11/05/2019;SABATO; ; ; ; ;","12/05/2019;DOMENICA; ; ; ; ;","13/05/2019;LUNEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","14/05/2019;MARTEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert","15/05/2019;MERCOLEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione","16/05/2019;GIOVEDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","17/05/2019;VENERDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","18/05/2019;SABATO; ; ; ; ;","19/05/2019;DOMENICA; ; ; ; ;","20/05/2019;LUNEDI; Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","21/05/2019;MARTEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","22/05/2019;MERCOLEDI; Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","23/05/2019;GIOVEDI; Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","24/05/2019;VENERDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","25/05/2019;SABATO; ; ; ; ;","26/05/2019;DOMENICA; ; ; ; ;","27/05/2019;LUNEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato","28/05/2019;MARTEDI;;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta","29/05/2019;MERCOLEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione","30/05/2019;GIOVEDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione","31/05/2019;VENERDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione","01/06/2019;SABATO; ; ; ; ;","02/06/2019;DOMENICA; ; ; ; ;","03/06/2019;LUNEDI; Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione","04/06/2019;MARTEDI;Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione","05/06/2019;MERCOLEDI; Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione","06/06/2019;GIOVEDI; Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert","07/06/2019;VENERDI; Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione","08/06/2019;SABATO; ; ; ; ;","09/06/2019;DOMENICA; ; ; ; ;","10/06/2019;LUNEDI;Fettuccine al ragù;Mozzarella/Formaggio;Pomodori/finocchi;Frutta di stagione","11/06/2019;MARTEDI; Minestra di legumi;Prosciutto cotto;Fagiolini;Gelato","12/06/2019;MERCOLEDI; Risotto con piselli;Fesa di tacchino;Carote;Frutta di stagione","13/06/2019;GIOVEDI; Pasta al pomodoro;Polpette Vitellone;Piselli al vapore;Frutta di stagione","14/06/2019;VENERDI;Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione","15/06/2019;SABATO; ; ; ; ;","16/06/2019;DOMENICA; ; ; ; ;","17/06/2019;LUNEDI; Pasta al tonno;Filettoni di merluzzo;Spinaci;Frutta di stagione","18/06/2019;MARTEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione","19/06/2019;MERCOLEDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","20/06/2019;GIOVEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","21/06/2019;VENERDI; Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","22/06/2019;SABATO; ; ; ; ;","23/06/2019;DOMENICA; ; ; ; ;","24/06/2019;LUNEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","25/06/2019;MARTEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","26/06/2019;MERCOLEDI; Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","27/06/2019;GIOVEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","28/06/2019;VENERDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","29/06/2019;SABATO; ; ; ; ;","30/06/2019;DOMENICA; ; ; ; ;",


            };
            TextView cibo_data = rootView.findViewById(R.id.data);


            TextView cibo_primo = rootView.findViewById(R.id.primo);
            TextView cibo_secondo = rootView.findViewById(R.id.secondo);


            TextView cibo_contorno = rootView.findViewById(R.id.contorno);

            TextView cibo_dolce = rootView.findViewById(R.id.dolce);







            int a = ((getArguments().getInt(ARG_SECTION_NUMBER)));


        String[] giorno = menu_cibo[a].split(";");




            cibo_data.setText(giorno[1]+' '+giorno[0]); // DATA
//            cibo_giorno.setText(giorno[1]); //GIORNO
            cibo_primo.setText("Primo: "+giorno[2]); //Primo
            cibo_secondo.setText("Secondo: "+giorno[3]); //secondo
            cibo_contorno.setText("Contorno: "+giorno[4]); //contorno
            cibo_dolce.setText("Frutta: "+giorno[5]); //dolce



            return rootView;
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 91;
        }
    }
}
