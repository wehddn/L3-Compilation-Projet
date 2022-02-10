package zoot.exceptions;

public class DoubleDeclarationExeption extends AnalyseException{
    protected DoubleDeclarationExeption(String m) {
        super("DOUBLE DECLARATION : " + m) ;
    }
}