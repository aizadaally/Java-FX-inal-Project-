package main.java.util;

import com.google.gson.Gson;
import main.java.logic.Board;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONParser {

    public static void main(String[] args) {
        String rawJSON = readJSONToString("initialLayout");
        Board b = convertStringToObject(rawJSON);
        System.out.println(b);
        System.out.println(convertObjectToString(b));
    }

    /**
     * tokenizer for generic JSON
     * @param rawJSON the string containing all json characters
     * @return a constructed java object
     */
    public static Board convertStringToObject(String rawJSON){
        Gson g = new Gson();
        return g.fromJson(rawJSON, Board.class);
    }

    public static String convertObjectToString(Object obj) {
        Gson g = new Gson();
        return g.toJson(obj);
    }


    public static String readJSONToString(String jsonFilename) {

        String fileName = "src/main/resources/json/" + jsonFilename + ".json";
        File f = new File(fileName);

        char[] chars = new char[(int)f.length()];

        try (FileReader reader = new FileReader(f)) {
            reader.read(chars);
        }
        catch(IOException ioe){
            ioe.printStackTrace();
        }

        return new String(chars);
    }
}
