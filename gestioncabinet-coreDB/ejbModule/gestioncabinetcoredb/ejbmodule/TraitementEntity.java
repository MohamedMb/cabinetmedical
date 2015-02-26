package gestioncabinetcoredb.ejbmodule;

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


@Entity
@Table(name="t_traitement")
public class TraitementEntity {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_posologie")
	private String posologie;
	
	@Column(name="c_cis")
	private int cis;
	
	@Column(name="c_nom")
	private String nom;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_consultation")
	private ConsultationEntity consultation;
	
	@OneToMany(mappedBy="traitement")
	private List<InteractionEntity> interaction;
	
}
