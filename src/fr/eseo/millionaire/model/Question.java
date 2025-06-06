package fr.eseo.millionaire.model;

/**
 * Représente une question du quiz avec son énoncé, ses propositions,
 * la bonne réponse, sa difficulté et sa catégorie.
 */
public class Question {

    private final String enonce;         // Texte de la question
    private final String[] propositions; // Réponses possibles (4 au total)
    private final char bonneReponse;     // Lettre de la bonne réponse ('A', 'B', 'C' ou 'D')
    private final String niveau;         // Difficulté de la question (ex. : Facile, Moyen, Difficile)
    private final String categorie;      // Thème ou sujet de la question (ex. : Histoire, Maths)

    /**
     * Construit une question avec l'ensemble de ses éléments.
     *
     * @param enonce       L'énoncé de la question
     * @param propositions Tableau de 4 réponses possibles
     * @param bonneReponse Lettre correspondant à la bonne réponse ('A', 'B', 'C' ou 'D')
     * @param niveau       Niveau de difficulté de la question
     * @throws IllegalArgumentException si les propositions ne sont pas exactement 4
     *                                  ou si la bonne réponse n'est pas une lettre valide
     */
    public Question(String enonce, String[] propositions, char bonneReponse, String niveau,String categorie) {
        if (propositions == null || propositions.length != 4) {
            throw new IllegalArgumentException("Il faut exactement 4 propositions.");
        }
        if ("ABCD".indexOf(bonneReponse) == -1) {
            throw new IllegalArgumentException("La bonne réponse doit être A, B, C ou D.");
        }
        this.enonce = enonce;
        this.propositions = propositions;
        this.bonneReponse = bonneReponse;
        this.niveau = niveau;
        this.categorie = categorie;
    }

    /**
     * @return L'énoncé de la question
     */
    public String getEnonce() {
        return enonce;
    }

    /**
     * @return Un tableau de 4 propositions de réponse
     */
    public String[] getPropositions() {
        return propositions.clone(); // Pour éviter les modifications extérieures
    }

    /**
     * @return La lettre correspondant à la bonne réponse ('A', 'B', 'C' ou 'D')
     */
    public char getBonneReponse() {
        return bonneReponse;
    }

    /**
     * Vérifie si la réponse donnée par l'utilisateur correspond à la bonne réponse.
     *
     * @param reponse La lettre de réponse saisie par l'utilisateur (A, B, C ou D)
     * @return true si la réponse est correcte, false sinon
     */
    public boolean estBonneReponse(char reponse) {
        return Character.toUpperCase(reponse) == bonneReponse;
    }


    /**
     * @return Le niveau de difficulté de la question
     */
    public String getNiveau() {
        return niveau;
    }

    /**
     * @return La catégorie ou le thème de la question
     */
    public String getCategorie() {
        return categorie;
    }

    /**
     * Affiche la question avec ses 4 propositions dans la console.
     */
    public void afficherQuestion() {
        System.out.println("Question : " + enonce);
        char lettre = 'A';
        for (String p : propositions) {
            System.out.println(lettre + ") " + p);
            lettre++;
        }
    }

    /**
     * Fournit une représentation textuelle de la question, pour le débogage ou l'affichage complet.
     *
     * @return Une chaîne décrivant entièrement la question et ses détails
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Question: ").append(enonce).append("\n");
        char lettre = 'A';
        for (String p : propositions) {
            sb.append(lettre).append(") ").append(p).append("\n");
            lettre++;
        }
        sb.append("Bonne réponse: ").append(bonneReponse).append("\n");
        sb.append("Niveau: ").append(niveau).append(", Catégorie: ").append(categorie);
        return sb.toString();
    }
}
