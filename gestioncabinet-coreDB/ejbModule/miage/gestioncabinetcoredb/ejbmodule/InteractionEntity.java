package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import miage.gestioncabinet.api.Produit;

@Entity
@Table(name="t_interaction")
public class InteractionEntity implements Serializable, miage.gestioncabinet.api.Interaction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_severite")
	private String severite;
	
	@Column(name="c_risques")
	private String risques;
	
	@Column(name="c_preccautions")
	private String preccautions;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_consultation")
	private ConsultationEntity consultation;
	
	@ManyToOne(targetEntity=ProduitEntity.class)
	@JoinColumn(name="c_id_traitement1")
	private Produit produitA;
	
	@ManyToOne(targetEntity=ProduitEntity.class)
	@JoinColumn(name="c_id_traitement2")
	private Produit produitB;

	@Override
	public Produit getProduitA() {
		return this.produitA;
	}

	@Override
	public void setProduitA(Produit produit) {
		this.produitA = produit;
	}

	@Override
	public Produit getProduitB() {
		return this.produitB;
	}

	@Override
	public void setProduitB(Produit produit) {
		this.produitB = produit;
	}

	@Override
	public String getSeverite() {
		return this.severite;
	}

	@Override
	public void setSeverite(String severite) {
		this.severite = severite;
	}

	@Override
	public String getRisques() {
		return this.risques;
	}

	@Override
	public void setRisques(String risques) {
		this.risques = risques;
	}

	@Override
	public String getPrecautions() {
		return this.getPrecautions();
	}

	@Override
	public void setPrecautions(String precautions) {
		this.preccautions = precautions;
	}


}

