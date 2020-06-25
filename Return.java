package match;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Return {
	public static Boolean ReturnorNot( Judge judge ) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		String str = "";
		do {
			str = bf.readLine();
			if (str.length() == 0) { // 如果输入的字符串为空，则说明只输入了一个回车
				System.out.println("输入的是回车!正在为您输出正确答案...");
				return false;
			} 
			else {
				judge.user_Answer = str;
				return true;
			}
		} while (str.length() != 0);
 }
}