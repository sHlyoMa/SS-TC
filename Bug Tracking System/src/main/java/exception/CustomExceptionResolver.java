package exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class CustomExceptionResolver extends SimpleMappingExceptionResolver {
	private static final Logger LOGGER = Logger.getLogger(CustomExceptionResolver.class);	
	@Override
	protected ModelAndView getModelAndView(String exception, Exception ex,
			HttpServletRequest request) {	
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = formatter.format(now);
		LOGGER.error(date);
		LOGGER.error("Error!", ex);
		LOGGER.error(">");
		return getCustomModelAndView(exception, ex, request);
	}
	
	private ModelAndView getCustomModelAndView(String viewName, Exception ex,
			HttpServletRequest request){			
		ModelAndView mv = super.getModelAndView(viewName, ex);		
		mv.addObject("exceptionMessage" + ex.hashCode()) ;
		StackTraceElement[] stack =ex.getStackTrace(); 
		if(ex.getMessage() != null && ex.getMessage().contains("Access is denied")){
			System.out.println(ex.getMessage());			
			request.setAttribute("message", ex.getMessage());
			return mv;
		} else{
		     
		request.setAttribute("stackTrace", stack);		
		request.setAttribute("message", ex.toString());
		return mv;}
	}
}