package gestioncabinetcoredb.ejbmodule;

import java.io.Serializable;
import java.util.Calendar;
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
@Table(name="t_consultation")
public class ConsultationEntity implements Serializable{

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name="c_id")
	private Long id;
	
	@Column(name="c_dateDebut")
	private Calendar dateDebut;
	
	@Column(name="c_dateFin")
	private Calendar dateFin;
	
	@Column(name="c_compteRendu")
	private String compteRendu;
	
	@Column(name="c_dateRdv")
	private Calendar dateRdv;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_medecin")
	private MedecinEntity medecin;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="c_id_patient")
	private PatientEntity patient;
	
	@OneToMany(mappedBy="consultation")
	private List<TraitementEntity> traitement;
}
