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
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
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
    private EntityManager em;
	
    private static final String SELECT_ALL_MEDECIN = "SELECT m FROM MedecinEntity m";
    private static final String SELECT_PATIENTS = "SELECT p FROM PatientEntity p where p.nom=:nom and p.prenom=:prenom and p.dateNaissance=:dateNaissance";
    private static final String PARAM_NOM = "nom";
    private static final String PARAM_PRENOM = "prenom";
    private static final String PARAM_DATE_NAISSANCE = "dateNaissance";
    private static final String SELECT_CONSULTATION = "SELECT c FROM ConsultationEntity c JOIN c.medecin m WHERE m.id=:id";
    private static final String PARAM_ID = "id";


	private MedecinEntity mMedecin;
	private Calendar mDateDebut;
	private Calendar mDateFin;
	private ConsultationEntity mRdvCourant;
	private List<Consultation> mRDVs;
	private List<Medecin> mMedecins;
	private List<Patient> mpatients;
	
	public PlanningServiceJPA(){
		//Medecein courant
		this.mRdvCourant = new ConsultationEntity();
		this.mMedecin = new MedecinEntity();
		this.mpatients = new ArrayList<Patient>();
		this.mDateDebut = Calendar.getInstance();
		this.mDateFin = Calendar.getInstance();
	}
	


	@Override
	public Utilisateur getUtilisateur() {
		return this.mMedecin;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {  
		//On gere la liste des medecins
		this.mMedecins = new ArrayList<Medecin>();
		Query requete = em.createQuery( SELECT_ALL_MEDECIN);
        this.mMedecins.addAll(requete.getResultList());
        
		return mMedecins;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		
		Query requete = em.createQuery( SELECT_PATIENTS );
        requete.setParameter( PARAM_NOM, nom );
        requete.setParameter( PARAM_PRENOM, prenom );
        requete.setParameter( PARAM_DATE_NAISSANCE, dateNaissance.getTime() );
        try {
            return requete.getResultList();

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
		this.mRDVs = new ArrayList<Consultation>();
		
		Query requete = em.createQuery( SELECT_CONSULTATION);
        requete.setParameter( PARAM_ID, this.mMedecin.getId() );
		this.mRDVs.addAll(requete.getResultList());
		return mRDVs;		
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
		this.em.persist(this.mRdvCourant);
		this.em.flush();
		return mRdvCourant;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		ConsultationEntity ce = em.find(ConsultationEntity.class, mRdvCourant.getId());
        if (ce != null) 
        {
              em.remove(ce);
        }
	}
	

}
