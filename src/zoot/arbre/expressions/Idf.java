package zoot.arbre.expressions;

import zoot.tds.*;

/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 1.7.0
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Idf extends Expression {
    private final Entree entree;
    private SymboleVar symbole = null;

    public Idf(Entree e, int noLigne, int noColonne) {
        super(noLigne, noColonne);
        this.entree = e;
    }

    @Override
    public void verifier() {
        this.symbole=(SymboleVar) Tds.getInstance().identifier(entree, noLigne, noColonne);
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

    public SymboleVar getSymbole() {
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
