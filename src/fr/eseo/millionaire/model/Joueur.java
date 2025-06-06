package fr.eseo.millionaire.model;

public class Joueur {
    private final String pseudo;      // Le pseudo du joueur
    private int score;          // Score actuel
    private boolean joker5050;  // Joker 50/50 dispo ?
    private boolean jokerDouble; // Joker double points dispo ?

    // Constructeur avec pseudo
    // Constructeur avec pseudo et bonus (optionnel selon le diagramme UML)
    public Joueur(String pseudo) {
        this.pseudo = pseudo;
        this.joker5050 = true;
        this.jokerDouble = true;//
    }


    // Getters classiques
    public String getPseudo() {
        return pseudo;
    }

    public int getScore() {
        return score;
    }

    public boolean isJoker5050Disponible() {
        return joker5050;
    }

    public boolean isJokerDoubleDisponible() {
        return jokerDouble;
    }

    /**
     * Ajoute ou retire des points au score du joueur.
     * @param points Le nombre de points à ajouter (ou négatif pour retirer).
     */
    public void ajouterPoints(int points) {
        this.score += points;
        if (this.score < 0) {
            this.score = 0; // On évite le score négatif
        }
    }

    // Utilisation du joker 50/50 si dispo, retourne vrai si utilisé
    public boolean utiliserJoker5050() {
        if (joker5050) {
            joker5050 = false;
            return true;
        }
        return false;
    }

    // Utilisation du joker double points si dispo, retourne vrai si utilisé
    public boolean utiliserJokerDouble() {
        if (jokerDouble) {
            jokerDouble = false;
            return true;
        }
        return false;
    }

    // Remise à zéro du joueur : score à 0, jokers réinitialisés
    public void reset() {
        this.score = 0;
        this.joker5050 = true;
        this.jokerDouble = true;
    }

    // Pour affichage simple
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
