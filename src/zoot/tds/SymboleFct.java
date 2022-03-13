package zoot.tds;

/**
 * Classe qui représente un Symbole de fonction (type d'une entrée)
 *
 * @author Nicolas GRAFF
 * @version 1.8.0
 * @since 1.5.3
 * created on 06/03/2022
 */
public class SymboleFct extends Symbole{
    String etiquette;

    /**
     * Crée un symbole avec le type etiquette pour la TDS
     *
     * @param type le type du symbole
     */
    public SymboleFct(String type) {
        super(type);

    }

    public String getEtiquette() {
        return etiquette;
    }
}
