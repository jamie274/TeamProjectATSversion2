package AdvisorClasses;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class SQLDisplayAdvisorTables {

    public SQLDisplayAdvisorTables() {}

    public void DisplayAdvisorBlankTable(JTable blankTable, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT AdvisorID, ID, BlankType, UsedStatus FROM BlankStock WHERE AdvisorID = " + staffID);

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
                //"Advisor ID", "Blank ID", "Blank Type","Used Status"
                String btAdvisorID = result.getString("AdvisorID");
                String btBlankID = Integer.toString(result.getInt("ID"));
                String btBlankType = result.getString("BlankType");
                String btBlankUsedStatus = result.getString("UsedStatus");

                String[] row = {btAdvisorID, btBlankID, btBlankType, btBlankUsedStatus};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }
    }

    public void DisplaySalesTable(JTable blankTable, String staffID){

        try {
            // connecting to the database server
            Connection con = DriverManager.getConnection("jdbc:mysql://smcse-stuproj00.city.ac.uk:3306/in2018g08",
                    "in2018g08_a", "R8pV1HmN");

            Statement stm = con.createStatement();
            ResultSet result = stm.executeQuery("SELECT ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, PaymentType," +
                    " SaleType, Country, TotalAmount, AmountPaid, Status, Date  " +
                    "FROM Sales Where AdvisorID =" + staffID);

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
                //ID, BlankStockID, CustomerID, CommissionRateTicketType, AdvisorID, DiscountPlanType, PaymentType, SaleType,
                // ExchangeRateCurrency, TotalAmount, AmountPaid, Status, Date
                String ID = Integer.toString(result.getInt(1));
                String BlankID = Integer.toString(result.getInt(2));
                String CustomerID = Integer.toString(result.getInt(3));
                String Commission = Integer.toString(result.getInt(4));
                String AdvisorID = Integer.toString(result.getInt(5));
                String Payment = result.getString(6);
                String SaleType = result.getString(7);
                String Country = result.getString(8);
                String Total =  Integer.toString(result.getInt(9));
                String Paid = Integer.toString(result.getInt(10));
                String Status = result.getString(11);
                String Date = String.valueOf(result.getDate(12));


                String[] row = {ID, BlankID, CustomerID, Commission, AdvisorID, Payment, SaleType, Country, Total, Paid, Status, Date};
                //add the rows to the table
                model.addRow(row);
            }
            //closes the connection to db
            con.close();
        }catch (SQLException s) {
            s.printStackTrace();
        }

    }


    public void ClearTable(JTable table){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
    }
}
