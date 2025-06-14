package fr.eseo.millionaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Classe pour le joker 50/50 qui élimine deux mauvaises réponses.
 */
public class Joker5050 extends Joker {

    // Liste des lettres restantes après suppression de deux mauvaises réponses
    private List<Character> lettresRestantes;

    /**
     * Utilise le joker 50/50 si disponible :
     * garde la bonne réponse et une seule mauvaise réponse aléatoire.
     * N'affiche plus directement les propositions (affichage fait dans Quiz).
     *
     * @param joueur   Le joueur qui utilise le joker
     * @param question La question concernée
     */
    @Override
    public void utiliser(Joueur joueur, Question question) {
        if (isDisponible()) {
            // Message d’activation du joker
            System.out.println(">> Joker 50/50 activé !");

            char bonneReponse = question.getBonneReponse();
            List<Character> mauvaisesReponses = new ArrayList<>();

            // Collecte toutes les mauvaises réponses possibles
            for (char c : new char[]{'A', 'B', 'C', 'D'}) {
                if (c != bonneReponse) {
                    mauvaisesReponses.add(c);
                }
            }

            // Mélange pour choisir une mauvaise réponse au hasard
            Collections.shuffle(mauvaisesReponses);
            char mauvaiseRestante = mauvaisesReponses.get(0);

            // Stocke les deux lettres restantes (bonne + mauvaise)
            lettresRestantes = new ArrayList<>();
            lettresRestantes.add(bonneReponse);
            lettresRestantes.add(mauvaiseRestante);

            // Désactive le joker pour ce joueur
            desactiver();
        } else {
            System.out.println(">> Ce joker a déjà été utilisé.");
        }
    }

    /**
     * Retourne la liste des lettres restantes (après 50/50).
     *
     * @return Liste de caractères des propositions restantes
     */
    public List<Character> getLettresRestantes() {
        return lettresRestantes;
    }
}
