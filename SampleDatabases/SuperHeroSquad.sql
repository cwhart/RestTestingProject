create database if not exists superhero;

use superhero;

CREATE TABLE if not exists `Super` (
  `ID` BIGINT auto_increment not null,
  `Name` VARCHAR(50) not null,
  `Description` VARCHAR(200) null,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Power` (
  `ID` BIGINT auto_increment not null,
  `Name` VARCHAR(50) not null,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Location` (
  `ID` BIGINT auto_increment,
  `name` VARCHAR(50) not null,
  `description` VARCHAR(200) null,
  `street` VARCHAR(50) not null,
  `city` VARCHAR(30) not null,
  `state` VARCHAR(2) not null,
  `zip` VARCHAR(5) not null,
  `latitude` DECIMAL(5,2) null,
  `longitude` DECIMAL(5,2) null,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Organization` (
  `ID` BIGINT auto_increment not null,
  `location_id` BIGINT not null,
  `name` VARCHAR(50) not null,
  `description` VARCHAR(200) null,
  `phone` VARCHAR(12) null,
  `email` VARCHAR(50) null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`location_id`) references `location`(id) 
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `SuperOrganization` (
  `ID` BIGINT auto_increment not null,
  `super_id` BIGINT not null,
  `organization_id` BIGINT not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`super_id`) references `super`(id),
  FOREIGN KEY (`organization_id`) references `organization`(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Sighting` (
  `ID` BIGINT auto_increment not null,
  `location_id` BIGINT not null,
  `date` DATE not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`location_id`) references `location`(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `SuperSighting` (
  `ID` BIGINT auto_increment not null,
  `super_id` BIGINT not null,
  `sighting_id` BIGINT not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`super_id`) references `super`(id),
  FOREIGN KEY (`sighting_id`) references `sighting`(id)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `SuperPower` (
  `ID` BIGINT auto_increment not null,
  `super_id` BIGINT not null,
  `power_id` BIGINT not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`super_id`) references `super`(id), 
  FOREIGN KEY (`power_id`) references `power`(id) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 





drop database if exists superhero_test;

create database superhero_test;

use superhero_test;

CREATE TABLE if not exists `Super` (
  `ID` BIGINT auto_increment not null,
  `Name` VARCHAR(50) not null,
  `Description` VARCHAR(200) null,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Power` (
  `ID` BIGINT auto_increment not null,
  `Name` VARCHAR(50) not null,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Location` (
  `ID` BIGINT auto_increment,
  `name` VARCHAR(50) not null,
  `description` VARCHAR(200) null ,
  `street` VARCHAR(50) not null,
  `city` VARCHAR(30) not null,
  `state` VARCHAR(2) not null,
  `zip` VARCHAR(5) not null,
  `latitude` DECIMAL(5,2) null,
  `longitude` DECIMAL(5,2) null,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Organization` (
  `ID` BIGINT auto_increment not null,
  `location_id` BIGINT not null,
  `name` VARCHAR(50) not null,
  `description` VARCHAR(200) null,
  `phone` VARCHAR(12) null,
  `email` VARCHAR(50) null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`location_id`) references `location`(id) 
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `SuperOrganization` (
  `ID` BIGINT auto_increment not null,
  `super_id` BIGINT not null,
  `organization_id` BIGINT not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`super_id`) references `super`(id),
  FOREIGN KEY (`organization_id`) references `organization`(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `Sighting` (
  `ID` BIGINT auto_increment not null,
  `location_id` BIGINT not null,
  `date` DATE not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`location_id`) references `location`(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `SuperSighting` (
  `ID` BIGINT auto_increment not null,
  `super_id` BIGINT not null,
  `sighting_id` BIGINT not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`super_id`) references `super`(id),
  FOREIGN KEY (`sighting_id`) references `sighting`(id)  
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 

CREATE TABLE if not exists `SuperPower` (
  `ID` BIGINT auto_increment not null,
  `super_id` BIGINT not null,
  `power_id` BIGINT not null,
  PRIMARY KEY (`ID`),
  FOREIGN KEY (`super_id`) references `super`(id), 
  FOREIGN KEY (`power_id`) references `power`(id) 
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1; 
