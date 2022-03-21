package zoot.tds;

import zoot.tds.Tds;

import java.util.Objects;

/**
 * Classe qui représente un Symbole (type d'une entrée)
 *
 * @author Elhadji Moussa FAYE
 * @version 2.5.0
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Symbole)) return false;
        Symbole symbole = (Symbole) o;
        return type == symbole.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
