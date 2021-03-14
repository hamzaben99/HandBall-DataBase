import java.sql.*;
import java.util.LinkedList;

public class Statistiques {

    private static void InitDataPrint(HTMLGenerator hg,ResultSetMetaData rsetm, String caption) throws SQLException{
        hg.setCaption(caption);

        LinkedList<String> header = new LinkedList<String>();
        for (int i = 1; i <= rsetm.getColumnCount(); i++) {
            header.add(rsetm.getColumnLabel(i));
        }
        hg.setHeader(header);
    }

    public static void getAveragePointspergameDate(Connection conn, String date) // Date format "19-JAN-2020"
            throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.prepareStatement("SELECT AVG(MATCH_POINTS) "
                    + "FROM RENCONTRE R JOIN MATCH_STATISTICS MS ON R.NUMERO_RENCONTRE = MS.NUMERO_RENCONTRE "
                    + "WHERE R.DATE_RENCONTRE = ? ");
            stmt.setDate(1, Date.valueOf(date));
            rset = stmt.executeQuery();

            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Moyenne des points marqués à la date " + date);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "AvgPointsDate");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getAveragePointsperSeason(Connection conn, int Season)
            throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.prepareStatement("SELECT AVG(SS.SAISON_POINTS) "
                                        + "FROM SAISON S, SEASON_STATISTICS SS "
                                        + "WHERE SS.NUMERO_SAISON = S.NUMERO_SAISON AND S.NUMERO_SAISON = ? ");
            stmt.setInt(1, Season);
            rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Moyenne des points marqués dans la saison " + Season);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "AveragePointsSeason");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getPlayerRankingDateCategory(Connection conn, String date, int Category)
                                                                                                  
            throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.prepareStatement("SELECT FM.NUMERO_PERSONNE, FM.NOM_PERSONNE, FM.PRENOM_PERSONNE, FM.CUMUL_POINT, FM.CUMUL_FAUTE "
                                        + "FROM RENCONTRE R JOIN FEUILLE_MATCH FM ON R.NUMERO_RENCONTRE = FM.NUMERO_RENCONTRE "
                                        + "JOIN JOUEUR J ON FM.NUMERO_PERSONNE = J.NUMERO_PERSONNE "
                                        + "JOIN EQUIPE E ON E.NUMERO_EQUIPE = J.NUMERO_EQUIPE "
                                        + "WHERE R.DATE_RENCONTRE = ? AND E.NUMERO_CATEGORIE = ? "
                                        + "ORDER BY FM.CUMUL_POINT DESC, FM.CUMUL_FAUTE ASC ");
            stmt.setDate(1, Date.valueOf(date));
            stmt.setInt(2, Category);
            rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Classement des Joueurs de la categorie " + Category + " à la date " + date);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(rset.getString(3));
                row.add(Integer.toString(rset.getInt(4)));
                row.add(Integer.toString(rset.getInt(5)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "PlayerRankingDate");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getPlayerRankingRoundCategory(Connection conn, int Round, int Category) // Category Format
                                                                                                 // "Cadet"
            throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.prepareStatement("SELECT FM.NUMERO_PERSONNE, FM.NOM_PERSONNE, FM.PRENOM_PERSONNE, FM.CUMUL_POINT, FM.CUMUL_FAUTE "
                    + "FROM RENCONTRE R JOIN FEUILLE_MATCH FM ON R.NUMERO_RENCONTRE = FM.NUMERO_RENCONTRE "
                    + "JOIN JOUEUR J ON FM.NUMERO_PERSONNE = J.NUMERO_PERSONNE  "
                    + "JOIN EQUIPE E ON E.NUMERO_EQUIPE = J.NUMERO_EQUIPE "
                    + "WHERE E.NUMERO_CATEGORIE = ? AND R.JOURNEE = ? "
                    + "ORDER BY FM.CUMUL_POINT DESC, FM.CUMUL_FAUTE ASC ");
            stmt.setInt(1, Category);
            stmt.setInt(2, Round);
            rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Classement des joueurs de la categorie " + Category + " dans la journée " + Round);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(rset.getString(3));
                row.add(Integer.toString(rset.getInt(4)));
                row.add(Integer.toString(rset.getInt(5)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "PlayerRankingRound");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getTeamRanking(Connection conn)
            throws SQLException, ClassNotFoundException, java.io.IOException {
        Statement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * " + "FROM EQUIPE_WDL " + "ORDER BY WINS ASC, DRAWS ASC, LOSSES DESC ");
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Classement des équipes");
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(Integer.toString(rset.getInt(2)));
                row.add(Integer.toString(rset.getInt(3)));
                row.add(Integer.toString(rset.getInt(4)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "TeamRanking");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getClubRanking(Connection conn)
            throws SQLException, ClassNotFoundException, java.io.IOException {
        Statement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * " 
                                    + "FROM CLUB_WDL "
                                    + "ORDER BY WINS DESC, DRAWS DESC, LOSSES ASC ");
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Classement des clubs");
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(Integer.toString(rset.getInt(3)));
                row.add(Integer.toString(rset.getInt(4)));
                row.add(Integer.toString(rset.getInt(5)));
                row.add(Integer.toString(rset.getInt(6)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "ClubRanking");
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}