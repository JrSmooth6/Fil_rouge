package players;
import games.*;

import java.util.Scanner;

//Classe utilisée quand joue un joueur humain(classe qui implémente l'interface GamePlayer)
public class Human implements GamePlayer{
    String name;
    //Constructeur de la classe
    public Human(String name){
        this.name = name;
    }

    //Méthode qui retourne le nom d'un joueur
    public String toString(){
        return this.name;
    }

    //Méthode qui permet d'afficher les coups valides/possibles, elle indique le nom du joueur qui doit jouer et retourne le coup joué
    //Cette méthode indique également l'exception si le Joueur ne joue pas un coup qui est dans la liste des coups jouables elle va redemander de jouer le coup jusqu'à avoir un coup jouable
    public int chooseMove(AbstractGame game){
        Scanner scanner = new Scanner(System.in);

	//Donne la liste des coups jouables en parcourant la liste retournée par validMoves et en affichant ses valeurs
        System.out.print("Coups valides :");
        for (Integer move : game.validMoves()) {
            System.out.print(" " + move);
        }
        System.out.println();

	//Demande le coup à jouer
        System.out.println(game.getPlayerCourant().toString() + ", c'est à toi de jouer ! Que fais-tu ? ");
        int nb = scanner.nextInt();

	//Tant que la valeur de nb, c'est a dire du coup joué n'est pas dans la liste retournée par validMoves on redemande au joueur de saisir une valeur
        while (!game.validMoves().contains(nb)){
            System.out.println(game.getPlayerCourant().toString() + ", ton nombre n'est pas valide, choisis en un autre :");
            nb = scanner.nextInt();
        }
	//On retourne le coup joué
        return nb;
    }





}
