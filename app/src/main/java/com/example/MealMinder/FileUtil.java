package com.example.MealMinder;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {
    private static void createNewFile(String path){
        int lastSep = path.lastIndexOf(File.separator);
        if (lastSep>0){
            String dirPath = path.substring(0,lastSep);
            makeDir(dirPath);
        }

        File file = new File(path);

        try{
            if(!file.exists()) file.createNewFile();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String readFile(String path){
        createNewFile(path);

        StringBuilder sb = new StringBuilder();
        FileReader fr = null;
        try{
            fr = new FileReader(new File(path));
            char[] buff = new char[1024];
            int length = 0;
            while((length = fr.read(buff))>0){
                sb.append(new String(buff,0,length));
            }
        } catch (IOException e ){
            e.printStackTrace();
        }finally {
            if(fr!=null){
                try{
                    fr.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
    }

    public static void writeFile(String path,String str){
        createNewFile(path);
        FileWriter fileWriter = null;

        try{
            fileWriter = new FileWriter(new File(path),false);
            fileWriter.write(str);
            fileWriter.flush();
        }catch (IOException e){
            e.printStackTrace();
        } finally {
            try {
                if(fileWriter!=null) fileWriter.close();

            }catch (IOException e ){
                e.printStackTrace();
            }
        }
    }

    private static void makeDir(String path){
        if(!isExistFile(path)){
            File file = new File(path);
            file.mkdirs();
        }
    }

    public static Boolean isExistFile(String path){
        File file = new File(path);
        return file.exists();
    }
}