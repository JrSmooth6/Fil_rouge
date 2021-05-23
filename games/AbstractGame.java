package games;

import java.util.List;

import players.GamePlayer;

public abstract class AbstractGame {
    public GamePlayer p1;
	public GamePlayer p2;
    public GamePlayer p_courant;

    //Constructeur de la classe
    public AbstractGame(GamePlayer p1, GamePlayer p2){
        this.p1 = p1;
        this.p_courant = p1;
        this.p2 = p2;
    }
    
    
    public GamePlayer getPlayerCourant(){
        return this.p_courant;
    }
 

    public abstract Boolean isOver ();
    public abstract List<Integer> validMoves();
    public abstract String situationToString();
    public abstract String moveToString(Integer move);
    public abstract GamePlayer getWinner();
    public abstract AbstractGame getCopy();
    public abstract void execUnCoup(int nb);
    public abstract int getHeuristicValue(GamePlayer p);
    public abstract void jouerUnCoup(int nb);


}
