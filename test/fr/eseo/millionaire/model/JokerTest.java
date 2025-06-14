package fr.eseo.millionaire.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Classe de tests unitaires pour la classe abstraite Joker.
 * Utilise une implémentation concrète minimale pour tester le comportement générique.
 */
class JokerTest {

    private Joker joker;

    // Implémentation minimale pour les tests
    private static class JokerTestImpl extends Joker {
        @Override
        public void utiliser(Joueur joueur, Question question) {
            if (disponible) {
                desactiver(); // Simule un effet de joker
            }
        }
    }

    @BeforeEach
    void setUp() {
        joker = new JokerTestImpl();
    }

    @Test
    void testJokerDisponibleParDefaut() {
        assertTrue(joker.isDisponible(), "Le joker doit être disponible par défaut");
    }

    @Test
    void testDesactiverJoker() {
        joker.desactiver();
        assertFalse(joker.isDisponible(), "Le joker doit être désactivé après appel à desactiver()");
    }

    @Test
    void testUtiliserJokerUneFois() {
        Joueur joueur = new Joueur("Testeur");
        Question question = new Question(
                "Quelle est la capitale de la France ?",
                new String[]{"Paris", "Lyon", "Marseille", "Nice"},
                'A',
                "Géographie",
                "Facile"
        );

        joker.utiliser(joueur, question);
        assertFalse(joker.isDisponible(), "Le joker ne doit plus être disponible après utilisation");
    }

    @Test
    void testUtiliserJokerDeuxFois() {
        Joueur joueur = new Joueur("Testeur");
        Question question = new Question(
                "Quelle est la capitale de la France ?",
                new String[]{"Paris", "Lyon", "Marseille", "Nice"},
                'A',
                "Géographie",
                "Facile"
        );

        joker.utiliser(joueur, question);
        assertFalse(joker.isDisponible(), "Le joker ne doit plus être disponible après première utilisation");

        // Deuxième utilisation ne doit rien changer
        joker.utiliser(joueur, question);
        assertFalse(joker.isDisponible(), "Le joker reste indisponible après une deuxième tentative");
    }
}
