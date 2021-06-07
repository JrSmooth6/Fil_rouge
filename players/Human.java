package players;
import games.*;

import java.util.Scanner;

public class Human implements GamePlayer{
    String name;
    public Human(String name){
        this.name = name;
    }

    public String toString(){
        return this.name;
    }

    public int chooseMove(AbstractGame game){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Coups valides :");
        for (Integer move : game.validMoves()) {
            System.out.print(" " + move);
        }
        System.out.println();

        System.out.println(game.getPlayerCourant().toString() + ", c'est à toi de jouer ! Que fais-tu ? ");
        int nb = scanner.nextInt();

        while (!game.validMoves().contains(nb)){
            System.out.println(game.getPlayerCourant().toString() + ", ton nombre n'est pas valide, choisis en un autre :");
            nb = scanner.nextInt();
        }
        return nb;
    }





}
