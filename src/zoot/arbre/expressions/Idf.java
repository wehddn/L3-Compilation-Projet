package zoot.arbre.expressions;

import zoot.arbre.Symbole;
import zoot.exceptions.ZootException;

/**
 * Classe représentant un identifant de variable
 *
 * @author Elhadji Moussa FAYE
 * @version 1.0.2
 * @since 1.0.0
 * created on 08/02/2022
 */
public class Idf extends Expression {
    private String nom;
    private Symbole symbole = null;

    public Idf(String nom, int noLigne) {
        super(noLigne);
        this.nom = nom;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }

    public String getType() throws ZootException {
        if (symbole == null)
            throw new ZootException("L'Idf n'a pas de symbole associé");
        else
            return symbole.getType();
    }

    @Override
    public String toString() {
        return nom;
    }
}
