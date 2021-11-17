package cn.gkq.camel.classloader;

import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author GKQ
 * @Classname MyClassLoader
 * @Description TODO
 * @Date 2021/3/5
 */
@Component
public class MyClassLoader1 extends ClassLoader {

   private String myClassPath = "D:\\bytecode";

    public MyClassLoader1() {

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        String fileName = getFileName(name);
        File file = new File(myClassPath, fileName);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int len;
            while ((len = fis.read()) != -1) {
                baos.write(len);
            }

            byte[] bytes = baos.toByteArray();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public String getFileName(String name) {
        Assert.notNull(name, "Full name not null!");
        String replace = name.replace(".", "/");
        return replace+".class";
    }

}
