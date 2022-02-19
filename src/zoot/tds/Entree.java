package zoot.tds;

import java.util.Objects;

/**
 * Représente une entrée dans la TDS (juste l'identification)
 *
 * @author Elhadji Moussa FAYE
 * @version 1.4.1
 * @since 1.4.0
 * created on 19/02/2022
 */
public class Entree {
    private String nom;

    public Entree(String nom) {
        this.nom = nom;
    }

    /**
     * Donne le nom de l'entrée
     * @return le nom de l'entrée
     */
    public String getNom() {
        return nom;
    }

    /**
     * Permet de comparer l'entrée à un autre objet
     * @param o l'objet avec lequel comparer
     * @return vrai si les objets sont égaux, faux sinon
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entree entree)) return false;
        return nom.equals(entree.nom);
    }

    /**
     * Retourne le code de hashage pour les hashmap
     * @return le code de hashage pour les hashmap
     */
    @Override
    public int hashCode() {
        return Objects.hash(nom);
    }

    @Override
    public String toString() {
        return nom;
    }
}
