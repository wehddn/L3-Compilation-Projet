package zoot.exceptions;

import zoot.tds.EntreeFct;
import zoot.tds.EntreeVar;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.0.0
 * @since 2.0.0
 * created on 19/03/2022
 */
public class DeclencheurEntreeNonDefinie extends DeclencheurDException{
    public DeclencheurEntreeNonDefinie(int ligne, int colonne) {
        super(ligne, colonne);
    }

    @Override
    public void entreeFct(EntreeFct e, String message) {
        throw new FonctionNonDefinieException(ligne, colonne, message);
    }

    @Override
    public void entreeVar(EntreeVar e, String message) {
        throw new VariableNonDefinieException(ligne, colonne, message);
    }
}
