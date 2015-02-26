package miage.gestioncabinet.client;

import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;

import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.PlanningRemoteService;

public class PlanningTestClientDB {

	private PlanningRemoteService ejb;

	public PlanningTestClientDB(){
		String service = "ejb:gestioncabinet-ear/gestioncabinet-coreDB//PlanningServiceJPA!miage.gestioncabinet.api.PlanningRemoteService?stateful";
		try{
			ServiceLocator locator = ServiceLocator.INSTANCE;
			this.ejb = (PlanningRemoteService) locator.getRemoteInterface(service);
		}
		catch(ServiceLocatorException e){
			System.out.println("Le service "+service+" est introuvable");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		PlanningTestClientDB app = new PlanningTestClientDB();
		System.out.println("On developpe un scenario de test du planning de consultation en mode persistance");
		
		try {
			System.out.println("Creation");

			Medecin m = app.ejb.getMedecin();
			System.out.println("affichage");
			//System.out.println(m.getPrenom());
		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			e.printStackTrace();
		}
	}
}
