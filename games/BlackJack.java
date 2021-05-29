package games;
import java.util.HashMap;
import java.util.ArrayList;
import cartesAJouer.*;
import players.*;
public class BlackJack extends AbstractGame2JAvecHasard{
	

	public ArrayList<Integer> ArrayListeTotals ;
	public ArrayList<Boolean> ArrayListeStatuts;
	private Deck deck;
	public GamePlayer player_precedent;
	public GamePlayer naturePlayer;
	public BlackJack(GamePlayer p1, GamePlayer p2, Deck deck,ArrayList<Integer> ArrayListeTotals,ArrayList<Boolean>ArrayListeStatuts) {
		super(p1,p2);
		this.ArrayListeTotals = ArrayListeTotals;
		this.ArrayListeStatuts = ArrayListeStatuts;
		this.deck = deck;
		this.player_precedent = this.p1;
		this.naturePlayer = new NaturePlayer();
	}

	public void init() {
		this.ArrayListeTotals.add(0);
		this.ArrayListeTotals.add(0);
		this.ArrayListeStatuts.add(true);
		this.ArrayListeStatuts.add(true);
	}


	public ArrayList<Integer> validMoves() {
		ArrayList<Integer>ArrayList = new ArrayList<>();
		ArrayList.add(0);
		ArrayList.add(1);
		return ArrayList;
	}


	public String situationToString() {
		String str = " total J1 : " + this.ArrayListeTotals.get(0)+ " total J2 : " + this.ArrayListeTotals.get(1);	
		return str;
			

	}
	@Override
	public String moveToString(Integer move) {
		// TODO Auto-generated method stub
		return null;
	}


	public GamePlayer getWinner() {
		if (ArrayListeTotals.get(0)>21) {
			return p2;
		}
		if(ArrayListeTotals.get(1)>21) {
			return p1;
		}
		if(ArrayListeTotals.get(1)<21 && ArrayListeTotals.get(0)<21) {
			if(ArrayListeTotals.get(0)>ArrayListeTotals.get(1)) {
				return p1;
			}else {
				return p2;
			}
		}
		return p1;
		
	}
	public Boolean isOver() {
		return( ((this.ArrayListeStatuts.get(0)==false) && (this.ArrayListeStatuts.get(1)==false)) || ((this.ArrayListeTotals.get(0)>21) ||(this.ArrayListeTotals.get(1)>21)));
	}


	public BlackJack getCopy() {
		BlackJack copy = new BlackJack(this.p1,this.p2,this.deck,this.ArrayListeTotals,this.ArrayListeStatuts);
		return copy;
	}
	public int getHeuristicValue(GamePlayer p) {
		if(p==this.p1) {
			if(this.ArrayListeTotals.get(0)>21) {
				return 0;
			}else if(this.ArrayListeTotals.get(0)<21) {
				if(this.ArrayListeTotals.get(0)>18 && this.ArrayListeTotals.get(1)<this.ArrayListeTotals.get(0)) {
					return 3;
				}if(this.ArrayListeTotals.get(1)>21) {
					return 4;
				}if(this.ArrayListeTotals.get(0)<18 && this.ArrayListeTotals.get(1)<this.ArrayListeTotals.get(0)) {
					return 2;
				}if(this.ArrayListeTotals.get(0)<18 && this.ArrayListeTotals.get(1)>this.ArrayListeTotals.get(0)) {
					return 1;
				}
			}
		}
		if(p==this.p2) {
			if(this.ArrayListeTotals.get(1)>21) {
				return 0;
			}else if(this.ArrayListeTotals.get(1)<21) {
				if(this.ArrayListeTotals.get(1)>18 && this.ArrayListeTotals.get(0)<this.ArrayListeTotals.get(1)) {
					return 3;
				}if(this.ArrayListeTotals.get(1)>21) {
					return 4;
				}if(this.ArrayListeTotals.get(1)<18 && this.ArrayListeTotals.get(0)<this.ArrayListeTotals.get(1)) {
					return 2;
				}if(this.ArrayListeTotals.get(1)<18 && this.ArrayListeTotals.get(0)>this.ArrayListeTotals.get(1)) {
					return 1;
				}
			}
		}
		return 0;
	}
	public HashMap<Integer,Float>  getProba(GamePlayer p) {
		HashMap<Integer,Float> tableProbaBJ = new HashMap<>();
		for(int i = 1;i<14;i++) {
			int compteur = 0;
			for(int j = 0;j<this.deck.deck.size();j++) {
				if (this.deck.deck.get(j).valeur == i) {
					compteur +=1;
				}
				tableProbaBJ.put(i, ((float)compteur/this.deck.deck.size()));
			}
		}
		float probaTete =0;
		for(int i: tableProbaBJ.keySet()) {
			if(i<=10) {
				probaTete += tableProbaBJ.get(i);
				tableProbaBJ.remove(i);
			}
			tableProbaBJ.put(10, probaTete);
		}
		return tableProbaBJ;	
	}
	
	public float calculEsperance(GamePlayer p) {
		float esperance = 0;
		HashMap<Integer,Float> map = this.getProba(p);
		for(int i :map.keySet()) {
			esperance+=i*map.get(i);
		}
		return esperance;
	}
	public void jouerUnCoup(int nb) {
		System.out.println(this.p_courant);
		if(this.p_courant == this.p1) {
			if(this.ArrayListeStatuts.get(0)==true) {
				if(nb==0){
					this.ArrayListeStatuts.set(0, false);
					this.p_courant = this.p2;
				}if(nb==1) {
					this.p_courant = this.naturePlayer;
					this.player_precedent=this.p1;
				}
			}else {
				this.p_courant = this.p2;
			}
		}		
		if(this.p_courant == this.p2) {
			if(this.ArrayListeStatuts.get(1)==true) {
				if(nb==0){
					this.ArrayListeStatuts.set(1, false);
					this.p_courant = this.p1;
				}if(nb==1) {
					this.p_courant = this.naturePlayer;
					this.player_precedent=this.p2;
				}
			}else {
				this.p_courant = this.p1;
			}
		}
		 if(this.p_courant==this.naturePlayer) {
			 int valeur=this.deck.pioche().valeur;
			 if(valeur>=10) {
				 valeur = 10;
			 }
			 if(this.player_precedent==this.p1) {
				 this.ArrayListeTotals.set(0, this.ArrayListeTotals.get(0)+valeur);
				 this.p_courant = this.p2;
			 }else{
				 this.ArrayListeTotals.set(1, this.ArrayListeTotals.get(1)+valeur);
				 this.p_courant = this.p1;
			 }
		}	
	}

	@Override
	public void execUnCoup(int nb) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float getEsperance(HashMap<Integer, Float> map) {
		// TODO Auto-generated method stub
		return 0;
	}




}
