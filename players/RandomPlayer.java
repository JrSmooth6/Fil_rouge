package players;
import games.*;

import java.util.Random;

public class RandomPlayer implements GamePlayer{
	public int total;
    public RandomPlayer(int total){
    	this.total = total;
    }

    public String toString(){
        return "Joueur aléatoire #" + this.hashCode();
    }

    public int chooseMove(AbstractGame game){
        Random r = new Random();
        int nb = game.validMoves().get(r.nextInt(game.validMoves().size()));
        return nb;
    }


}
