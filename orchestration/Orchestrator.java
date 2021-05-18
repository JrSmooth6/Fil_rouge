package orchestration;
import players.*;
import games.*;
import java.util.Scanner;

/*Classe qui contient la boucle du jeu, elle permet de jouer et elle définit quand un joueur gagne ou si il y a une égalité*/
public class Orchestrator{
    /*Constructeur*/
    public Orchestrator (){}

    public void playGame(AbstractGame game){
        Scanner scanner = new Scanner(System.in);

	/*Affiche la situation du jeu au départ*/
        System.out.println(game.situationToString());

	/*Tant que la situation du jeu n'est pas gagnante pour un joueur on continue de jouer et on change de joueur à chaque tour*/
        while (game.isOver() == false){
            int nb = game.getPlayerCourant().chooseMove(game);

	    /*On joue un coup et on change de joueur*/
            game.jouerUnCoup(nb);

	    /*Affiche la situation du jeu en temps réel*/
            System.out.println(game.situationToString());

	    /*Si jamais il n'y a plus de coups possible dans la liste des validMoves et qu'il n'y a pas de vainqueur on casse la boucle pour sortir car il y a égalité*/
            if (game.validMoves().size() == 0){
                break;
            }
        }

	/*Si jamais il n'y a plus de coups possible dans la liste des validMoves et qu'il n'y a pas de vainqueur il y a égalité*/
        if (game.isOver() == true && game.getWinner() == null){
            System.out.println("Egalité.");
        }

	/*Sinon il y a un gagnant et on affiche son nom*/
        else{
            System.out.println(game.getWinner().toString() + " a gagné la partie !");
        }
        scanner.close();
    }
}
