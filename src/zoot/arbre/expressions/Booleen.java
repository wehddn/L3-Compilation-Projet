package zoot.arbre.expressions;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 0.1.0
 * @since 0.1.0
 * created on 09/02/2022
 */
public class Booleen extends Expression{
    private boolean booleen;

    public Booleen(boolean valeur, int n) {
        super(n);
        booleen = valeur;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {
        return null;
    }
}
