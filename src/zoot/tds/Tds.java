package zoot.tds;

import zoot.exceptions.DoubleDeclarationException;
import zoot.exceptions.VariableNonDefinieException;

import java.util.HashMap;

/**
 * Classe représentant la table des symboles
 *
 * @author Elhadji Moussa FAYE
 * @version 1.5.2
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
    private int deplacementCourant = 0;
    /**
     * L'instance (Singleton)
     */
    private static Tds instance = null;

    private Tds() {
        dict = new HashMap<>();
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
        if(dict.get(e) != null){
            throw new DoubleDeclarationException(noLigne, noColonne, e.getNom());
        } else {
            dict.put(e, s);
            deplacementCourant += 4;
        }
    }

    /**
     * Permet de récupérer le symbole associé à une entrée déclenche une exception
     * si l'entrée n'existe pas.
     * @param e l'entrée
     * @return le symbole associé à l'entrée
     */
    public Symbole identifier(Entree e, int noLigne, int noColonne) throws VariableNonDefinieException {
        if (dict.get(e)==null){
            throw new VariableNonDefinieException(noLigne, noColonne, e.getNom());
        }
        return dict.get(e);
    }

    /**
     * Retourne la taille de la zone des variables (pile locale à l'application)
     * @return la taille de la zone des variables
     */
    public int getTailleZoneVariables() {
        return deplacementCourant;
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
        deplacementCourant = 0;
        dict.clear();
    }
}
