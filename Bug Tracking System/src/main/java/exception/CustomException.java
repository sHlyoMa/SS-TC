package exception;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class CustomException extends Exception {
	
	private static final long serialVersionUID = 3026393903863754932L;
	private static final Logger LOGGER = Logger.getLogger(CustomException.class);	
	public CustomException (Exception ex){
		super (ex);
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String date = formatter.format(now);
		LOGGER.error(date);
		LOGGER.error("Error!", ex);
		LOGGER.error(">");
	}

}
