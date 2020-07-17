import com.studiohartman.jamepad.ControllerManager;
import com.studiohartman.jamepad.ControllerState;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;

public class Joyinput implements Runnable {

    private UIController UI;
    public Joyinput(UIController UI) {
        this.UI = UI;
    }

    @Override
    public void run() {
        System.out.println("New Thread Started");
        try {
            listenController();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listenController() throws AWTException, NoSuchFieldException, IllegalAccessException {

        ControllerManager controllers = new ControllerManager();
        controllers.initSDLGamepad();
        DecimalFormat df = new DecimalFormat("#.##");
        Robot robot = new Robot();
        robot.setAutoDelay(80);

        while(true) {

            ControllerState currState = controllers.getState(0);

            if(currState.isConnected) { UI.connected(); }
            else { UI.disconnected(); }

            double leftAngle = currState.leftStickAngle;
            double leftMagnitude = currState.leftStickMagnitude;

            double rightAngle = currState.rightStickAngle;
            double rightMagnitude = currState.rightStickMagnitude;

            if (Map.section(leftAngle) != -1) {
                if (currState.lb) {
                    robot.keyPress((int)(Map.keyMap[0][Map.ring(leftMagnitude)][Map.section(leftAngle)]));
                }
                UI.hover(0, Map.ring(leftMagnitude), Map.section(leftAngle));
            }

            if (Map.section(rightAngle) != -1) {
                if (currState.rb) {
                    robot.keyPress((int)(Map.keyMap[1][Map.ring(rightMagnitude)][Map.section(rightAngle)]));
                }
                UI.hover(1, Map.ring(rightMagnitude), Map.section(rightAngle));
            }

            if (currState.leftTrigger > 0.5) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                UI.shiftPress();
            }
            else {
                robot.keyRelease(KeyEvent.VK_SHIFT);
                UI.shiftRelease();
            }

            if (currState.dpadUp) robot.keyPress(KeyEvent.VK_UP);
            if (currState.dpadDown) robot.keyPress(KeyEvent.VK_DOWN);
            if (currState.dpadLeft) robot.keyPress(KeyEvent.VK_LEFT);
            if (currState.dpadRight) robot.keyPress(KeyEvent.VK_RIGHT);

            if (currState.a) robot.keyPress(KeyEvent.VK_ENTER);
            if (currState.b) robot.keyPress(KeyEvent.VK_BACK_SPACE);

            UI.rotateArc(0, leftAngle, leftMagnitude);
            UI.rotateArc(1, rightAngle, rightMagnitude);

        }
    }
}