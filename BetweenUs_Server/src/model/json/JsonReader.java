package model.json;

import com.google.gson.Gson;
import model.json.Data;

import java.io.FileReader;

public class JsonReader {
    static Data data = new Data();
    public static Data llegeixJSON(){
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader("BetweenUs_Server/files/config.json"));
            data = gson.fromJson(reader, Data.class);

            System.out.println("\nLectura JSON finalitzada.\n");

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
        return data;
    }
    public Data getDades() {
        return data;
    }
}

//hola