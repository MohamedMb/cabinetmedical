package miage.gestioncabinet.coreM;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateful;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Interaction;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;

@Stateful
public class ConsultationM implements Consultation {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compareTo(Consultation o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Patient getPatient() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPatient(Patient patient) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Medecin getMedecin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMedecin(Medecin medecin) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calendar getDebut() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setDebut(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Calendar getFin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setFin(Calendar date) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getCompteRendu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setCompteRendu(String texte) {
		// TODO Auto-generated method stub
		
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
