package zoot.tds;

import java.util.Objects;

/**
 * Classe qui représente un Symbole de fonction (type d'une entrée)
 *
 * @author Nicolas GRAFF
 * @version 2.5.0
 * @since 1.5.3
 * created on 06/03/2022
 */
public class SymboleFct extends Symbole{
    private final String etiquette;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SymboleFct)) return false;
        if (!super.equals(o)) return false;
        SymboleFct that = (SymboleFct) o;
        return Objects.equals(etiquette, that.etiquette);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), etiquette);
    }
}
