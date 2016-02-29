package info.ups.fr.puzzlegame_template;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Jean-Phi on 25/03/2015.
 */
public class JeuPuzzle extends ActionBarActivity {

    private int niveau = 1;
    private int nbPict = 2;
    private int nbcolonne;
    private int nbligne;
    List<Piece> listePiece = new ArrayList<Piece>();
    private Bitmap imageAParser;
    private ImageView image1;
    private ImageView image2;
    private boolean colle = false;
    private Drawable mExampleDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu_puzzle);
        final GlobalVariable globalVariable = (GlobalVariable) getApplicationContext();
        this.nbcolonne = globalVariable.getNbcolonne();
        this.nbligne = globalVariable.getNbligne();
        this.nbPict = globalVariable.getNbPiece();


        addListenerPicture();
        Log.d("Niveau :"+niveau,"NbPiece :"+nbPict);
        initialiserPiece();

    }

    public void initialiserPiece(){
        DisplayMetrics metrics = new DisplayMetrics();
        //on parse l'image du niveau
        this.imageAParser = BitmapFactory.decodeResource(getResources(), R.drawable.terre);
        List<Bitmap> listeImage = parserImage(imageAParser);
        //on l'applique a chaque piece



        //int X = (int) metrics.xdpi;
        //int Y = (int) metrics.ydpi;
        int X = getWindowManager().getDefaultDisplay().getWidth();
        int Y = getWindowManager().getDefaultDisplay().getHeight();
        Log.d("Ecran largeur :"+X,"Ecran longueur :"+Y+"\n");
        Random r = new Random();
        int valeur1;
        int valeur2;
        listePiece.clear();
        Log.d("TTTTTTTTTTTTTTTT","TTTTTTTTTTTTTTTTT\n");
        for(int i=0;i<nbPict;i++){

            valeur1 = 0 + r.nextInt(X - 0 + 1);
            valeur2 = 0 + r.nextInt(Y - 0 + 1);
            Piece unePiece = new Piece(i+1 , valeur1, valeur2 ,  listeImage.get(i),0,0 );
            listePiece.add(unePiece);
        }
        Log.d("AAAAAAAAAAAAAA","AAAAAAAAAAAAAAAA\n");

    }

    //fonction permettant de de couper une image en plusieurs images de meme taille
    private List<Bitmap> parserImage(Bitmap images){
        List<Bitmap> tabImages = new ArrayList<Bitmap>();
        Bitmap imageCourante;


        int width;
        int height;
        //largeur et longueur de la piece principale
        width = images.getWidth();
        height= images.getHeight();
        int largeurPiece = width/nbPict;
        int longueurPiece = height/nbPict;
        Log.d("Largeur image :"+width+", Hauteur image :"+height,"Largeur Piece :"+largeurPiece+", longueur Piece :"+longueurPiece);

        int pixellargeur = 0;
        int pixellongueur = 0;
        for( int i = 0 ; i < nbPict ;i++ ){
            imageCourante = images.createBitmap(images, pixellargeur , pixellongueur, largeurPiece, longueurPiece ) ;
            tabImages.add(imageCourante);
            pixellargeur += largeurPiece;
            pixellongueur += longueurPiece;
        }


        return tabImages;
    }


    public void addListenerPicture() {


        //permet de deplacer l'image 1 du puzzle
        this.image1 = (ImageView) findViewById(R.id.image1_1);
        this.image1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x;
                float y;
                int action = event.getAction();
                Log.d("onTouch Image1","avant le switch");
                switch(action) {
                    case MotionEvent.ACTION_MOVE:   if(colle) {
                                                        image2 = (ImageView) findViewById(R.id.image1_2);
                                                        image2.setX(event.getX() + image1.getX() + image1.getWidth() - 250);
                                                        image2.setY(event.getY() + image1.getY() - 250);
                                                    }
                                                        v.setX(event.getX() + v.getX() - 250);
                                                        v.setY(event.getY() + v.getY() - 250);
                                                        Log.d("myTag", " x :  " + event.getX() + "    y : " + event.getY());
                                                  break;
                    case MotionEvent.ACTION_UP: accrocherImage();
                                                  break;
                }
                return true;
            }
        });

        //permet de deplacer l'image 2 du puzzle
        this.image2 = (ImageView) findViewById(R.id.image1_2);
        this.image2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                float x;
                float y;
                int action = event.getAction();
                Log.d("onTouch image2", "avant le switch");
                switch(action) {
                    case MotionEvent.ACTION_MOVE:   if(colle) {
                                                        image1 = (ImageView) findViewById(R.id.image1_1);
                                                        image1.setX(event.getX() + image2.getX() - image1.getWidth() - 250);
                                                        image1.setY(event.getY() + image2.getY() - 250);
                                                    }
                                                    v.setX(event.getX() + v.getX() - 250);
                                                    v.setY(event.getY() + v.getY() - 250);
                                                    Log.d("myTag", " x :  " + event.getX() + "    y : " + event.getY());
                                                  break;
                    case MotionEvent.ACTION_UP: accrocherImage();
                                                  break;
                }
                return true;
            }
        });
    }

    public void accrocherImage() {
        float x = Math.abs(image1.getX()) - Math.abs(image2.getX()) + image1.getWidth();
        float y = Math.abs(image1.getY()) - Math.abs(image2.getY());
        Log.d("accrocherImage", "avant le if");
        if ( -20 <= x && x <= 20 && -20 <= y && y <= 20) {
            //float xfinal = image1.getX() + image2.getX();
            Bitmap image1_1 = BitmapFactory.decodeResource(getResources(), R.drawable.niveau1_1);
            Bitmap image1_2 = BitmapFactory.decodeResource(getResources(), R.drawable.niveau1_2);

            this.colle = true;
            Log.d("accrocherImage", "DANS DANS DANS le if");
        }
    }

}
