package zoot.tds;

import zoot.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Classe représentant la table des symboles
 *
 * @author Elhadji Moussa FAYE
 * @version 2.0.0
 * @since 1.0.0
 * created on 08/02/2022
 */
public class Tds {
    /**
     * Associe les noms des variables à leur type
     */
    private final HashMap<Entree, Symbole> dict;
    /**
     * Le deplacement courant dans la pile locale
     */
    private int taillePile = 0;
    /**
     * L'instance (Singleton)
     */
    private static Tds courant = null;

    public ArrayList<Tds> sousTds;

    public Tds parent;

    private Tds(Tds tds) {

        courant = this;
        sousTds = new ArrayList<>();
        parent = tds;
        if (tds != null)
            parent.sousTds.add(courant);
        dict = new HashMap<>();
        System.out.println(parent + " " + this);
    }

    /**
     * Retourne l'unique instance de la TDS (singleton) et la crée
     * si elle n'existe pas
     * @return l'unique instance de la TDS (singleton)
     */
    public static Tds getInstance() {
        if (courant == null)
            courant = new Tds(null);
        return courant;
    }

    /**
     * Ajoute une entrée avec son type dans la TDS lance une exception s'il y' a déjà
     * le couple (entree, symbole) dans la TDS
     * @param e l'entrée
     * @param s Le Symbole de l'entrée (type)
     */
    public void ajouter(Entree e, Symbole s, int noLigne, int noColonne) throws DoubleDeclarationException {
        if(dict.get(e) != null){
            throw new DoubleDeclarationException(noLigne, noColonne, e.getNom());
        } else {
            dict.put(e, s);
        }
    }

    /**
     * Permet de récupérer le symbole associé à une entrée déclenche une exception
     * si l'entrée n'existe pas.
     * @param e l'entrée
     * @return le symbole associé à l'entrée
     */
    public Symbole identifier(Entree e, int noLigne, int noColonne) throws AnalyseSemantiqueException {
        Symbole s = dict.get(e);
        if (s==null){
            if (parent != null) {
                s = parent.identifier(e, noLigne, noColonne);
            }
            else
            {
                DeclencheurDException d = new DeclencheurEntreeNonDefinie(noLigne, noColonne);
                e.declencherException(d, e.getNom());
            }
        }
        return s;
    }

    /**
     * Retourne la taille de la zone des variables (pile locale à l'application)
     * @return la taille de la zone des variables
     */
    public int getTaillePile() {
        return taillePile;
    }

    public void augmenterTaillePile() {
        taillePile += 4;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (Entree e : dict.keySet()) {
            i++;
            sb.append(dict.get(e).toString()).append(" ").append(e.toString());
            if (i < dict.keySet().size())
                sb.append("\n");
        }
        return "TDS :\n" + sb;
    }

    /**
     * Réinitialise la TDS pour une nouvelle utilisation
     */
    public void reset() {
        taillePile = 0;
        dict.clear();
    }

    public void entreeBloc(){
        new Tds(courant);
    }

    public void sortieBloc(){
        courant = parent;
    }
}
