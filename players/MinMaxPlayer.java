package players;

import games.AbstractGame;

public class MinMaxPlayer implements GamePlayer {

	public int chooseMove(AbstractGame game) {
		return coupAJouer(game);
	}
	public int minMax(AbstractGame game, int profondeur, GamePlayer p) {
		if (profondeur ==0 || game.isOver()==true) {
			return game.getHeuristicValue(p);
		}else if(game.p_courant.equals(p)) {
			int valeur = -10000;
			for (int coup : game.validMoves()) {
				AbstractGame situation = game.getCopy();
				situation.jouerUnCoup(coup);
				valeur = Math.max(valeur, minMax(situation,profondeur-1,p));
			}
		}
		else if (game.p_courant.equals(p)==false){
				int valeur = 10000;
				for (int coup: game.validMoves()) {
					AbstractGame situation = game.getCopy();
					situation.jouerUnCoup(coup);
					valeur = Math.min(valeur,minMax(situation,profondeur-1,p));
				}
			}
		return 0;
	}
	public int coupAJouer(AbstractGame situation) {
		int valeur =-10000;
		int valeurmax = -10000;
		int meilleurCoup =-10000;
		AbstractGame situation2;
		GamePlayer p = situation.p_courant;
        for (int coup : situation.validMoves()) {
            situation2 =  situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = minMax(situation2,3,p);
            System.out.println(valeur);
            if (valeur > valeurmax){
                valeurmax = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }
	


	

}
