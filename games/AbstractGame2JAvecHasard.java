package games;
import players.GamePlayer;
import players.Human;
import players.NaturePlayer;
import players.RandomPlayer;

public abstract class AbstractGame2JAvecHasard extends AbstractGame {
	public GamePlayer naturePlayer;
	public AbstractGame2JAvecHasard(GamePlayer p1, GamePlayer p2) {
		super(p1, p2);
		this.naturePlayer = new NaturePlayer(p1);
	}
    public void jouerUnCoup(int nb){
    	Human homme = new Human("homme",0);
    	RandomPlayer random = new RandomPlayer(0);
        if (this.p_courant == this.p1){
        	if(this.p1.getClass() == homme.getClass() || this.p1.getClass()==random.getClass() ) {
        		execUnCoup(nb);
        		this.p_courant = this.p2;
        	}else {
        		this.naturePlayer = new NaturePlayer(this.p1);
        		this.p_courant = this.naturePlayer;
        		execUnCoup(nb);
        		this.p_courant = this.p2;
        	}
        }
        if (this.p_courant == this.p2){
        	if(this.p2.getClass() == homme.getClass() || this.p2.getClass()==random.getClass() ) {
        		execUnCoup(nb);
        		this.p_courant = this.p1;
        	}else {
        		this.naturePlayer = new NaturePlayer(this.p2);
        		this.p_courant = this.naturePlayer;
        		execUnCoup(nb);
        		this.p_courant = this.p1;
        	}
        }
    }
}
