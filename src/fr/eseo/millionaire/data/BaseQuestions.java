package fr.eseo.millionaire.data;

import fr.eseo.millionaire.model.Question;

public class BaseQuestions {

    private static final Question[] QUESTIONS = {
            // Niveau facile
            new Question("Quelle équipe a gagné la Coupe du Monde 2018 ?",
                    new String[]{"A) France", "B) Croatie", "C) Brésil", "D) Allemagne"}, 'A', "facile", "sport"),
            new Question("Combien y a-t-il de continents ?",
                    new String[]{"A) 5", "B) 6", "C) 7", "D) 8"}, 'C', "facile", "géographie"),
            new Question("Quelle couleur obtient-on en mélangeant le rouge et le blanc ?",
                    new String[]{"A) Rose", "B) Violet", "C) Orange", "D) Marron"}, 'A', "facile", "arts"),
            new Question("Quel est l’animal symbole de la sagesse ?",
                    new String[]{"A) Chouette", "B) Chat", "C) Lion", "D) Aigle"}, 'A', "facile", "culture générale"),
            new Question("Quel est le principal gaz que nous respirons ?",
                    new String[]{"A) Oxygène", "B) Azote", "C) Dioxyde de carbone", "D) Hydrogène"}, 'B', "facile", "science"),

            // Niveau moyen
            new Question("Qui a écrit 'Les Misérables' ?",
                    new String[]{"A) Victor Hugo", "B) Emile Zola", "C) Balzac", "D) Molière"}, 'A', "moyen", "littérature"),
            new Question("Quel pays a la plus grande superficie ?",
                    new String[]{"A) Canada", "B) Chine", "C) Russie", "D) États-Unis"}, 'C', "moyen", "géographie"),
            new Question("Quelle est la formule chimique de l'eau ?",
                    new String[]{"A) CO2", "B) H2O", "C) O2", "D) NaCl"}, 'B', "moyen", "science"),
            new Question("Dans quel sport utilise-t-on un 'drive' ?",
                    new String[]{"A) Tennis", "B) Golf", "C) Football", "D) Rugby"}, 'B', "moyen", "sport"),
            new Question("Quelle œuvre est un tableau de Léonard de Vinci ?",
                    new String[]{"A) La Nuit étoilée", "B) La Joconde", "C) Le Cri", "D) Guernica"}, 'B', "moyen", "arts"),

            // Niveau difficile
            new Question("Quel physicien est connu pour la théorie de la relativité ?",
                    new String[]{"A) Newton", "B) Einstein", "C) Bohr", "D) Tesla"}, 'B', "difficile", "science"),
            new Question("Qui a composé la symphonie n°9 en ré mineur ?",
                    new String[]{"A) Mozart", "B) Beethoven", "C) Bach", "D) Chopin"}, 'B', "difficile", "musique"),
            new Question("Quel pays a été dirigé par Margaret Thatcher ?",
                    new String[]{"A) Royaume-Uni", "B) États-Unis", "C) Canada", "D) Australie"}, 'A', "difficile", "histoire"),
            new Question("Quel est le nombre premier le plus grand connu en 2024 ?",
                    new String[]{"A) 2^82,589,933−1", "B) 2^57,885,161−1", "C) 2^31,622,547−1", "D) 2^43,112,609−1"}, 'A', "difficile", "mathématiques"),
            new Question("Quelle est la capitale de la Mongolie ?",
                    new String[]{"A) Oulan-Bator", "B) Tachkent", "C) Astana", "D) Bichkek"}, 'A', "difficile", "géographie"),
    };

    /**
     * Retourne toutes les questions.
     * @return tableau de toutes les questions.
     */
    public static Question[] getAllQuestions() {
        return QUESTIONS.clone();
    }

    /**
     * Retourne les questions correspondant au niveau donné.
     * @param niveau niveau demandé (facile, moyen, difficile)
     * @return tableau de questions filtrées
     */
    public static Question[] getQuestionsParNiveau(String niveau) {
        return java.util.Arrays.stream(QUESTIONS)
                .filter(q -> q.getNiveau().equalsIgnoreCase(niveau))
                .toArray(Question[]::new);
    }
}
