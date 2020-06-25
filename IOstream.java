package match;

import java.util.Scanner;

public class IOstream {
	
	public static Scanner in = new Scanner(System.in);

	public void CreateGame( Create create ) {
		System.out.println("～火柴棒游戏开始～");
		while( create.max_Number == -1) {
			System.out.print("请输入最大数字的位数:");
			create.max_Number = in.nextInt();
			if( create.max_Number <= 4 && create.max_Number >= 1 ) {
				System.out.println("读入成功.");
			}
			else {
				System.out.println("读入失败,位数过大或过小.");
				create.match_Number = -1;
			}
		}
		
		while( create.hint_Number == -1 ) {
			System.out.print("请输入提示数:");
			create.hint_Number = in.nextInt();
			if( create.hint_Number >=2 && create.hint_Number <=3 ) {
				System.out.println("读入成功.");
			}
			else {
				System.out.println("读入失败,提示数过大或过小.");
				create.hint_Number = -1;
			}
		}
			
		while( create.option_Number == -1 ) {
			System.out.print("请输入题目类型(1:移除;2:移动;3:添加):");
			create.option_Number = in.nextInt();
			if( create.option_Number >= 1 && create.option_Number <= 3 ) {
				System.out.println("读入成功.");			
			}
			else {
				System.out.println("读入失败,操作数不合法.");
				create.option_Number = -1;
			}
		}
		while( create.match_Number == -1 ) {
			System.out.print("请输入操作火柴棒数目:");
			create.match_Number = in.nextInt();
			if( create.match_Number >=1 && create.match_Number <= 5 ) {
				System.out.println("数据读入完毕!正在生成游戏...");
			}
			else {
				System.out.println("读入失败,数目过大或过小.");
				create.match_Number = -1;
			}
		}
		
		System.out.println("游戏如下:");

		//输出游戏内容
		System.out.println(create.Inequality());
		System.out.println("您需要"+Option.getName(create.option_Number)+create.match_Number+"根火柴棒.");
	}
	
	public void Answer( Create create ) {
		System.out.println("正确答案如下:");
		System.out.println(create.equality_Str);
	}
	
	public Boolean JudgeGame( Judge judge ) throws Exception{
		System.out.print("请输入您的答案:");
		return Return.ReturnorNot( judge );
	}
	
	public void Success() {
		System.out.println("恭喜您!答案正确!你可以继续新的游戏!");
	}
	
	public void Failed() {
		System.out.println("好可惜,差一点点就正确了,再想想?");
	}
	
}
