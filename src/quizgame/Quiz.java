package quizgame;

import java.lang.reflect.Array;

public class Quiz{
    String[] countryArray = {"Armenia","Belarus","China","Denmark","Egypt","France","Hungary","Georgia","Italy","U.S.A",
    "Jamaica","Japan","Kuwait","Zimbabwe","U.K","Russia","Germany"};
    int randomisation = ((int) (Math.random()*10*10)) % Array.getLength(countryArray);
    public void run(){
            WindowThread.questionLabel.setText("What is a capital of - " + countryArray[randomisation]);
            WindowThread.iterator=randomisation;
            randomisation = ((int) (Math.random()*10*10)) % Array.getLength(countryArray);
    }
}
