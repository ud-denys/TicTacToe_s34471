public class tictactoelib {
    static{
        System.loadLibrary("libtictactoelib");
    }


    public static native void makeMoveJNI(int row, int col);
    public static native String[][] getBoardStateJNI();
    public static native void resetGameJNI();
    public static native String getCurrentPlayerJNI();
    public static native boolean checkWinJNI();
    public static native boolean isDrawJNI();
}
