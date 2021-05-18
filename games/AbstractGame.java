package games;
//On importe le package players pour utiliser les joueurs quel que soit le type
import players.*;
import java.util.List;
import java.util.ArrayList;

//Classe abstraite qui regroupe les methodes generales pour tous les jeux
public abstract class AbstractGame{
    //On definit les 2 joueurs et le joueur courant comme etant public
    GamePlayer p1,p2;
    public GamePlayer p_courant;

    //Constructeur de la classe
    public AbstractGame(GamePlayer p1, GamePlayer p2){
        this.p1 = p1;
        this.p_courant = p1;
        this.p2 = p2;
    }

    //Methode abstraite qui permet de jouer un coup qui sera donc definie pour chaque jeu
    public abstract void execUnCoup(int nb);

    //Methode qui permet d'éxécuter le coup et de changer de joueur a chaque tour
    public void jouerUnCoup(int nb){
        execUnCoup(nb);
        if (this.p_courant == this.p1){
            this.p_courant = this.p2;
        } else { this.p_courant = this.p1; }
    }

    //Methode qui sert a savoir quel est le joueur actif
    public GamePlayer getPlayerCourant(){
        return this.p_courant;
    }
    //Ensemble des methodes abstraites qui sont communes a tous les jeux et que l'on va definir dans les different jeux
    public abstract Boolean isOver ();
    public abstract List<Integer> validMoves();
    public abstract String situationToString();
    public abstract String moveToString(Integer move);
    public abstract GamePlayer getWinner();
    public abstract AbstractGame getCopy();
}
