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
  
  create table training
(
  ID_training int auto_increment
    primary key,
  chest       varchar(255) null,
  shoulders   varchar(255) null,
  biceps      varchar(255) null,
  triceps     varchar(255) null,
  back        varchar(255) null,
  abs         varchar(255) null,
  legs        varchar(255) null,
  chest_s     int          null,
  shoulders_s int          null,
  biceps_s    int          null,
  triceps_s   int          null,
  back_s      int          null,
  abs_s       int          null,
  legs_s      int          null,
  chest_p     int          null,
  shoulders_p int          null,
  biceps_p    int          null,
  triceps_p   int          null,
  back_p      int          null,
  abs_p       int          null,
  legs_p      int          null,
  ID_user     int          null,
  constraint training_user_IdUser_fk
  foreign key (ID_user) references user (IdUser)
);

create index training_user_IdUser_fk
  on training (ID_user);


create table trainer_profile
(
  id_trainer_profile int auto_increment
    primary key,
  id_trainer         int          null,
  specjalizacja      varchar(255) not null,
  informacje         varchar(255) not null,
  constraint trainer_profile_user_IdUser_fk
    foreign key (id_trainer) references user (iduser)
);

create table calendarevent
(
  IdEvent     int auto_increment
    primary key,
  IdUser      int          not null,
  day         int          not null,
  month       int          not null,
  year        int          not null,
  hour        int          not null,
  title       varchar(255) not null,
  description varchar(255) not null
)
  collate = utf8_polish_ci;

create table nutrition
(
  ID_nutrition int auto_increment
    primary key,
  calories     int not null,
  protein      int not null,
  carbs        int not null,
  fat          int not null,
  sugars       int not null,
  saturedfat   int not null,
  unsaturedfat int not null,
  ID_user      int not null
);