package com.mminf.myapplicationeeeee;

import android.icu.util.Calendar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static com.mminf.myapplicationeeeee.R.id.dolce;
import static com.mminf.myapplicationeeeee.R.id.expanded_menu;
import static java.time.LocalDate.now;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView myWebView =  findViewById(R.id.SCUOLA);
        myWebView.setInitialScale(80);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        myWebView.getSettings().setDomStorageEnabled(true);


        myWebView.loadUrl("https://www.icsandropertinifontenuova.edu.it");
        myWebView.setWebViewClient(new WebViewClient() {
            @SuppressWarnings("deprecation")
            @Override
            public boolean shouldOverrideUrlLoading(WebView viewx, String urlx) {
                viewx.loadUrl(urlx);
                return false;
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        Date d = new Date();
        String date = new SimpleDateFormat("dd", Locale.getDefault()).format(new Date());


mViewPager.setCurrentItem(Integer.parseInt(date.toString())-1);




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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
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

            String[] menu_cibo = {";;;;;;","01/04/2019;LUNEDI;Risotto con zucchine;Polpette al forno;Spinaci;Frutta di stagione","02/04/2019;MARTEDI;Gnocchetti al pomodoro;Mozzarella o formaggio;Pomodori/finocchi;Frutta di stagione","03/04/2019;MERCOLEDI;Pasta al burro;Arrosto di tacchino;Carote;Dessert","04/04/2019;GIOVEDI;Minestra di legumi;Prosciutto cotto;Fagiolini;Frutta di stagione",
                    "05/04/2019;VENERDI;Pasta al tonno con pomodoro;Filetti di platessa impanati;insalata mista;Frutta di stagione","06/04/2019;SABATO; ; ; ; ;","07/04/2019;DOMENICA; ; ; ; ;","08/04/2019;LUNEDI;Pasta all’Amatriciana;Grana / formaggio;Bieta all’agro;Frutta di stagione","09/04/2019;MARTEDI;Risotto alla parmigiana;Arista di maiale;Insalata;Frutta di stagione","10/04/2019;MERCOLEDI;Pasta  e piselli in bianco;Prosciutto cotto;Fagiolini all’agro;Frutta di stagione","11/04/2019;GIOVEDI;Pasta al pomodoro;Bastoncini merluzzo;Carote al vapore;Succo di frutta","12/04/2019;VENERDI;Pasta e patate;Nuggets di pollo;pomodori/finocchi;Frutta di stagione","13/04/2019;SABATO; ; ; ; ;","14/04/2019;DOMENICA; ; ; ; ;","15/04/2019;LUNEDI;Pasta al pesto;Bresaola;Fagiolini;Frutta di  stagione","16/04/2019;MARTEDI;Pasta al  pomodoro;Coscio pollo forno;Carote filangee;Gelato","17/04/2019;MERCOLEDI;Risotto primavera;Bocconcini di Merluzzo;bieta;Succo di frutta","18/04/2019;GIOVEDI;Gnocchetti di semola al pomodoro;Arrosto di vitellone;pomodori/finocchi;Frutta di stagione","19/04/2019;VENERDI;Pasta olio e parmigiano;Mozzarella o formaggio;insalata;Frutta di stagione","20/04/2019;SABATO; ; ; ; ;","21/04/2019;DOMENICA; ; ; ; ;","22/04/2019;LUNEDI;Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione","23/04/2019;MARTEDI;Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione","24/04/2019;MERCOLEDI;1*Tagliatelle al pomodoro;Polpette Vitellone al forno;Bieta all’agro;Frutta di stagione","25/04/2019;GIOVEDI;Pasta e zucchine;Prosciutto cotto;Insalata;Frutta di stagione","26/04/2019;VENERDI;Risotto al pomodoro;Frittata;Fagiolini all’agro;Dessert","27/04/2019;SABATO; ; ; ; ; ;",
                    "28/04/2019;DOMENICA; ; ; ; ;","29/04/2019;LUNEDI;Pasta al burro;Scaloppine tacchino;pomodori/finocchi;Frutta di stagione","30/04/2019;MARTEDI;Pasta pomodoro e basilico;Filetti di platessa mugnaia;Patate lesse/forno;Frutta di stagione"};





            TextView cibo_data = (TextView)  rootView.findViewById(R.id.data);



            TextView cibo_primo = (TextView)  rootView.findViewById(R.id.primo);
            TextView cibo_secondo = (TextView) rootView.findViewById(R.id.secondo);


            TextView cibo_contorno = (TextView)  rootView.findViewById(R.id.contorno);

            TextView cibo_dolce = (TextView)  rootView.findViewById(R.id.dolce);







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

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
            return 30;
        }
    }
}
