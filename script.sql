DROP TABLE CALENDAREVENT;
DROP TABLE NUTRITION;
DROP TABLE TRAINING;
DROP TABLE USER_PROFILE;
DROP TABLE TRAINER_PROFILE;
DROP TABLE USERS;
DROP TABLE USER_PROGRESS;

DROP SEQUENCE CALENDAREVENT_SEQ;
DROP SEQUENCE NUTRITION_SEQ;
DROP SEQUENCE TRAINING_SEQ;
DROP SEQUENCE USER_PROFILE_SEQ;
DROP SEQUENCE TRAINER_PROFILE_SEQ;
DROP SEQUENCE USERS_SEQ;
DROP SEQUENCE USER_PROGRESS_SEQ;

create table users
(
  IdUser   number(10) 
  primary key,
  Login    varchar2(255) not null,
  Password varchar2(255) not null,
  Imie     varchar2(255) not null,
  Nazwisko varchar2(255) not null,
  Plec     varchar2(255) not null,
  Pesel    varchar2(255) not null,
  Rola     varchar2(255) not null,
  IDUSER_U NUMBER
);

-- Generate ID using sequence and trigger
create sequence users_seq start with 1 increment by 1;

create or replace trigger users_seq_tr
 before insert on users for each row
 when (new.IdUser is null)
begin
 select users_seq.nextval into :new.IdUser from dual;
end;
/

create table user_profile
(
  ID      number(10) 
  primary key,
  USER_ID number(10)          not null,
  height  varchar2(255) null,
  weight  varchar2(255) null,
  neat    varchar2(255) null,
  goal    varchar2(255) null,
  other   varchar2(512) null,
  constraint users_fk
  foreign key (USER_ID) references users (IdUser)
);

-- Generate ID using sequence and trigger
create sequence user_profile_seq start with 1 increment by 1;

create or replace trigger user_profile_seq_tr
 before insert on user_profile for each row
 when (new.ID is null)
begin
 select user_profile_seq.nextval into :new.ID from dual;
end;
/

create index users_fk
  on user_profile (USER_ID);

create table training
(
  ID_training number(10) 
  primary key,
  chest       varchar2(255) null,
  shoulders   varchar2(255) null,
  biceps      varchar2(255) null,
  triceps     varchar2(255) null,
  back        varchar2(255) null,
  abs         varchar2(255) null,
  legs        varchar2(255) null,
  chest_s     number(10)          null,
  shoulders_s number(10)          null,
  biceps_s    number(10)          null,
  triceps_s   number(10)          null,
  back_s      number(10)          null,
  abs_s       number(10)          null,
  legs_s      number(10)          null,
  chest_p     number(10)          null,
  shoulders_p number(10)          null,
  biceps_p    number(10)          null,
  triceps_p   number(10)          null,
  back_p      number(10)          null,
  abs_p       number(10)          null,
  legs_p      number(10)          null,
  ID_user     number(10)          null,
  constraint training_users_IdUser_fk
  foreign key (ID_user) references users (IdUser)
);

-- Generate ID using sequence and trigger
create sequence training_seq start with 1 increment by 1;

create or replace trigger training_seq_tr
 before insert on training for each row
 when (new.ID_training is null)
begin
 select training_seq.nextval into :new.ID_training from dual;
end;
/

create index training_users_IdUser_fk
  on training (ID_user);


create table trainer_profile
(
  id_trainer_profile number(10) 
  primary key,
  id_trainer         number(10)          null,
  specjalizacja      varchar2(255) not null,
  informacje         varchar2(255) not null,
  constraint trainer_profile_user_IdUser_fk
  foreign key (id_trainer) references users (iduser)
);

-- Generate ID using sequence and trigger
create sequence trainer_profile_seq start with 1 increment by 1;

create or replace trigger trainer_profile_seq_tr
 before insert on trainer_profile for each row
 when (new.id_trainer_profile is null)
begin
 select trainer_profile_seq.nextval into :new.id_trainer_profile from dual;
end;
/

create table calendarevent
(
  IdEvent     number(10) 
  primary key,
  IdUser      number(10)          not null,
  day         number(10)          not null,
  month       number(10)          not null,
  year        number(10)          not null,
  hour        number(10)          not null,
  title       varchar2(255) not null,
  description varchar2(255) not null
)
 ;

-- Generate ID using sequence and trigger
create sequence calendarevent_seq start with 1 increment by 1;

create or replace trigger calendarevent_seq_tr
 before insert on calendarevent for each row
 when (new.IdEvent is null)
begin
 select calendarevent_seq.nextval into :new.IdEvent from dual;
end;
/

create table nutrition
(
  ID_nutrition number(10) 
  primary key,
  calories     number(10) not null,
  protein      number(10) not null,
  carbs        number(10) not null,
  fat          number(10) not null,
  sugars       number(10) not null,
  saturedfat   number(10) not null,
  unsaturedfat number(10) not null,
  ID_user      number(10) not null
);

-- Generate ID using sequence and trigger
create sequence nutrition_seq start with 1 increment by 1;

create or replace trigger nutrition_seq_tr
 before insert on nutrition for each row
 when (new.ID_nutrition is null)
begin
 select nutrition_seq.nextval into :new.ID_nutrition from dual;
end;
/


CREATE TABLE USER_PROGRESS 
(
  ID_PROGRESS NUMBER NOT NULL 
, WAGA NUMBER(*, 0) NOT NULL 
, KLATKA NUMBER(*, 0) NOT NULL 
, TALIA NUMBER(*, 0) NOT NULL 
, PAS NUMBER(*, 0) NOT NULL 
, BIODRO NUMBER(*, 0) NOT NULL 
, UDO NUMBER(*, 0) NOT NULL 
, RAMIE NUMBER(*, 0) NOT NULL 
, ID_USER NUMBER NOT NULL 
, CONSTRAINT USER_PROGRESS_PK PRIMARY KEY 
  (
    ID_PROGRESS 
  )
  ENABLE 
);

ALTER TABLE USER_PROGRESS
ADD CONSTRAINT USER_PROGRESS_FK1 FOREIGN KEY
(
  ID_USER 
)
REFERENCES USERS
(
  IDUSER 
)
ENABLE;

create sequence user_progress_seq start with 1 increment by 1;

create or replace trigger user_progress_seq_tr
 before insert on USER_PROGRESS for each row
 when (new.ID_PROGRESS is null)
begin
 select user_progress_seq.nextval into :new.ID_PROGRESS from dual;
end;
/

INSERT INTO USERS(IdUser, Login, Password, Imie, Nazwisko, Plec, Pesel, Rola) VALUES (USERS_SEQ.nextval, 'admin', 'admin', 'Admin', 'Adminowski', 'mezczyzna', '30', 'admin');