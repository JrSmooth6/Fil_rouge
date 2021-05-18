package cartesAJouer;

public class Carte {
	public int valeur;
	public String forme;
	
	public Carte(int valeur,String forme) {
		this.valeur = valeur;
		this.forme = forme;
	}
	
	public void affiche() {
		System.out.println(this.valeur + " "+ this.forme);
	}


public static void main(String[] args){
	  Carte a = new Carte(1,"PIQUE");
	  a.affiche();
	 
}
}