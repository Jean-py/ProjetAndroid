package info.ups.fr.puzzlegame_template;

import android.graphics.Bitmap;
import android.graphics.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Benjamin on 26/03/2015.
 */
public class Piece  {
    private int id;
    private float x;
    private float y;
    private Bitmap image;
    private int largeur;
    private int longueur;
    private List<Piece> listePieceAccroche = new ArrayList<Piece>();

    public Piece(int id, float x, float y, Bitmap image, int largeur, int longueur) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.image = image;
        this.largeur = largeur;
        this.longueur = longueur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int longueur) {
        this.longueur = longueur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public List<Piece> getListePieceAccroche() {
        return listePieceAccroche;
    }
}
