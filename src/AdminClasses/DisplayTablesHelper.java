package AdminClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Abdul Rehman
 */
// Class used to read data from the database and display
// it in tables on the User Dashboards
public class DisplayTablesHelper {


    public DisplayTablesHelper() {}


    public void DisplayBlankTable(JTable blankTable){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, UsedStatus FROM BlankStock");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the blank table
            DefaultTableModel model = (DefaultTableModel) blankTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){
                //"Advisor ID", "Blank ID", "Blank Type", "Blank Status", "Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = result.getString("BlankType");
                String btBlankStatus = result.getString("Status");
                String btBlankUsedStatus = result.getString("UsedStatus");

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, btBlankUsedStatus};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }

    }

    public void DisplayAdvisorTableManager(JTable staffTable){
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor'");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){

                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");


                String[] row = {userID, userFirstName, userLastName};
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }


    //This method takes in JTable to edit the staff table model then add rows of data retrieved from db
    public void DisplayUserTableAdmin(JTable staffTable){
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Role FROM AirViaUser");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) staffTable.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){

                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");
                String userRole = result.getString("Role");

                String[] row = {userID, userFirstName, userLastName, userRole};
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchStaff(JTable table, String user) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName, Role FROM AirViaUser WHERE ID = " + user);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");
                String userRole = result.getString("Role");

                String[] row = {userID, userFirstName, userLastName, userRole};
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayUserTableAdmin(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchBlank(JTable table, String blank) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, Status, UsedStatus FROM BlankStock WHERE ID = " + blank);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                //"Advisor ID", "Blank ID", "Blank Type", "Blank Status", "Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = result.getString("BlankType");
                String btBlankStatus = result.getString("Status");
                String btBlankUsedStatus = result.getString("UsedStatus");

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankStatus, btBlankUsedStatus};
                //add the rows to the table
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayBlankTable(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Blank does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void deleteStaff(JTable table, String user) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            // user is deleted, as their id is passed through
            stm.executeUpdate("DELETE FROM AirViaUser WHERE ID = " + user);
            stm.execute("SET FOREIGN_KEY_CHECKS=1");

            // message box, showing that the user has been successfully deleted
            JOptionPane.showMessageDialog(null, "User has been deleted");
            ClearTable(table); // clears the table
            DisplayUserTableAdmin(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void deleteBlank(JTable table, String blankId) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            // user is deleted, as their id is passed through
            stm.executeUpdate("DELETE FROM BlankStock WHERE ID = " + blankId);
            stm.execute("SET FOREIGN_KEY_CHECKS=1");

            // message box, showing that the user has been successfully deleted
            JOptionPane.showMessageDialog(null, "Blank has been deleted");
            ClearTable(table); // clears the table
            DisplayBlankTable(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchAdvisor(JTable table, String user) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, FirstName, LastName FROM AirViaUser WHERE Role = 'Travel Advisor' AND ID = " + user);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                // The variable UserID, UserFirstName, UserLastName and UserRole are to store the data retrieved from the db.
                String userID = result.getString("ID");
                String userFirstName = result.getString("FirstName");
                String userLastName = result.getString("LastName");

                String[] row = {userID, userFirstName, userLastName};
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayAdvisorTableManager(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "User does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    // Method used to assign and re-assign blanks to advisors
    public void assignBlank(JTable table, String advisorID, String blankID) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            // Blank stock record is updated to assign the blanlk to an advisor and change the status to 'assigned'
            stm.executeUpdate("UPDATE BlankStock SET AdvisorID = " + "'" + advisorID + "' ," + "Status = 'Assigned'" + " WHERE ID = " + blankID);
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            // message box, showing that the blank has been successfully assigned
            JOptionPane.showMessageDialog(null, "Blank has been successfully assigned to " + advisorID);
            ClearTable(table); // clears the table
            DisplayBlankTable(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void DisplayCommissionTable(JTable table){
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT TicketType, Percentage FROM CommisionRate");

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the commission table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            while(result.next()){
                // The variable ticket type and commission rate are to store the data retrieved from the db.
                String ticketType = Integer.toString(result.getInt("TicketType"));
                String commissionRate = Float.toString(result.getFloat("Percentage"));

                String[] row = {ticketType, commissionRate};
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void searchTicketType(JTable table, String type) {
        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT TicketType, Percentage FROM CommisionRate WHERE TicketType = " + type);

            //this stores all the meta-data received from the query result
            ResultSetMetaData rsmd = result.getMetaData();

            //this creates a default model of the staff table
            DefaultTableModel model = (DefaultTableModel) table.getModel();

            //checks the number of columns and creates a string object of column names
            int cols = rsmd.getColumnCount();
            String[] colName = new String[cols];

            //inserts column names to the table
            for(int i = 0; i < cols; i++){
                colName[i] = rsmd.getColumnName(i+1);
            }
            model.setColumnIdentifiers(colName);

            //inserts all the data in the respective columns
            if(result.next()){
                // The variable ticket type and commission rate are to store the data retrieved from the db.
                String ticketType = Integer.toString(result.getInt("TicketType"));
                String commissionRate = Float.toString(result.getFloat("Percentage"));

                String[] row = {ticketType, commissionRate};
                model.addRow(row);
            } else { // user has not been found
                ClearTable(table); // clears the table
                DisplayCommissionTable(table); // refreshes the table by re-displaying the data
                JOptionPane.showMessageDialog(null, "Ticket type does not exist, please try again");
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void addCommission(JTable table, String type, String rate) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            // Blank stock record is updated to assign the blanlk to an advisor and change the status to 'assigned'
            stm.executeUpdate("UPDATE CommisionRate SET Percentage = " + "'" + rate + "'" + " WHERE TicketType = " + type);
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            // message box, showing that the blank has been successfully assigned
            JOptionPane.showMessageDialog(null, "Commission has been successfully applied to " + type);
            ClearTable(table); // clears the table
            DisplayCommissionTable(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    // method used to delete a commission rate from a ticket type
    public void deleteCommission(JTable table, String type) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.execute("SET FOREIGN_KEY_CHECKS=0");
            // Ticket type record is updated to delete the commission rate
            stm.executeUpdate("UPDATE CommisionRate SET Percentage = NULL WHERE TicketType = " + type);
            stm.execute("SET FOREIGN_KEY_CHECKS=1");
            // message box, showing that the commission has been successfully deleted
            JOptionPane.showMessageDialog(null, "Commission has been successfully deleted from " + type);
            ClearTable(table); // clears the table
            DisplayCommissionTable(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    // method used to update the customer's details
    public void updateStaff(JTable table, String id, String role, String fName, String lName, String emailAd, String pass) {

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            stm.executeUpdate("UPDATE AirViaUser SET Role = " + ("'" + role + "',") + (" FirstName = '" + fName + "',") +
                    ("LastName = '" + lName + "',") + ("Email = '" + emailAd + "',") + ("Password = '" + pass + "' ") + "WHERE ID = " + id);

            // message box, showing that the customer's details have been successfully updated
            JOptionPane.showMessageDialog(null, "Customer details has been successfully updated");
            ClearTable(table); // clears the table
            DisplayUserTableAdmin(table); // refreshes the table by re-displaying the data

            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    // Clears the table of data
    public void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
}