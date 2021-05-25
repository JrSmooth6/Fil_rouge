package players;

import java.util.HashMap;

import games.AbstractGame;

public class MinMaxPlayer implements GamePlayer {

	@Override
	public int chooseMove(AbstractGame game) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int minMax(AbstractGame game, int profondeur, GamePlayer p) {
		if (profondeur ==0 || game.isOver()==true) {
			return game.getHeuristicValue(p);
		}else if(game.p_courant.equals(p)) {
			int valeur = -10000;
			for (int coup : game.validMoves()) {
				AbstractGame situation = game.getCopy();
				situation.jouerUnCoup(coup);
				valeur = Math.max(valeur, minMax(situation,profondeur-1,situation.p_courant));
			}
			return valeur;
		}
		else if (game.p_courant.equals(p)==false){
				int valeur = 10000;
				for (int coup: game.validMoves()) {
					AbstractGame situation = game.getCopy();
					situation.jouerUnCoup(coup);
					valeur = Math.min(valeur,minMax(situation,profondeur-1,situation.p_courant));
				}
				return valeur;
			}
		return 0;
	}

	@Override
	public float calculEsperance(HashMap<Integer, Float> map) {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
