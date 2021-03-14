-- ============================================================
--   Nom de la base   :  HANDBALL                                
--   Nom de SGBD      :  ORACLE Version 7.0                    
--   Date de creation :  16/11/20  18:45                      
-- ============================================================

drop table PERSONNE cascade constraints;

drop table CLUB cascade constraints;

drop table EQUIPE cascade constraints;

drop table CATEGORIE cascade constraints;

drop table RENCONTRE cascade constraints;

drop table SAISON cascade constraints;

drop table JOUEUR cascade constraints;

drop table PERFORMER cascade constraints;

drop table ENTRAINER cascade constraints;

drop sequence AUTO_INCREMENTE;


-- ============================================================
--   Table : PERSONNE                                
-- ============================================================
create table PERSONNE
(
    NUMERO_PERSONNE                 NUMBER(3)               not null, 
    NOM_PERSONNE                    CHAR(20)                not null,
    PRENOM_PERSONNE                 CHAR(20)                        ,
    DATE_DE_NAISSANCE               DATE                    not null,
    ADRESSE                         CHAR(20)                        ,
    NUMERO_CLUB                     NUMBER(3)               not null,
    POSITION                        CHAR(20)                        , -- A vérifier
    DATE_ENTREE                     DATE                    not null,
    constraint pk_personne primary key (NUMERO_PERSONNE)
);

-- ============================================================
--   Table : CLUB                                     
-- ============================================================
create table CLUB
(
    NUMERO_CLUB                     NUMBER(3)               not null,
    NOM_CLUB                        CHAR(20)                        ,
    constraint pk_club primary key (NUMERO_CLUB)
);

-- ============================================================
--   Table : EQUIPE                                   
-- ============================================================
create table EQUIPE
    (
    NUMERO_EQUIPE                   NUMBER(3)               not null,
    NUMERO_CATEGORIE                NUMBER(2)               not null,
    NUMERO_CLUB                     NUMBER(3)               not null,
    constraint pk_equipe primary key (NUMERO_EQUIPE)
);

-- ============================================================
--   Table : CATEGORIE                                
-- ============================================================
create table CATEGORIE
(
    NUMERO_CATEGORIE                NUMBER(2)               not null,
    NOM_CATEGORIE                   CHAR(20)                        ,
    constraint pk_categorie primary key (NUMERO_CATEGORIE)
);

-- ============================================================
--   Table : RENCONTRE                        
-- ============================================================
create table RENCONTRE
(
    NUMERO_RENCONTRE                NUMBER(3)               not null,
    DATE_RENCONTRE                  DATE                    not null,
    NUMERO_SAISON                   NUMBER(2)               not null,
    JOURNEE                         NUMBER(2)               not null,
    constraint pk_rencontre primary key (NUMERO_RENCONTRE)
);

-- ============================================================
--   Table : SAISON                         
-- ============================================================
create table SAISON
(
    NUMERO_SAISON                   NUMBER(2)               not null,
    constraint pk_saison primary key (NUMERO_SAISON)       
);

-- ============================================================
--   Table : JOUEUR                                
-- ============================================================
create table JOUEUR
(
    NUMERO_PERSONNE                 NUMBER(3)               not null,
    NUMERO_LICENCE                  CHAR(20)                not null,
    NUMERO_EQUIPE                   NUMBER(3)               not null,
    constraint pk_joueur primary key (NUMERO_PERSONNE)
);

-- ============================================================
--   Table : PERFORMER                      
-- ============================================================
create table PERFORMER
(
    NUMERO_RENCONTRE                NUMBER(3)               not null,
    NUMERO_PERSONNE                 NUMBER(3)               not null,
    CUMUL_POINT                     NUMBER(2)               not null,
    CUMUL_FAUTE                     NUMBER(2)               not null,
    NUMERO_EQUIPE                   NUMBER(3)               not null,
    constraint pk_performer primary key (NUMERO_RENCONTRE, NUMERO_PERSONNE)
);

-- ============================================================
--   Table : ENTRAINER                      
-- ============================================================
create table ENTRAINER
(
    NUMERO_PERSONNE                 NUMBER(3)               not null,         
    NUMERO_EQUIPE                   NUMBER(3)               not null,
    constraint pk_entrainer primary key (NUMERO_PERSONNE, NUMERO_EQUIPE)
);

alter table EQUIPE
    add constraint fk1_equipe foreign key (NUMERO_CATEGORIE)
        references CATEGORIE (NUMERO_CATEGORIE);

alter table EQUIPE
    add constraint fk2_equipe foreign key (NUMERO_CLUB)
        references CLUB (NUMERO_CLUB);

alter table RENCONTRE
    add constraint fk1_rencontre foreign key (NUMERO_SAISON)
        references SAISON (NUMERO_SAISON);

