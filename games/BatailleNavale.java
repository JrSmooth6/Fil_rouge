package games;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import players.GamePlayer;

public class BatailleNavale extends AbstractGame2JAvecHasard {
		int[][]gridj1;
		int[][]gridj2;
	public BatailleNavale(GamePlayer p1, GamePlayer p2,int[][]gridj1,int[][]gridj2) {
		super(p1,p2);
		this.gridj1 = gridj1;
		this.gridj2 = gridj2;
	}
	public void initGame() {
		this.gridj1=new int[5][5];
		this.gridj2=new int[5][5];
		this.gridj1[0][0]=1;this.gridj1[1][0]=1;this.gridj1[2][0]=1;
		this.gridj1[2][2]=1;this.gridj1[2][3]=1;
		this.gridj2[0][0]=1;this.gridj2[0][1]=1;
	}
    public String ToString(int[][]grid ){
        String res = "";   
        for (int i=0; i<grid.length ; i++){
            for (int j=0; j<grid[0].length ; j++){  
                if (grid[i][j] == 0){ 
                    res += "0 ";         
                } else if (grid[i][j] == 1){ 
                    res += "1 "; 
                } else if (grid[i][j]==2) {
    
                    res += "2 "; 
                }
            }
            res += System.lineSeparator();  
        }
        return res; 
    }
    
	public void doMove(int nb,int[][]grid) {
		if(grid[(nb-1)/5][(nb-1)%5]==0) {
			System.out.println("Coulé !");
		}else if(grid[(nb-1)/5][(nb-1)%5]==1) {
			grid[(nb-1)/5][(nb-1)%5]=2;
			System.out.println("Touché !");
		}
		
	}
	public List<Integer> listeMove(int[][] grid ) {
		int c = 1; 
        List<Integer> list = new ArrayList<> ();
        for (int i=0; i<grid.length;i++) {
        	for(int j = 0 ; j<grid[i].length;j++) {
        		if((grid[i][j]==0)||(grid[i][j]==1)) {
        			list.add(c);
        		}
        		c+=1;
        	}
        }
        return list;
	}
	public boolean verifGrille(int[][]grid) {
		for (int i=0;i<grid.length;i++) {
			for(int j =0; j<grid[i].length;j++) {
				if(grid[i][j]==1) {
					return false;
				}
			}
		}return true;
	}
	
	public void execUnCoup(int nb) {
        if (!validMoves().contains(nb)){ /*Si le nombre n'est pas contenu dans la méthode validmove */
            throw new IllegalArgumentException("Le coup joué n'est pas valide.");/* alors evidement on affiche le coup n'est pas valide*/
        }else {
        	if (this.p_courant==p1) {
        		this.doMove(nb, this.gridj2);
        	}else if(this.p_courant==p2) {
        		this.doMove(nb, this.gridj1);
        	}
		}
	}

	public Boolean isOver() {
		
		return ((this.verifGrille(this.gridj1))||(this.verifGrille(this.gridj2)));
	}

	public List<Integer> validMoves() {
		if (this.p_courant==p1) {
			return this.listeMove(this.gridj2);
		}else if(this.p_courant==p2){
			return this.listeMove(this.gridj1);
		}
		return this.listeMove(gridj1);
	}

	public String situationToString() {
		if (this.p_courant==p1) {
			return this.ToString(gridj1);
		}else {
			return this.ToString(gridj2);
		}
	}
	@Override
	public String moveToString(Integer move) {
		// TODO Auto-generated method stub
		return null;
	}

	public GamePlayer getWinner() {
		if(this.verifGrille(this.gridj1)==true) {
			return p2;
		}else {
			return p1;
		}
		
	}

    public AbstractGame getCopy(){//Methode qui copie la grille du jeu en profondeur pour prévoir les meilleurs coups avec MinMaxPlayer
		int[][]grid1=new int[5][5];
		int[][]grid2=new int[5][5];
    	for (int i=0; i<this.gridj1.length ; i++){
            for (int j=0; j<this.gridj1[i].length ; j++){
                    grid1[i][j]=this.gridj1[i][j];
                    grid2[i][j]=this.gridj2[i][j];
            }
    	}
    	
    	
    	BatailleNavale res = new BatailleNavale(super.p1,super.p2,grid1,grid2);
    	return res;
    }

	public int compteurTouche(GamePlayer p,int nb) {
		int compteur =0;
			for(int i =0;i<this.gridj1.length;i++) {
				for(int j= 0; j<this.gridj1[i].length;j++) {
					if(p==this.p1) {
						if(this.gridj1[i][j]==nb) {
							compteur++;
						}
					}if(p==this.p2) {
						if(this.gridj2[i][j]==nb){
							compteur++;
						}
					}
				}
			}
			return compteur;
		
	}
	public int getHeuristicValue(GamePlayer p) {
		if(p==this.p1) {
			if(this.compteurTouche(p,1)==0) {
				return 0;
			}if(this.compteurTouche(p, 1)==1) {
				return 1;
			}if(this.compteurTouche(p, 1)==2){
				return 2;
			}if(this.compteurTouche(this.p2, 2)==1) {
				return 4;
			}if(this.compteurTouche(this.p2, 2)==2) {
				return 3;
			}
		}
		if(p==this.p2) {
			if(this.compteurTouche(p,1)==0) {
				return 0;
			}if(this.compteurTouche(p, 1)==1) {
				return 1;
			}if(this.compteurTouche(p, 1)==2){
				return 2;
			}if(this.compteurTouche(this.p1, 2)==1) {
				return 4;
			}if(this.compteurTouche(this.p1, 2)==2) {
				return 3;
			}
		}
		return 0;
	}
	public HashMap<Integer,Float> getProba(GamePlayer p ){
		HashMap<Integer,Float>map = new HashMap<>();
		if(p==this.p1) {
			int cle1 = this.compteurTouche(this.p2, 2);
			int bateau = 6-cle1;
			int eau = 19;
			map.put(0, (float) (eau/25));
			map.put(1,(float) (bateau/25));
		}
		if(p==this.p2) {
			int cle1 = this.compteurTouche(this.p1, 2);
			int bateau = 6-cle1;
			int eau = 19;
			map.put(0, (float) (eau/25));
			map.put(1,(float) (bateau/25));
		}
		return map;
	}
	@Override
	public void jouerUnCoup(int nb) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public float getEsperance(HashMap<Integer, Float> map) {
		// TODO Auto-generated method stub
		return 0;
	}

}
