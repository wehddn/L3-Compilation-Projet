package zoot.arbre.expressions;

public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public String toMIPS() {

        return "\tli $v0, " + this.cste + " # Constante entiére\n";
    }

}
