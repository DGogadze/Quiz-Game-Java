import quizgame.DataAccessObject;
import quizgame.Quiz;
import quizgame.QuizTimer;
import quizgame.WindowThread;

public class Main {
    public static void main(String[] args) throws Exception{
        WindowThread myBox = new WindowThread();
        Quiz quiz = new Quiz();
        QuizTimer quizTimer = new QuizTimer();
        myBox.start();
        quiz.run();
        quizTimer.start();
        DataAccessObject dao = new DataAccessObject();
        dao.ConnectToMySql();
        dao.readDataFromMySql();
        dao.close();
    }
}