package quizgame;

import java.sql.*;

public class DataAccessObject {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    public void ConnectToMySql()throws Exception {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect = DriverManager
                    .getConnection("jdbc:mysql://localhost/quizgamedatabase?"
                            + "user=root&password=root");

        } catch (Exception e) {
            throw e;
        }
    }

    public void readDataFromMySql()throws Exception {
        try {
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from quizgamedatabase.quiz");
            writeResultSet(resultSet);
        } catch (Exception e) {
            throw e;
        }
        finally {
            statement.close();
        }
    }

    public void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            String id = "" + resultSet.getInt("id");
            String capital = resultSet.getString("capital");
            String country = resultSet.getString("country");

            System.out.print("Id: " + id + "\t");
            System.out.print("  capital: " + capital + "\t");
            System.out.print("  country: " + country + "\t\n");

        }
    }

    public ResultSet getResultSet(){
        try {
            statement = connect.createStatement();
            resultSet = statement
                    .executeQuery("select * from quizgamedatabase.quiz");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }


}
