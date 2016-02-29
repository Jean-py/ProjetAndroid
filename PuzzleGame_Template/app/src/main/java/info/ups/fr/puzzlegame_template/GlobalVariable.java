package info.ups.fr.puzzlegame_template;

import android.app.Application;

/**
 * Created by Benjamin on 26/03/2015.
 */
public class GlobalVariable extends Application {

    private int nbPiece;
    private int nbcolonne;
    private int nbligne;

    public int getNbcolonne() {
        return nbcolonne;
    }

    public void setNbcolonne(int nbcolonne) {
        this.nbcolonne = nbcolonne;
    }

    public int getNbPiece() {
        return nbPiece;
    }

    public void setNbPiece(int nbPiece) {
        this.nbPiece = nbPiece;
    }


    public int getNbligne() {
        return nbligne;
    }

    public void setNbligne(int nbligne) {
        this.nbligne = nbligne;
    }
}
