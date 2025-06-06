package fr.eseo.millionaire.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void testCreationQuestionValide() {
        // Préparation d’un tableau de 4 propositions valides
        String[] propositions = {"Paris", "Lyon", "Marseille", "Nice"};

        // Création d'une question avec des données valides
        // ✅ Correction : ajout du 5e argument "Géographie"
        Question q = new Question("Quelle est la capitale de la France ?", propositions, 'A', "Facile", "Géographie");

        // Vérifie que l'énoncé a bien été enregistré
        assertEquals("Quelle est la capitale de la France ?", q.getEnonce());

        // Vérifie que les propositions sont bien enregistrées et identiques
        assertArrayEquals(propositions, q.getPropositions());

        // Vérifie que la bonne réponse est correctement enregistrée
        assertEquals('A', q.getBonneReponse());

        // Vérifie que le niveau de difficulté est correctement défini
        assertEquals("Facile", q.getNiveau());

        // Vérifie que la catégorie est correctement définie
        assertEquals("Géographie", q.getCategorie());
    }

    @Test
    void testCreationAvecMauvaisNombrePropositions() {
        // Préparation d’un tableau invalide avec seulement 2 propositions
        String[] propositions = {"Oui", "Non"};

        // On s'attend à ce qu'une IllegalArgumentException soit levée
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // ✅ Correction : ajout du 5e argument "Divers"
            new Question("Est-ce vrai ?", propositions, 'A', "Facile", "Divers");
        });

        // Vérifie que le message d’erreur est correct
        assertEquals("Il faut exactement 4 propositions.", exception.getMessage());
    }

    @Test
    void testCreationAvecMauvaiseBonneReponse() {
        // Préparation d’un tableau valide de 4 propositions
        String[] propositions = {"Bleu", "Rouge", "Vert", "Jaune"};

        // Tentative de créer une question avec une mauvaise lettre (ex: 'E')
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            // ✅ Correction : ajout du 5e argument "Couleurs"
            new Question("Quelle couleur n'est pas primaire ?", propositions, 'E', "Moyen", "Couleurs");
        });

        // Vérifie que le message d’erreur est celui attendu
        assertEquals("La bonne réponse doit être A, B, C ou D.", exception.getMessage());
    }

    @Test
    void testToStringContientEnonceEtPropositions() {
        // Création d'une question
        String[] propositions = {"Java", "Python", "C++", "Ruby"};
        // ✅ Correction : ajout du 5e argument "Informatique"
        Question q = new Question("Quel langage est utilisé pour Android ?", propositions, 'A', "Moyen", "Informatique");

        // Conversion en texte de la question avec toString()
        String str = q.toString();

        // Vérifie que l’énoncé apparaît dans la représentation textuelle
        assertTrue(str.contains("Quel langage est utilisé pour Android ?"));

        // Vérifie que chaque proposition apparaît bien
        for (String p : propositions) {
            assertTrue(str.contains(p));
        }

        // Vérifie que la bonne réponse est affichée
        assertTrue(str.contains("Bonne réponse: A"));

        // Vérifie que le niveau et la catégorie sont présents
        assertTrue(str.contains("Moyen"));
        assertTrue(str.contains("Informatique"));
    }
}
