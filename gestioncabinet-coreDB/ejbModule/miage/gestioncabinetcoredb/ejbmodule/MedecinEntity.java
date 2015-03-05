package miage.gestioncabinetcoredb.ejbmodule;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import miage.gestioncabinet.api.Medecin;

@Entity
@Table(name="t_medecin")
public class MedecinEntity extends PersonneEntity implements Medecin {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="c_compte")
	private String compte;
	
	@Column(name="c_motdepasse")
	private String motDePasse;
	
	@Column(name="c_rpps")
	private String rpps;

	public String getCompte() {
		return compte;
	}

	public void setCompte(String compte) {
		this.compte = compte;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	@Override
	public String getRPPS() {
		return rpps;
	}
	public void setRpps(String rpps) {
		this.rpps = rpps;
	}

}
