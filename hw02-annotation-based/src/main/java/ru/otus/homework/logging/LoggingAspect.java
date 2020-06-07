package ru.otus.homework.logging;

import java.util.Arrays;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

  private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

  @Before("execution(* ru.otus.homework.quiz.service.*.*(..))")
  public void logBeforeInvokeFromService(JoinPoint joinPoint) {
    Object[] args = joinPoint.getArgs();
    if (args.length > 0) {
      logger.info("Вызов метода {} с параметрами {}", joinPoint.getSignature().getName(),
          Arrays.toString(args));
    } else {
      logger.info("Вызов метода {}", joinPoint.getSignature().getName());
    }
  }
}
