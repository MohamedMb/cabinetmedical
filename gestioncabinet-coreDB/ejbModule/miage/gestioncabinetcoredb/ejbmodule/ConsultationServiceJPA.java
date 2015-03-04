package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	private ApplicationServiceInterface appService;
	
	@EJB
	private PrescriptionServiceInterface mPrescriptionService;
	
	@PersistenceContext(unitName = "gestioncabinet-coreDB")
    private EntityManager entityManager;
	
	private ConsultationEntity consultationEntity;
    private static final String SELECT_MEDICAMENT = "SELECT t FROM TraitementEntity t WHERE t.nom LIKE :key";
    private static final String PARAM_KEY = "key";

	public ConsultationServiceJPA(){
		this.consultationEntity = new ConsultationEntity();
	}
	@Override
	public Consultation getConsultation() {
		return this.consultationEntity;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		this.consultationEntity = (ConsultationEntity) consultation;
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

	@Override
	public List<Produit> rechercherMedicament(String keyword) throws GestionCabinetException {
		
			/*Query requete = em.createQuery(SELECT_MEDICAMENT);
			requete.setParameter(SELECT_MEDICAMENT, "%" + keyword + "%");

			return requete.getResultList();*/
		return mPrescriptionService.rechercherProduit(keyword);
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		this.entityManager.persist(this.consultationEntity);
		this.entityManager.flush();
		return this.consultationEntity;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		ConsultationEntity ce = entityManager.find(ConsultationEntity.class, consultationEntity.getId());
        if (ce != null) 
        {
              entityManager.remove(ce);
        }
	}
	
	public void ajouterMedicament(TraitementEntity traitementEntity){
		this.entityManager.persist(traitementEntity);
		this.entityManager.flush();
	}

}
