package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.*;

/**
 * Classe représentant un appel de fonction
 *
 * @author Nicolas GRAFF
 * @version 2.5.0
 * @since 1.7.0
 * created on 08/03/2022
 */

public class AppelFonction extends Expression{

    private final EntreeFct entree;
    private SymboleFct symbole = null;

    public AppelFonction(EntreeFct i, int n, int m) {
        super(n, m);
        this.entree = i;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        this.symbole= (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
    }

    @Override
    public String toMIPS() {
        return "jal " + entree;
    }

    @Override
    public Type getType() {
        if (symbole == null)
            return Type.NONDEFINI;
        else
            return symbole.getType();
    }

    @Override
    public String getValeur() {
        return "0";
    }

    @Override
    public String toString() {
        return entree.getNom() + "()";
    }
}
