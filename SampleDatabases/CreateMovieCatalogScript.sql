CREATE database if not exists MovieCatalogue;

Use MovieCatalogue;
Create table if not exists Genre (
	GenreID int(11) not null auto_increment,
    GenreName varchar(30) not null,
    Primary key (GenreID)
);

Create table if not exists Director (
	DirectorID int(11) not null auto_increment,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    BirthDate date null,
    Primary Key (DirectorID)
);

Create table if not exists Rating (
	RatingID int(11) not null auto_increment,
    RatingName varchar(5) not null,
    Primary Key (RatingID)
);

Create table if not exists Actor (
	ActorID int(11) not null auto_increment,
    FirstName varchar(30) not null,
    LastName varchar(30) not null,
    BirthDate date null,
    primary key (ActorID)
);

Create table if not exists Movie (
	MovieID int(11) not null auto_increment,
    GenreID int(11) not null,
    DirectorID int(11) not null,
    RatingID int(11) not null,
    Title varchar(128) not null,
    ReleaseDate date,
    Primary key (MovieID),
    foreign key (GenreID) references Genre(GenreID),
    foreign key (DirectorID) references Director(DirectorID),
    foreign key (RatingID) references Rating(RatingID)

);

Create table if not exists CastMember (
	CastMemberID int(11) not null auto_increment,
    ActorID int(11) not null,
    MovieID int(11) not null,
    Role varchar(30) not null,
    primary key (CastMemberID),
    foreign key (ActorID) references Actor(ActorID),
    foreign key (MovieID) references Movie(MovieID)
    
);

