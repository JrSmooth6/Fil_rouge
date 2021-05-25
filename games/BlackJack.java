package games;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import cartesAJouer.*;
import players.*;
public class BlackJack extends AbstractGame2JAvecHasard{
	
	public Deck deck;
	public int totalj1;
	public int totalj2;
	boolean statutJ1;
	boolean statutJ2;
	public BlackJack(GamePlayer p1, GamePlayer p2, Deck deck,int totalj1,int totalj2,boolean statutJ1,boolean statutJ2) {
		super(p1,p2);
		this.deck = deck;
		this.totalj1 = totalj1;
		this.totalj2 = totalj2;
		this.statutJ1 = statutJ1;
		this.statutJ2 = statutJ2;
	}

	
	public void execUnCoup(int nb) {
		if(this.p_courant ==p1) {
			if(this.statutJ1 ==true) {
				if(nb ==1) {
					totalj1 +=this.deck.pioche().valeur;
					System.out.println(" total J1 : " + this.totalj1);
				}else if(nb==0){
					this.statutJ1=false;
				}
			}
		}
		else if (this.p_courant == p2) {
			if (this.statutJ2==true){
				if(nb == 1) {
					totalj2+= this.deck.pioche().valeur;
					System.out.println(" total J2 : " + this.totalj2);
				}
				else if(nb==0) {
					this.statutJ2=false;
				}
			}
		}
	}



	public List<Integer> validMoves() {
		List<Integer>list = new ArrayList<>();
		list.add(0);
		list.add(1);
		return list;
	}


	public String situationToString() {
		String str = "";
		if(this.p_courant == p1) {
			 str = " total J1 : " + this.totalj1;	
		}else {
			 str = " total J2 : " + this.totalj2;
			
		}
		return str;
	}


	@Override
	public String moveToString(Integer move) {
		// TODO Auto-generated method stub
		return null;
	}


	public GamePlayer getWinner() {
		if (totalj1>21) {
			return p2;
		}
		if(totalj2>21) {
			return p1;
		}
		if(totalj2<21 && totalj1<21) {
			if(totalj1>totalj2) {
				return p1;
			}else {
				return p2;
			}
		}
		return p1;
		
	}
	public Boolean isOver() {
		return( ((this.statutJ1==false) && (this.statutJ2==false)) || ((this.totalj1>21) ||(this.totalj2>21)));
	}


	public AbstractGame getCopy() {
		BlackJack copy = new BlackJack(this.p1,this.p2,this.deck,this.totalj1,this.totalj2,this.statutJ1,this.statutJ2);
		return copy;
	}
	public int getHeuristicValue(GamePlayer p) {
		if(p==this.p1) {
			if(this.totalj1>21) {
				return 0;
			}else if(this.totalj1<21) {
				if(this.totalj1>18 && this.totalj2<this.totalj1) {
					return 3;
				}if(this.totalj2>21) {
					return 4;
				}if(this.totalj1<18 && this.totalj2<this.totalj1) {
					return 2;
				}if(this.totalj1<18 && this.totalj2>this.totalj1) {
					return 1;
				}
			}
		}
		if(p==this.p2) {
			if(this.totalj2>21) {
				return 0;
			}else if(this.totalj2<21) {
				if(this.totalj2>18 && this.totalj1<this.totalj2) {
					return 3;
				}if(this.totalj2>21) {
					return 4;
				}if(this.totalj2<18 && this.totalj1<this.totalj2) {
					return 2;
				}if(this.totalj2<18 && this.totalj1>this.totalj2) {
					return 1;
				}
			}
		}
		return 0;
	}


	public HashMap<Integer, Float> getProba(GamePlayer naturePlayer2) {
		return this.deck.getProbaDeck();
	}
}
