package cartesAJouer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
public class Deck {
	public ArrayList<Carte> deck;
	public Deck(ArrayList<Carte>deck) {
		this.deck = deck;
	}
	public ArrayList<Carte> createDeck() {
		int[] valeurs = {1,2,3,4,5,6,7,8,9,10,11,12,13};
		String[] formes = {"Pique","Coeur"};
		for (int i =0;i<valeurs.length;i++) {
			for (int j = 0;j<formes.length;j++) {
				Carte card = new Carte(valeurs[i],formes[j]);
				this.deck.add(card);
			}
		}
		return this.deck;
	}
	public Carte pioche() {
		Carte card = this.deck.get(0);
		this.deck.remove(0);
		return card;
		
	}
	public void melangeDeck() {
		Collections.shuffle(this.deck);
	}
	public void affiche() {
		for (int i = 0;i<this.deck.size();i++) {
			System.out.println(this.deck.get(i).valeur + " "+ this.deck.get(i).forme);
		}
	}
	public ArrayList<Carte> cartesRestantes(){
		return this.deck;
	}
	public HashMap<Integer,Float>  getProba() {
		HashMap<Integer,Float> tableProbaBJ = new HashMap<>();
		for(int i = 1;i<14;i++) {
			int compteur = 0;
			for(int j = 0;j<this.deck.size();j++) {
				if (this.deck.get(j).valeur == i) {
					compteur +=1;
				}
				tableProbaBJ.put(i,(float) (compteur/this.deck.size()));
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

}
