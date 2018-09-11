package util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreateMD5 {

	public static void main(String[] args) {
		String s = CreateMD5.encryptionByMD5("12345678");
		System.out.println(s);
		//9d983f38f48b50629398b866b9b4c476
		//25d55ad283aa400af464c76d713c07ad
	}

	/**
	 * MD5加密
	 * @param plainText
	 *            明文
	 * @return 32位密文
	 */
	//静态方法，便于作为工具类
	public static String encryptionByMD5(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			//加盐
			md.update(plainText.getBytes());
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}

			re_md5 = buf.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}
}
