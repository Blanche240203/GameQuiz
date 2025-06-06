package fr.eseo.millionaire.app;

import fr.eseo.millionaire.controller.Quiz;
import fr.eseo.millionaire.model.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Combien de joueurs ? ");
        int nbJoueurs = Integer.parseInt(scanner.nextLine());

        List<Joueur> joueurs = new ArrayList<>();
        for (int i = 1; i <= nbJoueurs; i++) {
            System.out.print("Nom du Joueur " + i + " : ");
            String nom = scanner.nextLine();
            joueurs.add(new Joueur(nom));
        }

        System.out.print("Choisissez le niveau (facile, moyen, difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        Quiz quiz = new Quiz(joueurs, niveau);
        quiz.demarrer();
    }
}
