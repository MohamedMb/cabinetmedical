package miage.gestioncabinet.coreM;

import miage.gestioncabinet.api.Personne;

public class PersonneM implements Personne {
	private long mId;
	private String mNom;
	private String mPrenom;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/*public PersonneM() {
		this.id = super.toString().hashCode();
	}
	
	public PersonneM(String prenom, String nom) {
		this();
		this.prenom = prenom;
		this.nom = nom;
	}*/
	
	public long getId() {
		return mId;
	}
	
	public void setId(long id) {
		this.mId = id;
	}

	@Override
	public String getNom() {
		return mNom;
	}

	@Override
	public void setNom(String nom) {
		this.mNom = nom;
	}

	@Override
	public String getPrenom() {
		return mPrenom;
	}

	@Override
	public void setPrenom(String prenom) {
		this.mPrenom = prenom;
	}
}
