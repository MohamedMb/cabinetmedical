--
-- Base de données :  `gestioncabinet`
--

-- --------------------------------------------------------

--
-- Structure de la table `t_consultation`
--

CREATE TABLE IF NOT EXISTS `t_consultation` (
  `c_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `c_id_patient` int(11) DEFAULT NULL,
  `c_dateDebut` datetime DEFAULT NULL,
  `c_dateFin` datetime DEFAULT NULL,
  `c_compteRendu` varchar(255) DEFAULT NULL,
  `c_dateRdv` date DEFAULT NULL,
  `c_id_medecin` int(11) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structure de la table `t_interaction`
--

CREATE TABLE IF NOT EXISTS `t_interaction` (
  `c_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `c_severite` varchar(50) DEFAULT NULL,
  `c_risques` varchar(50) DEFAULT NULL,
  `c_preccautions` varchar(50) DEFAULT NULL,
  `c_id_consultation` int(11) DEFAULT NULL,
  `c_id_traitement1` int(11) DEFAULT NULL,
  `c_id_traitement2` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `t_medecin`
--

CREATE TABLE IF NOT EXISTS `t_medecin` (
  `c_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `c_nom` varchar(255) DEFAULT NULL,
  `c_prenom` varchar(255) DEFAULT NULL,
  `c_compte` varchar(255) DEFAULT NULL,
  `c_motdepasse` varchar(255) DEFAULT NULL,
  `c_rpps` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structure de la table `t_patient`
--

CREATE TABLE IF NOT EXISTS `t_patient` (
  `c_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `c_nom` varchar(255) DEFAULT NULL,
  `c_prenom` varchar(255) DEFAULT NULL,
  `c_sexe` varchar(255) DEFAULT NULL,
  `c_dateNaissance` date DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;


CREATE TABLE IF NOT EXISTS `t_produit` (
  `c_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `c_cis` varchar(255) DEFAULT NULL,
  `c_nom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


-- --------------------------------------------------------

--
-- Structure de la table `t_traitement`
--

CREATE TABLE IF NOT EXISTS `t_traitement` (
  `c_id` int(11) PRIMARY KEY AUTO_INCREMENT,
  `c_posologie` varchar(255) DEFAULT NULL,
  `c_cis` varchar(255) DEFAULT NULL,
  `c_nom` varchar(255) DEFAULT NULL,
  `c_id_consultation` int(11) DEFAULT NULL,
  `c_id_produit` int(11) DEFAULT NULL
  
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;



--
-- Index pour la table `t_consultation`
--
ALTER TABLE `t_consultation`
 ADD KEY `c_id_patient` (`c_id_patient`), ADD KEY `c_id_medecin` (`c_id_medecin`);

--
-- Index pour la table `t_interaction`
--
ALTER TABLE `t_interaction`
 ADD KEY `c_id_traitement1` (`c_id_traitement1`), ADD KEY `c_id_traitement2` (`c_id_traitement2`), ADD KEY `c_id_consultation` (`c_id_consultation`);


--
-- Index pour la table `t_traitement`
--
ALTER TABLE `t_traitement`
 ADD KEY `c_id_consultation` (`c_id_consultation`), ADD KEY `c_id_produit` (`c_id_produit`);



--
-- Contraintes pour la table `t_consultation`
--
ALTER TABLE `t_consultation`
ADD CONSTRAINT `t_consultation_ibfk_1` FOREIGN KEY (`c_id_patient`) REFERENCES `t_patient` (`c_id`),
ADD CONSTRAINT `t_consultation_ibfk_2` FOREIGN KEY (`c_id_medecin`) REFERENCES `t_medecin` (`c_id`);

--
-- Contraintes pour la table `t_interaction`
--
ALTER TABLE `t_interaction`
ADD CONSTRAINT `t_interaction_ibfk_1` FOREIGN KEY (`c_id_traitement1`) REFERENCES `t_traitement` (`c_id`),
ADD CONSTRAINT `t_interaction_ibfk_2` FOREIGN KEY (`c_id_traitement2`) REFERENCES `t_traitement` (`c_id`),
ADD CONSTRAINT `t_interaction_ibfk_3` FOREIGN KEY (`c_id_consultation`) REFERENCES `t_consultation` (`c_id`);

--
-- Contraintes pour la table `t_traitement`
--
ALTER TABLE `t_traitement`
ADD CONSTRAINT `t_traitement_ibfk_1` FOREIGN KEY (`c_id_consultation`) REFERENCES `t_consultation` (`c_id`),
ADD CONSTRAINT `t_traitement_ibfk_2` FOREIGN KEY (`c_id_produit`) REFERENCES `t_produit` (`c_id`);

--
-- Contenu de la table `t_consultation`
--
INSERT INTO `t_consultation` (`c_id_patient`, `c_dateDebut`, `c_dateFin`, `c_compteRendu`, `c_dateRdv`, `c_id_medecin`) VALUES
(1, '2015-02-28 00:00:00', '2015-02-28 00:00:00', 'compte rendu 1', '2015-02-28', 1),
(1, '2015-02-28 00:00:00', '2015-02-28 00:00:00', NULL, NULL, 1),
(1, '2015-02-28 00:00:00', '2015-02-28 00:00:00', NULL, NULL, 1),
(1, '2015-02-28 00:00:00', '2015-02-28 00:00:00', NULL, NULL, 1);

--
-- Contenu de la table `t_medecin`
--
INSERT INTO `t_medecin` (`c_nom`, `c_prenom`, `c_compte`, `c_motdepasse`, `c_rpps`) VALUES
('ABDOU', 'Said', 'saidABDOU', '12345', '00001');

--
-- Contenu de la table `t_patient`
--
INSERT INTO `t_patient` (`c_nom`, `c_prenom`, `c_sexe`, `c_dateNaissance`) VALUES
('MOUSSA MZE', 'Oussama', 'masculin', '1991-10-16');

--
-- Contenu de la table `t_traitement`
--
INSERT INTO `t_traitement` (`c_posologie`, `c_cis`, `c_nom`, `c_id_consultation`) VALUES
('posologie 1', 'cis 1 ', 'nom 1', 1);
