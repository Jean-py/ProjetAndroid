package info.ups.fr.puzzlegame_template;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * TODO: document your custom view class.
 */
public class TwoPiecesPuzzle extends View {
    private Drawable mExampleDrawable;
    private int nbPict = 4;
    private int nbcolonne=2;
    private int nbligne=2;
    List<Piece> listePiece = new ArrayList<Piece>();
    List<Bitmap> listeImage2 = new ArrayList<Bitmap>();
    private Bitmap imageAParser;
    private int largeur_image;
    private int longueur_image;
    private int x_tmp,y_tmp;
    private ImageView image1;
    private ImageView image2;
    private boolean colle = false;
    Bitmap myPict1 = BitmapFactory.decodeResource(getResources(), R.drawable.niveau1_1);
    Bitmap myPict2 = BitmapFactory.decodeResource(getResources(), R.drawable.niveau1_1);


    public TwoPiecesPuzzle(Context context) {
        super(context);
    }

    public TwoPiecesPuzzle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TwoPiecesPuzzle(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initialiserPiece();
        int left = 0;
        int top = 0;
        //canvas.drawBitmap(myPict1, getLeft(), getTop(), null);
        //canvas.drawBitmap(myPict2, getLeft() + getWidth()/3, getTop() + myPict2.getHeight() + 20, null);
        /*for(Bitmap b: listeImage2) {
            canvas.drawBitmap(b, getLeft()+left, getTop()+top, null);
            left += 100;
            top += 200;
        }*/
        for(Piece p:this.listePiece){
            canvas.drawBitmap(p.getImage(),p.getX(),p.getY(),null);
        }

        // Draw the example drawable.
        if (mExampleDrawable != null) {
            mExampleDrawable.draw(canvas);
        }
    }

    /**
     * Gets the example drawable attribute value.
     *
     * @return The example drawable attribute value.
     */
    public Drawable getExampleDrawable() {
        return mExampleDrawable;
    }

    /**
     * Sets the view's example drawable attribute value.
     *
     * @param exampleDrawable The example drawable attribute value to use.
     */
    public void setExampleDrawable(Drawable exampleDrawable) {
        mExampleDrawable = exampleDrawable;
    }

    public void initialiserPiece(){
        //on parse l'image du niveau
        this.imageAParser = BitmapFactory.decodeResource(getResources(), R.drawable.terre3);
        List<Bitmap> listeImage = parserImage(imageAParser);
        this.listeImage2 = listeImage;
        //on l'applique a chaque piece



        //int X = this.getWidth();
        //int Y = this.getHeight();
        //int X = getWindowManager().getDefaultDisplay().getWidth();
        //int Y = getWindowManager().getDefaultDisplay().getHeight();
        Log.d("Ecran largeur :"+X,"Ecran longueur :"+Y+"\n");
        //Random r = new Random();
        int x = 0;
        int y = 0;
        listePiece.clear();
        Piece unePiece;
        Log.d("TTTTTTTTTTTTTTTT","TTTTTTTTTTTTTTTTT\n");
        for(int i=0;i<nbPict;i++){

            //x = 0 + r.nextInt(X - 0 + 1);
            //y = 0 + r.nextInt(Y - 0 + 1);
            unePiece = new Piece(i+1 , x, y, listeImage.get(i),largeur_image, longueur_image );
            x+=100;
            y+=100;
            listePiece.add(unePiece);
        }
        Log.d("AAAAAAAAAAAAAA","AAAAAAAAAAAAAAAA\n");

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch(event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        x_tmp = (int)event.getX();
                        y_tmp = (int)event.getY();
                        break;

                    case MotionEvent.ACTION_MOVE:
                        if(nbcolonne == 2 && nbligne == 1) {
                            Piece piece = pieceTouched(event.getX(),event.getY());
                            movePiece(v,event,piece);
                            x_tmp = (int)event.getX();
                            y_tmp = (int)event.getY();
                            v.invalidate();
                        }
                        break;

                    case MotionEvent.ACTION_UP:
                        if(nbcolonne == 2 && nbligne == 1) {
                            Piece piece = pieceTouched(event.getX(),event.getY());
                            accrocherImageNiveau1(piece);
                            v.invalidate();
                        }
                        break;
                }
                return true;
            }
        });
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
        this.largeur_image = width / this.nbcolonne;
        this.longueur_image = height / this.nbligne;
        Log.d("Largeur image :" + width + ", Hauteur image :" + height, "Largeur Piece :" + largeur_image + ", longueur Piece :" + longueur_image);


        for( int i = 0 ; i < this.nbligne ;i++ ){
            for(int j = 0 ; j < this.nbcolonne ;j++ ) {
                imageCourante = images.createBitmap(images, j*largeur_image, i*longueur_image, largeur_image, longueur_image);
                tabImages.add(imageCourante);
            }
        }



        return tabImages;
    }

    public void accrocherImageNiveau1(Piece piece) {
        float x;
        float y;
        Piece piece2 = null;
        for(Piece p: listePiece) {
            if(p.getId() != 1) {
                piece2 = p;
            }
        }
        if(piece.getId() == 1) {
            x = Math.abs(piece.getX()) - Math.abs(piece2.getX()) + piece.getLargeur();
            y = Math.abs(piece.getY()) - Math.abs(piece2.getY());
        } else {
            x = Math.abs(piece2.getX()) - Math.abs(piece.getX()) + piece2.getLargeur();
            y = Math.abs(piece2.getY()) - Math.abs(piece.getY());
        }

        if ( -20 <= x && x <= 20 && -20 <= y && y <= 20) {
            piece.getListePieceAccroche().add(piece2);
        }

    }

    public Piece pieceTouched(float x, float y) {
        for(Piece p: listePiece) {
            if( p.getX() <= x && x <= p.getX()+p.getLargeur() && p.getY() <= y && y <= p.getY()+p.getLongueur() ) {
                return p;
            }
        }
        return null;
    }

    public void movePiece(View v,MotionEvent event,Piece piece) {
        for(Piece p: piece.getListePieceAccroche()) {
            p.setX((int) event.getX() + p.getX() - x_tmp);
            p.setY((int) event.getY() + p.getY() - y_tmp);
        }
    }

}