alter table JOUEUR
    add constraint fk1_joueur foreign key (NUMERO_PERSONNE)
        references  PERSONNE (NUMERO_PERSONNE);

alter table JOUEUR
    add constraint fk2_joueur foreign key (NUMERO_EQUIPE)
        references  EQUIPE (NUMERO_EQUIPE);

alter table PERFORMER
    add constraint fk1_performer foreign key (NUMERO_RENCONTRE)
        references RENCONTRE (NUMERO_RENCONTRE);

alter table PERFORMER
    add constraint fk2_performer foreign key (NUMERO_PERSONNE)
        references PERSONNE (NUMERO_PERSONNE);

alter table PERFORMER
    add constraint fk3_performer foreign key (NUMERO_EQUIPE)
        references EQUIPE (NUMERO_EQUIPE);

alter table ENTRAINER
    add constraint fk1_entrainer foreign key (NUMERO_PERSONNE)
        references PERSONNE (NUMERO_PERSONNE);

alter table ENTRAINER
    add constraint fk2_entrainer foreign key (NUMERO_EQUIPE)
        references EQUIPE (NUMERO_EQUIPE);

-- ============================================================
--   SEQUENCE   
-- ============================================================
create sequence AUTO_INCREMENTE
minvalue 0
start with 0
increment by 1;


-- ============================================================
--   VIEWS: Consultation      
-- ============================================================

-- Liste des joueurs de tous les dates.
create or replace view LIST_JOUEURS as
SELECT J.*, R.NUMERO_RENCONTRE, R.DATE_RENCONTRE, R.NUMERO_SAISON
FROM JOUEUR J
    JOIN PERFORMER P ON J.NUMERO_PERSONNE = P.NUMERO_PERSONNE
    JOIN RENCONTRE R ON R.NUMERO_RENCONTRE = P.NUMERO_RENCONTRE;

-- Table rassemblant les deux équipes participants aux rencontres
create or replace view EQUIPES_RENCONTRE as
SELECT DISTINCT R.NUMERO_RENCONTRE as NUMERO_RENCONTRE, P.NUMERO_EQUIPE as NUMERO_EQUIPE
FROM RENCONTRE R JOIN PERFORMER P ON P.NUMERO_RENCONTRE = R.NUMERO_RENCONTRE;

-- Table de rencontres avec plus d'informations comme les numeros d'équipe par exemple 
create or replace view RENCONTRE_INTER as
SELECT R.NUMERO_RENCONTRE as NUMERO_RENCONTRE, R.DATE_RENCONTRE as DATE_RENCONTRE, R.NUMERO_SAISON as NUMERO_SAISON, R.JOURNEE as JOURNEE, MAX(ER.NUMERO_EQUIPE) as NUMERO_EQUIPE1, MIN(ER.NUMERO_EQUIPE) as NUMERO_EQUIPE2
FROM RENCONTRE R JOIN EQUIPES_RENCONTRE ER ON R.NUMERO_RENCONTRE = ER.NUMERO_RENCONTRE
GROUP BY R.NUMERO_RENCONTRE, R.DATE_RENCONTRE, R.NUMERO_SAISON, R.JOURNEE;

-- Feuille de match à tous les dates
create or replace view FEUILLE_MATCH as
SELECT R.NUMERO_RENCONTRE, R.DATE_RENCONTRE as DATE_RENCONTRE, Pe.NUMERO_PERSONNE as NUMERO_PERSONNE, Pe.NOM_PERSONNE, Pe.PRENOM_PERSONNE, J.NUMERO_LICENCE, J.NUMERO_EQUIPE, P.CUMUL_POINT, P.CUMUL_FAUTE
FROM PERSONNE Pe, JOUEUR J, RENCONTRE_INTER R, PERFORMER P
WHERE Pe.NUMERO_PERSONNE = J.NUMERO_PERSONNE AND P.NUMERO_PERSONNE = Pe.NUMERO_PERSONNE AND R.NUMERO_RENCONTRE = P.NUMERO_RENCONTRE;

-- Feuille de match résumée à tous les dates
create or replace view FEUILLE_MATCH_RESUME as
SELECT NUMERO_RENCONTRE, NUMERO_EQUIPE, sum(CUMUL_POINT) as SCORE_EQUIPE, sum(CUMUL_FAUTE) as FAUTE_EQUIPE
FROM FEUILLE_MATCH
GROUP BY NUMERO_RENCONTRE, NUMERO_EQUIPE;

