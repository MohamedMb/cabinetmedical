package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Produit;

public class ProduitM implements Produit {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String cis;
	private String nom;
	
	/*public ProduitM(){}
	
	public ProduitM(String cis, String nom){
		this.cis = cis;
		this.nom = nom;
	}*/

	@Override
	public String getCis() {
		return this.cis;
	}

	@Override
	public void setCis(String cis) {
		this.cis = cis;
		
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
		
	}

}
