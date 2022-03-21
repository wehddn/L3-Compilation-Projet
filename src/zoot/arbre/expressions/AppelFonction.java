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
 * @version 2.5.2
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
        ArrayList<Type> types = new ArrayList<>(parametres.size());
        for (Expression e : parametres) {
            e.verifier();
            types.add(e.getType());
        }

        entree.setTypeParametres(types.toArray(new Type[0]));
        this.symbole= (SymboleFct) Tds.getInstance().identifier(entree, noLigne, noColonne);
    }

    @Override
    public String toMIPS() {
        return "jal " + symbole.getEtiquette();
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < parametres.size(); i++) {
            sb.append(parametres.get(i));
            if (i < (parametres.size()-1))
                sb.append(", ");
        }
        return entree.getNom()+ "( " + sb + " )";
    }
}
