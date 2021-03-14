import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.sql.*;
import oracle.jdbc.pool.OracleDataSource;


public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException, java.io.IOException {
        // Preparation de la connexion.
        OracleDataSource ods = new OracleDataSource();
        ods.setUser("username");
        ods.setPassword("password");
        // URL de connexion, on remarque que le pilote utilise est "thin".
        ods.setURL("jdbc:oracle:thin:@localhost:1521/oracle");

        Connection conn = null;
        try {
            conn = ods.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }

        while(true){
            Main.displayMenu(conn);
        }
    }
    
    public static String getString(String str, String format){
        System.out.println("Veuillez entrer une " + str + " sous forme : " + format);
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String s = bufferRead.readLine();
            return s;
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
        return "";
    }

    public static void displayConsultationMenu(Connection c)    
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|      Que souhaitez-vous consulter?                 |");
        System.out.println("|          1 - Liste des joueurs à une date donnée   |");
        System.out.println("|          2 - Liste des clubs                       |");
        System.out.println("|          3 - Liste des équipes                     |");
        System.out.println("|          4 - Liste de tous les joueurs             |");
        System.out.println("|          5 - Liste de tous les Entraineurs         |");
        System.out.println("|          6 - Liste de toutes les personnes         |");
        System.out.println("|          7 - Résultat des rencontres à une date    |");
        System.out.println("|          8 - Feuille d'un match                    |");
        System.out.println("|          9 - Feuille resumé d'un match             |");
        System.out.println("|          10 - Performance d'une équipe             |");
        System.out.println("|          11 - Performance des clubes               |");
        System.out.println("|      Tapez 0 pour revenir en arrière               |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt(),a;
        System.out.println("|----------------------------------------------------|");
        String s;
        switch (i) {
            case 0:
                displayMenu(c);
                break;
            case 1:
                s = getString("Date", "YYYY-MM-DD");
                Consultation.getJoueurDateInfo(c,s);
             break;
            case 2:
                Consultation.getInfo(c,"CLUB");
             break;
            case 3:
                Consultation.getInfo(c,"EQUIPE");
             break;
            case 4:
                Consultation.getJoueurInfo(c);
             break;
            case 5:
                Consultation.getEntraineurInfo(c);
             break;
            case 6:
                Consultation.getPersonneInfo(c);
             break;
            case 7:
                s = getString("Date", "YYYY-MM-DD");
                Consultation.getScoreMatch(c,s);
             break;
            case 8:
                System.out.println("Veuillez indiquer le numéro de rencontre (entrez un numéro négatif pour obtenir la feuille de tous les matches joués à une date)");
                a = sc.nextInt();
                s = getString("Date", "YYYY-MM-DD");
                Consultation.getFeuilleMatch(c,s,a);
             break;
            case 9:
                s = getString("Date", "YYYY-MM-DD");
                Consultation.getFeuilleMatchResume(c,s);
                break;
            case 10:
                System.out.println("Veuillez indiquer le numéro de l'équipe");
                a = sc.nextInt();
                Consultation.getWDLTeam(c,a);
             break;
            case 11:
                Consultation.getWDLClub(c);
                break;
            default:
                displayConsultationMenu(c);
          }
          //sc.close();
    }

    public static int getCategory() {
        System.out.println("|     Choisissezr une catégorie?                     |");
        System.out.println("|         1 - Senior                                 |");
        System.out.println("|         2 - Junior                                 |");
        System.out.println("|         3 - Cadet                                  |");
        System.out.println("|         4 - Minime                                 |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        //sc.close();
        return i;
    }

    public static void displayStatiqtiquesMenu(Connection c) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|   Quelle statistiques souhaitez-vous visualiser?   |");
        System.out.println("|         1 - Moyenne des points marqués             |");
        System.out.println("|               par rencontre à une date donnée      |");
        System.out.println("|         2 - Moyenne des points marqués depuis      |");
        System.out.println("|               le début de la saison                |");
        System.out.println("|         3 - Classement des meilleurs joueurs       |");
        System.out.println("|               d’une journée pour une catégorie     |");
        System.out.println("|         4 - Classement des équipes                 |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt(),a;
        System.out.println("|----------------------------------------------------|");
        String s;
        switch (i) {
            case 0:
                displayMenu(c);
                break;
            case 1:
                s = getString("Date", "YYYY-MM-DD");
                Statistiques.getAveragePointspergameDate(c,s);
             break;
            case 2:
                System.out.println("Veuillez indiquer le numéro de saison");
                a = sc.nextInt();
                Statistiques.getAveragePointsperSeason(c,a);
             break;
            case 3:
                a = getCategory();
                System.out.println("Veuillez indiquer le numéro de la journée");
                i = sc.nextInt();
                Statistiques.getPlayerRankingRoundCategory(c,i,a);
             break;
            case 4:
                Statistiques.getTeamRanking(c);
             break;
            default:
                displayStatiqtiquesMenu(c);
        }
        //sc.close();
    }

    // --------------------- MAJ -  Modification Menus  ---------------------------
    public static void displayModifyPersonMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de PERSONNE voulez vous modifiez? |");
        System.out.println("|         1 - Nom                                    |");
        System.out.println("|         2 - Prenom                                 |");
        System.out.println("|         3 - Date de naissance                      |");
        System.out.println("|         4 - Adresse                                |");
        System.out.println("|         5 - Num_Club                               |");
        System.out.println("|         6 - Position                               |");
        System.out.println("|         7 - Date d'entrée                          |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updateEntityStringField(c, "PERSONNE", "NOM_PERSONNE");
                break;
            case 2:
                MAJ.updateEntityStringField(c, "PERSONNE", "PRENOM_PERSONNE");
                break;
            case 3:
                MAJ.updateEntityDateField(c, "PERSONNE", "DATE_DE_NAISSANCE");
                break;
            case 4:
                MAJ.updateEntityStringField(c, "PERSONNE", "ADRESSE");
                break;
            case 5:
                MAJ.updateEntityIntField(c, "PERSONNE", "NUMERO_CLUB");
                break;
            case 6:
                MAJ.updateEntityStringField(c, "PERSONNE", "POSITION");
                break;
            case 7:
                MAJ.updateEntityDateField(c, "PERSONNE", "DATE_ENTREE");
                break;
            default:
                displayModifyPersonMenu(c);
        }
    }

    public static void displayModifyClubMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de CLUB voulez vous modifiez?     |");
        System.out.println("|         1 - Nom du club                            |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updateEntityStringField(c, "CLUB", "NOM_CLUB");
                break;
            default:
                displayModifyClubMenu(c);
        }
    }

    public static void displayModifyTeamMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de EQUIPE voulez vous modifiez?   |");
        System.out.println("|         1 - Num_Club                               |");
        System.out.println("|         2 - Num_Catégorie                          |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updateEntityIntField(c, "EQUIPE", "NUMERO_CLUB");
                break;
            case 2:
                MAJ.updateEntityIntField(c, "EQUIPE", "NUMERO_CATEGORIE");
                break;
            default:
                displayModifyTeamMenu(c);
        }
    }

    public static void displayModifyCategoryMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de CATEGORIE voulez vous modifiez?|");
        System.out.println("|         1 - Nom de la catégorie                    |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updateEntityStringField(c, "CATEGORIE", "NOM_CATEGORIE");
                break;
            default:
                displayModifyCategoryMenu(c);
        }
    }

    public static void displayModifyMatchMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de RENCONTRE voulez vous modifiez?|");
        System.out.println("|         1 - Date de la rencontre                   |");
        System.out.println("|         2 - Num_Saison                             |");
        System.out.println("|         3 - Journée                                |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updateEntityDateField(c, "RENCONTRE", "DATE_RENCONTRE");
                break;
            case 2:
                MAJ.updateEntityIntField(c, "RENCONTRE", "NUMERO_SAISON");
                break;
            case 3:
                MAJ.updateEntityIntField(c, "RENCONTRE", "JOURNEE");
                break;
            default:
                displayModifyMatchMenu(c);
        }
    }

    public static void displayModifyPlayerMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de JOUEUR voulez vous modifiez?|");
        System.out.println("|         1 - Licence                                |");
        System.out.println("|         2 - Num_Equipe                             |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updateEntityStringField(c, "JOUEUR", "NUMERO_LICENCE");
                break;
            case 2:
                MAJ.updatePlayerTeam(c);
                break;
            default:
                displayModifyPlayerMenu(c);
        }
    }

    public static void displayModifyPerformanceMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle champ de PERFORMER voulez vous modifiez?|");
        System.out.println("|         1 - Cumul de points marqués                |");
        System.out.println("|         2 - Cumul de fautes commises               |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayModifyEntityMenu(c);
                break;
            case 1:
                MAJ.updatePerformance(c, "PERFORMER", "CUMUL_POINT" );
                break;
            case 2:
                MAJ.updatePerformance(c, "PERFORMER", "CUMUL_FAUTE");
                break;
            default:
                displayModifyPerformanceMenu(c);
        }
    }

    public static void displayModifyCoachMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
                MAJ.updateCoach(c);
    }


    public static void displayModifyEntityMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle entité voulez-vous modifier?            |");
        System.out.println("|         1 - PERSONNE                               |");
        System.out.println("|         2 - CLUB                                   |");
        System.out.println("|         3 - EQUIPE                                 |");
        System.out.println("|         4 - CATEGORIE                              |");
        System.out.println("|         5 - RENCONTRE                              |");
        System.out.println("|         6 - JOUEUR                                 |");
        System.out.println("|         7 - PERFORMER                              |");
        System.out.println("|         8 - ENTRAINER                              |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayMAJMenu(c);
                break;
            case 1:
                displayModifyPersonMenu(c);
                break;
            case 2:
                displayModifyClubMenu(c);
                break;
            case 3:
                displayModifyTeamMenu(c);
                break;
            case 4:
                displayModifyCategoryMenu(c);
                break;
            case 5:
                displayModifyMatchMenu(c);
                break;
            case 6:
                displayModifyPlayerMenu(c);
                break;
            case 7:
                displayModifyPerformanceMenu(c);
                break;
            case 8:
                displayModifyCoachMenu(c);
                break;
            default:
                displayModifyEntityMenu(c);
        }

    }


    // ----------------------------- MAJ - Add Menus --------------------------------- 

    public static void displayAddEntityMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle entité voulez-vous ajouter?             |");
        System.out.println("|         1 - PERSONNE                               |");
        System.out.println("|         2 - CLUB                                   |");
        System.out.println("|         3 - EQUIPE                                 |");
        System.out.println("|         4 - CATEGORIE                              |");
        System.out.println("|         5 - RENCONTRE                              |");
        System.out.println("|         6 - JOUEUR                                 |");
        System.out.println("|         7 - PERSONNE JOUEUR                        |");
        System.out.println("|         8 - PERFORMER                              |");
        System.out.println("|         9 - ENTRAINER                              |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayMAJMenu(c);
                break;
            case 1:
                MAJ.addPerson(c);
                break;
            case 2:
                MAJ.addClub(c);
                break;
            case 3:
                MAJ.addTeam(c);
                break;
            case 4:
                MAJ.addCategory(c);
                break;
            case 5:
                MAJ.addMatch(c);
                break;
            case 6:
                MAJ.addPlayer(c);
                break;
            case 7:
                MAJ.addPersonPlayer(c);
                break;
            case 8:
                MAJ.addPerformance(c);
                break;
            case 9:
                MAJ.addCoach(c);
                break;
            default:
                displayAddEntityMenu(c);
        }
    }

    // ------------------------- MAJ - Delete Menus ---------------------------

    public static void displayDeleteFromEntityMenu(Connection c)
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Quelle entité voulez-vous supprimer?           |");
        System.out.println("|         1 - PERSONNE                               |");
        System.out.println("|         2 - CLUB                                   |");
        System.out.println("|         3 - EQUIPE                                 |");
        System.out.println("|         4 - CATEGORIE                              |");
        System.out.println("|         5 - RENCONTRE                              |");
        System.out.println("|         6 - JOUEUR                                 |");
        System.out.println("|         7 - PERSONNE JOUEUR                        |");
        System.out.println("|         8 - PERFORMER                              |");
        System.out.println("|         9 - ENTRAINER                              |");
        System.out.println("|   Tapez 0 pour revenir en arrière                  |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayMAJMenu(c);
                break;
            case 1:
                MAJ.deleteFromEntity(c, "PERSONNE");
                break;
            case 2:
                MAJ.deleteFromEntity(c, "CLUB");
                break;
            case 3:
                MAJ.deleteFromEntity(c, "EQUIPE");
                break;
            case 4:
                MAJ.deleteFromEntity(c, "CATEGORIE");
                break;
            case 5:
                MAJ.deleteFromEntity(c, "RENCONTRE");
                break;
            case 6:
                MAJ.deleteFromEntity(c, "JOUEUR");
                break;
            case 7:
                MAJ.deleteFromEntity(c, "JOUEUR");
                MAJ.deleteFromEntity(c, "PERSONNE");
                break;
            case 8:
                MAJ.deletePerformance(c);
                break;
            case 9:
                MAJ.deleteCoach(c);
                break;
            default:
                displayDeleteFromEntityMenu(c);
        }
    }
  
    public static void displayMAJMenu(Connection c) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("|     Que souhaitez-vous mettre à jour?              |");
        System.out.println("|         1 - Ajouter une entité                     |");
        System.out.println("|         2 - Supprimer une entité                   |");
        System.out.println("|         3 - Modifier une entité                    |");
        System.out.println("|     Tapez 0 Pour revenir en arrière                |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                displayMenu(c);
                break;
            case 1:
                displayAddEntityMenu(c);
             break;
            case 2:
                displayDeleteFromEntityMenu(c);
             break;
            case 3:
                displayModifyEntityMenu(c);
             break;
            default:
                displayMAJMenu(c);
        }
        //sc.close();
    }

    public static void displayMenu(Connection c) 
    throws SQLException, ClassNotFoundException, java.io.IOException {
        System.out.println("######################################################");
        System.out.println("#                                                    #");
        System.out.println("#         Fédération sportive de Hand-ball           #");
        System.out.println("#                                                    #");
        System.out.println("######################################################");
        System.out.println("|                        Menu                        |");
        System.out.println("|                                                    |");
        System.out.println("|       Quelle opération vous souhaitez faire ?      |");
        System.out.println("|              1 - Consultation                      |");
        System.out.println("|              2 - Statistiques                      |");
        System.out.println("|              3 - Mise à jour                       |");
        System.out.println("|      Tapez 0 si vous voulez quittez                |");
        System.out.print  ("|---->Tapez le numéro de l'option: ");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        System.out.println("|----------------------------------------------------|");
        switch (i) {
            case 0:
                System.exit(0);
            case 1:
                displayConsultationMenu(c);
             break;
            case 2:
                displayStatiqtiquesMenu(c);
             break;
            case 3:
                displayMAJMenu(c);
             break;
            default:
                displayMenu(c);
          }
        //sc.close();
    }

}
