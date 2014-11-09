package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Medecin;

public class MedecinM extends UtilisateurM implements Medecin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String rpps;
	
	public MedecinM() {
		super();
	}
	
	public MedecinM(String prenom, String nom, String compte, String motDePasse, String rpps) {
		super(prenom, nom, compte, motDePasse);
		this.rpps = rpps;
	}

	@Override
	public String getRPPS() {
		return rpps;
	}
}
