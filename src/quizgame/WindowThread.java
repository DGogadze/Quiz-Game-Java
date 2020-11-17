package quizgame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;

public class WindowThread extends Thread{
    private DataAccessObject dao = new DataAccessObject();
    private ResultSet resultSet = null;
    public static String[] capitalArray = new String[17];
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
        try {
            dao.ConnectToMySql();
        } catch (Exception e){
            e.printStackTrace();
        }
        resultSet = dao.getResultSet();
        try {
            int iterator = 0;
            while (resultSet.next()){
                capitalArray[iterator]=resultSet.getString("capital");
                iterator++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
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
