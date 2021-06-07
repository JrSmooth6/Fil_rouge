package games;
import players.*;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Nim extends AbstractGame2JSansHasard{  
    int taille_ini; 
    int taille_courant;   
    int nb_max; 
    public Nim (int taille_ini, int nb_max, GamePlayer p1, GamePlayer p2){
        super(p1,p2); 
        if (nb_max < 0 || nb_max > taille_ini){ 
            throw new IllegalArgumentException("Le nombre max d'alumettes à enlever n'est pas valide.");
        }
        this.taille_ini = taille_ini;
        this.nb_max = nb_max;
        this.taille_courant = taille_ini;
    }

    public int getInitialNbMatches(){
        return this.taille_ini;       
    }
    public int getNbMatches(){
        return this.taille_courant; 
    }

    public String situationToString(){
        return ("Il reste "+this.taille_courant+" alumettes."); 
    }
    public String moveToString(Integer move){
        return move+"allumettes ont été retirées";
    }

    public void execUnCoup(int nb){ 
        if (!validMoves().contains(nb)){ 
            throw new IllegalArgumentException("Le coup joué n'est pas valide."); 
        }
        this.taille_courant -= nb; 
    }

    public List<Integer> validMoves(){ 
        List<Integer> list = new ArrayList<> ();  
        for (int i=1; i < this.nb_max + 1; i++) {  
            list.add(i);                   
        }
        return list; 
    }

    public GamePlayer getWinner(){ 
        if (this.taille_courant <= 0){ 
            return this.p_courant; 
        } else { return null; }  
    }

    public AbstractGame2JSansHasard getCopy(){ 
        Nim res = new Nim(this.taille_ini,this.nb_max,super.p1,super.p2);
        res.taille_courant = this.taille_courant;
        res.p_courant = super.p_courant;
        return res;
    }
    public Boolean isOver(){
        return (validMoves().size() == 0 || getWinner() != null);
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


	@Override
	public HashMap<Integer, Float> getProba(GamePlayer naturePlayer2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getEsperance(GamePlayer p ) {
		// TODO Auto-generated method stub
		return 0;
	}
}
