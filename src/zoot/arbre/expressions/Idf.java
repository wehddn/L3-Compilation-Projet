package zoot.arbre.expressions;

import zoot.tds.Symbole;
import zoot.tds.Tds;
/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 1.1.0
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Idf extends Expression {
    private String nom;
    private Symbole symbole = null;

    public Idf(String nom, int noLigne, int noColonne) {
        super(noLigne, noColonne);
        this.nom = nom;
    }

    @Override
    public void verifier() {
        this.symbole=Tds.getInstance().identifier(nom, noLigne, noColonne);
    }

    @Override
    public String toMIPS() {
        return null;
    }

    public String getType() {
        if (symbole == null)
            return null;
        else
            return symbole.getType();
    }

    @Override
    public String toString() {
        return nom;
    }
}
