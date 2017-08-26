package com.gobravery.wind.common;

import org.apache.log4j.Logger;

public class Log4jUtils {
	private static Logger logger = Logger.getLogger(Log4jUtils.class); 
	/** 
     * @param args 
     */  
    public static void d(String args) {  
          
        // 记录debug级别的信息  
        logger.debug(args);  
    }
    public static void i(String args) {  
          
    	// 记录info级别的信息  
        logger.info(args);  
    }
    public static void e(String args) {  
          
    	// 记录error级别的信息  
        logger.error(args);  
    }
}
