
package fr.eseo.millionaire.model;

import fr.eseo.millionaire.model.Joueur;
import fr.eseo.millionaire.model.Question;
public class Joker {

    /**
     * Classe abstraite représentant un Joker utilisable par un joueur.
     */
        protected boolean disponible = true; // Un joker est utilisable une seule fois

    /**
     * Applique l'effet du joker au joueur et/ou à la question.
     *
     * @param joueur   Le joueur qui utilise le joker
     * @param question La question en cours
     */
    public void utiliser(Joueur joueur, Question question) {

    }

    /**
         * Indique si le joker est encore disponible.
         * @return true si disponible, false sinon
         */
        public boolean isDisponible() {
            return disponible;
        }

        /**
         * Marque le joker comme utilisé.
         */
        protected void desactiver() {
            this.disponible = false;
        }


}
