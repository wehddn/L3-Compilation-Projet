package zoot.arbre.instructions;

import zoot.arbre.ArbreAbstrait;

/**
 * Représente une instruction (élément de l'arbre abstrait)
 */
public abstract class Instruction extends ArbreAbstrait {

    protected Instruction(int ligne, int colonne) {
        super(ligne, colonne);

    }

}
