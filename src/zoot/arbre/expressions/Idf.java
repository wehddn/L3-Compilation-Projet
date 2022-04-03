package zoot.arbre.expressions;

import zoot.mips.SnippetsMIPS;
import zoot.tds.*;

/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 2.8.1
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Idf extends Expression {

    private final Entree entree;
    private SymboleVar symbole = null;

    public Idf(Entree e, int ligne, int colonne) {
        super(ligne, colonne);
        this.entree = e;
    }

    @Override
    public void verifier() {
        this.symbole=(SymboleVar) Tds.getInstance().identifier(entree, ligne, colonne);
    }

    @Override
    public String toMIPS() {
        return """
                %s
                \tlw $v0, 0($v0)
                """.formatted(getPositionMIPS());
    }

    public Type getType() {
        return symbole.getType();
    }

    @Override
    public String getCommentaire() {
        return this.entree.getNom();
    }

    public SymboleVar getSymbole() {
        return this.symbole;
    }

    @Override
    public String toString() {
        return entree.toString();
    }

    public String getPositionMIPS() {
        return """
                \tli $a0, %s
                \tli $a1, %s
                %s""".formatted(symbole.getNoRegion(), symbole.getDeplacement(), SnippetsMIPS.appelRecherchePosition());
    }
}
