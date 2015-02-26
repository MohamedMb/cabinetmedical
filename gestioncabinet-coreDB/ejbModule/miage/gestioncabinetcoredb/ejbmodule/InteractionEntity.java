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

@Entity
@Table(name="t_interaction")
public class InteractionEntity implements Serializable {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_severite")
	private String severite;
	
	@Column(name="c_risques")
	private String risques;
	
	@Column(name="c_preccautions")
	private String preccautions;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_traitement")
	private TraitementEntity traitement;
}

