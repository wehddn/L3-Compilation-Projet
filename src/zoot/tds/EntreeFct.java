package zoot.tds;

import zoot.exceptions.DeclencheurDException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.5.0
 * @since 1.5.4
 * created on 06/03/2022
 */
public class EntreeFct extends Entree{
    private final ArrayList<String> typeParametres;

    public EntreeFct(String nom, String... typeParametres) {
        super(nom);
        this.typeParametres = new ArrayList<>();
        Collections.addAll(this.typeParametres, typeParametres);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        // Une fonction est égale à une autre fonction s'ils ont le même nom et les mêmes paramètres
        if (o instanceof EntreeFct entreeFct) {
            return nom.equals(entreeFct.nom) && this.typeParametres.equals(entreeFct.typeParametres);
        }

        return false;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void declencherException(DeclencheurDException d, String message) {
        d.entreeFct(this, message);
    }

    @Override
    public String toString() {
        return nom+"()";
    }
}
