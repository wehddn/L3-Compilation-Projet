package zoot.arbre.expressions;

public class ConstanteEntiere extends Constante {

    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

    @Override
    public void verifier() {

    }

    @Override
    public String toMIPS() {

        return "\t# Constante entiére\n" +
                "\tli $v0, " + this.cste;
    }

    @Override
    public String getType() {
        return "constante";
    }
}
