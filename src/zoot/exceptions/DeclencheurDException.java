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
public abstract class DeclencheurDException {
    protected int ligne;
    protected int colonne;
    public DeclencheurDException(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
    }

    public abstract void entreeFct(EntreeFct e, String message);
    public abstract void entreeVar(EntreeVar e, String message);
}
