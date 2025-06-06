package fr.eseo.millionaire.data;

import fr.eseo.millionaire.model.Joueur;

import java.io.*;

public class FichierManager {

    private static final String NOM_FICHIER = "score.txt"; // Nom du fichier de sauvegarde

    // Enregistre les informations du joueur dans un fichier
    public static void sauvegarderJoueur(Joueur joueur) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOM_FICHIER))) {
            // On écrit le pseudo, le score et les jokers restants sur 3 lignes
            writer.write(joueur.getPseudo());
            writer.newLine();
            writer.write(String.valueOf(joueur.getScore()));
            writer.newLine();
            writer.write(joueur.isJoker5050Disponible() + "," + joueur.isJokerDoubleDisponible());
        } catch (IOException e) {
            System.err.println("Erreur pendant la sauvegarde : " + e.getMessage());
        }
    }

    // Lit les informations depuis le fichier et crée un objet Joueur
    public static Joueur chargerJoueur() {
        try (BufferedReader reader = new BufferedReader(new FileReader(NOM_FICHIER))) {
            String pseudo = reader.readLine(); // ligne 1 : pseudo
            int score = Integer.parseInt(reader.readLine()); // ligne 2 : score
            String[] jokers = reader.readLine().split(","); // ligne 3 : jokers

            Joueur joueur = new Joueur(pseudo);
            joueur.ajouterPoints(score);

            // Si le joker est false, c’est qu’il a été utilisé → on l’utilise pour désactiver
            if (!Boolean.parseBoolean(jokers[0])) {
                joueur.utiliserJoker5050();
            }
            if (!Boolean.parseBoolean(jokers[1])) {
                joueur.utiliserJokerDouble();
            }

            return joueur;

        } catch (IOException | NumberFormatException | NullPointerException e) {
            // Si le fichier n'existe pas ou qu'il est mal formaté, on retourne null
            System.err.println("Erreur pendant le chargement : " + e.getMessage());
            return null;
        }
    }
}

