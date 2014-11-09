package miage.gestioncabinet.coreM;

import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Produit;

public class ConsultationRemoteServiceM implements ConsultationRemoteService {

	@Override
	public Consultation getConsultation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConsultation(Consultation consultation) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation creerRdv(Calendar date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Produit> rechercherMedicament(String keyword)
			throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void analyserPrescription() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Consultation enregistrer() throws GestionCabinetException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer() throws GestionCabinetException {
		// TODO Auto-generated method stub
		
	}

}
