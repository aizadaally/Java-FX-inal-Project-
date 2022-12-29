package main.java.logic;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Player {

    private String name;

    private List<Piece> deadPieces;

    public Player(){
        deadPieces = new ArrayList<>();
    }

}
