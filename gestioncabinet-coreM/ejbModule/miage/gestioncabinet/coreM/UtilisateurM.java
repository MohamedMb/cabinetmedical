package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Utilisateur;

public class UtilisateurM extends PersonneM implements Utilisateur {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String mCompte;
	private String mMotDePasse;
	
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
		return mCompte;
	}
	
	public String getMotDePasse() {
		return mMotDePasse;
	}
}
