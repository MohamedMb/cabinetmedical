
package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import miage.gestioncabinet.api.Patient;

@Entity
@Table(name="t_patient")
public class PatientEntity implements Serializable, Patient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	private Date dateNaissance;

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
	
	@Override
	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance = (Date) dateNaissance.getTime();
	}

	@Override
	public Integer getAge() {
		Calendar birth = Calendar.getInstance();
		birth.setTime(dateNaissance);
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		
		if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;

	}

	@Override
	public Calendar getDateNaissance() {
		Calendar gdt = Calendar.getInstance();
		gdt.setTime(this.dateNaissance);
		return gdt;
	}
	
	@OneToMany(mappedBy="patient")
	private List<ConsultationEntity> consultations;


	
}
