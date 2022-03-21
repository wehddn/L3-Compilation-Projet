package zoot.tds;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.DeclencheurDException;
import zoot.exceptions.DeclencheurEntreeNonDefinie;
import zoot.exceptions.DoubleDeclarationException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.5.3
 * @since 2.5.0
 * created on 19/03/2022
 */
public class NoeudTDS {
    private NoeudTDS parent = null;
    private final ArrayList<NoeudTDS> enfants;
    /**
     * Associe les noms des variables à leur type
     */
    private final HashMap<Entree, Symbole> dict;
    /**
     * Le deplacement courant dans la pile locale
     */
    private int taillePile = 0;

    private int enfantCourant = 0;


    public NoeudTDS() {
        dict = new HashMap<>();
        enfants = new ArrayList<>();
    }

    public void setParent(NoeudTDS parent)
    {
        this.parent = parent;
    }

    public void addEnfant(NoeudTDS suivant)
    {
        this.enfants.add(suivant);
    }

    public NoeudTDS getParent() {
        return parent;
    }

    public void ajouter(Entree e, Symbole s, int noLigne, int noColonne) throws DoubleDeclarationException {
        if (dict.containsKey(e)) {
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
                e.declencherException(d, e.toString());
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

        for (Entree e : dict.keySet()) {
            sb.append(dict.get(e).toString()).append(" ").append(e.toString()).append('\n');
        }

        for (NoeudTDS t : enfants) {
            sb.append(t.toString().indent(4));
        }
        return "TDS-start :\n" + sb.toString().indent(4) + "TDS-end";
    }

    public NoeudTDS enfantSuivant()
    {
        enfantCourant++;
        return enfants.get(enfantCourant - 1);
    }

    public String getEtiquette(String nom) {

        return  "__" + nom + "__" + UUID.randomUUID().toString().replace("-", "") + "__";
    }
}
