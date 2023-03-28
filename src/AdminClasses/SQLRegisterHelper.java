package AdminClasses;

import javax.swing.*;
import java.sql.*;

/**
 *
 * @author Sahi
 *
 * This class is used as an SQL helper where data is passed through to the database and inserts a new user record
 */
public class SQLRegisterHelper {

    public SQLRegisterHelper() {}

    // the text from the text boxes are passed through as a parameter and then used to insert the new data into the database
    public void registerStaff(String idText, String roleText, String firstNameText, String lastNameText,
                              String emailText, String passwordText) {
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();

            // data to be inserted into the database
            String insertStm = ("'" + idText + "'") + ", " + ("'" + roleText + "'") + ", " + ("'" + firstNameText + "'") + ", " + ("'" + lastNameText + "'") + ", "
                    + ("'" + emailText + "'") + ", " + ("'" + passwordText + "'");

            // insert statement is run
            stm.executeUpdate("INSERT INTO AirViaUser VALUES ( " + insertStm + ")");

            // message box, showing that the user has been successfully added to the database
            JOptionPane.showMessageDialog(null, "User has been successfully registered with AirVia");

            // close the connection to db
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // -- to be completed (Sahi)
    public void addBlank(String idText, String typeText, String statusText) {

    }

}


