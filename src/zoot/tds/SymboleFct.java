package zoot.tds;

import java.util.Objects;

/**
 * Classe qui représente un Symbole de fonction (type d'une entrée)
 *
 * @author Nicolas GRAFF
 * @version 2.6.1
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
    public SymboleFct(String type) {
        super(type);
    }

    public String getEtiquette() {
        return etiquette;
    }

    @Override
    public void setEtiquette(String etiquette) {
        this.etiquette = etiquette;
    }
}
