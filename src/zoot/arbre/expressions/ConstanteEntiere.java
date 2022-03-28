package zoot.arbre.expressions;

import zoot.tds.Type;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 2.6.4
 */
public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int ligne, int colonne) {
        super(texte, ligne, colonne) ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return "\tli $v0, " + this.cste;
    }

    @Override
    public Type getType() {
        return Type.ENTIER;
    }

    @Override
    public String getCommentaire() {
        return this.cste;
    }
}
