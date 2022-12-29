package main.java.logic;

import java.util.ArrayList;

public class CoordinateList extends ArrayList<Coordinate> {

    public void add(int row, int col) {
        this.add(new Coordinate(row, col));
    }



}
