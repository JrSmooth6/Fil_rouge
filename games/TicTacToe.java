package games;
import players.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class TicTacToe extends AbstractGame2JSansHasard{ /* La  classe TicTacToe est une extension AbstractGame (classe abstraite)*/
    public GamePlayer [][] grid; /* Cette variable correpond à une grille dans laquelle les objets seront des joueurs */

    public TicTacToe(GamePlayer p1, GamePlayer p2){
        super(p1,p2); /* p1 et p2 sont définies dans AbstractGame, ils correspondent aux joueurs*/
        this.grid = new GamePlayer [3][3]; /* Initialisation de la grille du jeu de taille 3*3 */
    }

    public void execUnCoup(int nb){ /*méthode pour executer un coup */
        if (!validMoves().contains(nb)){ /*Si le nombre n'est pas contenu dans la méthode validmove */
            throw new IllegalArgumentException("Le coup joué n'est pas valide.");/* alors evidement on affiche le coup n'est pas valide*/
        }
        this.grid[(nb-1)/3][(nb-1)%3] = this.p_courant;/*this.grid[(nb-1)/3][(nb-1)%3] correpond à
        la case concernée en fonction du nombre, la case selectionnée est ensuite égale au joueur qui joue  */
    }

    public List<Integer> validMoves(){
        int c = 1; /*creation de c correspondant à chaque case de la grille comme il y a 9 cases c à 9 valeurs possibles*/
        List<Integer> list = new ArrayList<> (); /*Creation d'une nouvelle liste qui contiendra les coups jouables*/
        for (int i=0; i<grid.length ; i++){  /* Pour chaque ligne de la grille du jeu*/
            for (int j=0; j<this.grid[0].length ; j++){ /*et pour chaque colone de la grille du jeu*/
                if (this.grid[i][j] == null){ /*Si la valeur contenue dans this.grid[i][j] est "null" (donc la case)*/
                    list.add(c); /*alors on ajoute c à la liste des nombres jouables*/
                }
                c++;  /*c prends la valeur  c+1 (pour la prochaine case)*/
            }
        }
        return list; /*On retourne finalement la liste avec tout les coups jouables*/
    }

    public String moveToString(Integer move){
        return "Case ("+(move-1)/3+","+(move-1)%3+")"; /*Methode retournant la case (i,j) modifiée
        durant le tour du joueur courant */
    }

    public String situationToString(){/*Methode retournant l'affichage de la grille*/
        String res = "";    /*Variable  contenant l'affichage de la grille */
        for (int i=0; i<grid.length ; i++){
            for (int j=0; j<this.grid[0].length ; j++){  /* Pour chaque j de la ligne i*/
                if (this.grid[i][j] == null){ /*Si la valeur de la case est null*/
                    res += ". ";          /*On ajoute à la variable res "." (avec un espace afin de séparer chaque case)*/
                } else if (this.grid[i][j] == this.p1){ /*Si la case est celle du joueurs 1 */
                    res += "X ";  /*On ajoute à la variable res X (avec un espace à fin de séparer chaque case)*/
                } else {  /*Sinon la case est celle du joueurs 2 */
                    res += "O "; /*On ajoute à la variable res . (avec un espace à fin de séparer chaque case)*/
                }
            }
            res += System.lineSeparator();  /*Fonction permettant un retour à la ligne ici utilisé à
            chaque ligne i à fin de les separer (il y a 3 lignes et 3 colones donc 9 cases )*/

        }
        return res;  /* On retourne alors à la fin le petit affichage (ressemblantà la grille du morpion) créer à partir
        d'un simple String*/
    }

    //Methode qui parcourt toute la grille pour trouver les combinaisons qui font gagner un joueur
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

        return null; //pas de gagnant ? on retourne null
    }

    public AbstractGame2JSansHasard getCopy(){//Methode qui copie la grille du jeu en profondeur pour prévoir les meilleurs coups avec MinMaxPlayer
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