-- Score de l'équipe 1 dans un match à tous les dates
create or replace view SCORE_EQUIPE1 as
SELECT R.NUMERO_RENCONTRE as NUMERO_RENCONTRE, R.NUMERO_EQUIPE1, sum(P.CUMUL_POINT) as SCORE, sum(P.CUMUL_FAUTE) as FAUTE
FROM RENCONTRE_INTER R, PERFORMER P, JOUEUR J
WHERE P.NUMERO_RENCONTRE = R.NUMERO_RENCONTRE AND P.NUMERO_PERSONNE = J.NUMERO_PERSONNE AND R.NUMERO_EQUIPE1 = P.NUMERO_EQUIPE  
GROUP BY R.NUMERO_RENCONTRE, R.NUMERO_EQUIPE1;


-- Score de l'équipe 2 dans un match à tous les dates
create or replace view SCORE_EQUIPE2 as
SELECT R.NUMERO_RENCONTRE as NUMERO_RENCONTRE, R.NUMERO_EQUIPE2, sum(CUMUL_POINT) as SCORE, sum(CUMUL_FAUTE) as FAUTE
FROM RENCONTRE_INTER R, PERFORMER P, JOUEUR J
WHERE P.NUMERO_RENCONTRE = R.NUMERO_RENCONTRE AND P.NUMERO_PERSONNE = J.NUMERO_PERSONNE AND P.NUMERO_EQUIPE = R.NUMERO_EQUIPE2 
GROUP BY R.NUMERO_RENCONTRE, R.NUMERO_EQUIPE2;


-- Score d'un mactch à tous les dates
create or replace view SCORE_MATCH as
SELECT R.NUMERO_RENCONTRE, R.DATE_RENCONTRE as DATE_RENCONTRE, R.NUMERO_EQUIPE1, SE1.SCORE as SCORE_EQUIPE1, SE2.SCORE as SCORE_EQUIPE2, R.NUMERO_EQUIPE2
FROM RENCONTRE_INTER R, SCORE_EQUIPE1 SE1, SCORE_EQUIPE2 SE2
WHERE R.NUMERO_RENCONTRE = SE1.NUMERO_RENCONTRE AND R.NUMERO_RENCONTRE = SE2.NUMERO_RENCONTRE;

-- counter of HOME wins
create or replace view COUNT_HOME_WINS as
SELECT E.NUMERO_EQUIPE, COUNT(NUM_WINS) as WINS
FROM EQUIPE E 
    FULL OUTER JOIN 
    (
        SELECT SM.NUMERO_EQUIPE1 as NUMERO_EQUIPE, COUNT(*) as NUM_WINS
        FROM SCORE_MATCH SM
        WHERE SM.SCORE_EQUIPE1 > SM.SCORE_EQUIPE2
        GROUP BY SM.NUMERO_EQUIPE1
    ) T
ON E.NUMERO_EQUIPE = T.NUMERO_EQUIPE
GROUP BY E.NUMERO_EQUIPE;

-- counter of HOME losses
create or replace view COUNT_HOME_LOSSES as
SELECT E.NUMERO_EQUIPE, COUNT(NUM_LOSSES) as LOSSES
FROM EQUIPE E 
    FULL OUTER JOIN 
    (
        SELECT SM.NUMERO_EQUIPE1 as NUMERO_EQUIPE, COUNT(*) as NUM_LOSSES
        FROM SCORE_MATCH SM
        WHERE SM.SCORE_EQUIPE1 < SM.SCORE_EQUIPE2
        GROUP BY SM.NUMERO_EQUIPE1
    ) T
ON E.NUMERO_EQUIPE = T.NUMERO_EQUIPE
GROUP BY E.NUMERO_EQUIPE;

-- counter of HOME draws
create or replace view COUNT_HOME_DRAWS as
SELECT E.NUMERO_EQUIPE, COUNT(NUM_DRAWS) as DRAWS
FROM EQUIPE E 
    FULL OUTER JOIN 
    (
        SELECT SM.NUMERO_EQUIPE1 as NUMERO_EQUIPE, COUNT(*) as NUM_DRAWS
        FROM SCORE_MATCH SM
        WHERE SM.SCORE_EQUIPE1 = SM.SCORE_EQUIPE2
        GROUP BY SM.NUMERO_EQUIPE1
    ) T
ON E.NUMERO_EQUIPE = T.NUMERO_EQUIPE
GROUP BY E.NUMERO_EQUIPE;

-- counter of AWAY wins
create or replace view COUNT_AWAY_WINS as
SELECT E.NUMERO_EQUIPE, COUNT(NUM_WINS) as WINS
FROM EQUIPE E 
    FULL OUTER JOIN 
    (
        SELECT SM.NUMERO_EQUIPE2 as NUMERO_EQUIPE, COUNT(*) as NUM_WINS
        FROM SCORE_MATCH SM
        WHERE SM.SCORE_EQUIPE2 > SM.SCORE_EQUIPE1
        GROUP BY SM.NUMERO_EQUIPE2
    ) T
