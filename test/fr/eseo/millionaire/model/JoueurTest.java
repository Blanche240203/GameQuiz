package fr.eseo.millionaire.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test unitaire pour la classe {@link Joueur}.
 * Vérifie le bon fonctionnement de la création, des jokers et du score.
 */
class JoueurTest {

    /**
     * Vérifie que la création d'un joueur initialise correctement le pseudo,
     * le score à 0 et les jokers disponibles.
     */
    @Test
    void testCreationJoueur() {
        Joueur joueur = new Joueur("Megane");

        assertEquals("Megane", joueur.getPseudo(), "Le pseudo n'est pas correctement initialisé.");
        assertEquals(0, joueur.getScore(), "Le score initial doit être à 0.");
        assertTrue(joueur.isJoker5050Disponible(), "Le joker 50/50 doit être disponible au départ.");
        assertTrue(joueur.isJokerDoubleDisponible(), "Le joker Double doit être disponible au départ.");
    }

    /**
     * Vérifie que l'ajout de points fonctionne correctement,
     * et que le score ne devient jamais négatif.
     */
    @Test
    void testAjouterPoints() {
        Joueur joueur = new Joueur("Test");

        joueur.ajouterPoints(100);
        assertEquals(100, joueur.getScore(), "Le score doit être mis à jour après ajout de points.");

        joueur.ajouterPoints(-150);
        assertEquals(0, joueur.getScore(), "Le score ne doit pas devenir négatif.");
    }

    /**
     * Vérifie que l'utilisation des jokers ne peut se faire qu'une seule fois.
     */
    @Test
    void testUtilisationJokers() {
        Joueur joueur = new Joueur("Test");

        assertTrue(joueur.utiliserJoker5050(), "Le joker 50/50 doit être utilisable une première fois.");
        assertFalse(joueur.utiliserJoker5050(), "Le joker 50/50 ne doit pas être réutilisable.");

        assertTrue(joueur.utiliserJokerDouble(), "Le joker Double doit être utilisable une première fois.");
        assertFalse(joueur.utiliserJokerDouble(), "Le joker Double ne doit pas être réutilisable.");
    }

    /**
     * Vérifie que la méthode {@code reset()} remet le score à 0
     * et rend les jokers à nouveau disponibles.
     */
    @Test
    void testReset() {
        Joueur joueur = new Joueur("Test");

        joueur.ajouterPoints(200);
        joueur.utiliserJoker5050();
        joueur.utiliserJokerDouble();

        joueur.reset();

        assertEquals(0, joueur.getScore(), "Le score doit être remis à zéro.");
        assertTrue(joueur.isJoker5050Disponible(), "Le joker 50/50 doit être réinitialisé.");
        assertTrue(joueur.isJokerDoubleDisponible(), "Le joker Double doit être réinitialisé.");
    }
}
