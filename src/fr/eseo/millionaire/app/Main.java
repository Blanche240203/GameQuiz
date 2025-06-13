package fr.eseo.millionaire.app;

import fr.eseo.millionaire.controller.Quiz;
import fr.eseo.millionaire.data.FichierManager;
import fr.eseo.millionaire.model.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Joueur> joueurs = new ArrayList<>();

        System.out.print("Souhaitez-vous charger un joueur existant ? (o/n) : ");
        String choix = scanner.nextLine().toLowerCase();

        if (choix.equals("o")) {
            Joueur joueurCharge = FichierManager.chargerJoueur();
            if (joueurCharge != null) {
                System.out.println("Joueur chargé : " + joueurCharge.getPseudo());
                joueurs.add(joueurCharge);
            } else {
                System.out.println("Aucun joueur chargé. Créons un nouveau joueur.");
            }
        }

        if (joueurs.isEmpty()) {
            System.out.print("Combien de joueurs ? ");
            int nbJoueurs = Integer.parseInt(scanner.nextLine());

            for (int i = 1; i <= nbJoueurs; i++) {
                System.out.print("Nom du Joueur " + i + " : ");
                String nom = scanner.nextLine();
                joueurs.add(new Joueur(nom));
            }
        }

        System.out.print("Choisissez le niveau (facile, moyen, difficile) : ");
        String niveau = scanner.nextLine().toLowerCase();

        Quiz quiz = new Quiz(joueurs, niveau);
        quiz.demarrer();

        // Sauvegarde du premier joueur (si un seul joueur)
        if (joueurs.size() == 1) {
            FichierManager.sauvegarderJoueur(joueurs.get(0));
            System.out.println("Joueur sauvegardé !");
        } else {
            System.out.println("Sauvegarde automatique désactivée pour les parties multijoueurs.");
        }
    }
}
