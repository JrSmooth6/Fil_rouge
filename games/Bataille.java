package games;


import java.util.ArrayList;
import java.util.Collections;

import cartesAJouer.Carte;
import cartesAJouer.Deck;
import players.BataillePlayer;

public class Bataille {
	BataillePlayer p1;
	BataillePlayer p2;
	public Bataille(BataillePlayer p1, BataillePlayer p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	public void jeu() {
		this.distribution();
		while(!p1.deck.deck.isEmpty() && !p2.deck.deck.isEmpty()) {
			this.manche();
			this.score();
		}
		this.gagnantPartie();
	}
	public void gagnantPartie() {
		if (p1.deck.deck.isEmpty()) {
			System.out.println(p2.name + " Gagne la partie");
		}else if(p2.deck.deck.isEmpty()){
			System.out.println(p1.name + " Gagne la partie");
		}
	}
	public void score() {
		System.out.println(p1.deck.deck.size() + " J1");
		System.out.println(p2.deck.deck.size() + " J2");
	}
		public void distribution() {
		ArrayList<Carte> deck = new ArrayList<Carte>();
		Deck croupier = new Deck(deck);
		croupier.createDeck();
		Collections.shuffle(croupier.deck);
		for(int i = 0;i<croupier.deck.size();i++) {
			if(i%2==0) {
				p1.deck.deck.add(croupier.deck.get(i));
			}else {
				p2.deck.deck.add(croupier.deck.get(i));
			}
		}

	}
	public void manche() {
		Carte Cj1 = this.p1.pioche();
		Carte Cj2 = this.p2.pioche();
		ArrayList<Carte> fosseCommune = new ArrayList<Carte>();
		fosseCommune.add(Cj1);
		fosseCommune.add(Cj2);
		gagnantManche(p1,p2,Cj1,Cj2,fosseCommune);
		
		
	}
	public void gagnantManche(BataillePlayer p1, BataillePlayer p2, Carte Cj1, Carte Cj2, ArrayList<Carte> fosseCommune) {

		if( Cj1.valeur > Cj2.valeur) {
			p1.ajoutCartes(fosseCommune);
		}
		else if(Cj1.valeur< Cj2.valeur) {
			p2.ajoutCartes(fosseCommune);
		}else {
			this.gestionEgalite(p1, p2, fosseCommune);
		}
	}
	public void gestionEgalite(BataillePlayer p1, BataillePlayer p2, ArrayList<Carte> fosseCommune) {
		if(!gestionEgaliteSansCarte(p1,p2,fosseCommune)) {
			gestionEgaliteAvec1Carte(p1, p2, fosseCommune);
		}
	}
	public boolean gestionEgaliteSansCarte(BataillePlayer p1, BataillePlayer p2, ArrayList<Carte>fosseCommune) {
		if ((p1.deck.deck.size()==0)&&(p2.deck.deck.size()==0)) {
			if (p1.deck.deck.size()>p2.deck.deck.size()) {
				p1.ajoutCartes(fosseCommune);
			}else {
				p2.ajoutCartes(fosseCommune);
			}
			return true;
		}
		return false;
	}
	public void gestionEgaliteAvec1Carte(BataillePlayer p1, BataillePlayer p2,ArrayList<Carte>fosseCommune) {
		if((p1.deck.deck.size() >=1)&&(p2.deck.deck.size()>=1)) {
			Carte Cj1tmp = p1.pioche();
			Carte Cj2tmp = p2.pioche();
			fosseCommune.add(Cj1tmp);
			fosseCommune.add(Cj2tmp);
			
			
			if(!gestionEgaliteAvec2Cartes(p1, p2, fosseCommune)) {
				Collections.shuffle(fosseCommune);
				this.gagnantManche(p1, p2, Cj1tmp, Cj2tmp, fosseCommune);
			}
		}
	}
	public boolean gestionEgaliteAvec2Cartes(BataillePlayer p1, BataillePlayer p2, ArrayList<Carte>fosseCommune) {
		if((p1.deck.deck.size()>=2)&&(p2.deck.deck.size()>=2)) {
			Carte Cj1 = p1.pioche();
			Carte Cj2 = p2.pioche();
			fosseCommune.add(Cj1);
			fosseCommune.add(Cj2);
			Collections.shuffle(fosseCommune);
			this.gagnantManche(p1, p2, Cj1, Cj2, fosseCommune);
			return true;
		}else {
			return false;
		}
		
	}
	
	public void afficheGagnant(BataillePlayer player) {
		System.out.println(player.name + " est le gagnat de la partie ");
	}
	public static void main(String[] args){
		ArrayList<Carte> tas = new ArrayList<Carte>();
		ArrayList<Carte> tas2 = new ArrayList<Carte>();
		Deck deck = new Deck(tas);
		Deck deck2 = new Deck(tas2);
		BataillePlayer p1 = new BataillePlayer("Hadrien",deck,0);
		BataillePlayer p2 = new BataillePlayer("Pierre",deck2,0);
		Bataille bataille = new Bataille(p1,p2);
		bataille.jeu();

		



	}
}


