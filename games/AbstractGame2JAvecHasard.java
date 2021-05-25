package games;


import players.GamePlayer;
import players.NaturePlayer;

public abstract class AbstractGame2JAvecHasard extends AbstractGame {
	public GamePlayer naturePlayer;
	public AbstractGame2JAvecHasard(GamePlayer p1, GamePlayer p2) {
		super(p1, p2);
		this.naturePlayer = new NaturePlayer(p1);
	}
    public void jouerUnCoup(int nb){
        execUnCoup(nb);
        if (this.p_courant == this.p1){
        	this.naturePlayer = new NaturePlayer(p1);
            this.p_courant = this.naturePlayer;
        }if(this.p_courant==this.p2) { 
        	this.naturePlayer = new NaturePlayer(p2);
        	this.p_courant = this.naturePlayer;
        }
    }
}
