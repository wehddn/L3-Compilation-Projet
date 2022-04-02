package zoot.arbre.expressions;

import zoot.mips.SnippetsMIPS;
import zoot.tds.Type;

/**
 * Represente une constant entière dans l'arbre abstrait
 * @version 2.8.0
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
