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
 * @version 2.6.2
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

    int noRegion = 0;
    private int tailleZoneVar = 0;
    private int tailleZonePar = 0;
    private int noEnfantCourant = 0;

    public NoeudTDS(int noRegion) {
        dict = new HashMap<>();
        enfants = new ArrayList<>();
        this.noRegion = noRegion;
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

    public int getTailleZoneVar() {
        return tailleZoneVar;
    }

    public void addVar(Type typeVar){
        int valeur = 0;
        switch (typeVar){
            case ENTIER: valeur = 4; break;
            case BOOLEEN: valeur = 4; break;
        }

        tailleZoneVar += valeur;
    }

    public int getTailleZonePar() {
        return tailleZonePar;
    }

    public void addParametre(Type typeParam){
        int valeur = 0;
        switch (typeParam){
            case ENTIER: valeur = 4; break;
            case BOOLEEN: valeur = 4; break;
        }

        tailleZonePar += valeur;
    }

    public NoeudTDS getEnfantCourant(){
        noEnfantCourant++;
        return enfants.get(noEnfantCourant - 1);
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

    public void enfantSuivant(){
        /*noEnfantCourant++;
        return enfants.get(noEnfantCourant - 1);*/

    }

    public void enfantPrecedent(){

    }

    public String getEtiquette(String nom) {
        return  "__" + nom + "__" + UUID.randomUUID().toString().replace("-", "") + "__";
    }
}
