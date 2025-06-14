package fr.eseo.millionaire.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests unitaires pour la classe Joker5050.
 */
class Joker5050Test {

    private Joker5050 joker;
    private Joueur joueur;
    private Question question;

    @BeforeEach
    void setUp() {
        joker = new Joker5050();
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
    void testUtiliserJoker5050() {
        joker.utiliser(joueur, question);

        // Vérifie que le joker est désactivé après utilisation
        assertFalse(joker.isDisponible(), "Le joker doit être désactivé après utilisation");

        // Vérifie que deux lettres sont bien conservées
        List<Character> lettres = joker.getLettresRestantes();
        assertNotNull(lettres, "La liste des lettres restantes ne doit pas être nulle");
        assertEquals(2, lettres.size(), "Il doit rester exactement deux lettres");

        // Vérifie que la bonne réponse est toujours présente
        assertTrue(lettres.contains('A'), "La bonne réponse doit être conservée");
    }

    @Test
    void testUtiliserJokerDeuxFois() {
        joker.utiliser(joueur, question);
        List<Character> lettres1 = joker.getLettresRestantes();

        // Deuxième appel ne doit rien changer
        joker.utiliser(joueur, question);
        List<Character> lettres2 = joker.getLettresRestantes();

        assertEquals(lettres1, lettres2, "Les lettres restantes ne doivent pas changer après une deuxième utilisation");
    }
}
