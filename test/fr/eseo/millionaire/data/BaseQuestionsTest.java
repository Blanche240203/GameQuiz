package fr.eseo.millionaire.data;

import fr.eseo.millionaire.model.Question;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BaseQuestionsTest {

    @Test
    void testGetAllQuestions() {
        Question[] toutes = BaseQuestions.getAllQuestions();
        assertNotNull(toutes, "Le tableau ne doit pas être nul");
        assertTrue(toutes.length > 0, "Il doit y avoir au moins une question");
    }

    @Test
    void testGetAllQuestionsRetourneUneCopie() {
        Question[] toutes1 = BaseQuestions.getAllQuestions();
        Question[] toutes2 = BaseQuestions.getAllQuestions();
        // Modifier une référence dans la copie ne doit pas modifier l'autre tableau
        assertNotSame(toutes1, toutes2, "Chaque appel doit retourner une copie");
    }

    @Test
    void testGetQuestionsParNiveauFacile() {
        Question[] faciles = BaseQuestions.getQuestionsParNiveau("facile");
        assertTrue(faciles.length > 0, "Doit retourner des questions de niveau facile");
        for (Question q : faciles) {
            assertEquals("facile", q.getNiveau().toLowerCase(), "Le niveau doit être facile");
        }
    }

    @Test
    void testGetQuestionsParNiveauInexistant() {
        Question[] inconnues = BaseQuestions.getQuestionsParNiveau("extrême");
        assertEquals(0, inconnues.length, "Aucune question ne doit correspondre à ce niveau");
    }

    @Test
    void testGetQuestionsParNiveauCaseInsensitive() {
        Question[] moyens = BaseQuestions.getQuestionsParNiveau("MoYeN");
        assertTrue(moyens.length > 0, "Le filtre doit être insensible à la casse");
        for (Question q : moyens) {
            assertEquals("moyen", q.getNiveau().toLowerCase(), "Niveau doit être moyen");
        }
    }
}
