package com.mathlife.mathlife.mwstrechner;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class ActMain extends AppCompatActivity {

    public static final boolean DGB = true;
    private static final String CNAME = "ActMain.";

    public static final String KEY_BRUTTO = "brutto";
    public static final String KEY_NETTO = "netto";
    public static final String KEY_STEUER = "steuer";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final String MNAME = "onCreate()";
        final String TAG = CNAME + MNAME;
        if (DGB) Log.v(TAG, "entering...");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main);
        if(DGB) Log.v(TAG, "...exiting");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                berechne();
            }
        });

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
    public void onClickUmrechnen (final View cmd){
        final String MNAME = "onClickUmrechnen()";
        final String TAG = CNAME + MNAME;
        if (DGB) Log.v(TAG, "entering...");
        //if (DGB) Log.d(TAG, "Test Enter");

        berechne();
        if (DGB) Log.v(TAG, "...exiting");
    }

    //Hier findet die Berechnung der Werte und Aufruf der Ergebnis Activity statt

    void berechne(){
        double dBrutto = 0.0,dNetto = 0.0, dSteuer = 0.0, dBetrag = 0.0;

        //Betrag
        final EditText txtBetrag = (EditText) findViewById(R.id.txtBetrag);

        String s1 = txtBetrag.getText().toString();
        if(
                s1 != null && s1.length() > 0
                )
            dBetrag = Double.parseDouble(s1);

        //MwSt-Satz:
        final Spinner spiMwstSatz = (Spinner) findViewById (R.id.spiMwstSatz);
        final int[] anProzente = getResources().getIntArray((R.array.mwstWerte));
        /* Variante:
     * final int mwst = (Integer) spiMwstSatz.getItemAtPosition(
                                  spiMwstSatz.getSelectedItemPosition() ); */
        final double dMwst = ((double) anProzente[spiMwstSatz.getSelectedItemPosition()]) /100;

        //Brutto oder Netto?
        final RadioGroup rg = (RadioGroup) findViewById(R.id.rgbBruttoNetto);
        if (rg.getCheckedRadioButtonId() == R.id.radNetto){
            dNetto = dBetrag;
            dSteuer = dNetto * dMwst;
            dBrutto = dNetto + dSteuer;
        }
        else {
            //Brutto
            dBrutto = dBetrag;
            dNetto = dBrutto / (1+dMwst);
            dSteuer = dBrutto - dNetto;
        }

        final Intent intent = new Intent(this, ActErgebnis.class);

        //Daten an Activity Ergebnis uebergeben
        intent.putExtra(KEY_BRUTTO,dBrutto);
        intent.putExtra(KEY_NETTO,dNetto);
        intent.putExtra(KEY_STEUER,dSteuer);
        // Activity Ergebnis aufrufen
        startActivity(intent);
    }
}
