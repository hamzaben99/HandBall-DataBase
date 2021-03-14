-- ============================================================
--    suppression des donnees
-- ============================================================

delete from ENTRAINER ;
delete from PERFORMER ;
delete from JOUEUR ;
delete from RENCONTRE ;
delete from SAISON ;
delete from EQUIPE ;
delete from CATEGORIE ;
delete from CLUB ;
delete from PERSONNE ;


commit ;

-- ============================================================
--    creation des donnees
-- ============================================================

-- PERSONNES
-- //JOUEUR
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'SAUTET'     , 'CLAUDE'       , to_date('01-JAN-94') , null , 1 , null , to_date('01-JAN-18')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'PINOTEAU'   , 'CLAUDE'       , to_date('21-FEB-89') , null , 1 , null , to_date('03-JAN-19')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DAVOY'      , 'ERIC'         , to_date('31-OCT-93') , null , 1 , null , to_date('08-JAN-17')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'ZIDI'       , 'CLAUDE'       , to_date('21-MAR-96') , null , 1 , null , to_date('09-JAN-19')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'AUTAN-LARA' , 'CLAUDE'       , to_date('11-JAN-00') , null , 1 , null , to_date('10-JAN-19')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'ROHMER'     , 'ERIC'         , to_date('21-JAN-98') , null , 1 , null , to_date('12-JAN-19')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MALLE'      , 'LOUIS'        , to_date('22-JUN-97') , null , 1 , null , to_date('02-JAN-18')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BESSON'     , 'LUC'          , to_date('26-NOV-93') , null , 1 , null , to_date('02-JAN-17')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'PREMINGER'  , 'OTTO'         , to_date('03-DEC-97') , null , 1 , null , to_date('06-JAN-19')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BEINEIX'    , 'JEAN-JACQUES' , to_date('02-SEP-99') , null , 1 , null , to_date('08-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'GERONIMI'   , 'C.'           , to_date('01-JAN-93') , null , 2 , null , to_date('19-JAN-18')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'LYNE'       , 'ADRIAN'       , to_date('02-FEB-96') , null , 2 , null , to_date('22-JAN-17')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'TRUFFAUT'   , 'FRANCOIS'     , to_date('06-DEC-00') , null , 2 , null , to_date('21-JAN-15')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'COCTEAU'    , 'JEAN'         , to_date('02-JAN-01') , null , 2 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BOURVIL'   , 'BOURVIL'       , to_date('01-JAN-01') , null , 2 , null , to_date('30-JAN-95')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MALAVOY'   , 'CHRISTOPHE'    , to_date('01-JAN-01') , null , 2 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'ROBERT'    , 'YVES'          , to_date('01-JAN-01') , null , 2 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MANESSE'   , 'GASPARD'       , to_date('01-JAN-01') , null , 2 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BELLI'     , 'AGOSTINA'      , to_date('01-JAN-01') , null , 2 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BRASSEUR'  , 'CLAUDE'        , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MARLAUD'   , 'PHILIPPE'      , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BELMONDO'  , 'JEAN-PAUL'     , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'ROURKE'    , 'MICKEY'        , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BASINGER'  , 'KIM'           , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DENEUVE'   , 'CATHERINE'     , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'GABIN'     , 'JEAN'          , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DE FUNES'  , 'LOUIS'         , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'LANGLET'   , 'AMANDA'        , to_date('01-JAN-01') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'BARR'      , 'JEAN-MARC'     , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'ARQUETTE'  , 'ROSANNA'       , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'RENO'      , 'JEAN'          , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'NEWMAN'    , 'PAUL'          , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DALLE'     , 'BEATRICE'      , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'ANGLADE'   , 'JEAN-HUGUES'   , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'RIVIERE'   , 'MARIE'         , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MALLE'     , 'ALAIN'         , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DALLE'     , 'HIND'          , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MONTAND'   , 'YVES'          , to_date('13-OCT-21') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'GARCIA'    , 'NICOLE'        , to_date('21-FEB-57') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'VILLERET'  , 'JACQUES'       , to_date('06-FEB-51') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DUBOIS'    , 'MARIE'         , to_date('12-FEB-37') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'SCHNEIDER' , 'ROMY'          , to_date('01-APR-42') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DUPRILLOT' , 'JOHEL'         , to_date('24-APR-68') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'LESTRADOS' , 'DOMINIQUOS'    , to_date('19-FEB-69') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DELON'     , 'ALAIN'         , to_date('04-OCT-33') , null , 5 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'VENTURA'   , 'LINO'          , to_date('01-JAN-01') , null , 5 , null , to_date('30-JAN-20')) ;


