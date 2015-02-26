package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

public class TraitementM implements Traitement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	private String posologie;
	private Produit produit;

	public TraitementM(){
		this.id = super.toString().hashCode();
	}

	@Override
	public Produit getProduit() {
		return produit;
	}

	@Override
	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	@Override
	public String getPosologie() {
		return this.posologie;
	}

	@Override
	public void setPosologie(String posologie) {
		this.posologie = posologie;		
	}

}
