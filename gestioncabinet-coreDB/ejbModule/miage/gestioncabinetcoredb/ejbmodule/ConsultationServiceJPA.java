package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import miage.gestioncabinet.api.ApplicationServiceInterface;
import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.PrescriptionServiceInterface;
import miage.gestioncabinet.api.Produit;

@Stateful
@Remote(ConsultationRemoteService.class)
public class ConsultationServiceJPA implements ConsultationRemoteService, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private ApplicationServiceInterface mAppService;
	
	@EJB
	private PrescriptionServiceInterface mPrescriptionService;
	
	@PersistenceContext(unitName = "gestioncabinet-coreDB")
    private EntityManager mEntityManager;
	
	private ConsultationEntity mConsultationEntity;
	private List<Produit> mProduits;
    private static final String SELECT_MEDICAMENT = "SELECT t FROM TraitementEntity t WHERE t.nom LIKE :key";
    private static final String PARAM_KEY = "key";

	public ConsultationServiceJPA(){
		mConsultationEntity = new ConsultationEntity();
	}
	@Override
	public Consultation getConsultation() {
		return mConsultationEntity;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		mConsultationEntity = (ConsultationEntity) consultation;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		ConsultationEntity consultationEntity = new ConsultationEntity();
		Calendar dateFin = (Calendar) date.clone();
		dateFin.add(Calendar.MINUTE, 15);
		consultationEntity.setDebut(date);
		consultationEntity.setFin(dateFin);
		setConsultation(consultationEntity);
		return getConsultation();
	}

	/**
	 * Pioche depuis la bdd, s'il n'y a rien => pioche 
	 * depuis le webservice et les enregistres dans la bdd
	 */
	@Override
	public List<Produit> rechercherMedicament(String keyword) throws GestionCabinetException {
		
		/*Query requete = entityManager.createQuery(SELECT_MEDICAMENT);
		requete.setParameter(SELECT_MEDICAMENT, "%" + keyword + "%");

		mProduits = requete.getResultList();*/
		
		if(mProduits == null || mProduits.isEmpty()) {
			mProduits = new ArrayList<Produit>();
			mProduits = mPrescriptionService.rechercherProduit(keyword);
			if(mProduits.size() > 0) {
				for(Produit produit : mProduits) {
					mEntityManager.persist((ProduitEntity)produit);
				}
				mEntityManager.flush();
			}
			
		}
		return mProduits;
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		mPrescriptionService.analyser(mProduits);
		
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		mEntityManager.persist(this.mConsultationEntity);
		mEntityManager.flush();
		return mConsultationEntity;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		ConsultationEntity ce = mEntityManager.find(ConsultationEntity.class, mConsultationEntity.getId());
        if (ce != null) 
        {
              mEntityManager.remove(ce);
        }
	}
	
	public void ajouterMedicament(TraitementEntity traitementEntity){
		mEntityManager.persist(traitementEntity);
		mEntityManager.flush();
	}

}
