package main.java.util;

public class Utils {

    private Utils(){}

    public static boolean isDarkTile(int row, int col){
        return (row%2==0 && col%2==0) || (row%2==1 && col%2==1);
    }

    public static boolean outOfBounds(int row, int col){
        return row < 0 || row > 7 || col < 0 || col > 7;
    }

}
