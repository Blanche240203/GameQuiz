package fr.eseo.millionaire.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

import fr.eseo.millionaire.model.Joueur;
import fr.eseo.millionaire.controller.Quiz;
import fr.eseo.millionaire.model.Question;
import fr.eseo.millionaire.data.BaseQuestions;

public class Main {
    public static void main(String[] args) {
        BaseQuestions base = new BaseQuestions(); // Création de la base contenant toutes les questions
        Scanner scanner = new Scanner(System.in); // Pour lire les entrées utilisateur

        // Saisie du nom du joueur 1
        System.out.print("Nom du Joueur 1 : ");
        String nom1 = scanner.nextLine();
        Joueur joueur1 = new Joueur(nom1);

        // Saisie du nom du joueur 2
        System.out.print("Nom du Joueur 2 : ");
        String nom2 = scanner.nextLine();
        Joueur joueur2 = new Joueur(nom2);

        // Choix du niveau de difficulté
        System.out.print("Choisissez le niveau (Facile, Moyen, Difficile) : ");
        String niveau = scanner.nextLine();

        // Création et lancement du jeu
        // Utilisation de l'instance 'jeu' pour lancer la partie
        List<Joueur> joueurs = new ArrayList<>();
        joueurs.add(joueur1);
        joueurs.add(joueur2);

        Quiz quiz = new Quiz(joueurs);
        quiz.demarrer();


    }
}
