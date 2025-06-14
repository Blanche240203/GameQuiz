package fr.eseo.millionaire.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe JokerDoublePoint.
 */
class JokerDoublePointTest {

    private JokerDoublePoint joker;
    private Joueur joueur;
    private Question question;

    @BeforeEach
    void setUp() {
        joker = new JokerDoublePoint();
        joueur = new Joueur("Testeur");
        question = new Question(
                "Quelle est la capitale de la France ?",
                new String[]{"Paris", "Lyon", "Marseille", "Nice"},
                'A',
                "Géographie",
                "Facile"
        );
    }

    @Test
    void testJokerDisponibleParDefaut() {
        assertTrue(joker.isDisponible(), "Le joker doit être disponible par défaut");
    }

    @Test
    void testUtiliserJokerDoublePoint() {
        joker.utiliser(joueur, question);
        assertFalse(joker.isDisponible(), "Le joker doit être désactivé après utilisation");
    }

    @Test
    void testUtiliserJokerDeuxFois() {
        joker.utiliser(joueur, question);
        assertFalse(joker.isDisponible(), "Le joker doit être désactivé après première utilisation");

        // Deuxième appel ne doit pas réactiver le joker
        joker.utiliser(joueur, question);
        assertFalse(joker.isDisponible(), "Le joker reste désactivé après une deuxième tentative");
    }
}
