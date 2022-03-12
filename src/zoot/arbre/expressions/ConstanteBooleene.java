package zoot.arbre.expressions;

import zoot.tds.Type;

/**
 * Représente une constante booléene
 *
 * @author Elhadji Moussa FAYE
 * @version 1.7.1
 * @since 1.0.0
 * created on 09/02/2022
 */
public class ConstanteBooleene extends Constante{

    public ConstanteBooleene(String valeur, int n, int m) {
        super(valeur, n, m);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        String valeur = "0";
        if (cste.equals("vrai"))
            valeur = "1";
        return "li $v0, " + valeur;
    }

    @Override
    public Type getType() {
        return Type.BOOLEEN;
    }

    @Override
    public String getValeur() {
        return this.cste;
    }
}
