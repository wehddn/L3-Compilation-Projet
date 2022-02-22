package zoot.arbre.expressions;

import zoot.tds.Type;

/**
 * Représente une constante booléene
 *
 * @author Elhadji Moussa FAYE
 * @version 1.5.0
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
        return "la $v0, " + this.cste;
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
