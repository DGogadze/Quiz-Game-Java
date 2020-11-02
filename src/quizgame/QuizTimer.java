package quizgame;

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
