public class Map {

    private static double deadzone = 0.2;
    private static double ringzone = 0.9;

    public static char[][][] keyMap = {
        {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'F', 'R', 'E', 'D', 'S', 'X', 'C'},
            {'T', 'W', 'Q', 'A', 'Z', 'V', 'G'}},

        {
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'K', 'O', 'I', 'J', 'N', ',', 'M'},
            {'P', 'U', 'Y', 'H', 'B', '.', 'L'}},
    };

    public static double getDeadzone() {
        return deadzone;
    }

    public static void setDeadzone(double deadzone) {
        Map.deadzone = deadzone;
    }

    public static double getRingzone() {
        return ringzone;
    }

    public static void setRingzone(double ringzone) {
        Map.ringzone = ringzone;
    }

    public static int ring(double magnitude){
        if (magnitude > ringzone)
            return 2;
        else if (magnitude > deadzone)
            return 1;
        else
            return 0;
    }

    public static int section(double angle){
        double sectionWide = 360 / 7;
        if (angle > 0 && angle <= sectionWide)
            return 0;
        else if (angle > sectionWide && angle <= 2 * sectionWide)
            return 1;
        else if (angle > 2 * sectionWide && angle <= 3 * sectionWide)
            return 2;
        else if (angle > 3 * sectionWide && angle <= 3.5 * sectionWide)
            return 3;
        else if (angle < 0 * sectionWide && angle >= -sectionWide)
            return 6;
        else if (angle < -sectionWide && angle >= 2 * -sectionWide)
            return 5;
        else if (angle < 2 * -sectionWide && angle >= 3 * -sectionWide)
            return 4;
        else if (angle < 3 * -sectionWide && angle >= 3.5 * -sectionWide)
            return 3;
        else
            return -1;
    }
}
