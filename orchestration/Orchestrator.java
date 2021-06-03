package orchestration;
import games.*;
import players.BataillePlayer;
import players.GamePlayer;
import players.Human;

import java.util.ArrayList;
import java.util.Scanner;

import cartesAJouer.Carte;
import cartesAJouer.Deck;

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
        if (game.isOver() == true && game.getWinner() == null){
            System.out.println("Egalité.");
        }
        else{
            System.out.println(game.getWinner().toString() + " a gagné la partie !");
        }
        scanner.close();
    }
    public ArrayList<Integer> compare(int nb,AbstractGame game,int choix){
    	ArrayList<Integer>resultat = new ArrayList<Integer>();
    	int nbD = 0;
    	int nbM = 0;
    	if(choix ==3) {
    		Scanner scanner = new Scanner(System.in);
    		System.out.println("Nb de départ");
    		nbD = scanner.nextInt();
    		System.out.println("Nb max");
    		nbM = scanner.nextInt();
    		
    	}
    	resultat.add(0);
    	resultat.add(0);
    	resultat.add(0);
    	for (int i = 0;i<nb;i++) {
    		System.out.println(i);
    		while(game.isOver()==false) {
    			int nbi = game.getPlayerCourant().chooseMove(game);
    			game.jouerUnCoup(nbi);
                if (game.validMoves().size() == 0){
                    break;
                }
    		}
    		if (game.isOver() == true && game.getWinner() == null){
    			resultat.set(1, resultat.get(1)+1);
    	    }else{
    	          if(game.getWinner()==game.p1) {
    	        	  resultat.set(0, resultat.get(0)+1);
    	          }else {
    	        	  resultat.set(2, resultat.get(2)+1);
    	          }	
 			}if(choix == 2) {
 				 game = new TicTacToe(game.p1,game.p2);		
 			}if(choix==6) {
				game = new PuissanceQuatre(game.p1,game.p2);
				
 			}if(choix ==3) {
    			 game = new Nim(nbD,nbM,game.p1,game.p2);
    	}
 			System.out.println(i);
    	}
    	return resultat;
    }
}

