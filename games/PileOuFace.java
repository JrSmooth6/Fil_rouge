package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import players.GamePlayer;
import players.NaturePlayer;

public class PileOuFace extends AbstractGame2JAvecHasard{
	public List<Integer> liste;
	public GamePlayer naturePlayer;
	public int cagnotte;
	public GamePlayer player_precedent;
	public PileOuFace(GamePlayer p1, GamePlayer p2, int cagnotte) {
		super(p1,p2);
		this.liste = new ArrayList<Integer>();
		this.naturePlayer = new NaturePlayer();
		this.cagnotte = cagnotte;
		this.player_precedent = p1;
	}
	public void init() {
		this.liste.add(0);
		this.liste.add(0);
	}
	public Boolean isOver() {
		if (this.liste.get(0)>10 || this.liste.get(1)>10) {
			return true;
		}else {
		return false;
		}
	}
	public ArrayList<Integer> validMoves() {
		ArrayList<Integer>ArrayList = new ArrayList<>();
		ArrayList.add(0);
		ArrayList.add(1);
		return ArrayList;
	}

	public String situationToString() {
		String str = "Score j1: "+this.liste.get(0)+" Score j2 : "+ this.liste.get(1);
		return str ;
	}

	@Override
	public String moveToString(Integer move) {
		// TODO Auto-generated method stub
		return null;
	}

	public GamePlayer getWinner() {
		if(this.liste.get(0)>10) {
			return this.p1;
		}else if(this.liste.get(1)>10) {
			return this.p2;
		}else {
			return null;
		}
	}

	public AbstractGame getCopy() {
		PileOuFace copy = new PileOuFace(this.p1,this.p2,this.cagnotte);
		ArrayList<Integer> listecopy = new ArrayList<Integer>();
		listecopy.add(0);
		listecopy.add(0);
		listecopy.set(0,this.liste.get(0));
		listecopy.set(1, this.liste.get(1));
		copy.liste = listecopy;
		return copy;
	}

	public void execUnCoup(int nb) {
	}

	public int getHeuristicValue(GamePlayer p) {
		if(this.p1 ==p) {
			if(this.liste.get(0)==0) {
				return 0;
			}
			if(this.liste.get(0)==1) {
				return 1;
			}
			if(this.liste.get(0)==2) {
				return 1;
			}
			if(this.liste.get(0)==2) {
				return 2;
			}
			if(this.liste.get(0)==3) {
				return 3;
			}
			if(this.liste.get(0)==4) {
				return 4;
			}
			if(this.liste.get(0)==5) {
				return 5;
			}
			if(this.liste.get(0)==6) {
				return 6;
			}
			if(this.liste.get(0)==7) {
				return 7;
			}
			if(this.liste.get(0)==8) {
				return 8;
			}
			if(this.liste.get(0)==9) {
				return 9;
			}
			if(this.liste.get(0)==10) {
				return 10;
			}
		}
		if(this.p2 ==p) {
			if(this.liste.get(1)==0) {
				return 0;
			}
			if(this.liste.get(1)==1) {
				return 1;
			}
			if(this.liste.get(1)==2) {
				return 1;
			}
			if(this.liste.get(1)==2) {
				return 2;
			}
			if(this.liste.get(1)==3) {
				return 3;
			}
			if(this.liste.get(1)==4) {
				return 4;
			}
			if(this.liste.get(1)==5) {
				return 5;
			}
			if(this.liste.get(1)==6) {
				return 6;
			}
			if(this.liste.get(1)==7) {
				return 7;
			}
			if(this.liste.get(1)==8) {
				return 8;
			}
			if(this.liste.get(1)==9) {
				return 9;
			}
			if(this.liste.get(1)==10) {
				return 10;
			}
		}
		return 0;
	}
	
	public String lancerPiece() {
		Random random = new Random();
		int nb;
		nb = random.nextInt(2);
		String resultat = "";
		if (nb==0) {
			resultat = "Pile !";
		}else {
			resultat = "Face !";
		}
		return resultat;	
	}
	public void ajoutCagnotte(GamePlayer p) {
		if (this.p1 ==p) {
			this.liste.set(0, this.liste.get(0)+ this.cagnotte);
			this.cagnotte =0;
		}
		if (this.p2 ==p) {
			this.liste.set(1, this.liste.get(1)+ this.cagnotte);
			this.cagnotte =0;
		}
	}
	public void jouerUnCoup(int nb) {
		System.out.println(this.p_courant);
		if(this.p_courant == this.p1) {
			if(nb==0){
				System.out.println("Refus");
				this.ajoutCagnotte(p1);
				this.cagnotte = 0;
				this.p_courant = this.p2;
				System.out.println(this.p_courant);
			}else {
				this.p_courant = this.naturePlayer;
				this.player_precedent=this.p1;
			}
		}
		 if(this.p_courant == this.p2) {
			if(nb==0){
				this.ajoutCagnotte(p2);
				this.cagnotte = 0;
				this.p_courant = this.p1;
			}if(nb==1) {
				this.p_courant = this.naturePlayer;
				this.player_precedent=this.p2;
			}
		}
		 if(this.p_courant==this.naturePlayer) {
			String resultat =this.lancerPiece();
			System.out.println(resultat);
			if(resultat =="Pile !") {
				this.cagnotte+=2;
				this.p_courant=this.player_precedent;
			}if(resultat =="Face !") {
				this.cagnotte =0;
				if(this.player_precedent ==this.p1) {
					this.p_courant = this.p2;
					
				}else{
					this.p_courant=this.p1;
				}
			}
		}
	}
	public HashMap<Integer, Float> getProba(GamePlayer naturePlayer2) {
		return null;
	}
	public float getEsperance(HashMap<Integer, Float> map) {

		return this.cagnotte/2;
	}

}
