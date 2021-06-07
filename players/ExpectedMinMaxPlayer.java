package players;
import games.*;
public class ExpectedMinMaxPlayer implements GamePlayer{
	public ExpectedMinMaxPlayer() {
		
	}
	   public String toString(){
	        return "Joueur optimisé #" + this.hashCode();
	    }
	   public float expectiMinMax(AbstractGame game, int profondeur,GamePlayer p) {
		   if(game.isOver()==true || profondeur == 0) {
			   return game.getHeuristicValue(p);
		   }else if(game.p_courant!=p) {
			   float alpha = 10000;
			   for(int coup : game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha = Math.min(alpha, expectiMinMax(situation2,profondeur-1,p));
				   
			   }
			   return alpha;
		   }else if(game.p_courant==p) {
			   float alpha = -10000;
			   for (int coup:game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   alpha = Math.max(alpha,expectiMinMax(situation2, profondeur -1,p));
			   }
			   return alpha;
		   }else{
			   float alpha = 0;
			   for(int coup : game.validMoves()) {
				   AbstractGame situation2 = game.getCopy();
				   situation2.jouerUnCoup(coup);
				   System.out.print(situation2.getEsperance(situation2.player_precedent));
				   alpha += (situation2.getEsperance(situation2.player_precedent)*this.expectiMinMax(situation2, profondeur-1,p));
			   }
			   return alpha;
		   }
	   }
	
	public int chooseMove(AbstractGame game) {
		return coupAJouer(game);
	}
	public int coupAJouer(AbstractGame situation) {
		System.out.println(situation.p_courant);
		GamePlayer p = situation.p_courant;
        float valeur = -10;
        float meilleureValeur = -10;
        int meilleurCoup = -10;
        AbstractGame situation2;

        for (int coup : situation.validMoves()) {
            situation2 =  situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = expectiMinMax(situation2,1,p);
            if (valeur > meilleureValeur){
                meilleureValeur = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
	}
}
