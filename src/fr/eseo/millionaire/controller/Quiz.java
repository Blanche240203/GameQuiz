package fr.eseo.millionaire.controller;

import fr.eseo.millionaire.data.BaseQuestions;
import fr.eseo.millionaire.model.*;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Classe principale qui gère le déroulement du quiz en console.
 * Pose des questions aléatoires selon le niveau choisi et
 * gère les jokers 50/50 et Double Points.
 */
public class Quiz {
    private final List<Joueur> joueurs;
    private final Scanner scanner;
    private final String niveau;

    /**
     * Constructeur du Quiz.
     *
     * @param joueurs Liste des joueurs participants.
     * @param niveau  Niveau de difficulté ("facile", "moyen", "difficile").
     */
    public Quiz(List<Joueur> joueurs, String niveau) {
        this.joueurs = joueurs;
        this.niveau = niveau;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Démarre la partie. Pour chaque joueur, pose des questions aléatoires
     * et applique les jokers selon le choix de l’utilisateur.
     * La partie continue tant que les joueurs répondent "o" à la fin de chaque tour.
     */
    public void demarrer() {
        System.out.println("Bienvenue au jeu !");
        boolean continuer = true;
        Random random = new Random();

        while (continuer) {
            for (Joueur joueur : joueurs) {
                System.out.println("\nTour de " + joueur.getPseudo());

                // Récupère toutes les questions du niveau
                Question[] questionsDuNiveau = BaseQuestions.getQuestionsParNiveau(niveau);
                if (questionsDuNiveau.length == 0) {
                    System.out.println("Aucune question disponible pour ce niveau.");
                    continue;
                }

                // Sélectionne une question aléatoire
                Question q = questionsDuNiveau[random.nextInt(questionsDuNiveau.length)];
                boolean questionRepondue = false;
                boolean doublePointsActif = false;
                List<Character> lettresRestantes = null;

                // Boucle jusqu’à ce que le joueur réponde
                while (!questionRepondue) {
                    // Affiche catégorie et énoncé
                    System.out.println("Catégorie : " + q.getCategorie());
                    System.out.println(q.getEnonce());

                    // ✅ Affichage des choix : réduire ou standard
                    if (lettresRestantes != null) {
                        // Affichage post-50/50, nettoyé des préfixes doublons
                        for (char lettre : lettresRestantes) {
                            int idx = lettre - 'A';
                            String prop = q.getPropositions()[idx];
                            // Si la chaîne commence par "X) ", on enlève ces 3 caractères
                            if (prop.matches("^[A-D]\\)\\s.*")) {
                                prop = prop.substring(3);
                            }
                            System.out.println(lettre + ") " + prop);
                        }
                    } else {
                        // Affichage complet initial (les propositions contiennent déjà A), B), etc.)
                        for (String prop : q.getPropositions()) {
                            System.out.println(prop);
                        }
                    }

                    // Invite de saisie
                    System.out.print("Réponds (A-D), J pour 50/50, P pour Double Points : ");
                    String rep = scanner.nextLine().toUpperCase();

                    switch (rep) {
                        case "J":
                            // Joker 50/50
                            if (joueur.isJoker5050Disponible()) {
                                Joker5050 joker5050 = new Joker5050();
                                joker5050.utiliser(joueur, q);
                                lettresRestantes = joker5050.getLettresRestantes();
                                joueur.utiliserJoker5050();
                            } else {
                                System.out.println(">> Joker 50/50 déjà utilisé.");
                            }
                            // ⚠️ Sans re-afficher la question tout de suite
                            continue;

                        case "P":
                            // Joker Double Points
                            if (joueur.isJokerDoubleDisponible()) {
                                JokerDoublePoint jokerDouble = new JokerDoublePoint();
                                jokerDouble.utiliser(joueur, q);
                                joueur.utiliserJokerDouble();
                                doublePointsActif = true;
                            } else {
                                System.out.println(">> Joker Double Points déjà utilisé.");
                            }
                            continue;

                        case "A": case "B": case "C": case "D":
                            // Réponse classique
                            char reponse = rep.charAt(0);
                            // Vérifie que la réponse est valide (post-50/50 ou pas)
                            if (lettresRestantes == null || lettresRestantes.contains(reponse)) {
                                if (q.estBonneReponse(reponse)) {
                                    System.out.println("Bonne réponse !");
                                    int pts = doublePointsActif ? 20 : 10;
                                    joueur.ajouterPoints(pts);
                                } else {
                                    System.out.println("Mauvaise réponse !");
                                }
                                System.out.println("Score actuel de " + joueur.getPseudo()
                                        + " : " + joueur.getScore() + " points");
                                questionRepondue = true;
                            } else {
                                System.out.println("Cette réponse n’est pas disponible après le 50/50.");
                            }
                            break;

                        default:
                            // Entrée invalide
                            System.out.println("Entrée invalide. Veuillez réessayer.");
                            break;
                    }
                }
            }

            // Fin de tour, demande de continuer
            System.out.print("Continuer ? (o/n) : ");
            String c = scanner.nextLine().toLowerCase();
            if (!c.equals("o")) {
                continuer = false;
            }
        }

        // Affichage des scores finaux
        System.out.println("\nScores finaux :");
        for (Joueur j : joueurs) {
            System.out.println(j.getPseudo() + " : " + j.getScore() + " points");
        }
    }
}
