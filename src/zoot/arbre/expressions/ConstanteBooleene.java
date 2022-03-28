package zoot.arbre.expressions;

import zoot.tds.Type;

/**
 * Représente une constante booléene
 *
 * @author Elhadji Moussa FAYE
 * @version 2.6.4
 * @since 1.0.0
 * created on 09/02/2022
 */
public class ConstanteBooleene extends Constante{

    public ConstanteBooleene(String valeur, int ligne, int colonne) {
        super(valeur, ligne, colonne);
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        String valeur = "0";
        if (cste.equals("vrai"))
            valeur = "1";
        return "\tli $v0, " + valeur;
    }

    public Type getType() {
        return Type.BOOLEEN;
    }

    @Override
    public String getCommentaire() {
        return this.cste;
    }

}
