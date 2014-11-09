package miage.gestioncabinet.coreM;

import java.util.Calendar;

import miage.gestioncabinet.api.Patient;

public class PatientM extends PersonneM implements Patient {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calendar dateNaissance;
	private String sexe;
	public PatientM() {
		super();
	}
	
	public PatientM(String prenom, String nom, Calendar dateNaissance) {
		super(prenom, nom);
		setDateNaissance(dateNaissance);
	}

	@Override
	public Calendar getDateNaissance() {
		return dateNaissance;
	}

	@Override
	public void setDateNaissance(Calendar dateNaissance) {
		this.dateNaissance = dateNaissance;
	}

	@Override
	public Integer getAge() {
		Calendar birth = Calendar.getInstance();
		birth.setTime(dateNaissance.getTime());
		Calendar today = Calendar.getInstance();
		int age = today.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
		
		if (today.get(Calendar.MONTH) < birth.get(Calendar.MONTH)) {
		  age--;  
		} else if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)
		    && today.get(Calendar.DAY_OF_MONTH) < birth.get(Calendar.DAY_OF_MONTH)) {
		  age--;  
		}
		return age;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
}
