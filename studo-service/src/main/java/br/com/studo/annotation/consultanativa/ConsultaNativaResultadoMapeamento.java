package br.com.studo.annotation.consultanativa;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Slf4j
public class ConsultaNativaResultadoMapeamento {

    private ConsultaNativaResultadoMapeamento() {
    }

    public static <T> List<T> mapear(List<Object[]> listaObjetos, Class<T> tipoGenerico) {

        List<T> resultado = new ArrayList<>();

        List<Field> mapeamento = getConsultaNativaColuna(tipoGenerico);

        try {
            for (Object[] objects : listaObjetos) {
                T tipo = tipoGenerico.newInstance();
                for (int i = 0; i < objects.length; i++) {
                    if (Objects.nonNull(objects[i])) {
                        BeanUtils.setProperty(tipo, mapeamento.get(i).getName(), objects[i]);
                    }
                }
                resultado.add(tipo);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException ie) {
            log.debug("Erro: ", ie);
            resultado.clear();
        }
        return resultado;
    }

    private static <T> List<Field> getConsultaNativaColuna(Class<T> tipoGenerico) {

        Field[] campos = tipoGenerico.getDeclaredFields();

        List<Field> camposOrdenados = Arrays.asList(new Field[campos.length]);

        for (int i = 0; i < campos.length; i++) {

            if (campos[i].isAnnotationPresent(ConsultaNativaColuna.class)) {

                ConsultaNativaColuna coluna = campos[i].getAnnotation(ConsultaNativaColuna.class);

                camposOrdenados.set(coluna.indice(), campos[i]);
            }
        }
        return camposOrdenados;
    }
}
