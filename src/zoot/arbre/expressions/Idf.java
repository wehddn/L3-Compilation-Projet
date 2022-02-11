package zoot.arbre.expressions;

import zoot.arbre.ArbreAbstrait;
import zoot.arbre.Symbole;
import zoot.arbre.Tds;
/**
 * Description
 *
 * @author Elhadji Moussa FAYE, Nicolas GRAFF
 * @version 1.1.0
 * @since 1.0.0
 * created on 11/02/2022
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
        this.symbole=Tds.getInstance().identifier(nom);
        getType();
    }

    @Override
    public String toMIPS() {
        return null;
    }

    public String getType() {
        if (symbole == null)
            return null;
        else
            return symbole.getType();
    }
}
