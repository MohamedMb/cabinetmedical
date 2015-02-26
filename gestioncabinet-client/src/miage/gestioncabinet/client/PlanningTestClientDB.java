package miage.gestioncabinet.client;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;

import miage.gestioncabinet.api.GestionCabinetException;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.Patient;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinetcoredb.ejbmodule.PatientEntity;

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
			System.out.println("Creation Medecin");
			Medecin m = app.ejb.getMedecin();
			System.out.println("On recupere la liste de medecin");
			List<Medecin> lm = app.ejb.rechercherMedecins();
			System.out.println(lm.size());
			System.out.println("On recupere la liste de patients");
				Calendar birth = Calendar.getInstance();

				birth.set(Calendar.YEAR, 1991);
				birth.set(Calendar.MONTH, 0);
				birth.set(Calendar.DAY_OF_MONTH, 16);
				birth.set(Calendar.HOUR_OF_DAY, 0);
				birth.set(Calendar.MINUTE, 0);
				birth.set(Calendar.SECOND, 0);
				birth.set(Calendar.MILLISECOND, 0);
				
				List<Patient> patients = app.ejb.rechercherPatients("MOUSSA MZE", "Oussama", birth);
				System.out.println("Taille -> " + patients.size());
				System.out.println("Date -> " + patients.get(0).getDateNaissance().getTime());


		} catch (Exception e) {
			System.out.println(e.fillInStackTrace());
			e.printStackTrace();
		}
	}
}
