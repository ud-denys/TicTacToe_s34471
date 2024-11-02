class tictactoelib {
    static {
        System.loadLibrary("libtictactoelib");
    }

    public native void initializeBoard();
    public native boolean makeMove(int x, int y, char player);
    public native char checkWin();
    public native void resetBoard();
    public native char[][] getBoard();
}