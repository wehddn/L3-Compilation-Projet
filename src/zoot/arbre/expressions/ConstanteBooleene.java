package zoot.arbre.expressions;

/**
 * Représente une constante booléene
 *
 * @author Elhadji Moussa FAYE
 * @version 1.0.2
 * @since 1.0.0
 * created on 09/02/2022
 */
public class ConstanteBooleene extends Constante{

    public ConstanteBooleene(String valeur, int n) {
        super(valeur, n);
    }

    @Override
    public void verifier() {

    }

    /**
     * @see zoot.arbre.ArbreAbstrait
     */
    @Override
    public String toMIPS() {
        return null;
    }

    @Override
    public String toString() {
        return cste;
    }

    @Override
    public String getType() {
        return "booleen";
    }
}
