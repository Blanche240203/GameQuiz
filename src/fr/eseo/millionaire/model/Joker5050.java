package fr.eseo.millionaire.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Joker5050 extends Joker {

    private List<Character> lettresRestantes;

    @Override
    public void utiliser(Joueur joueur, Question question) {
        if (isDisponible()) {
            System.out.println(">> Joker 50/50 activé ! Voici 2 propositions possibles :");

            char bonneReponse = question.getBonneReponse();
            List<Character> mauvaisesReponses = new ArrayList<>();

            for (char c : new char[]{'A', 'B', 'C', 'D'}) {
                if (c != bonneReponse) {
                    mauvaisesReponses.add(c);
                }
            }

            // Mélanger les mauvaises réponses et en garder une seule
            Collections.shuffle(mauvaisesReponses);
            char mauvaiseRestante = mauvaisesReponses.get(0);

            // Stocker les deux lettres restantes
            lettresRestantes = new ArrayList<>();
            lettresRestantes.add(bonneReponse);
            lettresRestantes.add(mauvaiseRestante);

            // Afficher les deux propositions restantes
            for (char lettre : lettresRestantes) {
                int index = lettre - 'A';
                System.out.println(lettre + ") " + question.getPropositions()[index]);
            }

            desactiver();
        } else {
            System.out.println(">> Ce joker a déjà été utilisé.");
        }
    }

    public List<Character> getLettresRestantes() {
        return lettresRestantes;
    }
}
