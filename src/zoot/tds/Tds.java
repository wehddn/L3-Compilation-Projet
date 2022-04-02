package zoot.tds;

import zoot.exceptions.*;

/**
 * Classe représentant la table des symboles
 *
 * @author Elhadji Moussa FAYE
 * @version 2.8.0
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

    private int nbNoeuds = 0;

    private Tds() {
        noeudCourant = new NoeudTDS(nbNoeuds);
        nbNoeuds++;
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

    public void addVariable(Type typeVar){
        noeudCourant.addVariable(typeVar);
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
            NoeudTDS next = new NoeudTDS(nbNoeuds);
            nbNoeuds++;
            noeudCourant.addEnfant(next);
            next.setParent(noeudCourant);
            noeudCourant = next;
        } else {
            noeudCourant.enfantSuivant();
            noeudCourant = noeudCourant.getEnfantCourant();
        }
    }

    public void sortieBloc(){
        noeudCourant = noeudCourant.getParent();
    }

    public void endConstruction(){
        this.enConstruction = false;
    }

    public String getEtiquette(String nom) {
        return noeudCourant.getEtiquette(nom);
    }

    public int getNoRegion() {
        return noeudCourant.getNoRegion();
    }
}
