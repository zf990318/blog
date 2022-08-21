package com.czf.blog.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private  final Logger logger = LoggerFactory.getLogger(this.getClass());


    @Pointcut("execution(* com.czf.blog.web.*.*(..))")
    public void log(){}

    @Before("log()")
    public void doBefore(JoinPoint joinPoint){

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request =attributes.getRequest();
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url,ip,classMethod,args);


        logger.info("RequestLog: " + requestLog);
    }

    @After("log()")
    public void doAfter(){
        logger.info("-------doAfter----------------");
    }

    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result){
        logger.info("Result:{}" + result);
    }

    private  class  RequestLog{
        private String url;
        private String ip;
        private String ClassMethod;
        private Object[] args;

        public RequestLog(String url, String ip, String ClassMethod, Object[] args){
            this.url = url;
            this.ip = ip;
            this. ClassMethod = ClassMethod;
            this.args = args;
        }

        public String toString(){
            return "RequestLog(" + "url=" + url + '\'' + ", ip=" +'\'' + ", args=" + Arrays.toString(args)+ ")";
        }
    }
}
