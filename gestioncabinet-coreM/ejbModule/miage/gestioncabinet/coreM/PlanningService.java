package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.ApplicationServiceInterface;
import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Utilisateur;

@Stateful
@Remote(PlanningRemoteService.class)
public class PlanningService implements PlanningRemoteService {

	private Medecin mMedecin;
	private Calendar mDateDebut;
	private Calendar mDateFin;
	private Utilisateur mUtilisateur ;
	private Consultation mRdvCourant;
	private List<Consultation> mRDVs;
	private List<Medecin> mMedecins;
	private Set<Patient> mpatients;

	public PlanningService() {
		this.mRDVs = new ArrayList<Consultation>();
		this.mMedecins = new ArrayList<Medecin>();
		this.mpatients = new HashSet<Patient>();
		this.mDateDebut = Calendar.getInstance();
		this.mDateFin = Calendar.getInstance();
	}

	@EJB
	ApplicationServiceInterface appService;

	@Override
	public Utilisateur getUtilisateur() {
		return mUtilisateur;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		return mMedecins;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		
		List<Patient> patients = new ArrayList<Patient>();
	
		for(Patient patient : this.getPatients()) {
			
			boolean patientFound = patient.getNom().equals(nom) &&
					patient.getPrenom().equals(prenom) &&
					patient.getDateNaissance().equals(dateNaissance);
			
			if(patientFound) {
				mpatients.add(patient);
			}
		}
		return patients;
	}

	@Override
	public Calendar getDateDebut() {
		return mDateDebut;
	}

	@Override
	public void setDateDebut(Calendar date) {		
		mDateDebut = date;
	}

	@Override
	public Calendar getDateFin() {
		return mDateFin;
	}

	@Override
	public void setDateFin(Calendar date) {
		mDateFin = date;

	}

	@Override
	public Medecin getMedecin() {
		if(mMedecin == null){
			mMedecin = new MedecinM();
		}
		return mMedecin;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		mMedecin = medecin;
	}

	@Override
	public List<Consultation> listerRdv() {
		return mRDVs;
	}

	@Override
	public Consultation getRdvCourant() {
		return mRdvCourant;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		mRdvCourant = rdv;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation consultation = new ConsultationM();
		Calendar fin = Calendar.getInstance();
		consultation.setMedecin(this.getMedecin());
		fin.add(Calendar.MINUTE, 15); //durée d'une consultation = 15min
		consultation.setDebut(date);
		consultation.setFin(fin);
		return consultation;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		mRDVs.add(mRdvCourant);
		return mRdvCourant;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		mRDVs.remove(mRdvCourant); 

	}

	private Set<Patient> getPatients() {
		for(Consultation consultation : mRDVs){
			mpatients.add(consultation.getPatient());
		}
		return mpatients;
	}
	
}