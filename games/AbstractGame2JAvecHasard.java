package games;
import players.GamePlayer;
import players.NaturePlayer;

public abstract class AbstractGame2JAvecHasard extends AbstractGame {
	public GamePlayer naturePlayer;
	public GamePlayer p_courant;
	public AbstractGame2JAvecHasard(GamePlayer p1, GamePlayer p2) {
		super(p1, p2);
		this.naturePlayer = new NaturePlayer();
		this.p_courant = p1;
	}

}
