/**
 * 
 */
package com.dw.util;

import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;

/**
 *@Description: 生成UUID工具类
 *@Package cn.com.binzang.common.utils
 *@ClassName UUIDUtil
 *@author sqw
 *@since jdk1.7
 *@version 1.0
 *@date 2018年3月13日
 */
public class UUIDUtil {

	/**  
	* @Title: generateShortUuid  
	* @Description:截取UUID后六位
	* @author sqw
	* @param @return 
	* @return String  
	* @throws
	* @date 2018年3月13日
	*/
	public static String generateShortUuid(){
		String uuid=uuid32();
        return uuid.substring(uuid.length()-6);
	}
	
	
	/**  
	* @Title: uuid32  
	* @Description:生成32位UUID
	* @author sqw
	* @param @return 
	* @return String  
	* @throws
	* @date 2018年3月13日
	*/
	public static String uuid32() {
		String uuid = UUID.randomUUID().toString(); //获取UUID并转化为String对象  
        return uuid.replace("-", "");
	}
	
	public static void main(String[] args) {
		System.out.println(generateShortUuid());
	}
}