-- //ENTR
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DUPON'     , 'GAETAN'        , to_date('24-APR-68') , null , 1 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'VESCH'     , 'DOMINIQUOS'    , to_date('19-FEB-69') , null , 2 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DELOU'     , 'REDA'          , to_date('04-OCT-33') , null , 3 , null , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'VERA'      , 'YASSIN'        , to_date('01-JAN-01') , null , 4 , null , to_date('30-JAN-20')) ;

-- //RESP
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'FREY'      , 'SAMY'          , to_date('24-MAY-40') , null , 1 , 'PRESIDENT'      , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DELON'     , 'BENJAMIN'      , to_date('04-OCT-33') , null , 2 , 'PRESIDENT'      , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'MASSARI'   , 'LEA'           , to_date('01-JAN-01') , null , 3 , 'PRESIDENT'      , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'RICARDO'   , 'BRUNOZZI'      , to_date('08-APR-58') , null , 1 , 'VICE PRESIDENT' , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'LEVANDOWSK', 'LUIS'          , to_date('01-JAN-66') , null , 3 , 'VICE PRESIDENT' , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'POIRET'    , 'JEAN'          , to_date('01-JAN-78') , null , 3 , 'VICE PRESIDENT' , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'REMONTADA' , 'HOUSSAM'       , to_date('01-JAN-59') , null , 1 , 'TRESORIER'      , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'DUPOT'     , 'JEAN'          , to_date('01-JAN-61') , null , 2 , 'TRESORIER'      , to_date('30-JAN-20')) ;
insert into PERSONNE values ( AUTO_INCREMENTE.nextval , 'CLUZET'    , 'FRANCOIS'      , to_date('01-JAN-77') , null , 3 , 'TRESORIER'      , to_date('30-JAN-20')) ;

commit ;

-- CLUBS

insert into CLUB values ( 1 ,  'BORDEAUX HANDBALL'   ) ;
insert into CLUB values ( 2 ,  'MARSEILLE HANDBALL'  ) ;
insert into CLUB values ( 3 ,  'LYON HANDBALL'       ) ;
insert into CLUB values ( 4 ,  'LILLE HANDBALL'      ) ;
insert into CLUB values ( 5 ,  'PARIS HANDBALL'      ) ;

commit;

-- CATEGORIES

insert into CATEGORIE values ( 1 , 'SENIOR'  ) ;
insert into CATEGORIE values ( 2 , 'JUNIOR'  ) ;
insert into CATEGORIE values ( 3 , 'CADET'   ) ;
insert into CATEGORIE values ( 4 , 'MINIME'  ) ;

commit;

-- EQUIPES
insert into EQUIPE values ( 1 , 1 , 1) ;
insert into EQUIPE values ( 2 , 1 , 2) ;
insert into EQUIPE values ( 3 , 1 , 3) ;
insert into EQUIPE values ( 4 , 1 , 4) ;
insert into EQUIPE values ( 5 , 1 , 5) ;

commit;

-- SAISON 

insert into SAISON values ( 20 ) ;

commit;

-- RENCONTRE

insert into RENCONTRE values (  1 , to_date('11-SEP-20') , 20 , 1) ;
insert into RENCONTRE values (  2 , to_date('11-SEP-20') , 20 , 1) ;
insert into RENCONTRE values (  3 , to_date('18-SEP-20') , 20 , 2) ;
insert into RENCONTRE values (  4 , to_date('18-SEP-20') , 20 , 2) ;
-- insert into RENCONTRE values (  5 , '25-SEP-20' , 20 , 1 , 3 ) ; NEXT TIME
-- insert into RENCONTRE values (  6 , '25-SEP-20' , 20 , 2 , 4 ) ; NEXT TIME

commit;


