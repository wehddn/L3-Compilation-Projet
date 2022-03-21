package zoot.tds;

import zoot.exceptions.*;

/**
 * Classe représentant la table des symboles
 *
 * @author Elhadji Moussa FAYE
 * @version 2.5.0
 * @since 1.0.0
 * created on 08/02/2022
 */
public class Tds {

    /**
     * L'instance (Singleton)
     */
    private static Tds instance = null;

    private NoeudTDS noeudCourant;

    private Tds() {
        noeudCourant = new NoeudTDS();
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

    /**
     * Retourne la taille de la zone des variables (pile locale à l'application)
     * @return la taille de la zone des variables
     */
    public int getTaillePile() {
        return noeudCourant.getTaillePile();
    }

    public void augmenterTaillePile() {
        noeudCourant.augmenterTaillePile();
    }

    @Override
    public String toString() {
        return noeudCourant.toString();
    }

    /**
     * Réinitialise la TDS pour une nouvelle utilisation
     */
    public void reset() {
        noeudCourant = new NoeudTDS();
    }

    public void entreeBlocContruction(){
        NoeudTDS next = new NoeudTDS();
        noeudCourant.addEnfant(next);
        next.setParent(noeudCourant);
        noeudCourant = next;
    }

    public void sortieBlocConstruction(){
        noeudCourant = noeudCourant.getParent();
    }

    public void entreeBlocUtilisation() {
        noeudCourant = noeudCourant.enfantSuivant();
    }

    public void sortieBlocUtilisation() {
        noeudCourant = noeudCourant.getParent();
    }

    public String getEtiquette(String nom) {
        return noeudCourant.getEtiquette(nom);
    }
}
