package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Personne;

public class PersonneM implements Personne {
	private long id;
	private String nom;
	private String prenom;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public PersonneM() {
		this.id = super.toString().hashCode();
	}
	
	public PersonneM(String prenom, String nom) {
		this();
		this.prenom = prenom;
		this.nom = nom;
	}
	
	public long getId() {
		return id;
	}

	@Override
	public String getNom() {
		return nom;
	}

	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String getPrenom() {
		return prenom;
	}

	@Override
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
