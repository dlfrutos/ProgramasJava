/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsontest1;

import org.json.JSONObject;

/**
 *
 * @author Daniel
 */
public class JsonTest1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JSONObject obj = new JSONObject();

        obj.put("name", "foo");
        obj.put("num", new Integer(100));
        obj.put("balance", new Double(1000.21));
        obj.put("is_vip", new Boolean(true));

        System.out.println(obj);

    }

}
