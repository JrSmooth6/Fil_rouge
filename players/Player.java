package players;
import java.util.ArrayList;

import cartesAJouer.Carte;
import cartesAJouer.Deck;
import games.AbstractGame;
import games.AbstractGame2JSansHasard;
public class Player implements GamePlayer{
	public String name;
	public Deck deck;
	public int total;
	public Player(String name, Deck deck, int total) {
		this.name = name;
		this.deck = deck;
		this.total = total;
	}

	public Carte pioche() {
		if (this.deck.deck.size() >0) {
			Carte card = this.deck.deck.get(this.deck.deck.size()-1);
			this.deck.deck.remove(this.deck.deck.size()-1);
			return card;
		}
		return null;
	}
	public void ajoutCartes(ArrayList<Carte> fosseCommune) {
		this.deck.deck.addAll(0,fosseCommune);
	}
	public int calculScore() {
		int total = 0;
		for (int i = 0; i<this.deck.deck.size();i++) {
			if(this.deck.deck.get(i).valeur >10) {
				total +=10;
			}else {
				total+= this.deck.deck.get(i).valeur;
			}
		}
		return total;
	}
	public void showCartes() {
		System.out.println(this.name);
		for (int i = 0;i<this.deck.deck.size();i++) {
			System.out.println(this.deck.deck.get(i).valeur + " " + this.deck.deck.get(i).forme);
		}
	}




	public static void main(String[] args){
	
	}

	@Override
	public int chooseMove(AbstractGame game) {
		// TODO Auto-generated method stub
		return 0;
	}
}

