package io.osnz;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelParserConfiguration;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Slf4j
@Aspect
@Component
public class LogAspect {

  @Pointcut("within(io.osnz..*)")
  public void inMyPackage() {}

  @Pointcut("execution(public * *Service(..))")
  public void invokeService() {}

  @Pointcut(value = "@annotation(audit)", argNames = "audit")
  public void requiredAudit(Audit audit) {}

  @Around(value = "invokeService() && requiredAudit(audit)", argNames = "joinPoint,audit")
  public Object invokeSayHello(ProceedingJoinPoint joinPoint, Audit audit) throws Throwable {
    log.info(ExpressionParserHelper.parseTemplate(audit.value(), discoveryArguments(joinPoint)));
    return joinPoint.proceed();
  }

  private Map<String, Object> discoveryArguments(ProceedingJoinPoint joinPoint) {
    final String[] parameters = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
    final Object[] values = joinPoint.getArgs();

    final Map<String, Object> variables = new HashMap<>();
    for (int idx = 0; idx < parameters.length; idx++) {
      variables.put(parameters[idx], values[idx]);
    }

    return variables;
  }


}
