package fr.eseo.millionaire.controller;
import fr.eseo.millionaire.data.BaseQuestions;
import fr.eseo.millionaire.model.Joueur;
import fr.eseo.millionaire.model.Question;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz {

    private List<Joueur> joueurs;       // Liste des joueurs participant au quiz
    private Scanner scanner;            // Scanner pour lire les entrées utilisateur
    private List<Question> questions;   // Liste des questions correspondant au niveau choisi
    private int indexQuestion;          // Index pour suivre la question courante

    // Constructeur Quiz, prend la liste des joueurs et le niveau de difficulté choisi
    public Quiz(List<Joueur> joueurs, String niveau) {
        this.joueurs = joueurs;                   // Initialisation de la liste des joueurs
        this.scanner = new Scanner(System.in);   // Initialisation du scanner pour la saisie utilisateur
        this.questions = new ArrayList<>();      // Création d'une liste vide pour stocker les questions du niveau
        this.indexQuestion = 0;                   // Initialisation de l'index des questions à 0

        // Récupération des questions correspondant au niveau choisi (ex: "facile", "moyen", "difficile")
        Question[] questionsNiveau = BaseQuestions.getQuestionsParNiveau(niveau.toLowerCase());

        // Ajout de toutes ces questions dans la liste locale 'questions' pour manipulation facile
        for (Question q : questionsNiveau) {
            questions.add(q);
        }
    }

    // Méthode principale qui lance le jeu et gère le déroulement des tours
    public void demarrer() {
        System.out.println("Bienvenue au jeu !");

        boolean continuer = true;  // Booléen pour savoir si la partie continue

        // Boucle principale du jeu, s'arrête si on n'a plus de questions ou si les joueurs veulent arrêter
        while (continuer && indexQuestion < questions.size()) {

            // Chaque joueur joue à son tour
            for (Joueur joueur : joueurs) {

                // Récupération de la question courante à poser au joueur
                Question q = questions.get(indexQuestion);

                System.out.println("\nTour de " + joueur.getPseudo());
                System.out.println("Catégorie : " + q.getCategorie());
                System.out.println(q.getEnonce());

                // Affichage des propositions de réponses, A), B), C), D)
                char lettre = 'A';
                for (String p : q.getPropositions()) {
                    System.out.println(lettre + ") " + p);
                    lettre++;
                }

                // Lecture de la réponse donnée par le joueur (lettre ou joker)
                System.out.print("Réponds (A-D) ou J pour joker 50/50 : ");
                String rep = scanner.nextLine().toUpperCase();

                // Gestion du joker 50/50
                if (rep.equals("J")) {
                    // Vérifie si le joueur peut encore utiliser un joker
                    if (joueur.utiliserJoker5050()) {
                        System.out.println("Joker 50/50 utilisé !");
                        afficherPropositions5050(q);  // Affiche 2 propositions dont la bonne
                        System.out.print("Réponds (A-D) : ");
                        rep = scanner.nextLine().toUpperCase();  // Nouvelle réponse après joker
                    } else {
                        System.out.println("Plus de joker dispo.");
                        // Pas de repos de la question ici, on passe au joueur suivant
                    }
                }

                // Vérifie si la réponse est correcte (compare à la bonne réponse de la question)
                if (rep.length() == 1 && rep.charAt(0) == q.getBonneReponse()) {
                    System.out.println("Bonne réponse !");
                    joueur.ajouterPoints(10);  // Ajoute 10 points au joueur
                } else {
                    System.out.println("Mauvaise réponse !");
                }

                // Affiche le score actuel du joueur juste après sa réponse
                System.out.println("Score actuel de " + joueur.getPseudo() + " : " + joueur.getScore() + " points");

                // Passe à la question suivante pour le joueur suivant
                indexQuestion++;

                // Si plus de questions disponibles, on arrête le jeu
                if (indexQuestion >= questions.size()) {
                    System.out.println("\nPlus de questions disponibles !");
                    continuer = false;
                    break;  // Sort de la boucle des joueurs
                }
            }

            // Si la partie est terminée, on sort aussi de la boucle principale
            if (!continuer) {
                break;
            }

            // Demande aux joueurs s'ils veulent continuer la partie
            System.out.print("Continuer ? (o/n) : ");
            String c = scanner.nextLine().toLowerCase();
            if (!c.equals("o")) {
                continuer = false;
            }
        }

        // Affichage des scores finaux quand la partie est terminée
        System.out.println("\nScores finaux :");
        for (Joueur j : joueurs) {
            System.out.println(j.getPseudo() + " : " + j.getScore() + " points");
        }
    }

    // Méthode pour afficher seulement 2 propositions quand le joueur utilise le joker 50/50
    private void afficherPropositions5050(Question q) {
        char bonneRep = q.getBonneReponse();      // Lettre de la bonne réponse (ex: 'A')
        String[] props = q.getPropositions();     // Tableau des propositions

        int bonneIndex = bonneRep - 'A';          // Convertit la lettre en index (0-3)
        int autreIndex = bonneIndex;

        // Cherche un index différent de la bonne réponse pour afficher la seconde proposition
        while (autreIndex == bonneIndex) {
            autreIndex = (int) (Math.random() * props.length);
        }

        // Affiche uniquement ces deux propositions
        System.out.println((char)('A' + bonneIndex) + ") " + props[bonneIndex]);
        System.out.println((char)('A' + autreIndex) + ") " + props[autreIndex]);
    }

    // Méthode main pour lancer le jeu depuis la console
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Demande du nombre de joueurs
        System.out.print("Combien de joueurs ? ");
        int nbJoueurs = Integer.parseInt(scanner.nextLine());

        List<Joueur> joueurs = new ArrayList<>();
        // Demande du nom pour chaque joueur
        for (int i = 1; i <= nbJoueurs; i++) {
            System.out.print("Nom du Joueur " + i + " : ");
            String nom = scanner.nextLine();
            joueurs.add(new Joueur(nom));
        }

        // Demande du niveau de difficulté (une seule fois pour tous)
        System.out.print("Choisissez le niveau (facile, moyen, difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        // Création du quiz avec la liste de joueurs et le niveau choisi
        Quiz jeu = new Quiz(joueurs, niveau);
        // Démarrage du jeu
        jeu.demarrer();
    }
}
