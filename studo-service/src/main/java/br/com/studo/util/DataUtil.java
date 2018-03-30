package br.com.studo.util;

import java.util.Calendar;

public class DataUtil {

    private DataUtil() {
    }

    public static Integer anoAtual() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }
}
