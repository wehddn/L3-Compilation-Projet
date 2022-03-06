package zoot.tds;

import zoot.tds.Tds;

/**
 * Classe qui représente un Symbole (type d'une entrée)
 *
 * @author Elhadji Moussa FAYE
 * @version 1.5.3
 * @since 1.0.1
 * created on 08/02/2022
 */
public class Symbole {
    private final Type type;

    /**
     * Crée un symbole avec le type
     *
     * @param type le type du symbole
     */
    public Symbole(String type) {
        switch (type) {
            case "entier":
                this.type = Type.ENTIER;
                break;
            case "booleen":
                this.type = Type.BOOLEEN;
                break;
            default:
                this.type = Type.NONDEFINI;
                break;
        }

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

    @Override
    public String toString() {
        String resutat = "non-defini";
        switch (type) {
            case ENTIER:
                resutat = "entier";
                break;
            case BOOLEEN:
                resutat = "booleen";
                break;
        }
        return resutat;
    }
}
