package fr.eseo.millionaire.data;

import fr.eseo.millionaire.model.Joueur;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import static org.junit.jupiter.api.Assertions.*;

class FichierManagerTest {

    private static final String NOM_FICHIER = "score.txt";

    // Avant chaque test, on supprime le fichier de score s’il existe
    // Cela permet de garantir un environnement propre pour chaque test
    @BeforeEach
    void nettoyerFichier() {
        File fichier = new File(NOM_FICHIER);
        if (fichier.exists()) {
            fichier.delete();
        }
    }

    @Test
    void testSauvegarderEtChargerJoueur() {
        // Création d’un joueur avec un score et un joker utilisé
        Joueur joueur = new Joueur("Megane");
        joueur.ajouterPoints(1500);
        joueur.utiliserJoker5050();

        // Sauvegarde du joueur dans un fichier
        FichierManager.sauvegarderJoueur(joueur);

        // Chargement du joueur à partir du fichier
        Joueur joueurCharge = FichierManager.chargerJoueur();

        // Vérification des données chargées
        assertNotNull(joueurCharge, "Le joueur chargé ne doit pas être null");
        assertEquals("Megane", joueurCharge.getPseudo(), "Le pseudo ne correspond pas");
        assertEquals(1500, joueurCharge.getScore(), "Le score ne correspond pas");
        assertFalse(joueurCharge.isJoker5050Disponible(), "Le joker 50/50 devrait être indisponible");
        assertTrue(joueurCharge.isJokerDoubleDisponible(), "Le joker Double devrait être disponible");
    }

    @Test
    void testChargerFichierInexistant() {
        // Aucun fichier n’a été créé → charger doit renvoyer null
        Joueur joueurCharge = FichierManager.chargerJoueur();
        assertNull(joueurCharge, "Le joueur chargé doit être null si le fichier n’existe pas");
    }

    @Test
    void testFichierCorrompu() throws Exception {
        // Création d’un fichier corrompu : score non entier
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(NOM_FICHIER))) {
            writer.write("JoueurX\nNotAnInteger\nTrue,False");
        }

        // Le chargement doit échouer proprement et renvoyer null
        Joueur joueurCharge = FichierManager.chargerJoueur();
        assertNull(joueurCharge, "Le joueur chargé doit être null si le fichier est mal formé");
    }
}

