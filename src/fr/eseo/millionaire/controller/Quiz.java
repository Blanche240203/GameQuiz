package fr.eseo.millionaire.controller;

import fr.eseo.millionaire.data.BaseQuestions;
import fr.eseo.millionaire.model.Joueur;
import fr.eseo.millionaire.model.Question;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
    private List<Joueur> joueurs;
    private Scanner scanner;
    private String niveau;

    public Quiz(List<Joueur> joueurs, String niveau) {
        this.joueurs = joueurs;
        this.niveau = niveau;
        this.scanner = new Scanner(System.in);
    }

    // Méthode principale pour lancer le jeu
    public void demarrer() {
        System.out.println("Bienvenue au jeu !");
        boolean continuer = true;
        Random random = new Random();

        while (continuer) {
            for (Joueur joueur : joueurs) {
                System.out.println("\nTour de " + joueur.getPseudo());

                // Récupérer toutes les questions du niveau choisi
                Question[] questionsDuNiveau = BaseQuestions.getQuestionsParNiveau(niveau);

                // Vérifier qu'il y a bien des questions pour ce niveau
                if (questionsDuNiveau.length == 0) {
                    System.out.println("Aucune question disponible pour ce niveau.");
                    continue;
                }

                // Choisir une question aléatoire dans ce tableau
                Question q = questionsDuNiveau[random.nextInt(questionsDuNiveau.length)];

                // Afficher la catégorie et l'énoncé de la question
                System.out.println("Catégorie : " + q.getCategorie());
                System.out.println(q.getEnonce());

                // Afficher les propositions (elles contiennent déjà "A)", "B)", etc. dans ta base)
                for (String proposition : q.getPropositions()) {
                    System.out.println(proposition);
                }

                // Lecture de la réponse utilisateur (A-D ou J pour joker)
                String rep;
                do {
                    System.out.print("Réponds (A-D) ou J pour joker 50/50 : ");
                    rep = scanner.nextLine().toUpperCase();
                } while (!rep.matches("[A-DJ]"));

                if (rep.equals("J")) {
                    System.out.println("Joker 50/50 non encore implémenté !");
                    // Plus tard tu pourras coder le joker ici
                    continue; // Passe au tour suivant
                }

                // Vérifier la réponse (bonneReponse est un char 'A', 'B', ...)
                if (rep.charAt(0) == q.getBonneReponse()) {
                    System.out.println("Bonne réponse !");
                    joueur.ajouterPoints(10); // Ajoute 10 points si bonne réponse
                } else {
                    System.out.println("Mauvaise réponse !");
                }

                // Afficher le score actuel du joueur
                System.out.println("Score actuel de " + joueur.getPseudo() + " : " + joueur.getScore() + " points");
            }

            // Demander si on continue le jeu
            System.out.print("Continuer ? (o/n) : ");
            String c = scanner.nextLine().toLowerCase();
            if (!c.equals("o")) {
                continuer = false;
            }
        }

        // Afficher les scores finaux à la fin de la partie
        System.out.println("\nScores finaux :");
        for (Joueur j : joueurs) {
            System.out.println(j.getPseudo() + " : " + j.getScore() + " points");
        }
    }
}
