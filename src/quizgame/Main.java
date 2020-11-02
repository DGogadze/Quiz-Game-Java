package quizgame;

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