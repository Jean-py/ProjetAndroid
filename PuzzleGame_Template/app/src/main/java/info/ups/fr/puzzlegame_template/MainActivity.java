package info.ups.fr.puzzlegame_template;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import static android.nfc.NdefRecord.createUri;


public class MainActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MediaPlayer media = MediaPlayer.create(this,R.raw.zik);

        setContentView(R.layout.activity_menu_jeu);

        if(!media.isPlaying()){
            media.start();
            media.setLooping(true);
        }
        addListenerOnButton();


       Log.d("my mess", "" + media.isPlaying());






    }

    public void addListenerOnButton(){



        //listener du menu:
        Button boutton_jouer ;
        boutton_jouer = (Button) findViewById(R.id.boutton_jouer);
        Button boutton_niveaux ;
        boutton_niveaux = (Button) findViewById(R.id.boutton_niveaux);
        Button boutton_option ;
        boutton_option = (Button) findViewById(R.id.boutton_option);

        boutton_jouer.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                //setContentView(R.layout.activity_jeu_puzzle);
                Intent intent = new Intent(MainActivity.this, JeuPuzzle.class);
                startActivity(intent);
                finish();
                return true;
            }
        });


        boutton_niveaux.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                Intent intent = new Intent(MainActivity.this, Niveaux.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        boutton_option.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                setContentView(R.layout.activity_main);
                finish();
                return true;
            }
        });





    }



/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }*/

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
