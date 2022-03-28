package zoot.tds;

/**
 * Classe qui représente un Symbole de variable (type d'une entrée)
 *
 * @author Nicolas GRAFF
 * @version 2.6.1
 * @since 1.5.3
 * created on 06/03/2022
 */
public class SymboleVar extends Symbole{
    private int deplacement = 0;
    private int noRegion = 0;

    /**
     * Crée un symbole avec le type type et utilise le déplacement courant
     * de la TDS pour set le déplacement du symbole
     *
     * @param type le type du symbole
     */
    public SymboleVar(String type) {
        super(type);
    }

    @Override
    public int getNoRegion() {
        return noRegion;
    }

    @Override
    public void setNoRegion(int noRegion) {
        this.noRegion = noRegion;
    }

    @Override
    public void setDeplacement(int deplacement) {
        this.deplacement = deplacement;
    }

    @Override
    public int getDeplacement() {
        return deplacement;
    }
}
