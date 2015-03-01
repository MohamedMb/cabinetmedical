package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import miage.gestioncabinet.api.Personne;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class PersonneEntity implements Serializable, Personne {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="c_id")
	private Long id;
	@Column(name="c_nom")
	private String nom;
	
	@Column(name="c_prenom")
	private String prenom;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	
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
