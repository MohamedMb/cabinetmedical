
package gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="t_patient")
public class PatientEntity implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_nom")
	private String nom;
	
	@Column(name="c_prenom")
	private String prenom;
		
	@Column(name="c_sexe")
	private String sexe;
	
	@Column(name="c_dateNaissance")
	private String dateNaissance;

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

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
	}
	
	@OneToMany(mappedBy="patient")
	private List<ConsultationEntity> consultations;
	
}
