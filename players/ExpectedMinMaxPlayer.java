package players;
import games.*;
public class ExpectedMinMaxPlayer implements GamePlayer{
	public ExpectedMinMaxPlayer() {
		
	}
	   public String toString(){
	        return "Joueur optimisé #" + this.hashCode();
	    }
	   public float expectiMinMax(AbstractGame game, int profondeur) {
		   if(game.isOver()==true || profondeur == 0) {
			   return game.getHeuristicValue(game.p_courant);
		   }else if(game.p_courant==game.p1) {
			   float alpha = 10000;
			   for(int coup : game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha = Math.min(alpha, expectiMinMax(situation2,profondeur-1));
				   
			   }
			   return alpha;
		   }else if(game.p_courant == game.p2) {
			   float alpha = -10000;
			   for (int coup:game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha = Math.max(alpha,expectiMinMax(situation2, profondeur -1));
			   }
			   return alpha;
		   }else{
			   float alpha = 0;
			   for(int coup : game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha += (situation2.getEsperance(situation2.getProba(situation2.player_precedent)))*this.expectiMinMax(situation2, profondeur-1);
			   }
			   return alpha;
		   }
	   }
	
	public int chooseMove(AbstractGame game) {
		return coupAJouer(game);
	}
	public int coupAJouer(AbstractGame game) {
		return 1;
	}
}
