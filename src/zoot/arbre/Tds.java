package zoot.arbre;

import zoot.exceptions.DoubleDeclarationExeption;
import zoot.exceptions.VariableNonDefinie;

import java.util.HashMap;

/**
 * Classe représentant la table des symboles
 *
 * @author Elhadji Moussa FAYE
 * @version 1.0.2
 * @since 1.0.0
 * created on 08/02/2022
 */
public class Tds {
    /**
     * Associe les noms des variables à leur type
     */
    private HashMap<String, Symbole> dict;
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
     * @param nom le nom de l'entrée
     * @param s Le Symbole de l'entrée (type)
     */
    public void ajouter(String nom, Symbole s) { // TODO throw
        if(dict.get(nom) != null){
            throw new DoubleDeclarationExeption(nom);
        } else {
            dict.put(nom, s);
            deplacementCourant += 4;
        }
    }

    /**
     * Permet de récupérer le symbole associé à une entrée déclenche une exception
     * si l'entrée n'existe pas.
     * @param nom le nom de l'entrée
     * @return le symbole associé à l'entrée
     */
    public Symbole identifier(String nomVar) { // TODO throw
        if (dict.get(nomVar)==null){
            throw new VariableNonDefinie(nomVar);
        }
        return dict.get(nomVar);
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
        for (String nom : dict.keySet()) {
            i++;
            sb.append(dict.get(nom).toString()).append(" ").append(nom);
            if (i < dict.keySet().size())
                sb.append("\n");
        }
        return "TDS :\n" + sb.toString().indent(2);
    }
}
