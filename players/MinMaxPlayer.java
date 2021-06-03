package players;

import games.AbstractGame;
import games.AbstractGame2JSansHasard;

public class MinMaxPlayer implements GamePlayer {

	public int chooseMove(AbstractGame game) {
		return coupAJouer((AbstractGame2JSansHasard)game,game.p_courant);
	}
    public String toString(){
        return "Joueur optimisé #" + this.hashCode();
    }
	public int minMax(AbstractGame2JSansHasard game,GamePlayer p, boolean isMax,int profondeur) {
		if (game.isOver()==true|| profondeur ==0) {
			return game.getHeuristicValue(p);
        }else {
        	if(isMax) {
        		int valeur = -10000;
        		AbstractGame2JSansHasard situation;
        		for (int coup : game.validMoves()) {
        			situation = (AbstractGame2JSansHasard) game.getCopy();
        			situation.jouerUnCoup(coup);
        			valeur = Math.max(valeur, minMax(situation,p,false,profondeur-1));
        		}
        		return valeur;
        	}else{
				int valeur = 10000;
				AbstractGame2JSansHasard situation;
				for (int coup: game.validMoves()) {
					situation = (AbstractGame2JSansHasard) game.getCopy();
					situation.jouerUnCoup(coup);
					valeur = Math.min(valeur,minMax(situation,p,true,profondeur-1));
				}
				return valeur;
			}
		}
	}
	public int coupAJouer(AbstractGame2JSansHasard situation,GamePlayer p_courant) {
		int valeur =Integer.MIN_VALUE;
		int valeurmax = Integer.MIN_VALUE;
		int meilleurCoup =Integer.MIN_VALUE;
		AbstractGame2JSansHasard situation2;
        for (int coup : situation.validMoves()) {
            situation2 =  (AbstractGame2JSansHasard) situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = minMax(situation2,p_courant,false,3);
            if (valeur > valeurmax){
                valeurmax = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }
	


	

}
