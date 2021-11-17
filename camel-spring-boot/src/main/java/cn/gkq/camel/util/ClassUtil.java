package cn.gkq.camel.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.Annotation;
import javassist.bytecode.annotation.StringMemberValue;
import lombok.Data;

import javax.jws.WebParam;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Optional;

/**
 * <p>字节码工具类</p>
 *
 * @author GKQ
 * @date 2021/8/11
 */
@Data
public class ClassUtil {

    public static final String METHOD_NAME = "methodName";
    public static final String PARAMETER_CONFIG = "parameterConfig";
    public static final String PARAMETER_TYPE = "parameterType";
    public static final String PARAMETER_NAME = "parameterName";
    public static final String PREFIX_CLASSNAME = "com.zdww.amc.hsb.bytecode.Ws$";


    /**
     * 生成WS接口
     *
     * @param classpath      字节码path
     * @param classname      类全限定名
     * @param signatureArray 方法签名配置
     * @return 字节码
     */
    public static void createWSInterface(String classpath, String classname, JSONArray signatureArray) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        CtClass ctClass = pool.makeInterface(classname);
        ClassFile ccFile = ctClass.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
        for (int i = 0; i < signatureArray.size(); i++) {
            ParameterAnnotationsAttribute paramAttribute = new ParameterAnnotationsAttribute(
                    constPool, ParameterAnnotationsAttribute.visibleTag);
            StringBuilder methodSb = new StringBuilder();
            JSONObject signatureItem = (JSONObject) signatureArray.get(i);
            String methodName = (String) signatureItem.get(METHOD_NAME);
            methodSb.append("String ").append(methodName).append("(");
            JSONArray parameterConfig = (JSONArray) Optional.ofNullable(signatureItem.get(PARAMETER_CONFIG)).get();
            Annotation[][] paramArrays = new Annotation[parameterConfig.size()][1];
            for (int j = 0; j < parameterConfig.size(); j++) {
                JSONObject parameterItem = (JSONObject) parameterConfig.get(j);
                String parameterName = (String) parameterItem.get(PARAMETER_NAME);
                String parameterType = (String) parameterItem.get(PARAMETER_TYPE);
                methodSb.append(parameterType).append(" ").append(parameterName).append(",");
                //添加参数注解
                Annotation paramAnnotation = new Annotation("javax.jws.WebParam", constPool);
                paramAnnotation.addMemberValue("name", new StringMemberValue(parameterName, constPool));
                paramArrays[j][0] = paramAnnotation;
            }
            //设置参数注解
            paramAttribute.setAnnotations(paramArrays);
            String parameterDesc = methodSb.substring(0, methodSb.lastIndexOf(",")) + ");";
            CtMethod method = CtNewMethod.make(parameterDesc, ctClass);
            //添加方法注解
            AnnotationsAttribute methodAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation methodAnnotations = new Annotation("javax.jws.WebMethod", constPool);
            methodAnnotations.addMemberValue("operationName", new StringMemberValue(methodName, constPool));
            methodAttribute.addAnnotation(methodAnnotations);
            method.getMethodInfo().addAttribute(methodAttribute);
            method.getMethodInfo().addAttribute(paramAttribute);
            ctClass.addMethod(method);
        }
        //添加类注解
        AnnotationsAttribute classAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation classAnnotation = new Annotation("javax.jws.WebService", constPool);
        classAttribute.addAnnotation(classAnnotation);
        ccFile.addAttribute(classAttribute);
        //将对象编译为class文件
        ctClass.writeFile(classpath);
    }

    /**
     * 修改WS接口
     *
     * @param classpath      字节码path
     * @param classname      类全限定名
     * @param signatureArray 方法签名配置
     * @return 字节码
     */
    public static void updateWSInterface(String classpath, String classname, JSONArray signatureArray) throws Exception {
        ClassPool pool = ClassPool.getDefault();
        pool.appendClassPath(classpath);
        CtClass ctClass = pool.get(classname);
        boolean frozen = ctClass.isFrozen();
        if(frozen) {
            ctClass.defrost();
        }
        CtMethod[] declaredMethods = ctClass.getDeclaredMethods();
        for (CtMethod declaredMethod : declaredMethods) {
            ctClass.removeMethod(declaredMethod);
        }
        ClassFile ccFile = ctClass.getClassFile();
        ConstPool constPool = ccFile.getConstPool();
        for (int i = 0; i < signatureArray.size(); i++) {
            ParameterAnnotationsAttribute paramAttribute = new ParameterAnnotationsAttribute(
                    constPool, ParameterAnnotationsAttribute.visibleTag);
            StringBuilder methodSb = new StringBuilder();
            JSONObject signatureItem = (JSONObject) signatureArray.get(i);
            String methodName = (String) signatureItem.get(METHOD_NAME);
            methodSb.append("String ").append(methodName).append("(");
            JSONArray parameterConfig = (JSONArray) Optional.ofNullable(signatureItem.get(PARAMETER_CONFIG)).get();
            Annotation[][] paramArrays = new Annotation[parameterConfig.size()][1];
            for (int j = 0; j < parameterConfig.size(); j++) {
                JSONObject parameterItem = (JSONObject) parameterConfig.get(j);
                String parameterName = (String) parameterItem.get(PARAMETER_NAME);
                String parameterType = (String) parameterItem.get(PARAMETER_TYPE);
                methodSb.append(parameterType).append(" ").append(parameterName).append(",");
                //添加参数注解
                Annotation paramAnnotation = new Annotation("javax.jws.WebParam", constPool);
                paramAnnotation.addMemberValue("name", new StringMemberValue(parameterName, constPool));
                paramArrays[j][0] = paramAnnotation;
            }
            //设置参数注解
            paramAttribute.setAnnotations(paramArrays);
            String parameterDesc = methodSb.substring(0, methodSb.lastIndexOf(",")) + ");";
            CtMethod method = CtNewMethod.make(parameterDesc, ctClass);
            //添加方法注解
            AnnotationsAttribute methodAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
            Annotation methodAnnotations = new Annotation("javax.jws.WebMethod", constPool);
            methodAnnotations.addMemberValue("operationName", new StringMemberValue(methodName, constPool));
            methodAttribute.addAnnotation(methodAnnotations);
            method.getMethodInfo().addAttribute(methodAttribute);
            method.getMethodInfo().addAttribute(paramAttribute);
            ctClass.addMethod(method);
        }
        //添加类注解
        AnnotationsAttribute classAttribute = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation classAnnotation = new Annotation("javax.jws.WebService", constPool);
        classAttribute.addAnnotation(classAnnotation);
        ccFile.addAttribute(classAttribute);
        //将对象编译为class文件
        ctClass.writeFile(classpath);
    }

    public static void main(String[] args) throws Exception {
        String param = "[\n" +
                "              {\n" +
                "                \"methodName\": \"sendMessageA\",\n" +
                "                \"parameterConfig\": [\n" +
                "                  {\n" +
                "                    \"parameterType\": \"String\",\n" +
                "                    \"parameterName\": \"name\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"parameterType\": \"Integer\",\n" +
                "                    \"parameterName\": \"age\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              },\n" +
                "              {\n" +
                "                \"methodName\": \"sendMessageB\",\n" +
                "                \"parameterDesc\": \"(String name, Integer age)\",\n" +
                "                \"parameterConfig\": [\n" +
                "                  {\n" +
                "                    \"parameterType\": \"String\",\n" +
                "                    \"parameterName\": \"name\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"parameterType\": \"Integer\",\n" +
                "                    \"parameterName\": \"age\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "            ]\n";
        JSONArray objects = JSONObject.parseArray(param);
        createWSInterface("D:\\bytecode", "com.zdww.amc.hsb.bytecode.Ws$Test661",  objects);
        Class aClass = loadClass("D:\\bytecode", "com.zdww.amc.hsb.bytecode.Ws$Test661");
        Method[] declaredMethods = aClass.getDeclaredMethods();
        System.out.println(declaredMethods);
        param = "[\n" +
                "              {\n" +
                "                \"methodName\": \"sendMessageA\",\n" +
                "                \"parameterConfig\": [\n" +
                "                  {\n" +
                "                    \"parameterType\": \"String\",\n" +
                "                    \"parameterName\": \"name\"\n" +
                "                  },\n" +
                "                  {\n" +
                "                    \"parameterType\": \"Integer\",\n" +
                "                    \"parameterName\": \"age\"\n" +
                "                  }\n" +
                "                ]\n" +
                "              }\n" +
                "            ]";
        objects = JSONObject.parseArray(param);
        updateWSInterface("D:\\bytecode", "com.zdww.amc.hsb.bytecode.Ws$Test661",  objects);
        Class aClass1 = loadClass("D:\\bytecode", "com.zdww.amc.hsb.bytecode.Ws$Test661");
        Method[] declaredMethods1 = aClass.getDeclaredMethods();
        System.out.println(declaredMethods);
    }


    /**
     * 加载class
     *
     * @param classpath 字节码path
     * @param classname 类全限定名
     * @return 字节码
     */
    public static Class loadClass(String classpath, String classname) throws Exception {
        URL url = new File(classpath).toURI().toURL();
        //得到系统类加载器
        URLClassLoader classLoader = (URLClassLoader) ClassLoader.getSystemClassLoader();
        Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
        method.setAccessible(true);
        //将path设定为classpath，类似 javac -cp
        method.invoke(classLoader, url.toURI().toURL());
        return Class.forName(classname);
    }

    /**
     * 加载class
     *
     * @param id content
     * @return classname
     */
    public static String acquireClassname(String id) {
        return PREFIX_CLASSNAME + id;
    }
}
