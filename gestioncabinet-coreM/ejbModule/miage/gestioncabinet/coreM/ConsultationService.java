package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.PrescriptionServiceInterface;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Remote(ConsultationRemoteService.class)
@Local
@Stateful
public class ConsultationService implements ConsultationRemoteService {
	
	@EJB
	private PlanningService mPlanningService;
	
	@EJB
	private PrescriptionServiceInterface mPrescriptionService;
	
	
	private Consultation mConsultation;
	//private List<Produit> mProduits = new ArrayList<Produit>();
	//private List<Consultation> mConsultations = new ArrayList<Consultation>();
	
	@Override
	public Consultation getConsultation() {
		return mConsultation;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		mConsultation = consultation;
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		Consultation consultation = new ConsultationM();
		consultation.setDebut(date);
		return consultation;
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		/*List<Produit> listProd = new ArrayList<Produit>();
		for (Produit produit : mProduits){
			if (produit.getNom().contains(keyword)){
				listProd.add(produit);
			}
		}
		return listProd;*/
		return mPrescriptionService.rechercherProduit(keyword);
	}


	@Override
	public void analyserPrescription() throws GestionCabinetException {
		//mConsultation.getPrescription();
		List<Produit> produits = new ArrayList<Produit>();
		for(Traitement traitement : mConsultation.getPrescription()) {
			produits.add(traitement.getProduit());
		}
		mConsultation.setInteractions(mPrescriptionService.analyser(produits));
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		//mConsultations.add(mConsultation);
		if(!mPlanningService.listerRdv().contains(mConsultation)) {
			mPlanningService.listerRdv().add(mConsultation);
		}
		return mConsultation;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		//mConsultations.remove(mConsultation);
		mPlanningService.listerRdv().remove(mConsultation);
	}
}
