package businessLayer;

import com.google.gson.Gson;
import businessLayer.entities.json.Data;
import java.io.FileReader;

public class JsonReader {
    private static Data data = new Data();

    /**
     * Mètode que llegeix la informació del json
     * @return informació del json en una classe Data
     */
    public static Data llegeixJSON(){
        try{
            Gson gson = new Gson();
            com.google.gson.stream.JsonReader reader;
            reader = new com.google.gson.stream.JsonReader(new FileReader("BetweenUs/files/config.json"));
            data = gson.fromJson(reader, Data.class);

        }catch(Exception e){
            System.out.println("No s'ha pogut llegir el fitxer JSON: " + e.getMessage());
        }
        return data;
    }
}