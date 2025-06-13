package fr.eseo.millionaire.model;
import fr.eseo.millionaire.model.Joueur;
import fr.eseo.millionaire.model.Question;
    /**
     * Joker permettant de doubler les points sur la question courante.
     */
    public class JokerDoublePoint extends Joker {

        /**
         * Applique l'effet du joker : le joueur gagnera double points si la réponse est bonne.
         * Ici, on ne modifie pas la question, on peut indiquer au joueur que les points seront doublés.
         * @param joueur Le joueur qui utilise le joker
         * @param question La question en cours (non modifiée ici)
         */
        @Override
        public void utiliser(Joueur joueur, Question question) {
            if (isDisponible()) {
                System.out.println(">> Joker Double Points activé ! Vous gagnerez 20 points au lieu de 10 si vous répondez juste.");
                desactiver();
            } else {
                System.out.println(">> Ce joker a déjà été utilisé.");
            }
        }
    }


