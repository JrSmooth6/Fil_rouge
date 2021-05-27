package players;

import java.util.HashMap;

import games.AbstractGame;

public class NaturePlayer implements GamePlayer {
	public GamePlayer player;
	public NaturePlayer(GamePlayer player){
		this.player = player;
	}
	@Override
	public int chooseMove(AbstractGame game) {
		// TODO Auto-generated method stub
		return 0;
	}
	public float calculEsperance(HashMap<Integer,Float> map) {
		float esperance = 0;
		for(int i :map.keySet()) {
			esperance+=i*map.get(i);
		}
		return esperance;
	}
	


}
