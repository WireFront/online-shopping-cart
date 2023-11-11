package org.com.xyz.onlineshoppingcart.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("within(org.com.xyz.onlineshoppingcart.dao..*)" +
            " || within(org.com.xyz.onlineshoppingcart.service..*)" +
            " || within(org.com.xyz.onlineshoppingcart.controller..*)")
    public void logMethodEntry(JoinPoint joinPoint) {
        logger.info("MCI >> {}, {}, {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }

    @After("within(org.com.xyz.onlineshoppingcart.dao..*)" +
            " || within(org.com.xyz.onlineshoppingcart.service..*)" +
            " || within(org.com.xyz.onlineshoppingcart.controller..*)")
    public void logMethodExit(JoinPoint joinPoint) {
        logger.info("MCO >> {}, {}, {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
    }
}
