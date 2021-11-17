//package cn.gkq.camel.filter;
//
//import org.apache.camel.component.file.GenericFile;
//import org.apache.camel.component.file.GenericFileFilter;
//import org.springframework.stereotype.Component;
//
///**
// * @author GKQ
// * @Classname TxtFilter
// * @Description Txt
// * @Date 2021/2/26
// */
//@Component
//public class TxtFilter implements GenericFileFilter {
//
//
//    @Override
//    public boolean accept(GenericFile file) {
//        System.out.println(file);
//        String fileName = file.getFileName();
//        String fileExtension = fileName.substring(fileName.lastIndexOf(".") + 1);
//        if ("txt".equals(fileExtension)) {
//            return true;
//        }
//        return false;
//    }
//}
