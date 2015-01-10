package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Produit;


public class InteractionM implements Interaction{

	//TODO à refaire
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Produit mProduitA;
	private Produit mProduitB;
	private String mSeverite;
	private String mRisques;
	private String mPrecautions;
	
	
	@Override
	public Produit getProduitA() {
		return mProduitA;
	}
	
	@Override
	public void setProduitA(Produit produitA) {
		this.mProduitA = produitA;
	}
	
	@Override
	public Produit getProduitB() {
		return mProduitB;
	}
	
	@Override
	public void setProduitB(Produit produitB) {
		mProduitB = produitB;
	}
	
	@Override
	public String getSeverite() {
		return mSeverite;
	}
	
	@Override
	public void setSeverite(String severite) {
		mSeverite = severite;
	}
	
	@Override
	public String getRisques() {
		return mRisques;
	}
	
	@Override
	public void setRisques(String risques) {
		mRisques = risques;
	}
	
	@Override
	public String getPrecautions() {
		return mPrecautions;
	}
	
	@Override
	public void setPrecautions(String precautions) {
		mPrecautions = precautions;
	}

}
