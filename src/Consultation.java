import java.sql.*;
import java.util.LinkedList;

public class Consultation {

    private static void InitDataPrint(HTMLGenerator hg,ResultSetMetaData rsetm, String caption) throws SQLException{
        hg.setCaption(caption);

        LinkedList<String> header = new LinkedList<String>();
        for (int i = 1; i <= rsetm.getColumnCount(); i++) {
            header.add(rsetm.getColumnLabel(i));
        }
        hg.setHeader(header);
    }

    public static void getInfo(Connection conn, String typeInfo)
    throws SQLException, ClassNotFoundException, java.io.IOException {    
        PreparedStatement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.prepareStatement("SELECT * " 
                                        + "FROM " + typeInfo );
            rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();
            HTMLGenerator hg = new HTMLGenerator();
            if(typeInfo.equals("CLUB")) {
                InitDataPrint(hg, rsetm, "Liste des Clubs");

                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(rset.getString(2));
                    hg.setRow(row);
                }
            } else {
                InitDataPrint(hg, rsetm, "Liste des equipes");

                while (rset.next()) {
                    LinkedList<String> row = new LinkedList<String>();
                    row.add(Integer.toString(rset.getInt(1)));
                    row.add(Integer.toString(rset.getInt(2)));
                    row.add(Integer.toString(rset.getInt(3)));
                    hg.setRow(row);
                }
            }
            hg.end();
            hg.printHTML(true, "List" + typeInfo);
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getJoueurDateInfo(Connection conn, String date)
    throws SQLException, ClassNotFoundException, java.io.IOException {    
        PreparedStatement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.prepareStatement("SELECT * "
                                        + "FROM LIST_JOUEURS "
                                        + "WHERE DATE_RENCONTRE = ?");
            stmt.setDate(1, Date.valueOf(date));
            rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Liste des joueur à " + date);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(Integer.toString(rset.getInt(3)));
                row.add(Integer.toString(rset.getInt(4)));
                row.add(rset.getDate(5).toString());
                row.add(Integer.toString(rset.getInt(6)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "PlayersListDate");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getPersonneInfo(Connection conn)
    throws SQLException, ClassNotFoundException, java.io.IOException {    
        Statement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT * " 
                                    + "FROM PERSONNE ");
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Liste de tous les personnes dans la bases");
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(rset.getString(3));
                row.add(rset.getDate(4).toString());
                row.add(rset.getString(5));
                row.add(Integer.toString(rset.getInt(6)));
                row.add(rset.getString(7));
                row.add(rset.getDate(8).toString());
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "PeopleList");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getEntraineurInfo(Connection conn)
    throws SQLException, ClassNotFoundException, java.io.IOException {    
        Statement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT P.*, E.NUMERO_EQUIPE as EQUIPE_ENTRAINEE " 
                                    + "FROM PERSONNE P JOIN ENTRAINER E " 
                                    + "ON P.NUMERO_PERSONNE = E.NUMERO_PERSONNE ");
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Liste des Entraineurs");
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(rset.getString(3));
                row.add(rset.getDate(4).toString());
                row.add(rset.getString(5));
                row.add(Integer.toString(rset.getInt(6)));
                row.add(rset.getString(7));
                row.add(rset.getDate(8).toString());
                row.add(Integer.toString(rset.getInt(9)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "EntraineurList");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getJoueurInfo(Connection conn)
    throws SQLException, ClassNotFoundException, java.io.IOException {    
        Statement stmt = null;
        ResultSet rset;
        try {
            stmt = conn.createStatement();
            rset = stmt.executeQuery("SELECT P.*, J.NUMERO_LICENCE, J.NUMERO_EQUIPE " 
                                    + "FROM PERSONNE P JOIN JOUEUR J " 
                                    + "ON J.NUMERO_PERSONNE = P.NUMERO_PERSONNE ");
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Liste de tous les joueurs");
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getString(2));
                row.add(rset.getString(3));
                row.add(rset.getDate(4).toString());
                row.add(rset.getString(5));
                row.add(Integer.toString(rset.getInt(6)));
                row.add(rset.getString(7));
                row.add(rset.getDate(8).toString());
                row.add(rset.getString(9));
                row.add(Integer.toString(rset.getInt(10)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "PlayerList");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getFeuilleMatch(Connection conn, String date, int numRencontre) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        try {
            if (numRencontre < 0) {
                stmt = conn.prepareStatement("SELECT * "
                                            + "FROM FEUILLE_MATCH FM "
                                            + "WHERE FM.DATE_RENCONTRE = ? ");
            } else {
                stmt = conn.prepareStatement("SELECT * "
                                            + "FROM FEUILLE_MATCH "
                                            + "WHERE DATE_RENCONTRE = ? AND NUMERO_RENCONTRE = ? ");
                stmt.setInt(2, numRencontre);
            }
            stmt.setDate(1, Date.valueOf(date));

            ResultSet rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Feuille du match à la date " + date);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(rset.getDate(2).toString());
                row.add(Integer.toString(rset.getInt(3)));
                row.add(rset.getString(4));
                row.add(rset.getString(5));
                row.add(rset.getString(6));
                row.add(Integer.toString(rset.getInt(7)));
                row.add(Integer.toString(rset.getInt(8)));
                row.add(Integer.toString(rset.getInt(9)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "feuilleMatch");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getFeuilleMatchResume(Connection conn, String date) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT DATE_RENCONTRE, F.* "
                                        + "FROM FEUILLE_MATCH_RESUME F JOIN RENCONTRE R ON R.NUMERO_RENCONTRE = F.NUMERO_RENCONTRE "
                                        + "WHERE DATE_RENCONTRE = ? ");
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Feuille de match resume à la date " + date);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(rset.getDate(1).toString());
                row.add(Integer.toString(rset.getInt(2)));
                row.add(Integer.toString(rset.getInt(3)));
                row.add(Integer.toString(rset.getInt(4)));
                row.add(Integer.toString(rset.getInt(5)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "FeuilleResume");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getScoreMatch(Connection conn, String date) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT NUMERO_RENCONTRE, NUMERO_EQUIPE1, SCORE_EQUIPE1, SCORE_EQUIPE2, NUMERO_EQUIPE2 "
                                        + "FROM SCORE_MATCH SM "
                                        + "WHERE SM.DATE_RENCONTRE = ? ");
            stmt.setDate(1, Date.valueOf(date));
            ResultSet rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Score des matches à la date " + date);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(Integer.toString(rset.getInt(2)));
                row.add(Integer.toString(rset.getInt(3)));
                row.add(Integer.toString(rset.getInt(4)));
                row.add(Integer.toString(rset.getInt(5)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "scoreMatch");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getWDLTeam(Connection conn, int numEquipe) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT * FROM EQUIPE_WDL "
                                        + "WHERE NUMERO_EQUIPE = ? ");
            stmt.setInt(1, numEquipe);
            ResultSet rset = stmt.executeQuery();
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Win/Draw/Loss de l'equipe " + numEquipe);
            
            while (rset.next()) {
                LinkedList<String> row = new LinkedList<String>();
                row.add(Integer.toString(rset.getInt(1)));
                row.add(Integer.toString(rset.getInt(2)));
                row.add(Integer.toString(rset.getInt(3)));
                row.add(Integer.toString(rset.getInt(4)));
                hg.setRow(row);
            }
            hg.end();
            hg.printHTML(true, "WDLTeam");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public static void getWDLClub(Connection conn) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
            ResultSet rset = stmt.executeQuery("SELECT * FROM CLUB_WDL");
            ResultSetMetaData rsetm = rset.getMetaData();

            HTMLGenerator hg = new HTMLGenerator();
            InitDataPrint(hg, rsetm, "Win/Draw/Loss par Club");
            
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
            hg.printHTML(true, "WDLClub");
        }
        finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}