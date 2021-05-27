package players;
import games.*;


//Classe du joueur negaMax, le joueur automatique et relativement optimisé
public class NegaMax implements GamePlayer{
	int total;
    public NegaMax(int total){
    	this.total = total;
    }

    //Méthode qui retourne le "nom" du joueur negaMax(Nombre aléatoire)
    public String toString(){
        return "Joueur optimisé #" + this.hashCode();
    }

    //Méthode qui retourne le meilleur coup possible en vue de la situation actuelle selon l'algorithme negamax
    public int chooseMove(AbstractGame game){
        return negaMax(game, game.p_courant);
    }

    //Méthode qui evalue la situation
    public int evaluer(AbstractGame situation, GamePlayer p_courant){
        if (situation.getWinner() == p_courant){
            return +1;
        }
        if (situation.isOver() && situation.getWinner() == null){
            return 0;
        }
        if (situation.isOver() && situation.getWinner() != p_courant){
            return -1;
        }
        else{ //la situation n'est pas finale
            int res = -10;
            AbstractGame2JSansHasard situation2;
            for (int coup : situation.validMoves()) {
                situation2 = (AbstractGame2JSansHasard) situation.getCopy();
                situation2.jouerUnCoup(coup);
                res = Math.max(res, -evaluer(situation2, situation2.p_courant));
            }
            return res;
        }
    }

    //Algorithme negamax qui retourne en fonction de l'evaluation le meilleur coup possible
    public int negaMax(AbstractGame situation, GamePlayer p_courant){
        int valeur = -10;
        int meilleureValeur = -10;
        int meilleurCoup = -10;
        AbstractGame situation2;

        for (int coup : situation.validMoves()) {
            situation2 =  situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = -evaluer(situation2, situation2.p_courant);
            if (valeur > meilleureValeur){
                meilleureValeur = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }






}
