import java.sql.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MAJ {
    private static int ID = 100;
    public static String getMAJString(String str, String format){
        String s;
        System.out.println("Veuillez entrer un(e) " + str + " sous forme : " + format);
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            s = bufferRead.readLine();
            while( str.contains("*") && s.length() == 0){
                System.out.println("Veuillez entrer un(e) " + str + " valide (non vide) sous forme : " + format);
                s = bufferRead.readLine();
            }
            if (s == ""){
                return null;
            }
            
            return s;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }


    public static void addPerson(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus d'insertion de la PERSONNE");
        System.out.println("Les champs * sont nécessaires \n");
        // Ajout de personne
        int num_club;
        String Nom, Prenom, Date_de_naissance, Adresse, Position, Date_entree;
        Nom = getMAJString("Nom *", "String");
        Prenom = getMAJString("Prenom *", "String");
        Date_de_naissance = getMAJString("Date de naissance *", "YYYY-MM-DD");
        Adresse = getMAJString("Adresse", "String");
        Position = getMAJString("Position", "String");
        Date_entree = getMAJString("Date d'entrée au club *" , "YYYY-MM-DD");
        System.out.println("Veuillez indiquer le numéro du club * - Attention il doit etre valide");
        Scanner sc = new Scanner(System.in);
        num_club = sc.nextInt();

        // SQL
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into PERSONNE values ( AUTO_INCREMENTE.nextval , ? , ? , ? , ? , ? , ? , ?) ");
            stmt.setString(1, Nom);
            stmt.setString(2, Prenom);
            stmt.setDate(3, Date.valueOf(Date_de_naissance));
            stmt.setString(4, Adresse);
            stmt.setInt(5, num_club);
            stmt.setString(6, Position);
            stmt.setDate(7, Date.valueOf(Date_entree));
            stmt.executeUpdate();
            stmt.close();

	        System.out.println("Insertion de la PERSONNE complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addClub(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        System.out.println("### Début du processus d'insertion de CLUB ");
        System.out.println("Les champs * sont nécessaires \n");
        // Ajout du club
        int num_club = 0;
        String Nom_club;
        Nom_club = getMAJString("Nom Club ", "String");
        
        // SQL
        PreparedStatement stmt = null;

        // Getting the last num_club used
        stmt = conn.prepareStatement("SELECT MAX(NUMERO_CLUB) FROM CLUB ");
        rset = stmt.executeQuery();
        while(rset.next()){
            num_club = rset.getInt(1);
        }
        stmt.close();
        try {
            stmt = conn.prepareStatement("insert into CLUB values ( ? , ? ) ");
            stmt.setInt(1, ++num_club);
            stmt.setString(2, Nom_club);
            stmt.executeUpdate();

            System.out.println("Insertion du CLUB complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addTeam(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        System.out.println("### Début du processus d'insertion de EQUIPE ");
        System.out.println("Les champs * sont nécessaires \n");
        int num_equipe = 0;
        System.out.println("Veuillez indiquer le numéro de la catégoerie * de cette équipe - Attention il doit être valide");
        Scanner sc = new Scanner(System.in);
        int num_categorie= sc.nextInt();
        System.out.println("Veuillez indiquer le numéro du club * de cette équipe - Attention il doit être valide");
        int num_club = sc.nextInt();
        
        // SQL
        PreparedStatement stmt = null;

        // Getting the last num_equipe used
        stmt = conn.prepareStatement("SELECT MAX(NUMERO_EQUIPE) FROM EQUIPE ");
        rset = stmt.executeQuery();
        while(rset.next()){
            num_equipe = rset.getInt(1);
        }
        stmt.close();
        try {
            stmt = conn.prepareStatement("insert into EQUIPE values ( ? , ? , ? ) ");
            stmt.setInt(1, ++num_equipe);
            stmt.setInt(2, num_categorie);
            stmt.setInt(3, num_club);
            stmt.executeUpdate();

            System.out.println("Insertion de EQUIPE num "+ num_equipe +" complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addCategory(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        System.out.println("### Début du processus d'insertion de CATEGORIE ");
        System.out.println("Les champs * sont nécessaires \n");
        int num_categorie = 0;
        String nom = getMAJString("NOM_CATEGORIE *", "String");
        
        // SQL
        PreparedStatement stmt = null;

        // Getting the last num_equipe used
        stmt = conn.prepareStatement("SELECT MAX(NUMERO_CATEGORIE) FROM CATEGORIE ");
        rset = stmt.executeQuery();
        while(rset.next()){
            num_categorie = rset.getInt(1);
        }
        stmt.close();
        try {
            stmt = conn.prepareStatement("insert into CATEGORIE values ( ? , ? ) ");
            stmt.setInt(1, ++num_categorie);
            stmt.setString(2, nom);
            stmt.executeUpdate();

            System.out.println("Insertion de CATEGORIE num "+ num_categorie +" complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addMatch(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        System.out.println("### Début du processus d'insertion de Match/RENCONTRE");
        System.out.println("Les champs * sont nécessaires \n");
        // Ajout du club
        int num_rencontre = 0, num_saison, num_eq1, num_eq2, journee;
        String date_rencontre;

        date_rencontre = getMAJString("Date de rencontre ", "13-JAN-20");
        System.out.println("Veuillez indiquer le numéro de la saison *");
        Scanner sc = new Scanner(System.in);
        num_saison = sc.nextInt();
        System.out.println("Veuillez indiquer la journée *");
        journee = sc.nextInt();

        // SQL
        PreparedStatement stmt = null;
        stmt = conn.prepareStatement("SELECT MAX(NUMERO_RENCONTRE) FROM RENCONTRE ");
        rset = stmt.executeQuery();
        while(rset.next()){
            num_rencontre = rset.getInt(1);
        }
        stmt.close();
        try {
            stmt = conn.prepareStatement("insert into RENCONTRE values ( ? , ? , ? , ? ) ");
            stmt.setInt(1, ++num_rencontre);
            stmt.setDate(2, Date.valueOf(date_rencontre));
            stmt.setInt(3, num_saison);
            stmt.setInt(4, journee);
            stmt.executeUpdate();
	        
            System.out.println("Insertion de la RENCONTRE num "+ num_rencontre +" complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addPlayer(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus d'insertion de JOUEUR ");
        System.out.println("Les champs * sont nécessaires \n");
        System.out.println("Veuillez indiquer le numéro de la PERSONNE * qui sera JOUEUR - Attention il doit être valide");
        Scanner sc = new Scanner(System.in);
        int num_personne = sc.nextInt();
        System.out.println("Veuillez indiquer le numéro d'équipe * pour laquelle jouera ce joueur - Attention il doit etre valide");
        int num_equipe = sc.nextInt();
        String licence = getMAJString("Numéro de licence *", "String");
        
        // SQL
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("insert into JOUEUR values ( ? , ? , ? ) ");
            stmt.setInt(1, num_personne);
            stmt.setString(2, licence);
            stmt.setInt(3, num_equipe);
            stmt.executeUpdate();

            System.out.println("Insertion de JOUEUR num "+ num_personne +" complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addPersonPlayer(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        System.out.println("### Début du processus d'insertion de JOUEUR");
        System.out.println("Les champs * sont nécessaires\n");
        // Ajout de Personne
        addPerson(conn);
        // Ajout de Joueur
        int num_equipe;
        String numero_licence;
        System.out.println("Veuillez indiquer le numéro d'équipe * dans laquelle ce JOUEUR - Attention il doit etre valide ");
        Scanner sc = new Scanner(System.in);
        num_equipe = sc.nextInt();
        numero_licence = getMAJString("Numéro de licence *", "String");

        // SQL
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("SELECT MAX(NUMERO_PERSONNE) FROM PERSONNE ");
            rset = stmt.executeQuery();
            while(rset.next()){
                ID = rset.getInt(1);
            }
            stmt.close();

            stmt = conn.prepareStatement("insert into JOUEUR values (  ? , ? , ? ) " );
            stmt.setInt(1, ID);
            stmt.setString(2, numero_licence);
            stmt.setInt(3, num_equipe);
            stmt.executeUpdate();

	        System.out.println("Insertion du JOUEUR num "+ ID +" complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addPerformance(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        System.out.println("### Début du processus d'insertion de PERFORMER ");
        System.out.println("Les champs * sont nécessaires \n");

        System.out.println("Veuillez indiquer le numéro de la rencontre * PERFORMEE - Attention il doit etre valide");
        Scanner sc = new Scanner(System.in);
        int num_rencontre= sc.nextInt();
        System.out.println("Veuillez indiquer le numéro de la PERSONNE * qui PERFORME - Attention il doit être valide");
        int num_personne = sc.nextInt();
        System.out.println("Veuillez indiquer le nombre de points marqués par le joueur " + num_personne + " dans la rencontre " + num_rencontre + " * PERFORMEE ");
        int point = sc.nextInt();
        System.out.println("Veuillez indiquer le nombre de fautes commises par le joueur " + num_personne + " dans la rencontre " + num_rencontre + " * PERFORMEE ");
        int faute = sc.nextInt();
        int num_equipe = 0;
        
        // SQL
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement("SELECT NUMERO_EQUIPE FROM JOUEUR WHERE NUMERO_PERSONNE = ? ");
            stmt.setInt(1, num_personne);
            rset = stmt.executeQuery();
            while(rset.next()){
                num_equipe = rset.getInt(1);
            }
            stmt.close();

            stmt = conn.prepareStatement("insert into PERFORMER values ( ? , ? , ? , ? , ? ) ");
            stmt.setInt(1, num_rencontre);
            stmt.setInt(2, num_personne);
            stmt.setInt(3, point);
            stmt.setInt(4, faute);
            stmt.setInt(5, num_equipe);
            stmt.executeUpdate();

            System.out.println("Insertion de PERFORMER num ("+num_rencontre +" , "+num_personne+") complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void addCoach(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus d'insertion de ENTRAINER ");
        System.out.println("Les champs * sont nécessaires \n");

        System.out.println("Veuillez indiquer le numéro de la PERSONNE * qui ENTRAINE - Attention il doit être valide");
        Scanner sc = new Scanner(System.in);
        int num_personne = sc.nextInt();
        System.out.println("Veuillez indiquer le numéro de l'EQUIPE * ENTRAINEE - Attention il doit être valide");
        int num_equipe = sc.nextInt();
        
        
        // SQL
        PreparedStatement stmt = null;
        try {
            if(checkClubChange(conn, num_personne, num_equipe) != -1){
                System.out.println("Insertion de ENTRAINER abondonnée: équipe souhaitée ne fait pas partie du club de la personne ###");
            }

            else{
                stmt = conn.prepareStatement("insert into ENTRAINER values ( ? , ? ) ");
                stmt.setInt(1, num_personne);
                stmt.setInt(2, num_equipe);
                stmt.executeUpdate();

                System.out.println("Insertion de ENTRAINER num ("+ num_personne +" , "+num_equipe+ ") complète!! ###");
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void deleteFromEntity(Connection conn, String entite) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus d'insertion de " + entite);
        System.out.println("Veuillez indiquer le numéro de " + entite + " * que vous voulez supprimer");
        Scanner sc = new Scanner(System.in);
        int numero_entite= sc.nextInt();
        PreparedStatement stmt = null;
        try {
            if (entite.equals("JOUEUR")){
                stmt = conn.prepareStatement("DELETE FROM " + entite + " WHERE " + "NUMERO_PERSONNE = ? ") ;
            }
            else {
                stmt = conn.prepareStatement("DELETE FROM " + entite + " WHERE " + "NUMERO_" + entite + " = ? ") ;
            }
            stmt.setInt(1, numero_entite);
            stmt.executeUpdate();

	        System.out.println("Suppression de " + entite + " complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void deletePerformance(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus d'insertion de PERFORMER ");
        System.out.println("Veuillez indiquer le numéro de la rencontre performée * ");
        Scanner sc = new Scanner(System.in);
        int numero_rencontre = sc.nextInt();
        System.out.println("Veuillez indiquer le numéro de la personne joueur qui a performè cette rencontre *");
        int numero_personne = sc.nextInt();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM PERFORMER WHERE NUMERO_RENCONTRE = ? AND NUMERO_PERSONNE = ?") ;
            stmt.setInt(1, numero_rencontre);
            stmt.setInt(2, numero_personne);
            stmt.executeUpdate();

	        System.out.println("Suppression de PERFORMER complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void deleteCoach(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus de supression de ENTRAINER ");
        System.out.println("Veuillez indiquer le numéro de la personne qui entraîne *");
        Scanner sc = new Scanner(System.in);
        int num_personne = sc.nextInt();
        System.out.println("Veuillez indiquer le numéro de l'équipe qu'il entraîne *");
        int num_equipe = sc.nextInt();

        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("DELETE FROM ENTRAINER WHERE NUMERO_PERSONNE = ? AND NUMERO_EQUIPE = ? ") ;
            stmt.setInt(1, num_personne);
            stmt.setInt(2, num_equipe);
            stmt.executeUpdate();

	        System.out.println("Suppression de ENTRAINER complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static String numeroEntity(String entity){
        if (entity.equals("JOUEUR")){
            return "PERSONNE";
        }else{
            return entity;
        }
    }

    public static void updateEntityStringField(Connection conn, String entity, String field) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus de modification de " + entity);
        System.out.println("Veuillez indiquez le numéro ID de : " + entity);
        Scanner sc = new Scanner(System.in);
        int id_entity = sc.nextInt();
        String s = getMAJString(entity+"."+field , "String");

        // SQL
        PreparedStatement stmt = null;
        //update Table set Attr=Val [,Attr=Val]* [where Condition]
        try {
            stmt = conn.prepareStatement("UPDATE " + entity
                                        + " set " + field + " = ? "
                                        + "where NUMERO_"+ numeroEntity(entity) + " = ? ");
            stmt.setString(1, s);
            stmt.setInt(2, id_entity);
            stmt.executeUpdate();

	        System.out.println("Modification de " + entity + " complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void updateEntityIntField(Connection conn, String entity, String field) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus de modification de " + entity);
        System.out.println("Veuillez indiquez le numéro ID de : " + entity);
        Scanner sc = new Scanner(System.in);
        int id_entity = sc.nextInt();
        System.out.println("Veuillez indiquez " + entity+"."+field + " sous forme : Int ");
        int i = sc.nextInt();

        // SQL
        PreparedStatement stmt = null;
        //update Table set Attr=Val [,Attr=Val]* [where Condition]
        try {
            stmt = conn.prepareStatement("UPDATE " + entity
                                        + " set " + field + " = ? "
                                        + "where NUMERO_"+ numeroEntity(entity) + " = ? ");
            stmt.setInt(1, i);
            stmt.setInt(2, id_entity);
            stmt.executeUpdate();

	        System.out.println("Modification de " + entity + " complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void updateEntityDateField(Connection conn, String entity, String field) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus de modification de " + entity);
        System.out.println("Veuillez indiquez le numéro ID de : " + entity);
        Scanner sc = new Scanner(System.in);
        int id_entity = sc.nextInt();
        String d = getMAJString(entity+"."+field , "Date: YYYY-MM-DD");

        // SQL
        PreparedStatement stmt = null;
        //update Table set Attr=Val [,Attr=Val]* [where Condition]
        try {
            stmt = conn.prepareStatement("UPDATE " + entity
                                        + " set " + field + " = ? "
                                        + "where NUMERO_"+ numeroEntity(entity) + " = ? ");
            stmt.setDate(1, Date.valueOf(d));
            stmt.setInt(2, id_entity);
            stmt.executeUpdate();

	        System.out.println("Modification de " + entity + " complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void updatePerformance(Connection conn, String entity, String field) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus de modification de PERFORMER ");
        System.out.println("Veuillez indiquez PERFORMER.NUMERO_PERSONNE de la performance que vous voulez modifier!  ");
        Scanner sc = new Scanner(System.in);
        int id_pers = sc.nextInt();
        System.out.println("Veuillez indiquez PERFORMER.NUMERO_RENCONTRE de la performance que vous voulez modifier! ");
        int id_match = sc.nextInt();
        System.out.println("Veuillez indiquez " + entity+"."+field + " sous forme : Int ");
        int i = sc.nextInt();

        // SQL
        PreparedStatement stmt = null;
        //update Table set Attr=Val [,Attr=Val]* [where Condition]
        try {
            stmt = conn.prepareStatement("UPDATE " + entity
                                        + " set " + field + " = ? "
                                        + "where NUMERO_PERSONNE = ? AND NUMERO_RENCONTRE = ? ");
            stmt.setInt(1, i);
            stmt.setInt(2, id_pers);
            stmt.setInt(3, id_match);
            stmt.executeUpdate();

	        System.out.println("Modification de " + entity + " complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }

    public static void updateCoach(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("### Début du processus de modification de ENTRAINER ");
        System.out.println("Veuillez indiquez ENTRAINER.NUMERO_PERSONNE que vous voulez modifier! - Attention il doit etre valide ");
        Scanner sc = new Scanner(System.in);
        int id_pers = sc.nextInt();
        System.out.println("Veuillez indiquez l'ancien ENTRAINER.NUMERO_EQUIPE de cet entraineur! - Attention il doit etre valide ");
        int id_team = sc.nextInt();
        System.out.println("Veuillez indiquez le nouveau ENTRAINER.NUMERO_EQUIPE de cet entraineur! - Attention il doit etre valide ");
        int id_new_team = sc.nextInt();
        
        // SQL
        PreparedStatement stmt = null;
        try {
            boolean changeClub = false;
            int id_new_club = checkClubChange(conn, id_pers, id_new_team);
            if (id_new_club != -1){
                changeClub = true;
            }
            stmt = conn.prepareStatement( "UPDATE ENTRAINER "
                                        + " set NUMERO_EQUIPE = ? "
                                        + "where NUMERO_PERSONNE = ? AND NUMERO_EQUIPE = ? ");
            stmt.setInt(1, id_new_team);
            stmt.setInt(2, id_pers);
            stmt.setInt(3, id_team);
            stmt.executeUpdate();
            stmt.close();

            if (changeClub){
                System.out.println("Ce changement d'équipe implique un changement de club! Veuillez ajouter les informations suivantes!");
                String date_entree = getMAJString("Date d'entrée à cette équipe", "Date: YYYY-MM-DD");
                stmt = conn.prepareStatement("UPDATE PERSONNE "
                                            + " set DATE_ENTREE = ? , NUMERO_CLUB = ? "
                                            + "where NUMERO_PERSONNE = ? ");
                stmt.setDate(1, Date.valueOf(date_entree));
                stmt.setInt(2, id_new_club);
                stmt.setInt(3, id_pers);
                stmt.executeUpdate();
            }

	        System.out.println("Modification de ENTRAINER complète!! ###");

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        //sc.close();
    }



        public static void updatePlayerTeam(Connection conn) throws SQLException, ClassNotFoundException, java.io.IOException {
            System.out.println("### Début du processus de modification de JOUEUR");
            System.out.println("Veuillez indiquez le numéro ID de : JOUEUR");
            Scanner sc = new Scanner(System.in);
            int id_pers = sc.nextInt();
            System.out.println("Veuillez indiquez son nouveau NUMERO_EQUIPE sous forme : Int ");
            int id_new_team = sc.nextInt();
            
    
            // SQL
            PreparedStatement stmt = null;
            //update Table set Attr=Val [,Attr=Val]* [where Condition]
            try {
                boolean changeClub = false;
                int id_new_club = checkClubChange(conn, id_pers, id_new_team);
                System.out.println("numero_club est : "+ id_new_club);
                if (id_new_club != -1){
                    changeClub = true;
                }
                stmt = conn.prepareStatement("UPDATE JOUEUR "
                                            + " set NUMERO_equipe = ? "
                                            + "where NUMERO_PERSONNE = ? ");
                stmt.setInt(1, id_new_team);
                stmt.setInt(2, id_pers);
                stmt.executeUpdate();
                stmt.close();

                if (changeClub){
                    System.out.println("Ce changement d'équipe implique un changement de club! Veuillez ajouter les informations suivantes!");
                    String date_entree = getMAJString("Date d'entrée à cette équipe", "Date: YYYY-MM-DD");
                    stmt = conn.prepareStatement("UPDATE PERSONNE "
                                            + " set DATE_ENTREE = ?, NUMERO_CLUB = ? "
                                            + "where NUMERO_PERSONNE = ? ");
                    stmt.setDate(1, Date.valueOf(date_entree));
                    stmt.setInt(2, id_new_club);
                    stmt.setInt(3, id_pers);
                    stmt.executeUpdate();
                }
    
                System.out.println("Modification de JOUEUR complète!! ###");
    
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
            //sc.close();
    }

    public static int checkClubChange(Connection conn, int num_personne, int new_num_equipe) throws SQLException, ClassNotFoundException, java.io.IOException {
        ResultSet rset;
        int old_club = 0, new_club = -1;
        // SQL
        PreparedStatement stmt = null;
        //update Table set Attr=Val [,Attr=Val]* [where Condition]
        try {
            stmt = conn.prepareStatement("SELECT NUMERO_CLUB "
                                        + "FROM PERSONNE "
                                        + "WHERE NUMERO_PERSONNE = ? ");
            stmt.setInt(1, num_personne);
            rset = stmt.executeQuery();
            while(rset.next()){
                old_club = rset.getInt(1);
            }
            stmt.close();


            stmt = conn.prepareStatement("SELECT NUMERO_CLUB "
                                        + "FROM EQUIPE "
                                        + "where NUMERO_EQUIPE = ? ");
            stmt.setInt(1, new_num_equipe);
            rset = stmt.executeQuery();
            while(rset.next()){
                new_club = rset.getInt(1);
            }

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        if(old_club != new_club){
            return new_club; // returns number of new club
        }
        return -1;   
    }
    
}