package dev.base.datasource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源处理切面
 * 事务管理：
 * 事务管理在开启时，需要确定数据源，也就是说数据源切换要在事务开启之前，
 * 我们可以使用Order来配置执行顺序，在AOP实现类上加Order注解，
 * 就可以使数据源切换提前执行，order值越小，执行顺序越靠前。
 */
@Aspect
@Order(1) //order值越小，执行顺序越靠前。<!-- 设置切换数据源的优先级 -->
@Component
public class DsAspect {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 所有添加了DataSource自定义注解的方法都进入切面
     */
    @Pointcut("@annotation(dev.base.datasource.DataSource)")
    public void dsPointCut() {

    }
    //　这里使用@Around，在调用目标方法前，进行aop拦截，通过解析注解上的值来切换数据源。
    // 在调用方法结束后，清除数据源。
    // 也可以使用@Before和@After来编写，原理一样，这里就不多说了。
    @Around("dsPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        if (method.isAnnotationPresent(DataSource.class)) {
            //获取方法上的注解
            DataSource dataSource = method.getAnnotation(DataSource.class);
            if (dataSource != null) {
                //切换数据源
                DynamicDataSourceContextHolder.setDB(dataSource.value().getName());
            }
        }
        try {
            return point.proceed();
        } finally {
            // 销毁数据源 在执行方法之后
            DynamicDataSourceContextHolder.clearDB();
        }
    }
}