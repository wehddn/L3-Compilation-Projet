package zoot.tds;

import zoot.tds.Tds;

import java.util.Objects;

/**
 * Classe qui représente un Symbole (type d'une entrée)
 *
 * @author Elhadji Moussa FAYE
 * @version 2.8.1
 * @since 1.0.1
 * created on 08/02/2022
 */
public class Symbole implements Typed {
    protected final Type type;

    /**
     * Crée un symbole avec le type
     *
     * @param type le type du symbole
     */
    public Symbole(String type) {
        this.type = Type.valueOf(type.toUpperCase());

    }

    /**
     * Retourne le type du symbole
     * @return le type du symbole
     */
    public Type getType() {
        return type;
    }

    public int getNoRegion() {
        throw new RuntimeException("Fonction non implémentée");
    }

    public void setNoRegion(int noRegion) {
        throw new RuntimeException("Fonction non implémentée");
    }

    public void setDeplacement(int deplacement) {
        throw new RuntimeException("Fonction non implémentée");
    }

    public int getDeplacement() {
        throw new RuntimeException("Fonction non implémentée");
    }

    public void setEtiquette(String etiquette) {
        throw new RuntimeException("Fonction non implémentée");
    }

    public String getEtiquette() {
        throw new RuntimeException("Fonction non implémentée");
    }

    @Override
    public String toString() {
        return type.toString();
    }

}
