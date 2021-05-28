package games;
//On importe le package players pour utiliser les joueurs quel que soit le type
import players.*;
import java.util.List;
import java.util.ArrayList;

//Classe abstraite qui regroupe les methodes generales pour tous les jeux
public abstract class AbstractGame2JSansHasard extends AbstractGame{
	public GamePlayer p_courant;
    public AbstractGame2JSansHasard(GamePlayer p1, GamePlayer p2) {
		super(p1, p2);
		this.p_courant=p1;
	}

    public void jouerUnCoup(int nb){
        execUnCoup(nb);
        if (this.p_courant == this.p1){
            this.p_courant = this.p2;
        } else { this.p_courant = this.p1; }
    }
}
