package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Entity
@Table(name="t_consultation")
public class ConsultationEntity implements Serializable, Consultation{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_dateDebut")
	private Date dateDebut;
	
	@Column(name="c_dateFin")
	private Date dateFin;
	
	@Column(name="c_compteRendu")
	private String compteRendu;
	
	@Column(name="c_dateRdv")
	private Calendar dateRdv;


	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_medecin")
	private MedecinEntity medecin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_patient")
	private PatientEntity patient;
	
	@OneToMany(mappedBy="consultation")
	private List<TraitementEntity> traitements;
	
	@OneToMany(mappedBy="consultation")
	private List<InteractionEntity> interactions;

	public Long getId(){
		return this.id;
	}
	@Override
	public int compareTo(Consultation arg0) {
		Calendar db = Calendar.getInstance();
		db.setTime(this.dateDebut);
		Calendar df = Calendar.getInstance();
		df.setTime(this.dateFin);
		if(db.compareTo(arg0.getDebut()) != 0) {
			return db.compareTo(arg0.getDebut());
		}
		return df.compareTo(arg0.getFin());
	}

	@Override
	public Patient getPatient() {
		return this.patient;
	}

	@Override
	public void setPatient(Patient patient) {
		this.patient = (PatientEntity) patient;
	}

	@Override
	public Medecin getMedecin() {
		return this.medecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.medecin = (MedecinEntity) medecin;
	}

	@Override
	public Calendar getDebut() {
		Calendar gdt = Calendar.getInstance();
		gdt.setTime(this.dateDebut);
		return gdt;
	}

	@Override
	public void setDebut(Calendar date) {
		this.dateDebut = new java.sql.Date(date.getTimeInMillis());
	}

	@Override
	public Calendar getFin() {
		Calendar gdt = Calendar.getInstance();
		gdt.setTime(this.dateFin);
		return gdt;
	}

	@Override
	public void setFin(Calendar date) {
		this.dateFin = new java.sql.Date(date.getTimeInMillis());
	}

	@Override
	public String getCompteRendu() {
		return this.compteRendu;
	}

	@Override
	public void setCompteRendu(String texte) {
		this.compteRendu = texte;
	}

	@Override
	public List<Traitement> getPrescription() {
		return (List<Traitement>)(List)this.traitements;
	}

	@Override
	public Boolean ajouterTraitement(Produit produit) {
		TraitementEntity traitementEntity = (TraitementEntity) produit;

	       if (!this.getPrescription().contains(traitementEntity)) {
	    	   this.getPrescription().add(traitementEntity);
	            if (traitementEntity.getConsultationEntity() != null) {
	            	traitementEntity.getConsultationEntity().getPrescription().remove(traitementEntity);
	            }
	            traitementEntity.setConsultationEntity(this);
	            return true;
	        }
	       return false;
	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		this.getPrescription().remove(medicament);
		return true;
	}

	@Override
	public List<Interaction> getInteractions() {
		return (List<Interaction>)(List)this.interactions;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		this.interactions = (List<InteractionEntity>)(List)interactions;
	}
}
