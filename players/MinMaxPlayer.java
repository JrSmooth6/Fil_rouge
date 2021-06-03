package players;

import games.AbstractGame;
import games.AbstractGame;

public class MinMaxPlayer implements GamePlayer {

	public int chooseMove(AbstractGame game) {
		return coupAJouer((AbstractGame)game,game.p_courant);
	}
    public String toString(){
        return "Joueur optimisé #" + this.hashCode();
    }
	public int minMax(AbstractGame game,GamePlayer p, boolean isMax,int profondeur) {
		if (game.isOver()==true|| profondeur ==0) {
			return game.getHeuristicValue(p);
        }else {
        	if(isMax) {
        		int valeur = -10000;
        		AbstractGame situation;
        		for (int coup : game.validMoves()) {
        			situation = (AbstractGame) game.getCopy();
        			situation.jouerUnCoup(coup);
        			valeur = Math.max(valeur, minMax(situation,p,false,profondeur-1));
        		}
        		return valeur;
        	}else{
				int valeur = 10000;
				AbstractGame situation;
				for (int coup: game.validMoves()) {
					situation = (AbstractGame) game.getCopy();
					situation.jouerUnCoup(coup);
					valeur = Math.min(valeur,minMax(situation,p,true,profondeur-1));
				}
				return valeur;
			}
		}
	}
	public int coupAJouer(AbstractGame situation,GamePlayer p_courant) {
		int valeur =Integer.MIN_VALUE;
		int valeurmax = Integer.MIN_VALUE;
		int meilleurCoup =Integer.MIN_VALUE;
		AbstractGame situation2;
        for (int coup : situation.validMoves()) {
            situation2 =  (AbstractGame) situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = minMax(situation2,p_courant,false,4);
            if (valeur > valeurmax){
                valeurmax = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }
	


	

}
