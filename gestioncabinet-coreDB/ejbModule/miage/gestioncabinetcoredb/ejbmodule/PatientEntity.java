package miage.gestioncabinetcoredb.ejbmodule;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import miage.gestioncabinet.api.Patient;

@Entity
@Table(name="t_patient")
public class PatientEntity extends PersonneEntity implements Patient{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name="c_sexe")
	private String sexe;
	
	@Column(name="c_dateNaissance")
	@Temporal(TemporalType.DATE)
	private Calendar dateNaissance;

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
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

	@Override
	public Calendar getDateNaissance() {
		return this.dateNaissance;
	}


	
}
