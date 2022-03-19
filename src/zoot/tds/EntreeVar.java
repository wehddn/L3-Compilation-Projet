package zoot.tds;

import zoot.exceptions.DeclencheurDException;

/**
 * Description
 *
 * @author Elhadji Moussa FAYE
 * @version 2.0.0
 * @since 1.5.4
 * created on 06/03/2022
 */
public class EntreeVar extends Entree{
    public EntreeVar(String nom) {
        super(nom);
    }

    @Override
    public void declencherException(DeclencheurDException d, String message) {
        d.entreeVar(this, message);
    }
}
