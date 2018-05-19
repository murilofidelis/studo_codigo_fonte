package br.com.studo.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    private DataUtil() {
    }

    public static Integer anoAtual() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    public static Date formatDate(String data) throws ParseException {
        java.util.Date dtFormat = new SimpleDateFormat("yyyy/MM/dd").parse(data);
        return new java.sql.Date(dtFormat.getTime());
    }
}
