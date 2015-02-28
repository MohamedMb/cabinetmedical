package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import miage.gestioncabinet.api.Consultation;
import miage.gestioncabinet.api.Produit;
import miage.gestioncabinet.api.Traitement;


@Entity
@Table(name="t_traitement")
public class TraitementEntity implements Serializable, Traitement {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_posologie")
	private String posologie;
	
	@Column(name="c_cis")
	private String cis;
	
	@Column(name="c_nom")
	private String nom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_consultation")
	private ConsultationEntity consultation;
	
	@OneToMany(mappedBy="traitement1")
	private List<InteractionEntity> interaction1;
	
	@OneToMany(mappedBy="traitement2")
	private List<InteractionEntity> interaction2;
		
	
	public ConsultationEntity getConsultationEntity(){
		return this.consultation;
	}
	
	public void setConsultationEntity(ConsultationEntity consultation){
		this.consultation = consultation;
	}
	
	@Override
	public Produit getProduit() {
		TraitementEntity te = new TraitementEntity();
		te.setCis(this.cis);
		te.setNom(this.nom);
		return (Produit) te;
	}

	public String getCis() {
		return cis;
	}

	public void setCis(String cis) {
		this.cis = cis;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public void setProduit(Produit produit) {
		this.cis = produit.getCis();
		
	}

	@Override
	public String getPosologie() {
		return this.posologie;
	}

	@Override
	public void setPosologie(String posologie) {
		this.posologie = posologie;
	}
	
}
