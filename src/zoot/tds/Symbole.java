package zoot.tds;

import zoot.tds.Tds;

/**
 * Classe qui représente un Symbole (type d'une entrée)
 *
 * @author Elhadji Moussa FAYE
 * @version 1.2.0
 * @since 1.0.1
 * created on 08/02/2022
 */
public class Symbole {
    private Type type;
    private int deplacement;

    /**
     * Crée un symbole avec le type type et utilise le déplacement courant
     * de la TDS pour set le déplacement du symbole
     * @param type le type du symbole
     */
    public Symbole(String type) {
        switch (type) {
            case "entier" -> this.type = Type.ENTIER;
            case "booleen" -> this.type = Type.BOOLEEN;
            default -> this.type = Type.NONDEFINI;
        }
        this.deplacement = Tds.getInstance().getTailleZoneVariables();
    }

    /**
     * Retourne le type du symbole
     * @return le type du symbole
     */
    public Type getType() {
        return type;
    }

    /**
     * Retourne la position du symbole dans la pile locale
     * @return la position du symbole dans la pile locale
     */
    public int getDeplacement() {
        return deplacement;
    }

    @Override
    public String toString() {
        String resutat = "non-defini";
        switch (type) {
            case ENTIER -> resutat = "entier";
            case BOOLEEN -> resutat = "booleen";
        }
        return resutat;
    }
}
