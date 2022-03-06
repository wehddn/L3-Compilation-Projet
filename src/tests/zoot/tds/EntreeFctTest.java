package zoot.tds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntreeFctTest {

    /**
     * Vérifie qu'une entree de fonction est égale à elle même
     */
    @Test
    void testEquals1() {
        EntreeFct e1 = new EntreeFct("e1", "entier", "booleen");
        assertEquals(e1, e1);
    }

    /**
     * Vérifie qu'une entrée de fonction est différente d'une autre entree avec les même type de paramètre
     * mais avec un nom différent
     */
    @Test
    void testEquals2() {
        EntreeFct e1 = new EntreeFct("e1", "entier", "booleen");
        EntreeFct e2 = new EntreeFct("e2", "entier", "booleen");
        assertNotEquals(e1, e2);
    }

    /**
     * Vérifie que deux entrée avec les mêmes nom mais des types de paramètres différents sont différents
     */
    @Test
    void testEquals3() {
        EntreeFct e1 = new EntreeFct("e1", "entier", "booleen");
        EntreeFct e2 = new EntreeFct("e1", "booleen", "booleen");
        assertNotEquals(e1, e2);
    }

    /**
     * Vérifie que deux entree avec les mêmes noms et les mêmes types de paramètres sont égaux
     */
    @Test
    void testEquals4() {
        EntreeFct e1 = new EntreeFct("e1", "entier", "booleen");
        EntreeFct e2 = new EntreeFct("e1", "entier", "booleen");
        assertEquals(e1, e2);
    }

    /**
     * Vérifie que deux entree avec les mêmes noms et les mêmes types de paramètres, mais avec l'ordre des paramètres qui
     * change sont différents
     */
    @Test
    void testEquals5() {
        EntreeFct e1 = new EntreeFct("e1", "entier", "booleen");
        EntreeFct e2 = new EntreeFct("e1", "booleen", "entier");
        assertNotEquals(e1, e2);
    }
}