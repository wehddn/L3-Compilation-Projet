package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.Symbole;
import zoot.exceptions.ZootException;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 0.1.0
 * @since 0.1.0
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
}
