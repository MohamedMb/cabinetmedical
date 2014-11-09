package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Utilisateur;

public class UtilisateurM extends PersonneM implements Utilisateur {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String compte;
	private String motDePasse;
	
	public UtilisateurM() {
		super();
	}
	
	public UtilisateurM(String prenom, String nom, String compte, String motDePasse) {
		super(prenom, nom);
		this.compte = compte;
		this.motDePasse = motDePasse;
	}
	
	@Override
	public String getNom() {
		return super.getNom();
	}

	@Override
	public void setNom(String nom) {
		super.setNom(nom);
		
	}

	@Override
	public String getPrenom() {
		return super.getPrenom();
	}

	@Override
	public void setPrenom(String prenom) {
		super.setPrenom(prenom);
		
	}

	@Override
	public String getCompte() {
		return this.compte;
	}
	
	public String getMotDePasse() {
		return motDePasse;
	}
}
