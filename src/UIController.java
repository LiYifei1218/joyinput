import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import java.util.Objects;

public class UIController {
    @FXML
    Button startButton;
    @FXML
    Text connectionIndicatorText;
    @FXML
    Circle connectionIndicator;
    @FXML
    Text shiftIndicator;
    @FXML
    Text lin0, lin1, lin2, lin3, lin4, lin5, lin6, lout0, lout1, lout2, lout3, lout4, lout5, lout6, lzero, rin0, rin1, rin2, rin3, rin4, rin5, rin6, rout0, rout1, rout2, rout3, rout4, rout5, rout6, rzero;
    @FXML
    Arc leftArc, rightArc;
    private Thread work;

    public void start() {
        if (work == null) {
            System.out.println("Initialize Event");
            Runnable run = new Joyinput(this);
            work = new Thread(run);
            work.start();
            startButton.setText("Stop");
        } else {
            work.stop();
            work = null;
            startButton.setText("Start");
            connectionIndicator.setFill(Color.valueOf("ffe21f"));
        }

    }

    public void disconnected() {
        connectionIndicator.setFill(Color.RED);
    }
    public void connected() {
        connectionIndicator.setFill(Color.GREEN);
    }

    public void shiftPress() {
        shiftIndicator.setStrokeWidth(2);
    }
    public void shiftRelease() {
        shiftIndicator.setStrokeWidth(0);
    }

    public String getField(int hand, int ring, int section) {
        String currSection = "";
        boolean flag = false;
        if (hand == 0 && ring == 1)
            currSection = "lin";
        else if (hand == 0 && ring == 2)
            currSection = "lout";
        else if (hand == 1 && ring == 1)
            currSection = "rin";
        else if (hand == 1 && ring == 2)
            currSection = "rout";
        else {
            if (hand == 0)
                currSection = "rzero";
            else
                currSection = "lzero";
            flag = true;
        }
        if (flag)
            return currSection;
        else
            return currSection + section;
    }

    public void hover(int hand, int ring, int section) throws NoSuchFieldException, IllegalAccessException {
        String Field = getField(hand, ring, section);

        for (int i = 0; i < 2; i++)
            for (int j = 1; j < 3; j++)
                for (int k = 0; k < 7; k++) {
                    String currField = getField(hand, j, k);
                    if (!Objects.equals(currField, Field))
                        ((Text) UIController.class.getDeclaredField(currField).get(this)).setStrokeWidth(0);
                }
        ((Text)UIController.class.getDeclaredField(Field).get(this)).setStrokeWidth(3);
        ((Text)UIController.class.getDeclaredField("lzero").get(this)).setStrokeWidth(0);
        ((Text)UIController.class.getDeclaredField("rzero").get(this)).setStrokeWidth(0);
    }

    public void rotateArc(int hand, double angle, double magnitude) {
        if (hand == 0) {
            leftArc.setStartAngle(angle - 25);
            if (magnitude < Map.getDeadzone())
                leftArc.setOpacity(0);
            else
                leftArc.setOpacity(0.5);
        }
        else {
            rightArc.setStartAngle(angle - 25);
            if (magnitude < Map.getDeadzone())
                rightArc.setOpacity(0);
            else
                rightArc.setOpacity(0.5);
        }
    }
}