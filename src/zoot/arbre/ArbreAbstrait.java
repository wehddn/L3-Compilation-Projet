package zoot.arbre;

import zoot.exceptions.AnalyseSemantiqueException;

/**
 * Represente un arbre abstrait (ou un sous arbre)
 * @version 2.8.1
 */
public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int ligne;
    protected int colonne;
    
    protected ArbreAbstrait(int ligne, int colonne) {
        this.ligne = ligne ;
        this.colonne = colonne;
    }

    /**
     * Retourne le numéro de ligne du début de l'instruction
     * @return le numéro de ligne du début de l'instruction
     */
    public int getLigne() {
            return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    /**
     * Permet de faire l'analyse sémantique de l'arbre abstrait
     */
    public abstract void verifier() throws AnalyseSemantiqueException;

    /**
     * Retourne le code mips de l'arbre abstrait
     * @return le code mips  de l'arbre abstrait
     */
    public abstract String toMIPS();
}
