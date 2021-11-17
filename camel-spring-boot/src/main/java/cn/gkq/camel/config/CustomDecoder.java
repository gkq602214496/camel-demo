package cn.gkq.camel.config;

import feign.Feign;
import feign.Response;
import feign.Util;
import feign.codec.Decoder;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * @author GKQ
 * @Classname CustomDecoder
 * @Description TODO
 * @Date 2021/2/2
 */
public class CustomDecoder extends Decoder.Default {



    @Override
    public Object decode(Response response, Type type) throws IOException {
        Reader reader = response.body().asReader(StandardCharsets.UTF_8);
        String result = IOUtils.toString(reader);
        System.out.println("result:" + result);
        return super.decode(response, type);
    }
}
