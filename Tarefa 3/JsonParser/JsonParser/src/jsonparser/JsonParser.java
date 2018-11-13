package jsonparser;

import org.json.JSONObject;

/**
 *
 * @author luciano
 */
public class JsonParser {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();
        obj.append("Valor1", "1");
        obj.append("Valor2", "2");

        System.out.println(obj.toString());

    }
}
