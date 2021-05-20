package games;

import java.util.ArrayList;
import java.util.List;

import players.GamePlayer;

public class PuissanceQuatre extends AbstractGame{
	public GamePlayer [][] grid;
	public PuissanceQuatre(GamePlayer p1, GamePlayer p2) {
		super(p1,p2);
		this.grid = new  GamePlayer [6][7];
	}
	
	public int getProfondeur(int x) {
		if((grid[0][x]==null) && (grid[1][x]==null) && (grid[2][x]==null) && (grid[3][x]==null) && (grid[4][x]==null) && (grid[5][x]==null)) {
			return 5;
		}else if((grid[0][x]==null) && (grid[1][x]==null) && (grid[2][x]==null) && (grid[3][x]==null) && (grid[4][x]==null)) {
			return 4;
		}
		else if((grid[0][x]==null) && (grid[1][x]==null) && (grid[2][x]==null) && (grid[3][x]==null) ) {
			return 3;
		}
		else if((grid[0][x]==null) && (grid[1][x]==null) && (grid[2][x]==null)  ) {
			return 2;
		}
		else if((grid[0][x]==null) && (grid[1][x]==null) ) {
			return 1;
		}
		else if((grid[0][x]==null) ) {
			return 0;
		}else {
			return 10;
		}
		
	}
	
	public boolean verifDiagGD(GamePlayer p1) {
		for (int i = 0; i<3;i++) {
			for (int j=0;j<4;j++) {
				if( (grid[i][j]==p1) && (grid[i+1][j+1]==p1) && (grid[i+2][j+2]==p1) && (grid[i+3][j+3]==p1)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean verifDiagDG(GamePlayer p1) {
		for (int i = 5; i>2;i=i-1) {
			for (int j=0;j<4;j++) {
				if( (grid[i][j]==p1) && (grid[i-1][j+1]==p1) && (grid[i-2][j+2]==p1) && (grid[i-3][j+3]==p1)) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean verifLigne(GamePlayer p1) {
		for (int i = 0; i<grid.length;i++) {
			for (int j = 0;j<4;j++) {
				if((grid[i][j]==p1) && (grid[i][j+1]==p1) && (grid[i][j+2]==p1) && (grid[i][j=3]==p1)) {
					return true;
				}
			}
		}
		return false;
	}
	public void execUnCoup(int nb) {
		this.grid[this.getProfondeur(nb)][nb]= this.p_courant;
	}
	public Boolean isOver() {
		if((this.verifDiagDG(p1)==true)||(this.verifDiagGD(p1)==true)||(this.verifLigne(p1)==true)){
			return true;
		}
		else if((this.verifDiagDG(p2)==true)||(this.verifDiagGD(p2)==true)||(this.verifLigne(p2)==true)){
			return true;
		}else {
			return false;
		}
	}
	public List<Integer> validMoves() {
		List<Integer>list = new ArrayList<>();
		for (int i =0; i<grid.length;i++) {
			if(this.getProfondeur(i)!=10) {
				list.add(i);
			}
		}
		return list;
	}
    public String situationToString(){
        String res = "";   
        for (int i=0; i<grid.length ; i++){
            for (int j=0; j<this.grid[0].length ; j++){ 
                if (this.grid[i][j] == null){ 
                    res += ". ";         
                } else if (this.grid[i][j] == this.p1){ 
                    res += "X "; 
                } else { 
                    res += "O "; 
                }
            }
            res += System.lineSeparator();  

        }
        return res; 
    }
	@Override
	public String moveToString(Integer move) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GamePlayer getWinner() {
		if((this.verifDiagDG(p1)==true)||(this.verifDiagGD(p1)==true)||(this.verifLigne(p1)==true)){
			return p1;
		}
		else if((this.verifDiagDG(p2)==true)||(this.verifDiagGD(p2)==true)||(this.verifLigne(p2)==true)){
			return p2;
		}else {
			return p1;
		}
	}
	
	@Override
    public AbstractGame getCopy(){//Methode qui copie la grille du jeu en profondeur pour prévoir les meilleurs coups avec MinMaxPlayer
        PuissanceQuatre res = new PuissanceQuatre(super.p1,super.p2);
        for (int i=0; i<grid.length ; i++){
            for (int j=0; j<this.grid[0].length ; j++){
                    res.grid[i][j] = this.grid[i][j];
            }
        }

        res.p_courant = super.p_courant;
        return res;
    }

}
