package players;
import java.util.HashMap;

import games.*;

//Interface pour les differents joueurs
public interface GamePlayer{

	


	public int chooseMove(AbstractGame game);

	public float calculEsperance(HashMap<Integer, Float> map);



}
