package players;
import java.util.HashMap;

import games.*;

//Interface pour les differents joueurs
public interface GamePlayer{

	

	int total = 0;
	GamePlayer p = null;

	public int chooseMove(AbstractGame game);

	public float calculEsperance(HashMap<Integer, Float> map);



}
