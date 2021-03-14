-- ============================================================
--   Requetes: Consultation      
-- ============================================================

SELECT * 
FROM CLUB;

SELECT *
FROM EQUIPE;

SELECT *
FROM PERSONNE;

-- Liste de tous les joueurs
SELECT P.*, J.NUMERO_LICENCE, J.NUMERO_EQUIPE 
FROM PERSONNE P JOIN JOUEUR J ON J.NUMERO_PERSONNE = P.NUMERO_PERSONNE;

--liste des entraineurs
SELECT P.*, E.NUMERO_EQUIPE as EQUIPE_ENTRAINEE 
FROM PERSONNE P JOIN ENTRAINER E ON P.NUMERO_PERSONNE = E.NUMERO_PERSONNE;

-- Liste des joueurs à une date donnée
SELECT *
FROM LIST_JOUEURS
WHERE DATE_RENCONTRE = '11-SEP-20';

-- Feuille de match à une date donnée
SELECT *
FROM FEUILLE_MATCH
WHERE DATE_RENCONTRE = '11-SEP-20';

-- Feuille de match à une date et d'un match donnés
SELECT *
FROM FEUILLE_MATCH
WHERE DATE_RENCONTRE = '11-SEP-20' AND NUMERO_RENCONTRE = 1;

-- Feuille de match à une date donnée
SELECT DATE_RENCONTRE, F.*
FROM FEUILLE_MATCH_RESUME F JOIN RENCONTRE R ON R.NUMERO_RENCONTRE = F.NUMERO_RENCONTRE
WHERE DATE_RENCONTRE = '11-SEP-20';

--Score des matchs jouer à une date donnée
SELECT NUMERO_RENCONTRE, NUMERO_EQUIPE1, SCORE_EQUIPE1, SCORE_EQUIPE2, NUMERO_EQUIPE2
FROM SCORE_MATCH
WHERE DATE_RENCONTRE = '11-SEP-20';

-- Counting wins, draws and losses for each team (equipe)
SELECT * FROM COUNT_WINS;

SELECT * FROM COUNT_DRAWS;

SELECT * FROM COUNT_LOSSES;

-- WIN, DRAW, LOSS Counter for teams
SELECT * FROM EQUIPE_WDL;

-- WIN, DRAW, LOSS Counter for clubs
SELECT * FROM CLUB_WDL;



-- ============================================================
--   Requetes: Statistiques      
-- ============================================================

-- Average points per game at a certain date
SELECT AVG(MATCH_POINTS)
FROM RENCONTRE R JOIN MATCH_STATISTICS MS ON R.NUMERO_RENCONTRE = MS.NUMERO_RENCONTRE
WHERE R.DATE_RENCONTRE = '18-SEP-20';

-- Average points per season
SELECT AVG(SS.SAISON_POINTS)
FROM SAISON S, SEASON_STATISTICS SS
WHERE SS.NUMERO_SAISON = S.NUMERO_SAISON AND S.NUMERO_SAISON = 20 ;

-- PLayer Ranking for a certain round in a certain category
SELECT FM.NUMERO_PERSONNE, FM.CUMUL_POINT, FM.CUMUL_FAUTE
FROM RENCONTRE R JOIN FEUILLE_MATCH FM ON R.NUMERO_RENCONTRE = FM.NUMERO_RENCONTRE
    JOIN JOUEUR J ON FM.NUMERO_PERSONNE = J.NUMERO_PERSONNE
    JOIN EQUIPE E ON E.NUMERO_EQUIPE = J.NUMERO_EQUIPE
WHERE R.DATE_RENCONTRE = '18-SEP-20' AND E.NUMERO_CATEGORIE = 1
ORDER BY FM.CUMUL_POINT DESC, FM.CUMUL_FAUTE ASC;

-- Team Ranking
SELECT *
FROM EQUIPE_WDL
ORDER BY WINS ASC, DRAWS ASC, LOSSES DESC;

-- Club Ranking
SELECT *
FROM CLUB_WDL
ORDER BY WINS ASC, DRAWS ASC, LOSSES DESC;


