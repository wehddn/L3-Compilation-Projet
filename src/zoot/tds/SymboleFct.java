package zoot.tds;

/**
 * Classe qui représente un Symbole de fonction (type d'une entrée)
 *
 * @author Nicolas GRAFF
 * @version 2.0.0
 * @since 1.5.3
 * created on 06/03/2022
 */
public class SymboleFct extends Symbole{
    private String etiquette;

    /**
     * Crée un symbole avec le type etiquette pour la TDS
     *
     * @param type le type du symbole
     */
    public SymboleFct(String type, String etiquette) {
        super(type);
        this.etiquette = etiquette;
    }

    public String getEtiquette() {
        return etiquette;
    }
}
