package zoot.tds;

import zoot.exceptions.DeclencheurDException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.5.2
 * @since 1.5.4
 * created on 06/03/2022
 */
public class EntreeFct extends Entree{
    private final ArrayList<Type> typeParametres;

    public EntreeFct(String nom, String... typeParametres) {
        super(nom);
        this.typeParametres = new ArrayList<>();
        for (String s : typeParametres) {
            switch (s) {
                case "entier":
                    this.typeParametres.add(Type.ENTIER);
                    break;
                case "booleen":
                    this.typeParametres.add(Type.BOOLEEN);
                    break;
                default:
                    this.typeParametres.add(Type.NONDEFINI);
                    break;
            }
        }
    }

    public EntreeFct(String nom, Type... typeParametres) {
        super(nom);
        this.typeParametres = new ArrayList<>();
        Collections.addAll(this.typeParametres, typeParametres);
    }

    public EntreeFct(String nom) {
        super(nom);
        this.typeParametres = new ArrayList<>();
    }

    public void setTypeParametres(Type... typeParametres) {
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

    public ArrayList<Type> getTypeParametres() {
        return typeParametres;
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
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < typeParametres.size(); i++) {
            sb.append(typeParametres.get(i));
            if (i < (typeParametres.size()-1))
                sb.append(", ");
        }
        return nom+ "( " + sb + " )";
    }

    @Override
    public String getNom() {
        return this.nom;
    }
}
