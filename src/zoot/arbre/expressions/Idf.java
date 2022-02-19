package zoot.arbre.expressions;

import zoot.tds.Symbole;
import zoot.tds.Tds;
import zoot.tds.Type;

/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 1.3.1
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
        return "lw $v0, " + (-symbole.getDeplacement()) + "($s7)";
    }

    public Type getType() {
        if (symbole == null)
            return null;
        else
            return symbole.getType();
    }

    @Override
    public String getValeur() {
        return nom;
    }

    public Symbole getSymbole() {
        return this.symbole;
    }

    public String getNom(){
        return this.nom;
    }

    @Override
    public String toString() {
        return nom;
    }
}
