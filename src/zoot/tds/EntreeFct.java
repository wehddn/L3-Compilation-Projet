package zoot.tds;

import zoot.exceptions.DeclencheurDException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.0.0
 * @since 1.5.4
 * created on 06/03/2022
 */
public class EntreeFct extends Entree{
    private ArrayList<String> typeParametres;

    public EntreeFct(String nom, String... typeParametres) {
        super(nom);
        this.typeParametres = new ArrayList<>();
        Collections.addAll(this.typeParametres, typeParametres);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntreeFct entree)) {
            return false;
        }

        return nom.equals(entree.nom) && this.typeParametres.equals(entree.typeParametres);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void declencherException(DeclencheurDException d, String message) {
        d.entreeFct(this, message);
    }
}
