package com.cggw.article.test;

import org.junit.Test;

import java.io.*;

/**
 * Created by cgw on 2018/7/23.
 */
public class utilTest {

    @Test
    public void testShowFile(){
        String fileName = "F:\\a.html";
        File file = new File(fileName);
        try {
            BufferedReader in=new BufferedReader(new InputStreamReader(new FileInputStream(file),"gbk"));
            String s = "";
            while((s = in.readLine())!=null){
                System.out.println(s);
            }
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
