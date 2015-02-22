package net.fclique.crowler.service;
import org.apache.log4j.Logger;

/**
 * Created by devesh.bharathan
 */
public class LoggingService
{
	private Logger logger = Logger.getLogger(LoggingService.class);
	public void log(String message)
	{
		logger.info(message);
	}
}
