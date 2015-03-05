package miage.gestioncabinet.client;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Medecin;
import miage.gestioncabinet.api.PlanningRemoteService;
import miage.gestioncabinet.coreM.PlanningService;

import com.novarem.jndi.ServiceLocator;
import com.novarem.jndi.ServiceLocatorException;


/**
 * Programme client permettant de tester le planning.
 * Déployer sur JBoss 7 l'application JEE de gestion du cabinet
 * L'application client a besoin de référencer dans son classpath (clic droit sur le projet > Properties > Java Build Path :
 * - le service locator (projet com.novarem.jndi),
 * - le projet contenant l'interface distante (idéalement le projet miage.gestioncabinet.api qui est un simple "jar" compatible Java SE),
 * - le projet EJB (dépendance à l'exécution)
 * @author sraybaud
 *
 */
public class PlanningTestClient {
	/**
	 * L'interface distante de l'EJB
	 */
	private PlanningRemoteService ejb;

	/**
	 * Constructeur avec lookup pour récupérer le proxy de l'EJB
	 * @see java.lang.Object#Object()
	 */
	public PlanningTestClient() {
		String service = "ejb:gestioncabinet-earM/gestioncabinet-coreM//PlanningService!miage.gestioncabinet.api.PlanningRemoteService?stateful";
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
		PlanningTestClient app = new PlanningTestClient();
		System.out.println("On developpe un scenario de test du planning de consultation");
		
		try{
			Medecin medecin = app.ejb.getMedecin();
			System.out.println("Selection du medecin courant : "+medecin);
			
	
			DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM);
			
			app.ejb.setDateDebut(df.getCalendar());
			
			Calendar dateFin = (Calendar) df.getCalendar().clone();
			dateFin.add(Calendar.MINUTE, 15);
			
			app.ejb.setDateFin(dateFin);
			
			System.out.println("Planning du jour : " + app.ejb.getDateDebut().getTime());
			
			System.out.println(df.format(app.ejb.getDateFin().getTime()));


			
			System.out.println("Planning du jour : du "+df.format(app.ejb.getDateDebut().getTime())+" au "+df.format(app.ejb.getDateFin().getTime()));
			String[] noms = {"MARTIN", "DUPOND", "GIUDICELLI"};
			String[] prenoms = {"Jean", "Henri", "Jeannette"};
			String[] datesNaissance = {"12/03/1964", "23/02/1958", "20/07/1943"};
			for(int i=0 ; i<3 ; i++){
				Calendar date = (Calendar) app.ejb.getDateDebut().clone();
				date.set(Calendar.HOUR_OF_DAY, 9);
				date.add(Calendar.HOUR_OF_DAY, i);
				Consultation rdv = app.ejb.creerRdv(date);
				rdv.getPatient().setNom(noms[i]);
				rdv.getPatient().setPrenom(prenoms[i]);
				Calendar dateNaissance = Calendar.getInstance();
				dateNaissance.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(datesNaissance[i]));
				rdv.getPatient().setDateNaissance(dateNaissance);
				app.ejb.setRdvCourant(rdv);
				app.ejb.enregistrerRdv();
				System.out.println("Enregistrement réussi du rdv de "+rdv+" (patient(e) de "+rdv.getPatient().getAge()+" ans).");
			}
			
			List<Consultation> rdvs = app.ejb.listerRdv();
			System.out.println("Rercherche des "+rdvs.size()+" rendez-vous pris pour la journée de "+medecin+" :");
			for(Consultation rdv : rdvs){
				System.out.println("- "+rdv);
			}
			
			Consultation rdv = rdvs.get(1);
			app.ejb.setRdvCourant(rdv);
			app.ejb.supprimerRdv();
			System.out.println("Annulation du rdv de "+rdv);
			
			rdvs = app.ejb.listerRdv();
			System.out.println("Il ne reste plus que "+rdvs.size()+" rendez-vous pour le "+medecin+" :");
			for(Consultation r : rdvs){
				System.out.println("- "+r);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

}