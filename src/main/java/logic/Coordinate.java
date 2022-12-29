package main.java.logic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinate {

    public Coordinate(int row, int col){
        this.row = row;
        this.col = col;
    }

    private int row;
    private int col;

    public String toString(){
        return this.row + ":" + this.col;
    }

}
