package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private Long mId;
	
	@Column(name="c_dateDebut")
	private Date mDateDebut;
	
	@Column(name="c_dateFin")
	private Date mDateFin;
	
	@Column(name="c_compteRendu")
	private String mCompteRendu;
	
	@Column(name="c_dateRdv")
	private Calendar mDateRdv;


	
	@ManyToOne(targetEntity=MedecinEntity.class)
	@JoinColumn(name="c_id_medecin")
	private Medecin mMedecin;
	
	@ManyToOne(targetEntity=PatientEntity.class)
	@JoinColumn(name="c_id_patient")
	private Patient mPatient;
	
	@OneToMany(targetEntity=TraitementEntity.class)
	@JoinColumn(name="c_id_consultation")
	private List<Traitement> mTraitements;
	
	@OneToMany(targetEntity=InteractionEntity.class)
	@JoinColumn(name="c_id_consultation")
	private List<Interaction> mInteractions;

	public Long getId(){
		return mId;
	}
	@Override
	public int compareTo(Consultation arg0) {
		Calendar db = Calendar.getInstance();
		db.setTime(mDateDebut);
		Calendar df = Calendar.getInstance();
		df.setTime(mDateFin);
		if(db.compareTo(arg0.getDebut()) != 0) {
			return db.compareTo(arg0.getDebut());
		}
		return df.compareTo(arg0.getFin());
	}

	@Override
	public Patient getPatient() {
		return mPatient;
	}

	@Override
	public void setPatient(Patient patient) {
		mPatient = (PatientEntity) patient;
	}

	@Override
	public Medecin getMedecin() {
		return mMedecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		mMedecin = (MedecinEntity) medecin;
	}

	@Override
	public Calendar getDebut() {
		Calendar gdt = Calendar.getInstance();
		gdt.setTime(mDateDebut);
		return gdt;
	}

	@Override
	public void setDebut(Calendar date) {
		mDateDebut = new java.sql.Date(date.getTimeInMillis());
	}

	@Override
	public Calendar getFin() {
		Calendar gdt = Calendar.getInstance();
		gdt.setTime(mDateFin);
		return gdt;
	}

	@Override
	public void setFin(Calendar date) {
		mDateFin = new java.sql.Date(date.getTimeInMillis());
	}

	@Override
	public String getCompteRendu() {
		return mCompteRendu;
	}

	@Override
	public void setCompteRendu(String texte) {
		mCompteRendu = texte;
	}

	@Override
	public List<Traitement> getPrescription() {
		return mTraitements;
	}
	

	public void setTraitements(List<Traitement> traitements) {
		mTraitements = traitements;
	}
	@Override
	public Boolean ajouterTraitement(Produit produit) {
		TraitementEntity traitementEntity = (TraitementEntity) produit;

	       if (!this.getPrescription().contains(traitementEntity)) {
	    	   
	            return this.getPrescription().add(traitementEntity);
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
		return mInteractions;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		mInteractions = interactions;
	}
}
