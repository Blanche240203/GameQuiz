package fr.eseo.millionaire.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testConstructeurValide() {
        String[] propositions = {"Paris", "Londres", "Berlin", "Rome"};
        Question q = new Question("Quelle est la capitale de la France ?", propositions, 'A', "Facile", "Géographie");
        // Vérifie que les getters retournent bien ce qu'on a passé
        assertEquals("Quelle est la capitale de la France ?", q.getEnonce());
        assertArrayEquals(propositions, q.getPropositions());
        assertEquals('A', q.getBonneReponse());
        assertEquals("Facile", q.getNiveau());
        assertEquals("Géographie", q.getCategorie());
    }

    @Test
    void testConstructeurAvecMoinsDe4Propositions() {
        String[] propositions = {"Paris", "Londres", "Berlin"}; // Seulement 3 propositions
        // Doit jeter une exception car il faut 4 propositions
        assertThrows(IllegalArgumentException.class, () -> {
            new Question("Question test", propositions, 'A', "Facile", "Test");
        });
    }

    @Test
    void testConstructeurAvecBonneReponseInvalide() {
        String[] propositions = {"Paris", "Londres", "Berlin", "Rome"};
        // La bonne réponse doit être A, B, C ou D, ici 'E' est invalide
        assertThrows(IllegalArgumentException.class, () -> {
            new Question("Question test", propositions, 'E', "Facile", "Test");
        });
    }

    @Test
    void testEstBonneReponse() {
        String[] propositions = {"Paris", "Londres", "Berlin", "Rome"};
        Question q = new Question("Quelle est la capitale de la France ?", propositions, 'B', "Facile", "Géographie");

        // Test avec bonne réponse majuscule
        assertTrue(q.estBonneReponse('B'));

        // Test avec bonne réponse minuscule
        assertTrue(q.estBonneReponse('b'));

        // Test avec mauvaise réponse
        assertFalse(q.estBonneReponse('A'));
        assertFalse(q.estBonneReponse('C'));
    }

    @Test
    void testGetPropositionsRetourneUneCopie() {
        String[] propositions = {"Paris", "Londres", "Berlin", "Rome"};
        Question q = new Question("Quelle est la capitale ?", propositions, 'A', "Facile", "Géographie");

        String[] copie = q.getPropositions();
        // Modifier la copie ne doit pas modifier les propositions internes de la question
        copie[0] = "Modified";

        assertNotEquals(copie[0], q.getPropositions()[0]);
    }
}
