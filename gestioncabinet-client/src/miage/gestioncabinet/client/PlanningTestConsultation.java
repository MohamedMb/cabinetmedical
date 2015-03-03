package miage.gestioncabinet.client;

import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinetcoredb.ejbmodule.ConsultationEntity;

import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;

public class PlanningTestConsultation {
	private ConsultationRemoteService ejb;

	public PlanningTestConsultation(){
		String service = "ejb:gestioncabinet-ear/gestioncabinet-coreDB//ConsultationServiceJPA!miage.gestioncabinet.api.ConsultationRemoteService?stateful";
		try{
			ServiceLocator locator = ServiceLocator.INSTANCE;
			this.ejb = (ConsultationRemoteService) locator.getRemoteInterface(service);
		}
		catch(ServiceLocatorException e){
			System.out.println("Le service "+service+" est introuvable");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PlanningTestConsultation app = new PlanningTestConsultation();
		System.out.println("On developpe un scenario de test du planning de consultation en mode persistance");
		
		try {

			Calendar date = Calendar.getInstance();
			Consultation consultationEntity = (ConsultationEntity) app.ejb.creerRdv(date);
			
			app.ejb.setConsultation(consultationEntity);

			
			List<Produit> listMedoc;
			try{
			listMedoc = app.ejb.rechercherMedicament("63368332");
			System.out.println("coucou");
			for(Produit produit : listMedoc) {
				produit.getCis();
				produit.getNom();
				System.out.println("ok\n");
			}
			}catch(Exception e) {
				e.printStackTrace();
			}
			//listMedoc.get(0);
			//app.ejb.enregistrer();

			
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			e.printStackTrace();
		}
	}

}
