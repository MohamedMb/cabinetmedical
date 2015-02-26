package gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
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
	
    private static final String SELECT_MEDECIN = "SELECT * FROM t_patient WHERE c_nom = :nom and c_prenom = :prenom";
    private static final String PARAM_NOM = "nom";
    private static final String PARAM_PRENOM = "prenom";

	private Medecin mMedecin;
	private Calendar mDateDebut;
	private Calendar mDateFin;
	private Utilisateur mUtilisateur ;
	private Consultation mRdvCourant;
	private List<Consultation> mRDVs;
	private List<Medecin> mMedecins;
	private Set<Patient> mpatients;
	
	public PlanningServiceJPA(){
		this.mRDVs = new ArrayList<Consultation>();
		this.mMedecins = new ArrayList<Medecin>();
		this.mpatients = new HashSet<Patient>();
		this.mDateDebut = Calendar.getInstance();
		this.mDateFin = Calendar.getInstance();
	}
	


	@Override
	public Utilisateur getUtilisateur() {
		return this.mMedecin;
	}

	@Override
	public List<Medecin> rechercherMedecins() throws GestionCabinetException {
		return mMedecins;
	}

	@Override
	public List<Patient> rechercherPatients(String nom, String prenom,
			Calendar dateNaissance) throws GestionCabinetException {
		
        Query requete = em.createQuery( SELECT_MEDECIN );
        requete.setParameter( PARAM_NOM, nom );
        requete.setParameter( PARAM_PRENOM, prenom );

        try {
            return requete.getResultList();

        } catch ( Exception e ) {
        	System.out.println(e.fillInStackTrace());
        }
        return null;
	}

	@Override
	public Calendar getDateDebut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDateDebut(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calendar getDateFin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDateFin(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medecin getMedecin() {
		Query requete = em.createQuery( "select p from MedecinEntity p where p.nom = 'ABDOU'" );
		requete.getSingleResult();
		return new Medecin() {
			
			@Override
			public void setPrenom(String prenom) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setNom(String nom) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public String getPrenom() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getNom() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getCompte() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getRPPS() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Override
	public void setMedecin(Medecin medecin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Consultation> listerRdv() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation getRdvCourant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRdvCourant(Consultation rdv) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Consultation enregistrerRdv() throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimerRdv() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}
}
