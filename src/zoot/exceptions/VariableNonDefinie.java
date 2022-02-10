package zoot.exceptions;

public class VariableNonDefinie extends AnalyseException{
    protected VariableNonDefinie(String m) {
        super("VARIABLE NON DEFINIE : " + m) ;
    }
}
