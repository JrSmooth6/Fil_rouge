package games;

import java.util.ArrayList;
import java.util.List;

import players.GamePlayer;

public class PuissanceQuatre extends AbstractGame2JSansHasard{
	public GamePlayer [][] grid;
	public PuissanceQuatre(GamePlayer p1, GamePlayer p2) {
		super(p1,p2);
		this.grid = new  GamePlayer [6][7];
	}
	/*Fonction qui va "imiter la gravité" */
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
	public GamePlayer verif(GamePlayer p) {
		if (this.verifDiagGD(p)!=null) {
			return this.verifDiagGD(p);
		}
		 if (this.verifDiagDG(p)!=null) {
			return this.verifDiagDG(p);
		}
		 if (this.verifLigneH(p)!=null) {
			return this.verifLigneH(p);
		}
		 if (this.verifLigneV(p)!=null) {
			return this.verifLigneV(p );
		}else {
			return null;
		}
	}
	public GamePlayer verifDiagGD(GamePlayer p) {
		for (int i = 0; i<3;i++) {
			for (int j=0;j<4;j++) {
				if( grid[i][j]== p && grid[i+1][j+1]==p && grid[i+1][j+1]== p && grid[i+2][j+2]==p && grid[i+2][j+2]== p && grid[i+3][j+3]==p) {
					return p;
				}
			}
		}
		return null;
	}
	public GamePlayer verifDiagDG(GamePlayer p) {
		for (int i = 5; i>2;i=i-1) {
			for (int j=0;j<4;j++) {
				if( grid[i][j]==p && grid[i-1][j+1]==p && grid[i-1][j+1]==p && grid[i-2][j+2]==p  && grid[i-2][j+2]== p &&grid[i-3][j+3]==p) {
					return p;
				}
			}
		}
		return null;
	}
	public GamePlayer verifLigneH(GamePlayer p) {
		for (int i = 0; i<grid.length;i++) {
			for (int j = 0;j<4;j++) {
				if(grid[i][j] == p && grid[i][j+1]==p&& grid[i][j+1]== p && grid [i][j+2]==p  && grid[i][j+2]==p &&grid [i][j+3]==p) {
					return p;
				}
			}
		}
		return null;
	}
	public GamePlayer verifLigneV(GamePlayer p) {
		for (int i= 0 ; i<3;i++) {
			for (int j = 0 ;j<grid[i].length;j++){
				if(grid[i][j] == p && grid[i+1][j]== p && grid[i+1][j]== p && grid [i+2][j]== p && grid[i+2][j]== p && grid [i+3][j]== p)  {
					return p;
				}
			}
		}
		return null;
	}
	public void execUnCoup(int nb) {
		this.grid[this.getProfondeur(nb)][nb]= this.p_courant;
	}
	public Boolean isOver() {
		if(this.verif(this.p1)!=null||this.verif(this.p2)!=null) {
			return true;
		}else {
			return false;
		}
	}
	public List<Integer> validMoves() {
		List<Integer>list = new ArrayList<>();
		for (int i =0; i<grid.length+1;i++) {
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
	public GamePlayer getWinner() {
		if(this.verif(this.p1)!=null) {
			return this.p1;
		}else {
			return this.p2;
		}
	}
	
    public AbstractGame2JSansHasard getCopy(){//Methode qui copie la grille du jeu en profondeur pour prévoir les meilleurs coups avec MinMaxPlayer
        PuissanceQuatre res = new PuissanceQuatre(super.p1,super.p2);
        for (int i=0; i<grid.length ; i++){
            for (int j=0; j<this.grid[0].length ; j++){
                    res.grid[i][j] = this.grid[i][j];
            }
        }

        res.p_courant = super.p_courant;
        return res;
    }
    
    /*Calcul de l'heuristique*/
    

    public boolean verifCasesHorizontal(GamePlayer p, int nb) {
    	for (int i=0; i<this.grid.length;i++) {
    		for(int j = 0; j<this.grid[i].length-(nb-1);j++) {
    			if(nb==2) {
    				if(grid[i][j]==p && this.grid[i][j+1]==p) {
    					return true;
    				}
    			}else {
    				if(grid[i][j]==p && this.grid[i][j+1]==p && this.grid[i][j+2]==p) {
    					return true;
    				}
    			}
    		}
    	}
    	return false;
    }
    public boolean verifCasesVertical(GamePlayer p, int nb ) {
    	for(int i = 0;i<this.grid.length -(nb-1);i++) {
    		for(int j = 0; j<this.grid[i].length;j++) {
    			if (nb==2) {
    				if(this.grid[i][j]==p && this.grid[i+1][j]==p) {
    					return true;
    				}else {
        				if(this.grid[i][j]==p && this.grid[i+1][j]==p && this.grid[i+2][j]==p) {
        					return true;
        				}
    				}
    			}
    		}
    	}
    	return false;
    }
    public boolean verifCasesDiagonalesGD(GamePlayer p, int nb) {
    	for (int i = 0;i<this.grid.length-(nb-1);i++) {
    		for (int j = 0;j<this.grid[i].length-(nb-1);j++) {
    			if(nb==2) {
    				if(this.grid[i][j]==p &&this.grid[i+1][j+1]==p ) {
    					return true;
    				}else {
    					if(this.grid[i][j]==p &&this.grid[i+1][j+1]==p &&this.grid[i+2][j+2]==p ) {
        					return true;
        				}
    				}
    			}
    		}
    	}
    return false;
    }
    public boolean verifCasesDiagonalesDG(GamePlayer p, int nb) {
    	for (int i = 5;i>0+(nb-1);i--){
    		for (int j= 6;j>this.grid[i].length-(nb-1);j--) {
    			if(nb == 2) {
    				if(this.grid[i][j]==p && this.grid[i-1][j-1]==p) {
    					return true;
    				}else {
        				if(this.grid[i][j]==p && this.grid[i-1][j-1]==p && this.grid[i-2][j-2]==p) {
        					return true;
        				}
    				}
    			}
    		}
    	}
    	return false;
    }
    public boolean verif2Cases(GamePlayer p) {
    	if(this.verifCasesDiagonalesDG(p, 2)==true || this.verifCasesDiagonalesGD(p, 2)== true || this.verifCasesHorizontal(p,2)==true || this.verifCasesVertical(p,2)==true) {
    		return true;
    	}else {
    		return false ;
    	}
    }
    public boolean verif3Cases(GamePlayer p) {
    	if(this.verifCasesDiagonalesDG(p, 3)==true || this.verifCasesDiagonalesGD(p, 3)== true || this.verifCasesHorizontal(p,3)==true || this.verifCasesVertical(p,3)==true) {
    		return true;
    	}else {
    		return false ;
    	}
    }
	public int getHeuristicValue(GamePlayer p) {
		if (p == this.p1) {
			if(this.getWinner() == p) {
				return 4;
			}else {
				if(this.getWinner()==this.p2) {
					return 0;
				}else if(this.getWinner()==null) {
					return 4;
				}else if(this.verif2Cases(p)==true) {
					if(this.verif3Cases(p)==true) {
						return 3;
					}else {
						return 2;
					}
				}
			}
		}
		if (p == this.p2) {
			if(this.getWinner() == p) {
				return 4;
			}else {
				if(this.getWinner()==this.p1) {
					return 0;
				}else if(this.getWinner()==null) {
					return 4;
				}else if(this.verif2Cases(p)==true) {
					if(this.verif3Cases(p)==true) {
						return 3;
					}else {
						return 2;
					}
				}
			}
		}
		return 1;
	}

}
