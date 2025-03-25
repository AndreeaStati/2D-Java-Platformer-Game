package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.* ;



public class DbConnection {


///BAZE DE DATE

    //INSERARE IN BAZA DE DATE
    public static void insertB(String nume_fisier, String nume_tabela, int lvlIndex, int currentHealth, int aniTick) {
        Connection c = null;
        Statement stmt = null;


        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Andreea\\Desktop\\Anul 2\\sem 2\\PAOO\\Around the World/dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

           // String sql = "INSERT INTO " + nume_tabela + "(playTime) " + "VALUES (" + a + ");";
            String sql = "INSERT INTO " + nume_tabela + " ( lvlIndex, currentHealth, aniTick ) " + " VALUES (" + lvlIndex + "," + currentHealth  + "," + aniTick + ");" ;

            stmt.executeUpdate(sql);
            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.out.println("Eroare la insert!!!");
            e.printStackTrace();
            // Optional rollback in case of error
            try {
                if (c != null) {
                    c.rollback();

                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

/*! \fn LoadLevelIndex(String nume_fisier, String nume_tabela)
         \brief Functia se ocupa de incarcarea indexului nivelului din baza de date.
*/
    //  EXTRAGERE CEA MAI RECENTA VALOARE DIN BAZA DE DATE
    public static int LoadLevelIndex(String nume_fisier, String nume_tabela) {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Andreea\\Desktop\\Anul 2\\sem 2\\PAOO\\Around the World/dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next()) {
                value = rs.getInt("lvlIndex");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }

    public static int LoadCurrentHealth(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Andreea\\Desktop\\Anul 2\\sem 2\\PAOO\\Around the World/dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("currentHealth");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }

    public static int LoadAnimationTick(String nume_fisier, String nume_tabela)
    {
        Connection c = null;
        Statement stmt = null;
        int value = 0;
        try
        {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\Andreea\\Desktop\\Anul 2\\sem 2\\PAOO\\Around the World/dataBase.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("SELECT * FROM " + nume_tabela + ";");
            while (rs.next())
            {
                value = rs.getInt("aniTick");
            }

            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e)
        {
            System.out.println("Eroare la get!!!");
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return value;
    }

}
