package players;
import games.*;
public class ExpectedMinMaxPlayer {
	public ExpectedMinMaxPlayer() {
		
	}
	   public String toString(){
	        return "Joueur optimisé #" + this.hashCode();
	    }
	   public int expectiMinMax(AbstractGame game, int profondeur) {
		   if(game.isOver()==true || profondeur == 0) {
			   return 0;
		   }else if(game.p_courant==game.p1) {
			   int alpha = 10000;
			   for(int coup : game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha = Math.min(alpha, expectiMinMax(situation2,profondeur-1));
				   
			   }
			   return alpha;
		   }else if(game.p_courant == game.p2) {
			   int alpha = -10000;
			   for (int coup:game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha = Math.max(alpha,expectiMinMax(situation2, profondeur -1));
			   }
			   return alpha;
		   }else if (game.p_courant == ) {
			   int alpha = 0;
			   for(int coup : game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha += (1/26*expectiMinMax(situation2, profondeur-1));
			   }
			   return alpha;
		   }
	   }
}
