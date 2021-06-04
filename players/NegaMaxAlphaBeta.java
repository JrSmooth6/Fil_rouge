package players;

import games.AbstractGame;
import games.AbstractGame2JSansHasard;

public class NegaMaxAlphaBeta implements GamePlayer {

	public int chooseMove(AbstractGame game) {
		return choixCoup(game,game.p_courant);
	}
	   public String toString(){
	        return "Joueur optimisé #" + this.hashCode();
	    }
    public int negaMax(AbstractGame situation, GamePlayer p,int profondeur,int alpha,int beta){
    	if(situation.isOver()==true || profondeur ==0) {
    		return situation.getHeuristicValue(p);
    	}
        else{ //la situation n'est pas finale
            int res = -10;
            AbstractGame2JSansHasard situation2;
            for (int coup : situation.validMoves()) {
                situation2 = (AbstractGame2JSansHasard) situation.getCopy();
                situation2.jouerUnCoup(coup);
                res = Math.max(res, -negaMax(situation2, situation2.p_courant,profondeur-1,alpha,beta));
                if(alpha>=beta) {
                	return res;
                }
            }
            return res;
        }
    }
    public int choixCoup(AbstractGame situation, GamePlayer p_courant){
        int valeur = -10;
        int meilleureValeur = -10;
        int meilleurCoup = -10;
        AbstractGame situation2;

        for (int coup : situation.validMoves()) {
            situation2 =  situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = -negaMax(situation2, situation2.p_courant,3,-1000,1000);
            if (valeur > meilleureValeur){
                meilleureValeur = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }

}
