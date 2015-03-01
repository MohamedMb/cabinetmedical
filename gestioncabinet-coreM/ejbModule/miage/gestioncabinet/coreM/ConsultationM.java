package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PrescriptionServiceInterface;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

public class ConsultationM implements Consultation {

	private static final long serialVersionUID = 1L;

	private Patient mPatient;
	private Medecin mMedecin;
	private Calendar mDebut;
	private Calendar mFin;
	private String mCompteRendu;
	private List<Interaction> mInteractions;
	private List<Traitement> mPrescriptions;
	private long id;

	public ConsultationM() {
		mInteractions = new ArrayList<Interaction>();
		mPrescriptions = new ArrayList<Traitement>();
		mPatient = new PatientM();
		mMedecin = new MedecinM();
		mDebut = Calendar.getInstance();
		mFin = Calendar.getInstance();
		this.id = super.toString().hashCode();
	}
	@Override
	public int compareTo(Consultation o) {
		if(mDebut.compareTo(o.getDebut()) != 0) {
			return mDebut.compareTo(o.getDebut());
		}
		return mFin.compareTo(o.getFin());
	}


	@Override
	public Patient getPatient() {
		if(mPatient == null) {
			mPatient = new PatientM();
		}
		return mPatient;
	}


	@Override
	public void setPatient(Patient patient) {
		this.mPatient = patient;
	}


	@Override
	public Medecin getMedecin() {
		if(mMedecin == null) {
			mMedecin = new MedecinM();
		}
		return mMedecin;
	}


	@Override
	public void setMedecin(Medecin medecin) {
		this.mMedecin = medecin;
	}


	@Override
	public Calendar getDebut() {
		return mDebut;
	}


	@Override
	public void setDebut(Calendar debut) {
		this.mDebut = debut;
	}


	@Override
	public Calendar getFin() {
		return mFin;
	}


	@Override
	public void setFin(Calendar fin) {
		this.mFin = fin;
	}


	@Override
	public String getCompteRendu() {
		return mCompteRendu;
	}


	@Override
	public void setCompteRendu(String compteRendu) {
		mCompteRendu = compteRendu;
	}


	@Override
	public List<Traitement> getPrescription() {
		if(mPrescriptions == null) {
			mPrescriptions = new ArrayList<Traitement>();
		}
		return mPrescriptions;
	}

	@Override
	public Boolean ajouterTraitement(Produit produit) {
		//return mTraitements.get(0).getProduit()
		Traitement traitement = new TraitementM();
		traitement.setProduit(produit);
		return mPrescriptions.add(traitement);
	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		return mPrescriptions.remove(medicament);
	}

	@Override
	public List<Interaction> getInteractions() {
		if(mInteractions == null) {
			mInteractions = new ArrayList<Interaction>();
		}
		return mInteractions;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		mInteractions = interactions;
	}
	
	public Long getId(){
		return this.id;
	}
	
	@Override
	public boolean equals(Object consultation){
		   return (this.getId().equals(((ConsultationM)consultation).getId()));
		}
	
}
