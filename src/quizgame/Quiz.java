package quizgame;

import java.lang.reflect.Array;
import java.sql.ResultSet;

import static quizgame.WindowThread.capitalArray;

public class Quiz{
    private DataAccessObject dao = new DataAccessObject();
    private ResultSet resultSet = null;
    String[] countryArray = new String[17];
    int randomisation = ((int) (Math.random()*10*10)) % Array.getLength(countryArray);
    public void run(){
        try {
            dao.ConnectToMySql();
        } catch (Exception e){
            e.printStackTrace();
        }
        resultSet = dao.getResultSet();
        try {
            int iterator = 0;
            while (resultSet.next()){
                countryArray[iterator]=resultSet.getString("country");
                iterator++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
            WindowThread.questionLabel.setText("What is a capital of - " + countryArray[randomisation]);
            WindowThread.iterator=randomisation;
            randomisation = ((int) (Math.random()*10*10)) % Array.getLength(countryArray);
    }
}
