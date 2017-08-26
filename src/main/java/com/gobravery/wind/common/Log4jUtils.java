package com.gobravery.wind.common;

import org.apache.log4j.Logger;

public class Log4jUtils {
	private static Logger logger = Logger.getLogger(Log4jUtils.class); 
	/** 
     * @param args 
     */  
    public static void d(String args) {  
          
        // ��¼debug�������Ϣ  
        logger.debug(args);  
    }
    public static void i(String args) {  
          
    	// ��¼info�������Ϣ  
        logger.info(args);  
    }
    public static void e(String args) {  
          
    	// ��¼error�������Ϣ  
        logger.error(args);  
    }
}
