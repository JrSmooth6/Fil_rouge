package games;
import players.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Nim extends AbstractGame2JSansHasard{  /* La classeNim est une extension AbstractGame (classe abstraite)*/
    int taille_ini; /* Cette variable correpond aux nombres d'allumettes au debut du jeu*/
    int taille_courant;    /* Cette variable est modifié au cours de la partie du jeu est correspond aux nombres d'allumettes restantes */
    int nb_max; /* Cette variable définie le nombre maximum d'allumettes pouvant être retiré
    par un joueur durant son tour*/

    /*Constructeur de la classe*/
    public Nim (int taille_ini, int nb_max, GamePlayer p1, GamePlayer p2){
        super(p1,p2); /* p1 et p2 sont définies dans AbstractGame, ils correspondent aux joueurs*/
        if (nb_max < 0 || nb_max > taille_ini){ /*le nombre max d'allumettes n'est pas valide si il est inferieur à 0
        ou egale au nombre d'allumette en debut de partie*/
            throw new IllegalArgumentException("Le nombre max d'alumettes à enlever n'est pas valide.");
        }
        this.taille_ini = taille_ini;
        this.nb_max = nb_max;
        this.taille_courant = taille_ini;
    }

    public int getInitialNbMatches(){
        return this.taille_ini;       /*Methode retournant la taille initiale d'alumettes*/
    }
    public int getNbMatches(){
        return this.taille_courant; /*Methode retournant la taille actuelle d'alumettes*/
    }

    public String situationToString(){
        return ("Il reste "+this.taille_courant+" alumettes."); /*Methode retournant une phrase avec le nbr
        d'allumettes restantes*/
    }
    public String moveToString(Integer move){
        return move+"allumettes ont été retirées"; /*Methode retournant une phrase avec le nbr
        d'allumettes retirées*/
    }

    public void execUnCoup(int nb){ /*méthode pour executer un coup */
        if (!validMoves().contains(nb)){ /*Si le nombre n'est pas contenu dans la methode validMoves */
            throw new IllegalArgumentException("Le coup joué n'est pas valide."); /* alors evidement on affiche que le coup n'est pas valide*/
        }
        this.taille_courant -= nb; /*Sinon le nombre d'allumettes retirées
         est enlevée au nombre d'allumettes restantes*/
    }

    public List<Integer> validMoves(){ /*méthode définissant les coups jouables */
        List<Integer> list = new ArrayList<> ();  /*creation d'une nouvelle liste qui contiendra les coups jouables*/
        for (int i=1; i < this.nb_max + 1; i++) {  /*Pour chaque coup i>0 et inferieur ou égale au nb_max autorisé  */
            list.add(i);                   /* Ajouter le coup à la liste des coups jouables*/
        }
        return list; /*Retourne la liste des coups valides*/
    }

    public GamePlayer getWinner(){ /*Methode définissant le vainqueur*/
        if (this.taille_courant <= 0){ /*Si le nombre d'allumettes est nul ou inferieur a 0*/
            return this.p_courant; /* retourner le joueur actuel qui sera gagnant */
        } else { return null; }   /* pas de vainceur*/
    }

    public AbstractGame2JSansHasard getCopy(){ /*Methode servant a MinMaxPlayer pour faire la copie de la situationdu jeu afin de prévoir le meilleur coup*/
        Nim res = new Nim(this.taille_ini,this.nb_max,super.p1,super.p2);
        res.taille_courant = this.taille_courant;
        res.p_courant = super.p_courant;
        return res; /*on retourne la copie du jeu*/
    }
    public Boolean isOver(){
        return (validMoves().size() == 0 || getWinner() != null);
    }

	
	public int getHeuristicValue(GamePlayer p) {
			if(this.taille_courant==1) {
				return 0;
			}if(this.taille_courant==2) {
				return 3;
			}if(this.taille_courant==this.nb_max+1) {
				return 3;
			}else {
				return 1;
			}
	}

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
