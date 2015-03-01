package miage.gestioncabinet.coreM;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;

public class ConsultationService implements ConsultationRemoteService {
	private Consultation mConsultation;
	private List<Produit> mProduits = new ArrayList<Produit>();
	private List<Consultation> mConsultations = new ArrayList<Consultation>();
	
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

	/**
	 * WEBSERVICE
	 */
	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		List<Produit> listProd = new ArrayList<Produit>();
		for (Produit produit : mProduits){
			if (produit.getNom().contains(keyword)){
				listProd.add(produit);
			}
		}
		return listProd;
	}


	/**
	 * WEBSERVICE
	 */
	@Override
	public void analyserPrescription() throws GestionCabinetException {
		mConsultation.getPrescription();
		
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		mConsultations.add(mConsultation);
		return mConsultation;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		mConsultations.remove(mConsultation);
	}
}
