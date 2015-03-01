package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

public class TraitementM implements Traitement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long mId;
	private String mPosologie;
	private Produit mProduit;

	public TraitementM(){
		this.mId = super.toString().hashCode();
	}
	public long getId() {
		return mId;
	}
	
	public void setId(long id) {
		this.mId = id;
	}

	@Override
	public Produit getProduit() {
		return mProduit;
	}

	@Override
	public void setProduit(Produit produit) {
		this.mProduit = produit;
	}

	@Override
	public String getPosologie() {
		return this.mPosologie;
	}

	@Override
	public void setPosologie(String posologie) {
		this.mPosologie = posologie;		
	}

}
