package com.springboot.sale.utils;

import java.util.Random;

public class KeyUtils {
    /** 线程锁：synchronized*/
    public static synchronized String setKey(){
        Random random = new Random();
       int number = random.nextInt(900000)+100000;

       return System.currentTimeMillis()+String.valueOf(number);
    }
}
