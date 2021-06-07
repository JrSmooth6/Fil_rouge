package players;
import games.*;


public class NegaMax implements GamePlayer{
    public NegaMax(){
    }
    public String toString(){
        return "Joueur optimisé #" + this.hashCode();
    }

    public int chooseMove(AbstractGame game){
        return negaMax(game, game.p_courant);
    }

    public int evaluer(AbstractGame situation, GamePlayer p_courant){
        if (situation.getWinner() == p_courant){
            return +1;
        }
        if (situation.isOver() && situation.getWinner() == null){
            return 0;
        }
        if (situation.isOver() && situation.getWinner() != p_courant){
            return -1;
        }
        else{ 
            int res = -10;
            AbstractGame2JSansHasard situation2;
            for (int coup : situation.validMoves()) {
                situation2 = (AbstractGame2JSansHasard) situation.getCopy();
                situation2.jouerUnCoup(coup);
                res = Math.max(res, -evaluer(situation2, situation2.p_courant));
            }
            return res;
        }
    }
    public int negaMax(AbstractGame situation, GamePlayer p_courant){
        int valeur = -10;
        int meilleureValeur = -10;
        int meilleurCoup = -10;
        AbstractGame situation2;

        for (int coup : situation.validMoves()) {
            situation2 =  situation.getCopy();
            situation2.jouerUnCoup(coup);
            valeur = -evaluer(situation2, situation2.p_courant);
            if (valeur > meilleureValeur){
                meilleureValeur = valeur;
                meilleurCoup = coup;
            }
        }
        return meilleurCoup;
    }






}
