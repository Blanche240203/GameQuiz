package fr.eseo.millionaire.data;

import fr.eseo.millionaire.model.Joueur;
import org.junit.jupiter.api.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class FichierManagerTest {

    private static final String NOM_FICHIER = "score.txt";

    @BeforeEach
    void cleanUp() throws Exception {
        // Supprime le fichier avant chaque test pour éviter les interférences
        Files.deleteIfExists(Path.of(NOM_FICHIER));
    }

    @AfterAll
    static void cleanAfterAll() throws Exception {
        // Supprime le fichier à la fin de tous les tests
        Files.deleteIfExists(Path.of(NOM_FICHIER));
    }

    @Test
    void testSauvegarderEtChargerJoueur() {
        Joueur joueurOriginal = new Joueur("TestPlayer");
        joueurOriginal.ajouterPoints(1500);
        // Par défaut, les jokers sont disponibles (true)
        assertTrue(joueurOriginal.isJoker5050Disponible());
        assertTrue(joueurOriginal.isJokerDoubleDisponible());

        FichierManager.sauvegarderJoueur(joueurOriginal);
        assertTrue(new File(NOM_FICHIER).exists(), "Le fichier doit être créé après sauvegarde");

        Joueur joueurCharge = FichierManager.chargerJoueur();
        assertNotNull(joueurCharge, "Le joueur chargé ne doit pas être null");
        assertEquals(joueurOriginal.getPseudo(), joueurCharge.getPseudo(), "Pseudo doit correspondre");
        assertEquals(joueurOriginal.getScore(), joueurCharge.getScore(), "Score doit correspondre");
        assertEquals(joueurOriginal.isJoker5050Disponible(), joueurCharge.isJoker5050Disponible(), "Joker 50/50 doit être identique");
        assertEquals(joueurOriginal.isJokerDoubleDisponible(), joueurCharge.isJokerDoubleDisponible(), "Joker Double doit être identique");
    }

    @Test
    void testChargerJoueurFichierAbsent() {
        // Aucun fichier créé, doit retourner null
        Joueur joueur = FichierManager.chargerJoueur();
        assertNull(joueur, "Doit retourner null si fichier absent");
    }

    @Test
    void testChargerJoueurFichierCorrompu() throws Exception {
        // Crée un fichier mal formé
        Files.writeString(Path.of(NOM_FICHIER), "pseudo\nabc\ntrue,false,extra");
        Joueur joueur = FichierManager.chargerJoueur();
        assertNull(joueur, "Doit retourner null si fichier mal formé");
    }

    @Test
    void testSauvegarderJoueurAvecJokersUtilises() {
        Joueur joueur = new Joueur("JokerTest");
        joueur.ajouterPoints(100);
        joueur.utiliserJoker5050();
        joueur.utiliserJokerDouble();

        FichierManager.sauvegarderJoueur(joueur);
        Joueur charge = FichierManager.chargerJoueur();

        assertNotNull(charge);
        assertFalse(charge.isJoker5050Disponible(), "Le joker 50/50 doit être marqué comme utilisé");
        assertFalse(charge.isJokerDoubleDisponible(), "Le joker Double doit être marqué comme utilisé");
    }
}
