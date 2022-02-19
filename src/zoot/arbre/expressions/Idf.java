package zoot.arbre.expressions;

import zoot.tds.Entree;
import zoot.tds.Symbole;
import zoot.tds.Tds;
import zoot.tds.Type;

/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 1.4.1
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Idf extends Expression {
    private Entree entree;
    private Symbole symbole = null;

    public Idf(Entree e, int noLigne, int noColonne) {
        super(noLigne, noColonne);
        this.entree = e;
    }

    @Override
    public void verifier() {
        this.symbole=Tds.getInstance().identifier(entree, noLigne, noColonne);
    }

    @Override
    public String toMIPS() {
        return "lw $v0, " + (-symbole.getDeplacement()) + "($s7)";
    }

    public Type getType() {
        if (symbole == null)
            return Type.NONDEFINI;
        else
            return symbole.getType();
    }

    @Override
    public String getValeur() {
        return entree.getNom();
    }

    public Symbole getSymbole() {
        return this.symbole;
    }

    public String getNom(){
        return this.entree.getNom();
    }

    @Override
    public String toString() {
        return entree.getNom();
    }
}
