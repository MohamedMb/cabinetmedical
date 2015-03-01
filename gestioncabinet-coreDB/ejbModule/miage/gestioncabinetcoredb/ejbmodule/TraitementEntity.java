package miage.gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
	
	@ManyToOne(targetEntity=ProduitEntity.class)
	@JoinColumn(name="c_id_produit")
	private Produit produit;
		
	
	@Override
	public Produit getProduit() {
		return this.produit;
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
		this.produit = produit;
		
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
