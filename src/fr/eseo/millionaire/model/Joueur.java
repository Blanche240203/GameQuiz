package fr.eseo.millionaire.model;

/**
 * Représente un joueur dans le jeu du Millionaire.
 * Un joueur possède un pseudo, un score, et deux jokers (50/50 et Double Points).
 */
public class Joueur {
    private final String pseudo;      // Le pseudo du joueur
    private int score;                // Score actuel du joueur
    private boolean joker5050;        // Indique si le joker 50/50 est disponible
    private boolean jokerDouble;      // Indique si le joker double points est disponible

    /**
     * Constructeur de la classe Joueur.
     * Initialise le pseudo et rend les jokers disponibles par défaut.
     * Le score est initialisé à 0.
     *
     * @param pseudo Le pseudo choisi par le joueur
     */
    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.score = 0;
        this.joker5050 = true;
        this.jokerDouble = true;
    }

    /**
     * Retourne le pseudo du joueur.
     *
     * @return le pseudo du joueur
     */
    public String getPseudo() {
        return pseudo;
    }

    /**
     * Retourne le score actuel du joueur.
     *
     * @return le score
     */
    public int getScore() {
        return score;
    }

    /**
     * Indique si le joker 50/50 est encore disponible pour ce joueur.
     *
     * @return true si disponible, false sinon
     */
    public boolean isJoker5050Disponible() {
        return joker5050;
    }

    /**
     * Indique si le joker double points est encore disponible pour ce joueur.
     *
     * @return true si disponible, false sinon
     */
    public boolean isJokerDoubleDisponible() {
        return jokerDouble;
    }

    /**
     * Ajoute (ou retire si points négatifs) un certain nombre de points au score.
     * Le score ne peut jamais devenir négatif.
     *
     * @param points nombre de points à ajouter (ou négatif pour retirer)
     */
    public void ajouterPoints(int points) {
        this.score += points;
        if (this.score < 0) {
            this.score = 0;
        }
    }

    /**
     * Utilise le joker 50/50 si disponible.
     *
     * @return true si le joker a été utilisé, false sinon
     */
    public boolean utiliserJoker5050() {
        if (joker5050) {
            joker5050 = false;
            return true;
        }
        return false;
    }

    /**
     * Utilise le joker double points si disponible.
     *
     * @return true si le joker a été utilisé, false sinon
     */
    public boolean utiliserJokerDouble() {
        if (jokerDouble) {
            jokerDouble = false;
            return true;
        }
        return false;
    }

    /**
     * Réinitialise le joueur :
     * - Score remis à 0
     * - Jokers remis disponibles
     */
    public void reset() {
        this.score = 0;
        this.joker5050 = true;
        this.jokerDouble = true;
    }

    /**
     * Représentation textuelle du joueur.
     *
     * @return une chaîne décrivant le joueur
     */
    @Override
    public String toString() {
        return "Joueur{" +
                "pseudo='" + pseudo + '\'' +
                ", score=" + score +
                ", joker5050=" + joker5050 +
                ", jokerDouble=" + jokerDouble +
                '}';
    }
}
