package zoot.tds;

import zoot.exceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.8.1
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

    private int noRegion;
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

    public void ajouter(Entree e, Symbole s, int ligne, int colonne) throws DoubleDeclarationException {
        if (dict.containsKey(e)) {
            GestionnaireErreursSemantiques.getInstance().ajouterErreurSemantique(
             new DoubleDeclarationException(ligne, colonne, e.getNom()));
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
    public Symbole identifier(Entree e, int ligne, int colonne) throws AnalyseSemantiqueException {
        Symbole s = dict.get(e);
        if (s==null){
            if (parent != null) {
                s = parent.identifier(e, ligne, colonne);
            }
            else
            {
                DeclencheurDException d = new DeclencheurEntreeNonDefinie(ligne, colonne);
                e.declencherException(d, e.toString());
            }
        }
        return s;
    }

    public int getTailleZoneVar() {
        return tailleZoneVar;
    }

    public void addVariable(Type typeVar){
        int valeur = switch (typeVar) {
            case ENTIER -> 4;
            case BOOLEEN -> 4;
            default -> 0;
        };

        tailleZoneVar += valeur;
    }

    public int getTailleZonePar() {
        return tailleZonePar;
    }

    public void addParametre(Type typeParam){
        int valeur = switch (typeParam) {
            case ENTIER -> 4;
            case BOOLEEN -> 4;
            default -> 0;
        };

        tailleZonePar += valeur;
    }

    public NoeudTDS getEnfantCourant(){
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
        return "TDS-start : bloc no " + noRegion + ":\n" + sb.toString().indent(4) + "TDS-end : bloc no " + noRegion;
    }

    public void enfantSuivant(){
        noEnfantCourant++;

    }

    public void enfantPrecedent(){
        noEnfantCourant--;
    }

    public String getEtiquette(String nom) {
        return  "__" + nom + "__" + UUID.randomUUID().toString().replace("-", "") + "__";
    }

    public int getNoRegion() {
        return noRegion;
    }
}
