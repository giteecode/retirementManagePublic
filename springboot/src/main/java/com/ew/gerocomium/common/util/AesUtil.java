package com.ew.gerocomium.common.util;

import com.ew.gerocomium.common.constant.Constant;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Objects;

/**
 * 自定义密码组件
 */
@Component
public class AesUtil {
    /**
     * 加密
     *
     * @param encodeStr
     * @return
     */
    public static String aesEncode(String encodeStr) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Constant.IV.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(Constant.AES_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(Constant.AES_TYPE);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] bytes = cipher.doFinal(encodeStr.getBytes());
            return new String(Hex.encode(bytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param decodeStr
     * @return
     */
    public static String aesDecode(String decodeStr) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(Constant.IV.getBytes());
            SecretKeySpec secretKeySpec = new SecretKeySpec(Constant.AES_KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance(Constant.AES_TYPE);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] bytes = cipher.doFinal(Hex.decode(decodeStr));
            return new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 校验
     *
     * @param needCheckPassword
     * @param encodePassword
     * @return
     */
    public static Boolean aesMatch(String needCheckPassword, String encodePassword) {
        return Objects.equals(aesEncode(needCheckPassword), encodePassword);
    }
}
