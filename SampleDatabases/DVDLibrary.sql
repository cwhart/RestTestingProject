DROP DATABASE if exists dvd_library;

CREATE DATABASE dvd_library;

USE dvd_library;

CREATE TABLE IF NOT EXISTS `director` (
`director_id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(20),
`last_name` varchar(30) NOT NULL,
PRIMARY KEY (`director_id`)
);

CREATE TABLE IF NOT EXISTS `dvd` (
 `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
 `director_id` int(11) NOT NULL,
 `dvd_title` varchar(50) NOT NULL,
 `release_date` DATE NOT NULL, 
 `rating` varchar(5) DEFAULT NULL,
 `notes` varchar(100) NOT NULL,
  PRIMARY KEY (`dvd_id`),
  FOREIGN KEY(`director_id`) REFERENCES director(`director_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;

DROP DATABASE if exists dvd_library_test;

CREATE DATABASE dvd_library_test;

USE dvd_library_test;

CREATE TABLE IF NOT EXISTS `director` (
`director_id` int(11) NOT NULL AUTO_INCREMENT,
`first_name` varchar(20),
`last_name` varchar(30) NOT NULL,
PRIMARY KEY (`director_id`)
);

CREATE TABLE IF NOT EXISTS `dvd` (
 `dvd_id` int(11) NOT NULL AUTO_INCREMENT,
 `director_id` int(11) NOT NULL,
 `dvd_title` varchar(50) NOT NULL,
 `release_date` DATE NOT NULL, 
 `rating` varchar(5) DEFAULT NULL,
 `notes` varchar(100) NOT NULL,
  PRIMARY KEY (`dvd_id`),
  FOREIGN KEY(`director_id`) REFERENCES director(`director_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=23 ;