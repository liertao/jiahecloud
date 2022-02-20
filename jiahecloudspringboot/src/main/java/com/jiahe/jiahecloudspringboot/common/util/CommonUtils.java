package com.jiahe.jiahecloudspringboot.common.util;

import java.security.MessageDigest;

/**
 * 公用工具类
 */
public class CommonUtils {

    /**
     * 加密算法，将输入的pwd字符串经过加密转换输出
     * @param pwd
     * @return
     */
    public static String encrypt(String pwd){
        Long sum = 0L,b = 0L;
        for(int i=1;i<=pwd.length();i++){
            b = Long.valueOf(pwd.charAt(i-1))*i;
            sum += b;
        }
        return String.valueOf(sum);
    }

    /**
     * emis6 加密方式
     * @return
     */
    public static String md5(String pwd)throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        byte[] b = md.digest();
        String retMd5 = "";
        StringBuffer buf = new StringBuffer("");
        for (int j = 0; j < b.length; j++) {
            int i = b[j];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            String enResult = Integer.toHexString(i);
            {
                enResult = enResult.toUpperCase();
                buf.append(enResult);
            }
            retMd5 = buf.toString();
        }
        return retMd5;
    }
}
