package com.mlv.springloggingcustomannotation.customannotation;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;


@Aspect
@Component
@Slf4j
public class CheckAdvice {

    private static final Logger logger= LoggerFactory.getLogger(CheckAdvice.class);

    @Around("@annotation(com.mlv.springloggingcustomannotation.customannotation.LogTime)")
    public Object checkTime(ProceedingJoinPoint pj) throws Throwable {

        LocalDateTime start=LocalDateTime.now();
        logger.info("Inside method calling :>>>>>"+pj.getSignature().getName()+"<<<< : "+start);
        Object obj=pj.proceed();
        LocalDateTime end=LocalDateTime.now();
        logger.info("Inside method ended:>>>>>"+pj.getSignature().getName()+"<<<<< : "+end);
        logger.info("Method >>>>>"+pj.getSignature().getName()+" took time "+ (end.getSecond()-start.getSecond()));
        return obj;
    }

    @Around("@annotation(com.mlv.springloggingcustomannotation.customannotation.LogReequest)")
    public Object checkRequest(ProceedingJoinPoint pj) throws Throwable {

        ObjectMapper objectMapper=new ObjectMapper();
        logger.info("In method >>>>"+pj.getSignature().getName()+"<<<< with request as "
                +objectMapper.writeValueAsString(pj.getArgs()));
        return pj.proceed();
    }

    @Around("@annotation(com.mlv.springloggingcustomannotation.customannotation.LogResponse)")
    public Object checkResponse(ProceedingJoinPoint pj) throws Throwable {

        ObjectMapper objectMapper=new ObjectMapper();
        Object obj=pj.proceed();
        logger.info("In method >>>>"+pj.getSignature().getName()+"<<<< with response as "
                +objectMapper.writeValueAsString(obj));
        return obj;
    }


}
