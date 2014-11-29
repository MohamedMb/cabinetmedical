package miage.gestioncabinet.coreM;

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

@Stateful
public class ConsultationM implements Consultation {

	@EJB
	PrescriptionServiceInterface prescriptionService;
	
	private static final long serialVersionUID = 1L;
	
	private Patient mPatient;
	
	private Medecin mMedecin;
	
	private Calendar mDebut;
	
	private Calendar mFin;
	
	private String mCompteRendu;
	@Override
	public int compareTo(Consultation o) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	@Override
	public Patient getPatient() {
		return mPatient;
	}


	@Override
	public void setPatient(Patient patient) {
		this.mPatient = patient;
	}


	@Override
	public Medecin getMedecin() {
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
		this.mCompteRendu = compteRendu;
	}



	@Override
	public List<Traitement> getPrescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean ajouterTraitement(Produit produit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean supprimerTraitement(Traitement medicament) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Interaction> getInteractions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setInteractions(List<Interaction> interactions) {
		// TODO Auto-generated method stub
		
	}
	

}
