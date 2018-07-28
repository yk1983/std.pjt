package jp.com.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class LoggerAspect {

	protected Logger log = Logger.getLogger(this.getClass());

	static String name = "";
	static String type = "";

	@Around("execution(* jp..controller.*Controller.*(..)) or execution(* jp..service.*Impl.*(..)) or execution(* jp..dao.*DAO.*(..))")
	public Object logPrint(ProceedingJoinPoint joinPoint) throws Throwable {
		/**
		 * joinPoint가 method이면 해당 class명을 가져온다
		 */
		type = joinPoint.getSignature().getDeclaringTypeName();
		if (type.indexOf("Controller") > -1) {
			name = "Controller \t : ";
		} else if (type.indexOf("Service") > -1) {
			name = "Service \t\t : ";
		} else if (type.indexOf("DAO") > -1) {
			name = "DAO \t\t : ";
		}
		log.debug(name + type + "." + joinPoint.getSignature().getName() + "()");
		
		return joinPoint.proceed();
	}

}
