package zoot.tds;

import zoot.exceptions.*;

/**
 * Classe représentant la table des symboles
 *
 * @author Elhadji Moussa FAYE
 * @version 2.6.2
 * @since 1.0.0
 * created on 08/02/2022
 */
public class Tds {

    /**
     * L'instance (Singleton)
     */
    private static Tds instance = null;

    private NoeudTDS noeudCourant;

    private boolean enConstruction = true;

    private int nbNoeuds;

    private Tds() {
        noeudCourant = new NoeudTDS(0);
    }

    /**
     * Retourne l'unique instance de la TDS (singleton) et la crée
     * si elle n'existe pas
     * @return l'unique instance de la TDS (singleton)
     */
    public static Tds getInstance() {
        if (instance == null)
            instance = new Tds();
        return instance;
    }

    /**
     * Ajoute une entrée avec son type dans la TDS lance une exception s'il y' a déjà
     * le couple (entree, symbole) dans la TDS
     * @param e l'entrée
     * @param s Le Symbole de l'entrée (type)
     */
    public void ajouter(Entree e, Symbole s, int noLigne, int noColonne) throws DoubleDeclarationException {
        noeudCourant.ajouter(e, s, noLigne, noColonne);
    }

    /**
     * Permet de récupérer le symbole associé à une entrée déclenche une exception
     * si l'entrée n'existe pas.
     * @param e l'entrée
     * @return le symbole associé à l'entrée
     */
    public Symbole identifier(Entree e, int noLigne, int noColonne) throws AnalyseSemantiqueException {
        return noeudCourant.identifier(e, noLigne, noColonne);
    }

    public int getTailleZoneVar() {
        return noeudCourant.getTailleZoneVar();
    }

    public void addVar(Type typeVar){
        noeudCourant.addVar(typeVar);
    }

    public int getTailleZonePar(){
        return noeudCourant.getTailleZonePar();
    }

    public void addParametre(Type typeParam){
        noeudCourant.addParametre(typeParam);
    }

    @Override
    public String toString() {
        return noeudCourant.toString();
    }

    /**
     * Réinitialise la TDS pour une nouvelle utilisation
     */
    public void reset() {
        noeudCourant = new NoeudTDS(0);
    }

    public void entreeBloc(){
        if (enConstruction){
            NoeudTDS next = new NoeudTDS(noeudCourant.noRegion + 1);
            noeudCourant.addEnfant(next);
            next.setParent(noeudCourant);
            noeudCourant = next;
        }
    }

    public void sortieBloc(){
        if (enConstruction)
            noeudCourant = noeudCourant.getParent();
    }

    public void endConstruction(){
        this.enConstruction = true;
    }

    public String getEtiquette(String nom) {
        return noeudCourant.getEtiquette(nom);
    }
}
