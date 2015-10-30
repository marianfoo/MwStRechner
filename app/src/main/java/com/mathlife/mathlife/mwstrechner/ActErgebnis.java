package com.mathlife.mathlife.mwstrechner;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

/**
 * Created by marianbauersachs on 12/10/15.
 */
public class ActErgebnis extends Activity {
    private static  final boolean DBG = ActMain.DGB;
    private static final String CNAME = "ActErgebnis.";

    @Override
    public  void onCreate(Bundle bundle){
        final String MNAME = "onCreate()";
        final String TAG = CNAME + MNAME;
        if (DBG) Log.v(TAG,"entering..." );

        super.onCreate(bundle);
        setTitle("Ergebnis");
        setContentView(R.layout.lay_ausgabe);



        final Bundle extras = getIntent().getExtras();

        if (extras != null){
            ((TextView) findViewById(R.id.txtNetto)).setText(String.format(
                    "%,6.2f", extras.getDouble(ActMain.KEY_NETTO)
            ));
            ((TextView) findViewById(R.id.txtSteuer)).setText(String.format(
                    "%,6.2f", extras.getDouble(ActMain.KEY_STEUER)
            ));
            ((TextView) findViewById(R.id.txtBrutto)).setText(String.format(
                    "%,6.2f", extras.getDouble(ActMain.KEY_BRUTTO)
            ));
        }
        if (DBG) Log.v(TAG, "...exiting");
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


}
