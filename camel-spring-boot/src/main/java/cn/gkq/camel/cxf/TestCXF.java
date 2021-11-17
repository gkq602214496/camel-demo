package cn.gkq.camel.cxf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * @author GKQ
 * @Classname TestCXF
 * @Description TODO
 * @Date 2021/3/26
 */
public class TestCXF {

    public static void main(String[] args) throws IOException {
        String ccc = getCCC();
        System.out.println(ccc);
    }

    public static String getCCC() throws IOException {
        String path = "http://172.16.110.174:8099/weather?wsdl";
        int successCode = 200;
        URL url = new URL(path);
        HttpURLConnection urlConnection =(HttpURLConnection) url.openConnection();
        System.out.println(urlConnection);
        urlConnection.setConnectTimeout(5000);
        urlConnection.setRequestMethod("GET");
        int responseCode = urlConnection.getResponseCode();
        String content="";
        if(successCode == responseCode) {
            InputStream inputStream = urlConnection.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuilder sb = new StringBuilder();
            String result;
            while ((result = bufferedReader.readLine()) != null) {
                sb.append(result).append("\r\n");
            }
            content = sb.toString();
            System.out.println(content);
        }
        return content;
    }

}
