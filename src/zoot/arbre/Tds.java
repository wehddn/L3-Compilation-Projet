package zoot.arbre;

import zoot.exceptions.DoubleDeclarationExeption;
import zoot.exceptions.VariableNonDefinie;

import java.util.HashMap;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 0.1.0
 * @since 0.1.0
 * created on 08/02/2022
 */
public class Tds {
    private HashMap<String, Symbole> dict;
    private int deplacementCourant = 0;
    private static Tds instance = null;

    private Tds() {
        dict = new HashMap<>();
    }

    public static Tds getInstance() {
        if (instance == null)
            instance = new Tds();
        return instance;
    }

    public void ajouter(String nom, Symbole s) { // TODO throw
        if(dict.get(nom) != null){
            throw new DoubleDeclarationExeption(nom);
        } else {
            dict.put(nom, s);
            deplacementCourant += 4;
        }
    }

    public Symbole identifier(String nomVar) { // TODO throw
        if (dict.get(nomVar)==null){
            throw new VariableNonDefinie(nomVar);
        }
        return dict.get(nomVar);
    }

    public int getTailleZoneVariables() {
        return deplacementCourant;
    }
}
