package zoot.arbre.expressions;

import zoot.mips.SnippetsMIPS;
import zoot.tds.*;

/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 3.0.1
 * @since 1.0.0
 * created on 11/02/2022
 */
public class Idf extends Expression {

    /**
     * L'entrée de la variable
     */
    private final Entree entree;
    /**
     * Le symbole de la variable (récupéré après l'analyse sémantique)
     */
    private Symbole symbole = null;

    /**
     *
     * @param e l'entrée
     * @param ligne la ligne de déclaration
     * @param colonne la colonne de déclaration
     */
    public Idf(Entree e, int ligne, int colonne) {
        super(ligne, colonne);
        this.entree = e;
    }

    /**
     * Fais l'analyse sémantique (vérifie que la variable existe dans la TDS)
     */
    @Override
    public void verifier() {
        this.symbole= Tds.getInstance().identifier(entree, ligne, colonne);
    }

    /**
     *
     * @return
     */
    @Override
    public String toMIPS() {
        return """
                %s
                \tlw $v0, 0($v0)""".formatted(getPositionMIPS());
    }

    public Type getType() {
        return symbole.getType();
    }

    @Override
    public String getCommentaire() {
        return this.entree.getNom();
    }

    public Symbole getSymbole() {
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
