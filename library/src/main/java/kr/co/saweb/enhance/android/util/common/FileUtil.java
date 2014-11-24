package kr.co.saweb.enhance.android.util.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by OKS on 2014-11-20.
 */
public class FileUtil {
    public static Object readObject(File file) throws Exception {//저장한 객체를 가저와서 읽는다.
        if (!file.isFile())
            return null;

        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(fis));
        Object result = ois.readObject();
        fis.close();
        ois.close();

        return result;
    }

    public static void objectWrite(Object o, File file) throws Exception {//파일을 만들고 객체를 파일안에 저장한다.
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(fos));
        oos.writeObject(o);
        oos.close();
        fos.close();
    }

    public static void writeFile(String text, File file) throws Exception {//파일을 쓴다
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(text);
        writer.close();
    }

    public static String readFile(File file) throws Exception {
        if (!file.isFile())
            return null;

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = null;
        StringBuffer sb = new StringBuffer();

        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }

        reader.close();

        return sb.toString();
    }


}
