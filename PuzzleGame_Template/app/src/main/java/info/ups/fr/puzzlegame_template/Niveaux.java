package info.ups.fr.puzzlegame_template;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

/**
 * Created by Benjamin on 26/03/2015.
 */
public class Niveaux extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.niveaux);
        addListenerOnButton();
    }

    public void addListenerOnButton(){



        //listener du menu:
        Button boutton_niveau1 ;
        boutton_niveau1 = (Button) findViewById(R.id.button);
        Button boutton_niveau2 ;
        boutton_niveau2 = (Button) findViewById(R.id.button2);
        Button boutton_niveau3 ;
        boutton_niveau3 = (Button) findViewById(R.id.button3);
        Button boutton_niveau4 ;
        boutton_niveau4 = (Button) findViewById(R.id.button4);

        boutton_niveau1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
                globalVariable.setNbPiece(2);
                globalVariable.setNbcolonne(2);
                globalVariable.setNbligne(1);
                Intent intent = new Intent(Niveaux.this, JeuPuzzle.class);
                startActivity(intent);
                finish();
                return true;
            }
        });


        boutton_niveau2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
                globalVariable.setNbPiece(4);
                globalVariable.setNbcolonne(2);
                globalVariable.setNbligne(2);
                Intent intent = new Intent(Niveaux.this, JeuPuzzle.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        boutton_niveau3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
                globalVariable.setNbPiece(9);
                globalVariable.setNbcolonne(3);
                globalVariable.setNbligne(3);
                Intent intent = new Intent(Niveaux.this, JeuPuzzle.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

        boutton_niveau4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                final GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
                globalVariable.setNbPiece(16);
                globalVariable.setNbcolonne(4);
                globalVariable.setNbligne(4);
                Intent intent = new Intent(Niveaux.this, JeuPuzzle.class);
                startActivity(intent);
                finish();
                return true;
            }
        });

    }


}
