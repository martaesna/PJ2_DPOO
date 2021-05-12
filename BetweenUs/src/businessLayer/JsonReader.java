package businessLayer;

import com.google.gson.Gson;
import businessLayer.entities.json.Data;

import java.io.FileReader;

public class JsonReader {
    private static Data data = new Data();
    public static Data llegeixJSON(){
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader("BetweenUs/files/config.json"));
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