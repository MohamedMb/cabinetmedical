package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import miage.gestioncabinet.api.Medecin;

@Entity
@Table(name="t_medecin")
public class MedecinEntity implements Serializable, Medecin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_nom")
	private String nom;
	
	@Column(name="c_prenom")
	private String prenom;
	
	@Column(name="c_compte")
	private String compte;
	
	@Column(name="c_motdepasse")
	private String motDePasse;
	
	@Column(name="c_rpps")
	private String rpps;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public String getRPPS() {
		// TODO Auto-generated method stub
		return rpps;
	}
	public void setRpps(String rpps) {
		this.rpps = rpps;
	}
	
	@OneToMany(mappedBy="medecin")
	private List<ConsultationEntity> consultations;




}
