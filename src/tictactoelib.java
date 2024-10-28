public class tictactoelib {

    public static native void makeMoveJNI(int row, int col);
    public static native String[][] getBoardStateJNI();
    public static native void resetGameJNI();
    public static native String getCurrentPlayerJNI();
}
