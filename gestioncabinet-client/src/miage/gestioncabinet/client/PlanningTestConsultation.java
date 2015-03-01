package miage.gestioncabinet.client;

import java.util.Calendar;
import java.util.List;




import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;

import miage.gestioncabinet.api.ConsultationRemoteService;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinetcoredb.ejbmodule.ConsultationEntity;
import miage.gestioncabinetcoredb.ejbmodule.ConsultationServiceJPA;
import miage.gestioncabinetcoredb.ejbmodule.MedecinEntity;
import miage.gestioncabinetcoredb.ejbmodule.PatientEntity;
import miage.gestioncabinetcoredb.ejbmodule.TraitementEntity;

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
			ConsultationEntity consultationEntity = (ConsultationEntity) app.ejb.creerRdv(date);
			
			app.ejb.setConsultation(consultationEntity);

			app.ejb.enregistrer();

		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			e.printStackTrace();
		}
	}

}
