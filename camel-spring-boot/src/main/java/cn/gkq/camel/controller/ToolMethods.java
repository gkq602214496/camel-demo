package cn.gkq.camel.controller;


import com.alibaba.druid.util.StringUtils;
import org.springframework.util.Assert;


/**
 * @author WangHeng
 * @date 2016/11/7
 */
public class ToolMethods {

    /**
     * @param str 需要处理的数据 xml 中名字不能以XML开头，包括其大小写，不能以数字开头，不能以标点符号开头，不能包含空格
     * @return 处理后的数据
     */
    public static String replaceXmlName(String str) {
        Assert.isTrue(!StringUtils.isEmpty(str), "替换字符串不能为空！");
        //标记首字母是数字
        if (Character.isDigit(str.charAt(0))) {
            str = "_n_" + str;
            //标记xml开头
        } else if (str.toUpperCase().contains("XML")) {
            str = "_xml_" + str;
            //标记标点开头
        } else if (isSymbol(str.charAt(0))) {
            str = "_p_" + str;
        }
        //替换空格
        if (str.contains(" ")) {
            str = str.replace(" ", "");
        }
        return str;
    }

    /**
     * 中文符号判断
     *
     * @param ch
     * @return
     */
    static boolean isCnSymbol(char ch) {
        if (0x3004 <= ch && ch <= 0x301C) return true;

        if (0x3020 <= ch && ch <= 0x303F) return true;

        return false;

    }

    /**
     * 符号判断
     *
     * @param ch
     * @return
     */
    static boolean isSymbol(char ch) {
        if (ch == 0x5f) return false;

        if (isCnSymbol(ch)) return true;

        if (isEnSymbol(ch)) return true;

        if (0x2010 <= ch && ch <= 0x2017) return true;

        if (0x2020 <= ch && ch <= 0x2027) return true;

        if (0x2B00 <= ch && ch <= 0x2BFF) return true;

        if (0xFF03 <= ch && ch <= 0xFF06) return true;

        if (0xFF08 <= ch && ch <= 0xFF0B) return true;

        if (ch == 0xFF0D || ch == 0xFF0F) return true;

        if (0xFF1C <= ch && ch <= 0xFF1E) return true;

        if (ch == 0xFF20 || ch == 0xFF65) return true;

        if (0xFF3B <= ch && ch <= 0xFF40) return true;

        if (0xFF5B <= ch && ch <= 0xFF60) return true;

        if (ch == 0xFF62 || ch == 0xFF63) return true;

        if (ch == 0x0020 || ch == 0x3000) return true;

        return false;

    }

    /**
     * 英文符号判断
     *
     * @param ch
     * @return
     */
    static boolean isEnSymbol(char ch) {

        if (ch == 0x40) return true;

        if (ch == 0x2D || ch == 0x2F) return true;

        if (0x23 <= ch && ch <= 0x26) return true;

        if (0x28 <= ch && ch <= 0x2B) return true;

        if (0x3C <= ch && ch <= 0x3E) return true;

        if (0x5B <= ch && ch <= 0x60) return true;

        if (0x7B <= ch && ch <= 0x7E) return true;

        return false;

    }
}
