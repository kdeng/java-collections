package io.osnz;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;

/**
 * @author Kefeng Deng (deng@51any.com)
 */
@Aspect
@Component
public class AuditAspect {
  private final LogService logService;

  public AuditAspect(LogService logService) {
    this.logService = logService;
  }

  @PostConstruct
  public void postConstruct() {
    logService.info("===> AuditAspect is ready", null);
  }

  @Before("execution(* HelloService.hello(..)) && args(name))")
  public void invokeHello(JoinPoint joinPoint, String name) {
    logService.info("===> invoked hello method with args : {}", name);
    logService.error("=> before execution : getArgs = {}", joinPoint.getArgs());
    logService.warn("=> before execution : getKind = {}", joinPoint.getKind());
    logService.warn("=> before execution : getTarget = {}", joinPoint.getTarget());
    logService.warn("=> before execution : getClass = {}", joinPoint.getClass());
    logService.warn("=> before execution : getStaticPart = {}", joinPoint.getStaticPart());
  }

  @Pointcut("@annotation(audit))")
  public void auditAction(Audit audit) throws Throwable {
  }

  @Around("auditAction(logAudit)")
  public Object invokeAuditMethods(ProceedingJoinPoint joinPoint, Audit logAudit) throws Throwable {
//    logService.printMessage(logAudit.level(), "Around: " + logAudit.message(), discoveryParameterValues(joinPoint));
    logService.printMessage(logAudit.level(), "Around: " + getAuditMessage(joinPoint), discoveryParameterValues(joinPoint));
    return joinPoint.proceed();
  }

  @Before("auditAction(audit)")
  public void beforeInvokeAuditMethods(JoinPoint joinPoint, Audit audit) {
    //logService.printMessage(audit.level(), "Before: " + audit.message(), discoveryParameterValues(joinPoint));
    logService.printMessage(audit.level(), "Before: " + getAuditMessage(joinPoint), discoveryParameterValues(joinPoint));
  }

  @After("auditAction(audit)")
  public void afterInvokeAuditMethods(JoinPoint joinPoint, Audit audit) {
//    logService.printMessage(audit.level(), "After: " + audit.message(), discoveryParameterValues(joinPoint));
    logService.printMessage(audit.level(), "After: " + getAuditMessage(joinPoint), discoveryParameterValues(joinPoint));
  }

  private Object[] discoveryParameterValues(JoinPoint joinPoint) {
    return joinPoint.getArgs();
  }

  private String getAuditMessage(JoinPoint joinPoint) {
    MethodSignature signature = (MethodSignature) joinPoint.getSignature();
    Method method = signature.getMethod();
    Audit audit = AnnotationUtils.findAnnotation(method, Audit.class);
    return audit.message();
  }

}
