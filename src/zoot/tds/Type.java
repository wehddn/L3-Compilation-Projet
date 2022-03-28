package zoot.tds;

/**
 * Représente le type d'un symbole
 *
 * @author Elhadji Moussa FAYE
 * @version 2.6.1
 * @since 1.1.1
 * created on 11/02/2022
 */
public enum Type {
    ENTIER("entier"),
    BOOLEEN("booleen"),
    NONDEFINI("non-defini");

    private final String value;

    Type(String valeur) {
        this.value = valeur;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }

}
