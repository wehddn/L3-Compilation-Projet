package zoot.exceptions;

public class DoubleDeclarationException extends AnalyseSyntaxiqueException{
    public DoubleDeclarationException(int ligne, int colonne, String m) {
        super("DOUBLE DECLARATION :\n\tligne " +  ligne + ", colonne " + colonne + ", variable " + m) ;

    }
}