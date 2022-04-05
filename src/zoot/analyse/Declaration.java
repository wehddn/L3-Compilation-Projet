package zoot.analyse;

import zoot.tds.EntreeVar;
import zoot.tds.SymboleVar;

/**
 * Classe qui représente la déclaration d'une variable
 *
 * @author Elhadji Moussa FAYE
 * @version 3.0.1
 * @since 2.5.1
 * created on 21/03/2022
 */
public class Declaration {
    private final int ligne;
    private final int colonne;
    private final String type;
    private final String nom;

    /**
     *
     * @param type le type de la variable
     * @param nom le nom de la variable
     * @param ligne la ligne de déclaration de la variable
     * @param colonne la colonne de déclaration de la variable
     */
    public Declaration(String type, String nom, int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.type = type;
        this.nom = nom;
    }

    /**
     * Donne la ligne à laquelle la variable a été déclarée
     * @return la ligne à laquelle la variable a été déclarée
     */
    public int getLigne() {
        return ligne;
    }

    /**
     * Donne la colonne à laquelle la variable a été déclarée
     * @return la colonne à laquelle la variable a été déclarée
     */
    public int getColonne() {
        return colonne;
    }

    /**
     * Donne le type de la variable dans un string
     * @return le type de la variable dans un string
     */
    public String getType() {
        return type;
    }

    /**
     * Donne le nom de la variable
     * @return le nom de la variable
     */
    public String getNom() {
        return nom;
    }

    /**
     * Retourne le symbole de la variable
     * @return le symbole de la variable
     */
    public SymboleVar getSymbole() {
        return new SymboleVar(type);
    }

    /**
     * Retourne l'entrée de la variable
     * @return l'entrée de la variable
     */
    public EntreeVar getEntree() {
        return new EntreeVar(nom);
    }
}
