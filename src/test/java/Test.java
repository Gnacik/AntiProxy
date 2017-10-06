import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;

public class Test {

    //json test

    public static void main(String[] args){
        System.out.println(Boolean.toString(hasProxy("test")));
    }

    private static boolean hasProxy(String address){
        ObjectMapper objectMapper = new ObjectMapper();
        boolean hasProxy = false;
        try {
            if (objectMapper.readTree(new URL("http://proxycheck.io/v1/1.160.165.115&key=111111-222222-333333-444444&vpn=1&asn=1&node=1&time=1&tag=forum%20signup%20page"))
                    .get("proxy").toString() //get "proxy" from json
                    .contains("yes")){ //equals|equalsIgnoreCase returns false
                hasProxy = true;
            }
        }
        catch (IOException e){

        }
        return hasProxy;
    }
}
