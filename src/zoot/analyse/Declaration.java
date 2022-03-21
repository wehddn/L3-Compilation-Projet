package zoot.analyse;

import zoot.tds.EntreeVar;
import zoot.tds.SymboleVar;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 0.1.0
 * @since 0.1.0
 * created on 21/03/2022
 */
public class Declaration {
    private int ligne;
    private int colonne;
    private String type;
    private String nom;

    public Declaration(String type, String nom, int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        this.type = type;
        this.nom = nom;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public String getType() {
        return type;
    }

    public String getNom() {
        return nom;
    }

    public SymboleVar getSymbole() {
        return new SymboleVar(type);
    }

    public EntreeVar getEntree() {
        return new EntreeVar(nom);
    }
}
