package com.cggw.research.test;


import org.apache.log4j.Logger;

/**
 * Created by cgw on 2018/7/27.
 */
public class TestLog {
    static Logger logger = Logger.getLogger(TestLog.class);

    public static void main(String[] args){
        logger.info("1***");
        logger.warn("2****");
    }

}
