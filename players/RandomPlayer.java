package players;
import games.*;
import java.util.Random;

//Classe du joueur random, un joueur automatique et comme son nom l'indique, qui joue de manière aléatoire
public class RandomPlayer implements GamePlayer{
	public int total;
    //Constructeur de la classe
    public RandomPlayer(int total){
    	this.total = total;
    }

	//Méthode qui retourne le "nom" du joueur random(Nombre aléatoire)
    public String toString(){
        return "Joueur aléatoire #" + this.hashCode();
    }

    //Méthode qui choisit aléatoirement un coup dans la liste des coups valides de validMoves grâce à la classe Random et qui retourne le coup joué
    public int chooseMove(AbstractGame game){
        Random r = new Random();
        int nb = game.validMoves().get(r.nextInt(game.validMoves().size()));
        return nb;
    }
}
