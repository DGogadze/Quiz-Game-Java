package quizgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;

public class Main {
    public static void main(String[] args){
        WindowThread myBox = new WindowThread();
        Quiz quiz = new Quiz();
        QuizTimer quizTimer = new QuizTimer();
        myBox.start();
        quiz.run();
        quizTimer.start();
    }
}
class WindowThread extends Thread{
    public static String[] capitalArray = {"Erevan","Minsk","Beijing","Copenhagen","Cairo","Paris","Budapest","Tbilisi","Rome","Washington"
    ,"Kingston","Tokyo","Kuwait City","Harare","London","Moscow","Berlin"};
    Quiz quiz = new Quiz();
    public static int randomisation;
    JLabel afterAnswerLabel = new JLabel();
    public static int iterator;
    public static JFrame myBox = new JFrame("Quiz Game");
    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    public static int score=0;
    JLabel scoreLabel = new JLabel("Score:" + score);
    public static JButton submitButton = new JButton("Submit");
    public static JLabel questionLabel= new JLabel();
    public static JTextField answerField = new JTextField();
    public static JLabel timerLabel = new JLabel();
    int width = dimension.width;
    int height = dimension.height;
    int windowX = width/4;
    int windowY = height/4;
    public static JPanel container = new JPanel();
    @Override
    public void run(){
        myBox.add(container);
        myBox.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        myBox.setLocation(width/4,height/4);
        scoreLabel.setBounds(15,5,500,20);
        questionLabel.setForeground(Color.magenta);
        container.add(answerField);
        answerField.setBounds(windowX-100,windowY-10,200,20);
        container.add(questionLabel);
        container.add(submitButton);
        answerField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==10){
                    submitButton.doClick();
                }
            }
        });
        container.add(afterAnswerLabel);
        container.add(timerLabel);
        timerLabel.setBounds(15,25,500,20);
        afterAnswerLabel.setBounds(windowX-75,windowY+25,200,20);
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (answerField.getText().equals(capitalArray[iterator])){
                    randomisation = (int) (Math.random() * 10);
                    score+= randomisation+1;
                    scoreLabel.setText("Score:"+score);
                    answerField.setText("");
                    afterAnswerLabel.setText("Correct,you gain " + (randomisation+1) + " points");
                    afterAnswerLabel.setForeground(Color.GREEN);
                    if (score>=0)
                        scoreLabel.setForeground(Color.GREEN);
                    else
                        scoreLabel.setForeground(Color.RED);
                    myBox.repaint();
                    quiz.run();
                }
                else if (answerField.getText().equals("")){
                    afterAnswerLabel.setText("Your field is empty");
                    afterAnswerLabel.setForeground(SystemColor.DARK_GRAY);
                }
                else {
                    randomisation = (int) (Math.random() * 10);
                    score-= randomisation-1;
                    scoreLabel.setText("Score:"+score);
                    afterAnswerLabel.setText("You are wrong,you lose " + (randomisation-1) + " points");
                    afterAnswerLabel.setForeground(Color.RED);
                    answerField.setText("");
                    if (score>=0)
                        scoreLabel.setForeground(Color.GREEN);
                    else
                        scoreLabel.setForeground(Color.RED);
                    myBox.repaint();
                    quiz.run();
                }
            }
        });
        submitButton.setBounds(windowX-75,windowY+50,150,20);
        questionLabel.setBounds(windowX-90,windowY-30,200,20);
        container.add(scoreLabel);
        container.setLayout(null);
        container.setBackground(Color.ORANGE);
        myBox.setSize(width/2,height/2);
        myBox.setVisible(true);
        myBox.setResizable(false);
    }
}
class Quiz{
    String[] countryArray = {"Armenia","Belarus","China","Denmark","Egypt","France","Hungary","Georgia","Italy","U.S.A",
    "Jamaica","Japan","Kuwait","Zimbabwe","U.K","Russia","Germany"};
    int randomisation = ((int) (Math.random()*10*10)) % Array.getLength(countryArray);
    public void run(){
            WindowThread.questionLabel.setText("What is a capital of - " + countryArray[randomisation]);
            WindowThread.iterator=randomisation;
            randomisation = ((int) (Math.random()*10*10)) % Array.getLength(countryArray);
    }
}
class QuizTimer extends Thread {
    @Override
    public void run() {
        for (int i = 180; i >= 0; i--) {
            WindowThread.timerLabel.setText("Timer:" + i);
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i == 0) {
                WindowThread.container.setVisible(false);
            }
        }
    }
}