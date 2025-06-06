package fr.eseo.millionaire.controller;

import fr.eseo.millionaire.data.BaseQuestions;
import fr.eseo.millionaire.model.Joueur;
import fr.eseo.millionaire.model.Question;

import java.util.List;
import java.util.Scanner;

public class Quiz{

    private List<Joueur> joueurs;
    private Scanner scanner;

    public Quiz(List<Joueur> joueurs) {
        this.joueurs = joueurs; // On assigne correctement le paramètre à l'attribut
        this.scanner = new Scanner(System.in);
    }


    public void demarrer() {
        System.out.println("Bienvenue au jeu !");

        int indexJoueur = 0;
        boolean continuer = true;

        while (continuer) {
            Joueur joueur = joueurs.get(indexJoueur);
            System.out.println("\nTour de " + joueur.getPseudo());

            System.out.print("Choisis un niveau (facile, moyen, difficile) : ");
            String niveau = scanner.nextLine().toLowerCase();

            Question[] questions = BaseQuestions.getQuestionsParNiveau(niveau);
            if (questions.length == 0) {
                System.out.println("Niveau incorrect ou pas de questions dispo. Recommence.");
                continue;
            }

            // On prend juste la première question du niveau pour simplifier
            Question q = questions[0];
            System.out.println("Catégorie : " + q.getCategorie());
            System.out.println(q.getEnonce());

            char lettre = 'A';
            for (String p : q.getPropositions()) {
                System.out.println(lettre + ") " + p);
                lettre++;
            }

            System.out.print("Réponds (A-D) ou J pour joker 50/50 : ");
            String rep = scanner.nextLine().toUpperCase();

            if (rep.equals("J")) {
                if (joueur.utiliserJoker5050()) {
                    System.out.println("Joker 50/50 utilisé !");
                    // Afficher seulement 2 propositions au hasard dont la bonne
                    afficherPropositions5050(q);
                    System.out.print("Réponds (A-D) : ");
                    rep = scanner.nextLine().toUpperCase();
                } else {
                    System.out.println("Plus de joker dispo.");
                    continue; // Reposer la question au même joueur
                }
            }

            if (rep.length() == 1 && rep.charAt(0) == q.getBonneReponse()) {
                System.out.println("Bonne réponse !");
                joueur.ajouterPoints(10);
            } else {
                System.out.println("Mauvaise réponse !");
            }

            // Passer au joueur suivant
            indexJoueur = (indexJoueur + 1) % joueurs.size();

            System.out.print("Continuer ? (o/n) : ");
            String c = scanner.nextLine().toLowerCase();
            if (!c.equals("o")) {
                continuer = false;
            }
        }

        System.out.println("\nScores finaux :");
        for (Joueur j : joueurs) {
            System.out.println(j.getPseudo() + " : " + j.getScore() + " points");
        }
    }

    private void afficherPropositions5050(Question q) {
        char bonneRep = q.getBonneReponse();
        String[] props = q.getPropositions();

        // On garde la bonne réponse
        // Puis on choisit une autre proposition au hasard différente
        int bonneIndex = bonneRep - 'A';
        int autreIndex = bonneIndex;
        while (autreIndex == bonneIndex) {
            autreIndex = (int) (Math.random() * props.length);
        }

        // Afficher seulement ces deux propositions
        System.out.println((char)('A' + bonneIndex) + ") " + props[bonneIndex]);
        System.out.println((char)('A' + autreIndex) + ") " + props[autreIndex]);
    }
}
