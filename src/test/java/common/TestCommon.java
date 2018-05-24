package common;

import com.google.common.collect.ImmutableList;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author YSH4807
 * @date 2018/4/8 9:42
 */
public class TestCommon {

    @Test
    public void re(){
        Integer integer = new Integer(100);
        byte b = integer.byteValue();
        System.out.println(b);

        ImmutableList<Integer> list = ImmutableList.of(14,29,46,128,129,130);
        for (Integer o : list) {
            System.out.println(String.format("16进制%s,  二进制%s, 十进制%s", Integer.toHexString(o), Integer.toBinaryString(o), o));

        }
        System.out.println(Integer.parseInt("80", 16) + "------------" + Integer.toBinaryString(128));
        System.out.println(Long.valueOf("C42014F290", 16) );
        System.out.println(Long.parseLong ("c42014f290", 16) );
        System.out.println(Long.parseLong ("c0", 16) );

    }

    @Test
    public void hash3DES(){
        String s = encode3Des("1", "1");
        byte[] hex = hex(s);
        System.out.println(hex.toString());
    }


    /**
     * 转换成十六进制字符串
     * @param key
     * @return
     *
     * lee on 2017-08-09 10:54:19
     */
    public static byte[] hex(String key){
        String f = DigestUtils.md5Hex(key);
        byte[] bkeys = new String(f).getBytes();
        byte[] enk = new byte[24];
        for (int i=0;i<24;i++){
            enk[i] = bkeys[i];
        }
        return enk;
    }

    /**
     * 3DES加密
     * @param key 密钥，24位
     * @param srcStr 将加密的字符串
     * @return
     *
     * lee on 2017-08-09 10:51:44
     */
    public static String  encode3Des(String key,String srcStr){
        byte[] keybyte = hex(key);
        byte[] src = srcStr.getBytes();
        try {
            //生成密钥
            SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
            //加密
            Cipher c1 = Cipher.getInstance("DESede");
            c1.init(Cipher.ENCRYPT_MODE, deskey);

            String pwd = Base64.encodeBase64String(c1.doFinal(src));
//           return c1.doFinal(src);//在单一方面的加密或解密
            return pwd;
        } catch (java.security.NoSuchAlgorithmException e1) {
            // TODO: handle exception
            e1.printStackTrace();
        }catch(javax.crypto.NoSuchPaddingException e2){
            e2.printStackTrace();
        }catch(java.lang.Exception e3){
            e3.printStackTrace();
        }
        return null;
    }

    @Test
    public void tee(){
        System.out.println(10000>>3);
    }
}
