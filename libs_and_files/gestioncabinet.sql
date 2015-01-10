-- phpMyAdmin SQL Dump
-- version 4.1.12
-- http://www.phpmyadmin.net
--
-- Client :  127.0.0.1
-- Généré le :  Sam 10 Janvier 2015 à 12:06
-- Version du serveur :  5.6.16
-- Version de PHP :  5.5.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données :  `gestioncabinet`
--

-- --------------------------------------------------------

--
-- Structure de la table `t_consultation`
--

CREATE TABLE IF NOT EXISTS `t_consultation` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_id_patient` int(11) NOT NULL,
  `c_dateDebut` datetime NOT NULL,
  `c_dateFin` datetime NOT NULL,
  `c_compteRendu` varchar(255) NOT NULL,
  PRIMARY KEY (`c_id`),
  KEY `c_id_patient` (`c_id_patient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `t_medecin`
--

CREATE TABLE IF NOT EXISTS `t_medecin` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_nom` varchar(255) NOT NULL,
  `c_prenom` varchar(255) NOT NULL,
  `c_compte` varchar(255) NOT NULL,
  `c_motdepasse` varchar(255) NOT NULL,
  `c_rpps` varchar(255) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `t_patient`
--

CREATE TABLE IF NOT EXISTS `t_patient` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_nom` varchar(255) NOT NULL,
  `c_prenom` varchar(255) NOT NULL,
  `c_sexe` varchar(255) NOT NULL,
  `c_dateNaissance` datetime NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Structure de la table `t_traitement`
--

CREATE TABLE IF NOT EXISTS `t_traitement` (
  `c_id` int(11) NOT NULL AUTO_INCREMENT,
  `c_posologie` varchar(255) NOT NULL,
  `c_cis` varchar(255) NOT NULL,
  `c_nom` varchar(255) NOT NULL,
  `c_id_consultation` int(11) DEFAULT NULL,
  PRIMARY KEY (`c_id`),
  KEY `c_id_consultation` (`c_id_consultation`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `t_consultation`
--
ALTER TABLE `t_consultation`
  ADD CONSTRAINT `t_consultation_ibfk_1` FOREIGN KEY (`c_id_patient`) REFERENCES `t_patient` (`c_id`);

--
-- Contraintes pour la table `t_traitement`
--
ALTER TABLE `t_traitement`
  ADD CONSTRAINT `t_traitement_ibfk_1` FOREIGN KEY (`c_id_consultation`) REFERENCES `t_consultation` (`c_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
