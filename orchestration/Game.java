package orchestration;
//On importe les packages players et games pour utiliser les joueurs quel que soit type et n'importe quel jeu
import games.*;
import players.*;

import java.util.ArrayList;
import java.util.Scanner;

import cartesAJouer.Carte;
import cartesAJouer.Deck;
import players.*;
//Classe principale du jeu
public class Game{
	public void lancerJeu(AbstractGame jeu) {
		Orchestrator capichef = new Orchestrator();
		capichef.playGame(jeu);
	}
	public AbstractGame creationJeu(int jeu) {
		Scanner scanner = new Scanner(System.in);
			if (jeu ==1) {
				System.out.println("Prenom Joueur 1 : ");
				String j1 = scanner.nextLine();
				System.out.println("Prenom Joueur 2 : ");
				String j2 = scanner.nextLine();
				ArrayList<Carte> tas = new ArrayList<Carte>();
				ArrayList<Carte> tas2 = new ArrayList<Carte>();
				Deck deck = new Deck(tas);
				Deck deck2 = new Deck(tas2);
				BataillePlayer p1 = new BataillePlayer(j1,deck,0);
				BataillePlayer p2 = new BataillePlayer(j2,deck2,0);
				Bataille bataille = new Bataille(p1,p2);
				bataille.jeu();		
			}
			else if(jeu == 2) {
				System.out.println("Joueur 1 : ");
				GamePlayer p1 = this.choixJoueur();
				System.out.println("Joueur 2 : ");
				GamePlayer p2 = this.choixJoueur();
				TicTacToe game = new TicTacToe(p1,p2);
				return game;
				
			}
			else if (jeu == 3) {
				System.out.println("Joueur 1 : ");
				GamePlayer p1 = this.choixJoueur();
				System.out.println("Joueur 2 : ");
				GamePlayer p2 = this.choixJoueur();
				System.out.println("Nb de départ : ");
				int nbStart = scanner.nextInt();
				System.out.println("Nb Max : ");
				int nbmax = scanner.nextInt();
				Nim game = new Nim(nbStart,nbmax,p1,p2);
				return game;			
			}
			else if (jeu==4) {
				System.out.println("Joueur 1 : ");
				GamePlayer p1 = this.choixJoueur();
				System.out.println("Joueur 2 : ");
				GamePlayer p2 = this.choixJoueur();
				System.out.println("Nb de départ : ");
				ArrayList<Carte> deck = new ArrayList<Carte>();
				Deck tas = new Deck(deck);
				tas.createDeck();
				tas.melangeDeck();
				ArrayList<Integer>liste1 = new ArrayList<Integer>();
				ArrayList<Boolean>liste2 = new ArrayList<Boolean>();
				BlackJack bj = new BlackJack(p1,p2,tas,liste1,liste2);
				bj.init();
				return bj;		}
			else if (jeu ==5) {
				GamePlayer p1 =new Human("h");
				GamePlayer p2 = new Human("C");
				int[][]gridj1=null;
				int[][]gridj2=null;
				BatailleNavale bn = new BatailleNavale(p1,p2,gridj1,gridj2);
				bn.initGame();
				return bn;		}
			else if(jeu==6) {
				System.out.println("Joueur 1 : ");
				GamePlayer p1 = this.choixJoueur();
				System.out.println("Joueur 2 : ");
				GamePlayer p2 = this.choixJoueur();
				PuissanceQuatre pq = new PuissanceQuatre(p1,p2);
				return pq;
				}else if(jeu==7) {
				GamePlayer p1 = new Human("h");
				GamePlayer p2 = new ExpectedMinMaxPlayer();
				PileOuFace pf = new PileOuFace(p1,p2,0);
				pf.init();
				return pf;
				}

			return null;
	}

		
	
	public void comparaison(AbstractGame game,int choix) {
		Orchestrator capichef = new Orchestrator();	
		Scanner scanner = new Scanner(System.in);
		System.out.println("Combien de fois voulez vous lancer le jeu ? ");
		int nbiter = scanner.nextInt();
		long tempsD = System.currentTimeMillis();
		 ArrayList<Integer> result = capichef.compare(nbiter,game,choix);
		 long tempsA = System.currentTimeMillis();
		 tempsA = tempsA-tempsD;
		 System.out.println(tempsA);
		 System.out.println(result.get(0)+ " = Nb victoire J1");
		 System.out.println(result.get(1)+ " = Nb Egalité ");
		 System.out.println(result.get(2)+ " = Nb victoire J2");
		
	}
	public GamePlayer choixJoueur() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Quel type de joueur désirez vous ? ");
		String type = scanner.nextLine();
		if (type.compareTo("human")==0) {
			System.out.println("Prenom ? ");
			String nom = scanner.nextLine();
			GamePlayer joueur = new Human(nom);
			return joueur; 
		}else if (type.compareTo("negamax")==0) {
			GamePlayer joueur = new NegaMax();
			return joueur;
		}else if(type.compareTo("random")==0) {
			GamePlayer joueur = new RandomPlayer(0);
			return joueur;
		}else if(type.compareTo("minmax")==0) {
			GamePlayer joueur = new MinMaxPlayer();
			return joueur;
		}else if(type.compareTo("negamaxalpha")==0) {
			GamePlayer joueur = new NegaMaxAlphaBeta();
			return joueur;
		}else if(type.compareTo("expectiminmax")==0) {
			GamePlayer joueur = new ExpectedMinMaxPlayer();
			return joueur;
		}
		else {
			throw new IllegalArgumentException("");
		}
	}
	public int  choixJeu() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("A quel jeu souhaiter vous jouer ? ");
		System.out.println("1 : Bataille ; 2 : Morpion ; 3 : Nim ; 4 : BlackJack ; 5 : Bataille Navale  ; 6 : Puissance 4; 7 : Pile ou Face; 8 : Comparaison");
		int jeu = scanner.nextInt();
		while(jeu>8||jeu<0) {
			System.out.println("Veuillez selectionnez un jeu");
			jeu = scanner.nextInt();
		}
		return jeu;
	}
    public static void main(String[] args){
    	Game g = new Game();
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Souhaitez vous jouer ou lancer une comparaison ? ");
    	System.out.println("1 : Jouer; 2 : Lancer une comparaison");
    	int choix = scanner.nextInt();
    	int choixjeu = g.choixJeu();
    	if(choix == 1) {
    		g.lancerJeu(g.creationJeu(choixjeu));
    	}else {
    		g.comparaison(g.creationJeu(choixjeu),choixjeu);
    	}
    	
    }
}