-- JOUEURS
insert into JOUEUR values (  1 , 'N102000001' , 1 ) ;
insert into JOUEUR values (  2 , 'N102000002' , 1 ) ;
insert into JOUEUR values (  3 , 'N102000003' , 1 ) ;
insert into JOUEUR values (  4 , 'N102000004' , 1 ) ;
insert into JOUEUR values (  5 , 'N102000005' , 1 ) ;
insert into JOUEUR values (  6 , 'N102000006' , 1 ) ;
insert into JOUEUR values (  7 , 'N102000007' , 1 ) ;
insert into JOUEUR values (  8 , 'N102000008' , 1 ) ;
insert into JOUEUR values (  9 , 'N102000009' , 1 ) ;
insert into JOUEUR values ( 10 , 'N102000010' , 1 ) ;
insert into JOUEUR values ( 11 , 'N102000011' , 2 ) ;
insert into JOUEUR values ( 12 , 'N102000012' , 2 ) ;
insert into JOUEUR values ( 13 , 'N102000013' , 2 ) ;
insert into JOUEUR values ( 14 , 'N102000014' , 2 ) ;
insert into JOUEUR values ( 16 , 'N102000015' , 2 ) ;
insert into JOUEUR values ( 15 , 'N102000016' , 2 ) ;
insert into JOUEUR values ( 17 , 'N102000017' , 2 ) ;
insert into JOUEUR values ( 18 , 'N102000018' , 2 ) ;
insert into JOUEUR values ( 19 , 'N102000019' , 2 ) ;
insert into JOUEUR values ( 20 , 'N102000020' , 3 ) ;
insert into JOUEUR values ( 21 , 'N102000021' , 3 ) ;
insert into JOUEUR values ( 22 , 'N102000022' , 3 ) ;
insert into JOUEUR values ( 23 , 'N102000023' , 3 ) ;
insert into JOUEUR values ( 24 , 'N102000024' , 3 ) ;
insert into JOUEUR values ( 25 , 'N102000025' , 3 ) ;
insert into JOUEUR values ( 26 , 'N102000026' , 3 ) ;
insert into JOUEUR values ( 27 , 'N102000027' , 3 ) ;
insert into JOUEUR values ( 28 , 'N102000028' , 3 ) ;
insert into JOUEUR values ( 29 , 'N102000029' , 4 ) ;
insert into JOUEUR values ( 30 , 'N102000030' , 4 ) ;
insert into JOUEUR values ( 31 , 'N102000031' , 4 ) ;
insert into JOUEUR values ( 32 , 'N102000032' , 4 ) ;
insert into JOUEUR values ( 33 , 'N102000033' , 4 ) ;
insert into JOUEUR values ( 34 , 'N102000034' , 4 ) ;
insert into JOUEUR values ( 35 , 'N102000035' , 4 ) ;
insert into JOUEUR values ( 36 , 'N102000036' , 4 ) ;
insert into JOUEUR values ( 37 , 'N102000037' , 4 ) ;
insert into JOUEUR values ( 38 , 'N102000038' , 5 ) ;
insert into JOUEUR values ( 39 , 'N102000039' , 5 ) ;
insert into JOUEUR values ( 40 , 'N102000040' , 5 ) ;
insert into JOUEUR values ( 41 , 'N102000041' , 5 ) ;
insert into JOUEUR values ( 42 , 'N102000042' , 5 ) ;
insert into JOUEUR values ( 45 , 'N102000043' , 5 ) ;
insert into JOUEUR values ( 46 , 'N102000044' , 5 ) ;

commit;

-- PERFORMER

insert into PERFORMER values (  1 ,  1  , 2 , 1 , 1 ) ;
insert into PERFORMER values (  1 ,  2  , 3 , 0 , 1 ) ;
insert into PERFORMER values (  1 ,  3  , 4 , 4 , 1 ) ;
insert into PERFORMER values (  1 ,  4  , 1 , 3 , 1 ) ;
insert into PERFORMER values (  1 ,  5  , 2 , 2 , 1 ) ;
insert into PERFORMER values (  1 ,  6  , 3 , 2 , 1 ) ;
insert into PERFORMER values (  1 ,  7  , 6 , 1 , 1 ) ;
insert into PERFORMER values (  1 ,  8  , 2 , 3 , 1 ) ;
insert into PERFORMER values (  1 ,  9  , 3 , 1 , 1 ) ;
insert into PERFORMER values (  1 ,  10 , 2 , 4 , 1 ) ;

insert into PERFORMER values (  1 ,  11 , 3 , 1 , 2 ) ;
insert into PERFORMER values (  1 ,  12 , 1 , 2 , 2 ) ;
insert into PERFORMER values (  1 ,  13 , 1 , 4 , 2 ) ;
insert into PERFORMER values (  1 ,  14 , 2 , 6 , 2 ) ;
insert into PERFORMER values (  1 ,  15 , 5 , 2 , 2 ) ;
insert into PERFORMER values (  1 ,  16 , 2 , 1 , 2 ) ;
insert into PERFORMER values (  1 ,  17 , 6 , 2 , 2 ) ;
insert into PERFORMER values (  1 ,  18 , 5 , 2 , 2 ) ;
insert into PERFORMER values (  1 ,  19 , 3 , 0 , 2 ) ;

insert into PERFORMER values (  2 ,  20 , 1 , 2 , 3 ) ;
insert into PERFORMER values (  2 ,  21 , 2 , 1 , 3 ) ;
insert into PERFORMER values (  2 ,  22 , 2 , 4 , 3 ) ;
insert into PERFORMER values (  2 ,  23 , 2 , 6 , 3 ) ;
insert into PERFORMER values (  2 ,  24 , 5 , 3 , 3 ) ;
insert into PERFORMER values (  2 ,  25 , 7 , 2 , 3 ) ;
insert into PERFORMER values (  2 ,  26 , 2 , 5 , 3 ) ;
insert into PERFORMER values (  2 ,  27 , 4 , 2 , 3 ) ;

