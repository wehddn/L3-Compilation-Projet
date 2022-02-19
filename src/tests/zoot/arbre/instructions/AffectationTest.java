package zoot.arbre.instructions;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zoot.arbre.expressions.ConstanteBooleene;
import zoot.arbre.expressions.ConstanteEntiere;
import zoot.arbre.expressions.Idf;
import zoot.exceptions.AnalyseSemantiqueException;
import zoot.tds.Symbole;
import zoot.tds.Tds;

import static org.junit.jupiter.api.Assertions.*;

class AffectationTest {

    @BeforeEach
    void setUp() {
        Tds.getInstance().ajouter("a", new Symbole("entier"), 0,0);
        Tds.getInstance().ajouter("b", new Symbole("entier"), 0,0);
        Tds.getInstance().ajouter("c", new Symbole("booleen"), 0,0);
        Tds.getInstance().ajouter("d", new Symbole("booleen"), 0,0);
    }

    /**
     * Test du retour de toMIPS d'une affectation d'une constante entiere à une variable entiere existant
     */
    @Test
    void toMIPS1() {
        Idf idfA = new Idf("a", 0, 0);
        ConstanteEntiere ce = new ConstanteEntiere("15", 0, 0);
        Affectation aff = new Affectation(idfA, ce, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail("Erreur lors de l'analyse sémantique");
        }

        String attendu = "# a = 15\n\tli $v0, 15\n\tsw $v0, 0($s7)";
        assertEquals(attendu.replaceAll("[\\t ]", ""), aff.toMIPS().replaceAll("[\\t ]", ""));

    }

    /**
     * Test du retour de toMIPS d'une affectation d'une constante entiere à une variable entiere existant
     */
    @Test
    void toMIPS2() {
        Idf idfA = new Idf("b", 0, 0);
        ConstanteEntiere ce = new ConstanteEntiere("45", 0, 0);
        Affectation aff = new Affectation(idfA, ce, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail("Erreur lors de l'analyse sémantique");
        }

        String attendu = "# b = 45\n\tli $v0, 45\n\tsw $v0, -4($s7)";
        assertEquals(attendu.replaceAll("[\\t ]", ""), aff.toMIPS().replaceAll("[\\t ]", ""));
    }

    /**
     * Test de verifier d'une affectation d'une constante entiere à une variable entiere existant
     */
    @Test
    void toMIPS3() {
        Idf idfB = new Idf("b", 0, 0);
        Idf idfA  = new Idf("b", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail("Erreur lors de l'analyse sémantique");
        }

        String attendu = "# b = b\n\tlw $v0, -4($s7)\n\tsw $v0, -4($s7)";
        assertEquals(attendu.replaceAll("[\\t ]", ""), aff.toMIPS().replaceAll("[\\t ]", ""));
    }

    /**
     * Test de verifier d'une affectation d'une constante entiere à une variable entiere existant
     */
    @Test
    void verifier1() {
        Idf idfA = new Idf("a", 0, 0);
        ConstanteEntiere ce = new ConstanteEntiere("15", 0, 0);
        Affectation aff = new Affectation(idfA, ce, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une constante entiere à une variable entiere existant
     */
    @Test
    void verifier2() {
        Idf idfA = new Idf("b", 0, 0);
        ConstanteEntiere ce = new ConstanteEntiere("45", 0, 0);
        Affectation aff = new Affectation(idfA, ce, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une constante entiere à une variable entiere non-existant
     */
    @Test
    void verifier3() {
        Idf idfA = new Idf("z", 0, 0);
        ConstanteEntiere ce = new ConstanteEntiere("45", 0, 0);
        Affectation aff = new Affectation(idfA, ce, 0, 0);
        try {
            aff.verifier();
            fail();
        } catch (AnalyseSemantiqueException ignored) {

        }
    }

    /**
     * Test de verifier d'une affectation d'une variable entiere à une variable entiere
     */
    @Test
    void verifier4() {
        Idf idfB = new Idf("b", 0, 0);
        Idf idfA  = new Idf("a", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une variable entiere à elle même
     */
    @Test
    void verifier5() {
        Idf idfB = new Idf("b", 0, 0);
        Idf idfA  = new Idf("b", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une constante booléene à une variable entière
     */
    @Test
    void verifier6() {
        Idf idfB = new Idf("b", 0, 0);
        ConstanteBooleene idfA  = new ConstanteBooleene("vrai", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
            fail();
        } catch (AnalyseSemantiqueException ignored) {
        }
    }

    /**
     * Test de verifier d'une affectation d'une constante booléene à une variable booléene
     */
    @Test
    void verifier7() {
        Idf idfB = new Idf("c", 0, 0);
        ConstanteBooleene idfA  = new ConstanteBooleene("faux", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException e) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une variable booléene à une variable booléene
     */
    @Test
    void verifier8() {
        Idf idfB = new Idf("c", 0, 0);
        Idf idfA  = new Idf("d", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une variable booléene à elle même
     */
    @Test
    void verifier9() {
        Idf idfB = new Idf("c", 0, 0);
        Idf idfA  = new Idf("c", 0, 0);
        Affectation aff = new Affectation(idfB, idfA, 0, 0);
        try {
            aff.verifier();
        } catch (AnalyseSemantiqueException exception) {
            fail();
        }
    }

    /**
     * Test de verifier d'une affectation d'une constante entière à une variable booléene
     */
    @Test
    void verifier10() {
        Idf idfA = new Idf("c", 0, 0);
        ConstanteEntiere ce = new ConstanteEntiere("15", 0, 0);
        Affectation aff = new Affectation(idfA, ce, 0, 0);
        try {
            aff.verifier();
            fail();
        } catch (AnalyseSemantiqueException ignored) {
        }
    }

    @AfterEach
    void tearDown() {
        Tds.getInstance().reset();
    }
}