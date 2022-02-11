package zoot.arbre;

/**
 * Represente un arbre abstrait (ou un sous arbre)
 * @version 1.0.2
 */
public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;
    protected int noColonne ;
    
    protected ArbreAbstrait(int n, int m) {
        noLigne = n ;
        noColonne = m;
    }

    /**
     * Retourne le numéro de ligne du début de l'instruction
     * @return le numéro de ligne du début de l'instruction
     */
    public int getNoLigne() {
            return noLigne ;
    }

    public int getNoColonne() {
        return noColonne;
    }

    /**
     * Permet de faire l'analyse sémantique de l'arbre abstrait
     */
    public abstract void verifier() ;

    /**
     * Retourne le code mips de l'arbre abstrait
     * @return le code mips  de l'arbre abstrait
     */
    public abstract String toMIPS();

}