ON E.NUMERO_EQUIPE = T.NUMERO_EQUIPE
GROUP BY E.NUMERO_EQUIPE;

-- counter of AWAY losses
create or replace view COUNT_AWAY_LOSSES as
SELECT E.NUMERO_EQUIPE, COUNT(NUM_LOSSES) as LOSSES
FROM EQUIPE E 
    FULL OUTER JOIN 
    (
        SELECT SM.NUMERO_EQUIPE2 as NUMERO_EQUIPE, COUNT(*) as NUM_LOSSES
        FROM SCORE_MATCH SM
        WHERE SM.SCORE_EQUIPE2 < SM.SCORE_EQUIPE1
        GROUP BY SM.NUMERO_EQUIPE2
    ) T
ON E.NUMERO_EQUIPE = T.NUMERO_EQUIPE
GROUP BY E.NUMERO_EQUIPE;

-- counter of AWAY draws
create or replace view COUNT_AWAY_DRAWS as
SELECT E.NUMERO_EQUIPE, COUNT(NUM_DRAWS) as DRAWS
FROM EQUIPE E 
    FULL OUTER JOIN 
    (
        SELECT SM.NUMERO_EQUIPE2 as NUMERO_EQUIPE, COUNT(*) as NUM_DRAWS
        FROM SCORE_MATCH SM
        WHERE SM.SCORE_EQUIPE2 = SM.SCORE_EQUIPE1
        GROUP BY SM.NUMERO_EQUIPE2
    ) T
ON E.NUMERO_EQUIPE = T.NUMERO_EQUIPE
GROUP BY E.NUMERO_EQUIPE;

-- Counting wins, draws and losses for each team (equipe)
create or replace view COUNT_WINS as
SELECT NUMERO_EQUIPE, sum(WINS) as WINS
FROM (
        SELECT *
        FROM COUNT_HOME_WINS
        UNION ALL
        SELECT *
        FROM COUNT_AWAY_WINS
    )
GROUP BY NUMERO_EQUIPE;

create or replace view COUNT_DRAWS as
SELECT NUMERO_EQUIPE, sum(DRAWS) as DRAWS
FROM (
        SELECT *
        FROM COUNT_HOME_DRAWS
        UNION ALL
        SELECT *
        FROM COUNT_AWAY_DRAWS
    )
GROUP BY NUMERO_EQUIPE;

create or replace view COUNT_LOSSES as
SELECT NUMERO_EQUIPE, sum(LOSSES) as LOSSES
FROM (
        SELECT *
        FROM COUNT_HOME_LOSSES
        UNION ALL
        SELECT *
        FROM COUNT_AWAY_LOSSES
)
GROUP BY NUMERO_EQUIPE;

-- WIN, DRAW, LOSS Counter for teams
create or replace view EQUIPE_WDL as
SELECT E.NUMERO_EQUIPE, W.WINS, D.DRAWS, L.LOSSES
FROM EQUIPE E
    FULL OUTER JOIN COUNT_WINS W ON E.NUMERO_EQUIPE = W.NUMERO_EQUIPE
    FULL OUTER JOIN COUNT_DRAWS D ON E.NUMERO_EQUIPE = D.NUMERO_EQUIPE
    FULL OUTER JOIN COUNT_LOSSES L ON E.NUMERO_EQUIPE = L.NUMERO_EQUIPE;

-- WIN, DRAW, LOSS Counter for clubs
create or replace view CLUB_WDL as
SELECT C.*, E.NUMERO_EQUIPE, WINS, DRAWS, LOSSES
FROM CLUB C, EQUIPE_WDL WDL, CATEGORIE CAT, EQUIPE E
WHERE  C.NUMERO_CLUB = E.NUMERO_CLUB AND CAT.NUMERO_CATEGORIE = E.NUMERO_CATEGORIE
        AND WDL.NUMERO_EQUIPE = E.NUMERO_EQUIPE;

-- ============================================================
--   VIEWS: Statistiques      
-- ============================================================

create or replace view MATCH_STATISTICS as
SELECT NUMERO_RENCONTRE, sum(CUMUL_POINT) as MATCH_POINTS, sum(CUMUL_FAUTE) as MATCH_FAUTES
FROM FEUILLE_MATCH
GROUP BY NUMERO_RENCONTRE;

create or replace view SEASON_STATISTICS as
SELECT S.NUMERO_SAISON as NUMERO_SAISON, sum(MATCH_POINTS) as SAISON_POINTS, sum(MATCH_FAUTES) as SAISON_FAUTES
FROM RENCONTRE_INTER R JOIN SAISON S ON R.NUMERO_SAISON = S.NUMERO_SAISON
    JOIN MATCH_STATISTICS MS ON R.NUMERO_RENCONTRE = MS.NUMERO_RENCONTRE
GROUP BY S.NUMERO_SAISON;