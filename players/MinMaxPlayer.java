package players;

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
		}if(game.p_courant==p) {
			int valeur = -10000;
			for (int coup : game.validMoves()) {
				AbstractGame situation = game.getCopy();
				situation.jouerUnCoup(coup);
				valeur = Math.max(valeur, minMax(situation,profondeur-1,p));
				return valeur;
			}
		}
		else {
				int valeur = 10000;
				for (int coup: game.validMoves()) {
					AbstractGame situation = game.getCopy();
					situation.jouerUnCoup(coup);
					valeur = Math.min(valeur,minMax(situation,profondeur-1,p));
					return valeur;
				}
			}
		return 0;
	}
	

}
