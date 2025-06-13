package fr.eseo.millionaire.controller;

import fr.eseo.millionaire.data.BaseQuestions;
import fr.eseo.millionaire.model.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Quiz {
    private final List<Joueur> joueurs;
    private final Scanner scanner;
    private final String niveau;

    public Quiz(List<Joueur> joueurs, String niveau) {
        this.joueurs = joueurs;
        this.niveau = niveau;
        this.scanner = new Scanner(System.in);
    }
    public void demarrer() {
        System.out.println("Bienvenue au jeu !");
        boolean continuer = true;
        Random random = new Random();

        while (continuer) {
            for (Joueur joueur : joueurs) {
                System.out.println("\nTour de " + joueur.getPseudo());

                Question[] questionsDuNiveau = BaseQuestions.getQuestionsParNiveau(niveau);
                if (questionsDuNiveau.length == 0) {
                    System.out.println("Aucune question disponible pour ce niveau.");
                    continue;
                }

                Question q = questionsDuNiveau[random.nextInt(questionsDuNiveau.length)];
                boolean questionRepondue = false;
                boolean doublePointsActif = false;
                List<Character> lettresRestantes = null;

                while (!questionRepondue) {
                    System.out.println("Catégorie : " + q.getCategorie());
                    System.out.println(q.getEnonce());

                    // Affichage des propositions selon joker 50/50
                    if (lettresRestantes != null) {
                        for (char lettre : lettresRestantes) {
                            int index = lettre - 'A';
                            System.out.println(lettre + ") " + q.getPropositions()[index]);
                        }
                    } else {
                        for (String proposition : q.getPropositions()) {
                            System.out.println(proposition);
                        }
                    }

                    System.out.print("Réponds (A-D), J pour 50/50, P pour Double Points : ");
                    String rep = scanner.nextLine().toUpperCase();

                    switch (rep) {
                        case "J":
                            if (joueur.isJoker5050Disponible()) {
                                Joker5050 joker5050 = new Joker5050();
                                joker5050.utiliser(joueur, q);
                                lettresRestantes = joker5050.getLettresRestantes();
                                joueur.utiliserJoker5050();
                            } else {
                                System.out.println(">> Joker 50/50 déjà utilisé.");
                            }
                            break;

                        case "P":
                            if (joueur.isJokerDoubleDisponible()) {
                                JokerDoublePoint jokerDouble = new JokerDoublePoint();
                                jokerDouble.utiliser(joueur, q);
                                joueur.utiliserJokerDouble();
                                doublePointsActif = true;
                            } else {
                                System.out.println(">> Joker Double Points déjà utilisé.");
                            }
                            break;

                        case "A":
                        case "B":
                        case "C":
                        case "D":
                            char reponse = rep.charAt(0);
                            if (lettresRestantes == null || lettresRestantes.contains(reponse)) {
                                if (q.estBonneReponse(reponse)) {
                                    System.out.println("Bonne réponse !");
                                    int points = doublePointsActif ? 20 : 10;
                                    joueur.ajouterPoints(points);
                                } else {
                                    System.out.println("Mauvaise réponse !");
                                }
                                System.out.println("Score actuel de " + joueur.getPseudo() + " : " + joueur.getScore() + " points");
                                questionRepondue = true;
                            } else {
                                System.out.println("Cette réponse n’est pas disponible après le 50/50.");
                            }
                            break;

                        default:
                            System.out.println("Entrée invalide. Veuillez réessayer.");
                            break;
                    }
                }
            }

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

}
