package fr.eseo.millionaire.data;

import fr.eseo.millionaire.model.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BaseQuestionsTest {

    @Test
    void testGetQuestionsParNiveau() {
        Question[] faciles = BaseQuestions.getQuestionsParNiveau("facile");
        assertEquals(5, faciles.length, "Il doit y avoir 5 questions faciles");
        for (Question q : faciles) {
            assertEquals("facile", q.getNiveau().toLowerCase());
            assertNotNull(q.getCategorie());
            assertFalse(q.getCategorie().isEmpty());
            assertEquals(4, q.getPropositions().length);
            assertTrue(q.getBonneReponse() >= 'A' && q.getBonneReponse() <= 'D');
        }

        Question[] moyens = BaseQuestions.getQuestionsParNiveau("moyen");
        assertEquals(5, moyens.length, "Il doit y avoir 5 questions moyens");

        Question[] difficiles = BaseQuestions.getQuestionsParNiveau("difficile");
        assertEquals(5, difficiles.length, "Il doit y avoir 5 questions difficiles");

        Question[] inconnues = BaseQuestions.getQuestionsParNiveau("expert");
        assertEquals(0, inconnues.length, "Niveau inconnu doit retourner un tableau vide");
    }

    @Test
    void testGetAllQuestions() {
        Question[] all = BaseQuestions.getAllQuestions();
        assertEquals(15, all.length, "Le total doit être de 15 questions");
    }
}
