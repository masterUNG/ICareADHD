package com.example.kamon.icareadhd;


/**
 * Created by Kamon on 25/1/2560.
 */
/**
public class ConnectionClass {
    Connection conn = null;
    String url = "jdbc:jtds:sqlserver://122.155.18.18/";
    String dbName = "TEST;Instance=MSSQLSERVER;";
    String userName = "icareadhdi_adhd";
    String password = "iCareadhd3";
    String driver = "net.sourceforge.jtds.jdbc.Driver";

    public ConnectionClass() {
        try {
            Log.d("SQL", "start");
            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url + dbName, userName, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT NAME FROM USERS WHERE (USNA = 'F')");

            while (rs.next()) {
                String lastName = rs.getString("NAME");
                Log.d("SQL", lastName);
            }

            conn.close();
        } catch (Exception e)

        {
            Log.d("SQL", "error occur");
        }
    }

    public void login(String user, String pass) throws SQLException {

        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT NAME FROM USERS WHERE username = " + user);
        while (rs.next()) {
            String lastName = rs.getString("NAME");
            Log.d("SQL", lastName);
        }
    }
}
*/