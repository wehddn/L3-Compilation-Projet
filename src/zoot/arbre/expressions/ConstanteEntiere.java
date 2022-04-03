package zoot.arbre.expressions;

import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 2.8.1
 */
public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String valeur, int ligne, int colonne) {
        super(valeur, ligne, colonne) ;
    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return SnippetsMIPS.mettreValeurDansRegistre("$v0", cste);
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