insert into PERFORMER values (  2 ,  28 , 3 , 1 , 4 ) ;
insert into PERFORMER values (  2 ,  29 , 1 , 2 , 4 ) ;
insert into PERFORMER values (  2 ,  30 , 1 , 4 , 4 ) ;
insert into PERFORMER values (  2 ,  31 , 2 , 6 , 4 ) ;
insert into PERFORMER values (  2 ,  32 , 5 , 2 , 4 ) ;
insert into PERFORMER values (  2 ,  33 , 2 , 1 , 4 ) ;
insert into PERFORMER values (  2 ,  34 , 6 , 2 , 4 ) ;
insert into PERFORMER values (  2 ,  35 , 5 , 2 , 4 ) ;
insert into PERFORMER values (  2 ,  36 , 3 , 0 , 4 ) ;
insert into PERFORMER values (  2 ,  37 , 1 , 2 , 4 ) ;

insert into PERFORMER values (  3 ,  20 , 1 , 2 , 3 ) ;
insert into PERFORMER values (  3 ,  21 , 0 , 1 , 3 ) ;
insert into PERFORMER values (  3 ,  22 , 3 , 1 , 3 ) ;
insert into PERFORMER values (  3 ,  23 , 3 , 7 , 3 ) ;
insert into PERFORMER values (  3 ,  24 , 1 , 4 , 3 ) ;
insert into PERFORMER values (  3 ,  25 , 2 , 5 , 3 ) ;
insert into PERFORMER values (  3 ,  26 , 4 , 1 , 3 ) ;
insert into PERFORMER values (  3 ,  27 , 5 , 2 , 3 ) ;
insert into PERFORMER values (  3 ,  28 , 2 , 1 , 3 ) ;


insert into PERFORMER values (  3 ,  11 , 1 , 1 , 2 ) ;
insert into PERFORMER values (  3 ,  12 , 4 , 2 , 2 ) ;
insert into PERFORMER values (  3 ,  13 , 0 , 4 , 2 ) ;
insert into PERFORMER values (  3 ,  14 , 3 , 5 , 2 ) ;
insert into PERFORMER values (  3 ,  15 , 5 , 6 , 2 ) ;
insert into PERFORMER values (  3 ,  16 , 2 , 3 , 2 ) ;
insert into PERFORMER values (  3 ,  17 , 0 , 4 , 2 ) ;
insert into PERFORMER values (  3 ,  18 , 1 , 2 , 2 ) ;
insert into PERFORMER values (  3 ,  19 , 6 , 2 , 2 ) ;

insert into PERFORMER values (  4 ,  1  , 0 , 1 , 1 ) ;
insert into PERFORMER values (  4 ,  2  , 4 , 1 , 1 ) ;
insert into PERFORMER values (  4 ,  3  , 6 , 2 , 1 ) ;
insert into PERFORMER values (  4 ,  4  , 5 , 1 , 1 ) ;
insert into PERFORMER values (  4 ,  5  , 1 , 3 , 1 ) ;
insert into PERFORMER values (  4 ,  6  , 4 , 2 , 1 ) ;
insert into PERFORMER values (  4 ,  7  , 5 , 2 , 1 ) ;
insert into PERFORMER values (  4 ,  8  , 3 , 3 , 1 ) ;
insert into PERFORMER values (  4 ,  9  , 1 , 0 , 1 ) ;
insert into PERFORMER values (  4 ,  10 , 6 , 4 , 1 ) ;

insert into PERFORMER values (  4 ,  29 , 0 , 3 , 4 ) ;
insert into PERFORMER values (  4 ,  30 , 4 , 2 , 4 ) ;
insert into PERFORMER values (  4 ,  31 , 3 , 0 , 4 ) ;
insert into PERFORMER values (  4 ,  32 , 4 , 1 , 4 ) ;
insert into PERFORMER values (  4 ,  33 , 1 , 4 , 4 ) ;
insert into PERFORMER values (  4 ,  34 , 1 , 6 , 4 ) ;
insert into PERFORMER values (  4 ,  35 , 0 , 4 , 4 ) ;
insert into PERFORMER values (  4 ,  36 , 0 , 2 , 4 ) ;
insert into PERFORMER values (  4 ,  37 , 5 , 1 , 4 ) ;

commit;

-- ENTRAINER

insert into ENTRAINER values (  47 ,  1 ) ;
insert into ENTRAINER values (  48 ,  2 ) ;
insert into ENTRAINER values (  49 ,  3 ) ;
insert into ENTRAINER values (  50 ,  4 ) ;

commit;