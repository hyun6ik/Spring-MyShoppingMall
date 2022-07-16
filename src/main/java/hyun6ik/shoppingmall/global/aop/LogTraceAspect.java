package hyun6ik.shoppingmall.global.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LogTraceAspect {

    @Around("@annotation(hyun6ik.shoppingmall.global.annotation.LogTrace) ")
    public Object doTracLog(ProceedingJoinPoint joinPoint) throws Throwable {
        final long startTime = System.currentTimeMillis();
        final Object result = joinPoint.proceed();
        final long endTime = System.currentTimeMillis();
        log.info("totalTime = {}ms", endTime - startTime);
        return result;
    }
}
