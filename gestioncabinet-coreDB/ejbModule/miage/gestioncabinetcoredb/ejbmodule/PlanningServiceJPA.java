package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import miage.gestioncabinet.api.ApplicationServiceInterface;
import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.api.Traitement;
import miage.gestioncabinet.api.Utilisateur;

@Stateful
@Remote(PlanningRemoteService.class)
public class PlanningServiceJPA implements PlanningRemoteService, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	ApplicationServiceInterface appService;
	
	@PersistenceContext(unitName = "gestioncabinet-coreDB")
    private EntityManager entityManager;
	
    private static final String SELECT_ALL_MEDECIN = "SELECT m FROM MedecinEntity m";
    private static final String SELECT_PATIENTS = "SELECT p FROM PatientEntity p where p.nom=:nom and p.prenom=:prenom and p.dateNaissance=:dateNaissance";
    private static final String PARAM_NOM = "nom";
    private static final String PARAM_PRENOM = "prenom";
    private static final String PARAM_DATE_NAISSANCE = "dateNaissance";
    private static final String SELECT_CONSULTATION = "SELECT c FROM ConsultationEntity c WHERE c.medecin = :id";
    private static final String PARAM_ID = "id";


	private MedecinEntity mMedecin;
	private Calendar mDateDebut;
	private Calendar mDateFin;
	private ConsultationEntity mRdvCourant;
	private List<Consultation> mRDVs;
	private List<Medecin> mMedecins;
	private List<Patient> mpatients;
	
	/*public PlanningServiceJPA(){
		//Medecein courant
		this.mRdvCourant = new ConsultationEntity();
		this.mMedecin = new MedecinEntity();
		this.mpatients = new ArrayList<Patient>();
		this.mDateDebut = Calendar.getInstance();
		this.mDateFin = Calendar.getInstance();
	}*/

	@Override
	public Utilisateur getUtilisateur() {
		return this.mMedecin;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {  
		//On gere la liste des medecins
		this.mMedecins = null;
		Query requete = entityManager.createQuery( SELECT_ALL_MEDECIN);
        this.mMedecins= requete.getResultList();
        /*
        for(Medecin medecin : this.mMedecins){
        	medecin.get
        }*/
        
		return requete.getResultList();
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		
		Query requete = entityManager.createQuery( SELECT_PATIENTS );
        requete.setParameter( PARAM_NOM, nom );
        requete.setParameter( PARAM_PRENOM, prenom );
        requete.setParameter( PARAM_DATE_NAISSANCE, dateNaissance);
        try {
        	List<Patient> patients = requete.getResultList();
            return patients;

        } catch ( Exception e ) {
        	System.out.println(e.fillInStackTrace());
        }
        return null;
	}

	@Override
	public Calendar getDateDebut() {
		return this.mDateDebut;
	}

	@Override
	public void setDateDebut(Calendar date) {
		this.mDateDebut = date;
	}

	@Override
	public Calendar getDateFin() {
		return this.mDateFin;
	}

	@Override
	public void setDateFin(Calendar date) {
		this.mDateFin = date;
	}

	@Override
	public Medecin getMedecin() {
		return this.mMedecin;
					
	}

	@Override
	public void setMedecin(Medecin medecin) {
		this.mMedecin = (MedecinEntity) medecin;
	}

	@Override
	public List<Consultation> listerRdv() {
		//On gere la liste des rdv
		this.mRDVs = null;
		
		Query requete = entityManager.createQuery( SELECT_CONSULTATION);
        requete.setParameter( PARAM_ID, this.mMedecin );
		this.mRDVs = requete.getResultList();
		for(Consultation consultation : this.mRDVs){
			consultation.getPatient();
			consultation.getMedecin();
			consultation.getPrescription().size();
			
			consultation.getInteractions().size();
			for(Traitement traitement : consultation.getPrescription()){
				traitement.getProduit();
			}
			for(Interaction interaction : consultation.getInteractions()){
				interaction.getProduitA();
				interaction.getProduitB();
			}
		}
		return this.mRDVs;		
	}

	@Override
	public Consultation getRdvCourant() {
		return this.mRdvCourant;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		this.mRdvCourant = (ConsultationEntity) rdv;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation consultation = new ConsultationEntity();
		Calendar fin = Calendar.getInstance();
		consultation.setMedecin(this.getMedecin());
		fin.add(Calendar.MINUTE, 15); //durée d'une consultation = 15min
		consultation.setDebut(date);
		consultation.setFin(fin);
		return consultation;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		this.entityManager.persist(this.mRdvCourant);
		this.entityManager.flush();
		return mRdvCourant;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		ConsultationEntity consultationEntity = entityManager.find(ConsultationEntity.class, mRdvCourant.getId());
        if (consultationEntity != null) {
              entityManager.remove(consultationEntity);
        }
	}
	

}