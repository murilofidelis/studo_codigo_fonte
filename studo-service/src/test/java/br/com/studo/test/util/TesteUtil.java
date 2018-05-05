package br.com.studo.test.util;


import org.codehaus.jackson.map.ObjectMapper;

public class TesteUtil {

    public static String connverteEmJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
