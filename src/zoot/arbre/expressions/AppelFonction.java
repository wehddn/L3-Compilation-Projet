package zoot.arbre.expressions;

import zoot.exceptions.AnalyseSemantiqueException;
import zoot.exceptions.TypeNonConcordantException;
import zoot.tds.*;

import java.util.ArrayList;
import java.util.Collections;

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
    private ArrayList<Expression> parametres;
    private SymboleFct symbole = null;

    public AppelFonction(EntreeFct i, int n, int m, Expression... parametres) {
        super(n, m);
        this.entree = i;
        this.parametres = new ArrayList<>();
        Collections.addAll(this.parametres, parametres);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
        this.symbole= (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
        ArrayList<Type> typeParams = entree.getTypeParametres();
        if (parametres.size() != typeParams.size())
            throw new TypeNonConcordantException(noLigne, noColonne, "La signature ne correspond pas");
        for (int i = 0; i < typeParams.size(); i++) {
            if (typeParams.get(i).equals(parametres.get(i).getType()))
                throw new TypeNonConcordantException(noLigne, noColonne, "La signature ne correspond pas");
        }

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
        return "appel";
    }

    @Override
    public String toString() {
        return entree.getNom() + "()";
    }
}
