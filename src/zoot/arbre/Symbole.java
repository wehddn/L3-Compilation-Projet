package zoot.arbre;

/**
 * Classe qui représente un Symbole (type d'une entrée)
 *
 * @author Elhadji Moussa FAYE
 * @version 1.0.1
 * @since 1.0.1
 * created on 08/02/2022
 */
public class Symbole {
    private String type;
    private int deplacement;

    /**
     * Crée un symbole avec le type type et utilise le déplacement courant
     * de la TDS pour set le déplacement du symbole
     * @param type le type du symbole
     */
    public Symbole(String type) {
        this.type = type;
        this.deplacement = Tds.getInstance().getTailleZoneVariables();
    }

    /**
     * Retourne le type du symbole
     * @return le type du symbole
     */
    public String getType() {
        return type;
    }

    /**
     * Retourne la position du symbole dans la pile locale
     * @return la position du symbole dans la pile locale
     */
    public int getDeplacement() {
        return deplacement;
    }
}
