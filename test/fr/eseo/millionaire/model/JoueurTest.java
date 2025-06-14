package fr.eseo.millionaire.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests unitaires pour la classe Joueur.
 * Elle vérifie le comportement de chaque méthode publique
 * pour assurer la conformité avec le cahier des charges.
 */
class JoueurTest {

    private Joueur joueur;

    /**
     * Initialisation avant chaque test : création d'un nouveau joueur.
     */
    @BeforeEach
    void setUp() {
        joueur = new Joueur("testeur");
    }

    /**
     * Teste l'initialisation correcte des attributs du joueur.
     */
    @Test
    void testInitialisation() {
        assertEquals("testeur", joueur.getPseudo(), "Le pseudo doit être initialisé correctement");
        assertEquals(0, joueur.getScore(), "Le score initial doit être 0");
        assertTrue(joueur.isJoker5050Disponible(), "Le joker 50/50 doit être disponible au début");
        assertTrue(joueur.isJokerDoubleDisponible(), "Le joker double points doit être disponible au début");
    }

    /**
     * Teste l'ajout de points positifs au score.
     */
    @Test
    void testAjouterPointsPositifs() {
        joueur.ajouterPoints(15);
        assertEquals(15, joueur.getScore(), "Le score doit augmenter correctement");
    }

    /**
     * Teste que le score ne devient jamais négatif,
     * même en retirant plus de points que le score actuel.
     */
    @Test
    void testAjouterPointsNegatifsSansDepasserZero() {
        joueur.ajouterPoints(10);
        joueur.ajouterPoints(-20); // On essaie de retirer trop de points
        assertEquals(0, joueur.getScore(), "Le score ne doit pas devenir négatif");
    }

    /**
     * Teste l'utilisation du joker 50/50 : il doit être utilisable une seule fois,
     * puis plus disponible.
     */
    @Test
    void testUtiliserJoker5050() {
        assertTrue(joueur.utiliserJoker5050(), "Le joker 50/50 doit pouvoir être utilisé la première fois");
        assertFalse(joueur.utiliserJoker5050(), "Le joker 50/50 ne doit plus être disponible après utilisation");
        assertFalse(joueur.isJoker5050Disponible(), "Le joker 50/50 doit être marqué comme non disponible");
    }

    /**
     * Teste l'utilisation du joker double points : il doit être utilisable une seule fois,
     * puis plus disponible.
     */
    @Test
    void testUtiliserJokerDouble() {
        assertTrue(joueur.utiliserJokerDouble(), "Le joker double points doit pouvoir être utilisé la première fois");
        assertFalse(joueur.utiliserJokerDouble(), "Le joker double points ne doit plus être disponible après utilisation");
        assertFalse(joueur.isJokerDoubleDisponible(), "Le joker double points doit être marqué comme non disponible");
    }

    /**
     * Teste la remise à zéro (reset) : score à 0 et jokers réactivés.
     */
    @Test
    void testReset() {
        // Modification d'état avant reset
        joueur.ajouterPoints(30);
        joueur.utiliserJoker5050();
        joueur.utiliserJokerDouble();

        // Appel de la méthode reset
        joueur.reset();

        // Vérifications post-reset
        assertEquals(0, joueur.getScore(), "Après reset, le score doit revenir à 0");
        assertTrue(joueur.isJoker5050Disponible(), "Après reset, joker 50/50 doit redevenir disponible");
        assertTrue(joueur.isJokerDoubleDisponible(), "Après reset, joker double points doit redevenir disponible");
    }

    /**
     * Teste que la méthode toString retourne une chaîne contenant les infos essentielles.
     */
    @Test
    void testToStringContientInfos() {
        String toString = joueur.toString();
        assertTrue(toString.contains("pseudo='testeur'"), "toString doit contenir le pseudo");
        assertTrue(toString.contains("score=0"), "toString doit contenir le score");
        assertTrue(toString.contains("joker5050=true"), "toString doit contenir l'état du joker 50/50");
        assertTrue(toString.contains("jokerDouble=true"), "toString doit contenir l'état du joker double points");
    }
}
