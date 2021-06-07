package games;
import players.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class TicTacToe extends AbstractGame2JSansHasard{ 
    public GamePlayer [][] grid; 
    public TicTacToe(GamePlayer p1, GamePlayer p2){
        super(p1,p2); 
        this.grid = new GamePlayer [3][3];
    }
    public void execUnCoup(int nb){ 
        if (!validMoves().contains(nb)){ 
            throw new IllegalArgumentException("Le coup joué n'est pas valide.");
        }
        this.grid[(nb-1)/3][(nb-1)%3] = this.p_courant;
    }

    public List<Integer> validMoves(){
        int c = 1; 
        List<Integer> list = new ArrayList<> ();
        for (int i=0; i<grid.length ; i++){ 
            for (int j=0; j<this.grid[0].length ; j++){ 
                if (this.grid[i][j] == null){ 
                    list.add(c); 
                }
                c++; 
            }
        }
        return list; 
    }

    public String moveToString(Integer move){
        return "Case ("+(move-1)/3+","+(move-1)%3+")"; 
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

    public GamePlayer getWinner(){
        boolean test = true;
        for (int i=0; i<3; i++) {
            if (this.grid[i][0] != null){ //les horizontales
                if (this.grid[i][0] == this.grid[i][1] && this.grid[i][1] == this.grid[i][2]){
                    return this.grid[i][0];
                }
            }

            if (this.grid[0][i] != null){ //les verticales
                if (this.grid[0][i] == this.grid[1][i] && this.grid[0][i] == this.grid[2][i]){
                    return this.grid[0][i];
                }
            }
        }
        //les diagonales
        if (this.grid[0][0] != null){
            if (this.grid[1][1] == this.grid[0][0] && this.grid[2][2] == this.grid[1][1]){
                return this.grid[0][0];
            }
        }
        if (this.grid[0][2] != null){
            if (this.grid[1][1] == this.grid[0][2] && this.grid[2][0] == this.grid[1][1]){
                return this.grid[0][2];
            }
        }

        return null; 
    }

    public AbstractGame2JSansHasard getCopy(){
        TicTacToe res = new TicTacToe(super.p1,super.p2);
        for (int i=0; i<grid.length ; i++){
            for (int j=0; j<this.grid[0].length ; j++){
                    res.grid[i][j] = this.grid[i][j];
            }
        }

        res.p_courant = super.p_courant;
        return res;
    }
    public Boolean isOver(){
        return (validMoves().size() == 0 || getWinner() != null);
    }
 /*Calcul de l'heuristique */
	public boolean verif2CasesDiag(GamePlayer p) {
		if(this.grid[1][1]==p) {
			if(this.grid[0][0]== p || this.grid[0][2]== p || this.grid[2][2]== p || this.grid[0][2]== p) {
				return true;
			}
		}
		return false;
	}
	public boolean verif2CasesVertical(GamePlayer p) {
		for(int i =0;i<2;i++) {
			for(int j = 0 ; j<this.grid[i].length;j++) {
				if(this.grid[i][j]==p && this.grid[i+1][j]==p) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean verif2CasesHorizontal(GamePlayer p) {
		for (int i = 0 ; i<this.grid.length;i++) {
			for (int j=0;j<2;j++) {
				if(this.grid[i][j]==p && this.grid[i][j+1]==p) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean verif2Cases(GamePlayer p) {
		if(this.verif2CasesDiag(p)==true || this.verif2CasesHorizontal(p)==true || this.verif2CasesVertical(p)==true) {
			return true;
		}
		return false;
	}

	public int getHeuristicValue(GamePlayer p) {
		if(this.getWinner()==p) {
			return 1;
		}if(this.getWinner()==null) {
			return 0;
		}else {
			return -1;
		}
	}
	/* Fin du calcul de l'heuristique */

	@Override
	public HashMap<Integer, Float> getProba(GamePlayer naturePlayer2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getEsperance(HashMap<Integer, Float> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
