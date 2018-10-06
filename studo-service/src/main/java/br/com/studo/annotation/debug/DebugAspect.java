package br.com.studo.annotation.debug;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class DebugAspect {

    @After("@annotation(br.com.studo.annotation.debug.Debug)")
    public void showDebug(JoinPoint joinPoint) throws JsonProcessingException {

        Method method = getMethod(joinPoint);

        Debug debug = method.getAnnotation(Debug.class);

        if (!debug.ativo()) {
            return;
        }

        log.info("======================================== STUDO DEGUB =================================================");
        log.info("Classe: {}", method.getDeclaringClass().getName());
        log.info("Método: {}", method.getName());
        log.info("Retono: {}", method.getReturnType().getName());
        log.info("Paramêtro: {}", method.getParameterTypes()[0].getName());
        log.info("_________________________________________________________________________________________________________________");

        Object[] args = getArgs(joinPoint);
        ObjectMapper mapper = new ObjectMapper();
        String jsonObject = mapper.writeValueAsString(args[0]);
        log.info("{}", method.getParameterTypes()[0].getName() + ": " + jsonObject);

        log.info("======================================================================================================");
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }

    private Object[] getArgs(JoinPoint joinPoint) {
        return joinPoint.getArgs();
    }
}
