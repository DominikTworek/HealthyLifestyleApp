create table user
(
  IdUser   int auto_increment
    primary key,
  Login    varchar(255) not null,
  Password varchar(255) not null,
  Imie     varchar(255) not null,
  Nazwisko varchar(255) not null,
  Plec     varchar(255) not null,
  Pesel    varchar(255) not null,
  Rola     varchar(255) not null
);

create table user_profile
(
  ID      int auto_increment
    primary key,
  USER_ID int          not null,
  height  varchar(255) null,
  weight  varchar(255) null,
  neat    varchar(255) null,
  goal    varchar(255) null,
  other   varchar(512) null,
  constraint user_fk
  foreign key (USER_ID) references user (IdUser)
);

create index user_fk
  on user_profile (USER_ID);


