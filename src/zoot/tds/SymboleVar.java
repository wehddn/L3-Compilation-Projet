package zoot.tds;

/**
 * Classe qui représente un Symbole de variable (type d'une entrée)
 *
 * @author Nicolas GRAFF
 * @version 1.8.0
 * @since 1.5.3
 * created on 06/03/2022
 */
public class SymboleVar extends Symbole{
    private final int deplacement;

    /**
     * Crée un symbole avec le type type et utilise le déplacement courant
     * de la TDS pour set le déplacement du symbole
     *
     * @param type le type du symbole
     */
    public SymboleVar(String type) {
        super(type);
        this.deplacement = Tds.getInstance().getTaillePile();
    }

    public int getDeplacement() {
        return deplacement;
    }
}
