package match;
import java.util.Random;

public class Create {
	protected int max_Number = -1;
	protected int hint_Number = -1;
	protected int option_Number = -1;
	protected int match_Number = -1;
	protected int match_Amount = 0;
	protected Boolean restart_Flag = false;
	protected int []Bits;
	protected int []numbers;//操作数字
	protected int []options;//操作(加或减)
	protected String equality_Str;
	
	//通过当前的信息，生成随机等式。
	public String Equality(){
		int max_Random;
		int sum = 0;
		String equality = "";
		Random my_Random = new Random();
		numbers = new int[hint_Number+1];
		options = new int[hint_Number-1];

		max_Random = (int) Math.pow(10, this.max_Number);
		for( int i = 0 ; i < hint_Number ; i ++ ) {
			numbers[i] = my_Random.nextInt(max_Random-1)+1;//生成1～（10^n-1）的随机数
			equality = equality + numbers[i];
			if( i < hint_Number - 1 ) {//
				options[i] = my_Random.nextInt(2)+4;//生成4～5的随机数
				equality = equality + Option.getName(options[i]);
			}
			if( i > 0 ) {//从第二个开始判断加或减
				if( options[i-1] == Option.PLUS.getIndex() ) {//加
					sum += numbers[i];
				}
				else if( options[i-1] == Option.MINUS.getIndex() ) {//减
					sum -= numbers[i];
				}
			}
			else sum = numbers[i];//第一个直接等于
		}
		numbers[numbers.length-1] = sum; //最后一个数字是sum
		equality = equality + "=" + sum;
		if( sum > 0 && sum < max_Random ) {
			return equality;
		}
		else
			return Equality();
	}
	
	private void CountAmount( String equality ) {
		Bits = new int[equality.length()];
		for( int i = 0 ; i < equality.length() ; i ++ ) {
			char ch = equality.charAt(i);
			switch(ch) {
			case '0':
				match_Amount += 6;
				Bits[i] = 6;
				break;
			case '1':
				match_Amount += 2;
				Bits[i] = 2;
				break;
			case '2':
				match_Amount += 5;
				Bits[i] = 5;
				break;
			case '3':
				match_Amount += 5;
				Bits[i] = 5;
				break;
			case '4':
				match_Amount += 4;
				Bits[i] = 4;
				break;
			case '5':
				match_Amount += 5;
				Bits[i] = 5;
				break;
			case '6':
				match_Amount += 6;
				Bits[i] = 6;
				break;
			case '7':
				match_Amount += 3;
				Bits[i] = 3;
				break;
			case '8':
				match_Amount += 7;
				Bits[i] = 7;
				break;
			case '9':
				match_Amount += 6;
				Bits[i] = 6;
				break;
			case '-':
				match_Amount += 1;
				Bits[i] = 1;
				break;
			case '+':
				match_Amount += 2;
				Bits[i] = 2;
				break;
			default:
				match_Amount += 0;
				Bits[i] = 0;
				break;
			}
		}
	}
	
	private String Add( String equality ){
		Random my_Random = new Random();
		int old_Amount = match_Amount;
		StringBuilder temp = new StringBuilder(equality);
		int count = 0;
		
		//该函数通过加减某一个位的数字的火柴棒数
		//直到原等式满足题意成为不等式题目
		//若两次遍历还没有形成题目则会强制退出
		//并提醒create创建一个新的等式
		
		while( match_Amount != old_Amount + match_Number ) {
			count++;
			if( count == 2 ) {
				restart_Flag = true;
				break;
			}
			for( int i = 0 ; i < equality.length() ; i ++ ) {
				if( my_Random.nextBoolean() == true ) {
				char ch = temp.charAt(i);
				switch(ch) {
				case '0':
					if( match_Amount + 1 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '8');
						match_Amount += 1;
						Bits[i] += 1;
					}
					break;
				case '1':
					if( match_Amount + 1 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '7');
						match_Amount += 1;
						Bits[i] += 1;
					}
					if( match_Amount + 2 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '4');
						match_Amount += 2;
						Bits[i] += 2;
					}
					break;
				case '2':
					if( match_Amount + 2 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '8');
						match_Amount += 2;
						Bits[i] += 2;
					}
					break;
				case '3':
					if( match_Amount + 1 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '9');
						match_Amount += 1;
						Bits[i] += 1;
					}
					break;
				case '4':
					if( match_Amount + 2 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '9');
						match_Amount += 2;
						Bits[i] += 2;
				}
					break;
				case '5':
					if( match_Amount + 1 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '6');
						match_Amount += 1;
						Bits[i] += 1;
					}
					if( match_Amount + 2 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '8');
						match_Amount += 2;
						Bits[i] += 2;
					}
					break;
				case '6':
					if( match_Amount + 2 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '8');
						match_Amount += 2;
						Bits[i] += 2;
					}
					break;
				case '7':
					if( match_Amount + 3 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '0');
						match_Amount += 3;
						Bits[i] += 3;
					}
					if( match_Amount + 2 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '3');
						match_Amount += 2;
						Bits[i] += 2;
					}
					break;
				case '9':
					if( match_Amount + 1 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '8');
						match_Amount += 1;
						Bits[i] += 1;
					}
					break;
				case '-':
					if( match_Amount + 1 <= old_Amount + match_Number ) {
						temp.setCharAt(i, '+');
						match_Amount += 1;
						Bits[i] += 1;
					}
					break;
				default:
					break;
				}
				}		
		}
		}
		String Inequality = temp.toString();
		return Inequality;	
	}

	public String Inequality ( ){
		String equality = Equality();
		CountAmount( equality );
		String inequality;
		//System.out.println(equality);
		
		//有些等式可能无法满足题目要求凑成不等式，所以需要restart一个新的等式
		//这里采用一次递归来得到这些不能凑成不等式的等式的新的等式
		
		if( Option.getName(option_Number) == "添加") {
			inequality = Minus(equality);
			if( restart_Flag == true ) {
				restart_Flag = false;
				return Inequality();
			}
			else { 
				equality_Str = equality;
				return inequality;
				}
		}
		else if( Option.getName(option_Number) == "移除") {
			inequality = Add(equality);
			if( restart_Flag == true ) {
				restart_Flag = false;
				return Inequality();
			}
			else { 
				equality_Str = equality;
				return inequality;
				}
		}
		else if( Option.getName(option_Number) == "移动") {
			inequality = Add(Minus(equality));
			if( restart_Flag == true || inequality.equals(equality) ) {
				restart_Flag = false;
				return Inequality();
			}
			else { 
				equality_Str = equality;
				return inequality;
				}
		}
		else return null;
	}

	private String Minus(String equality) {
		Random my_Random = new Random();
		int old_Amount = match_Amount;
		StringBuilder temp = new StringBuilder(equality);
		int count = 0;
		while( match_Amount != old_Amount - match_Number ) {
			count ++;
			if( count == 2 ) {
				restart_Flag = true;
				break;
			}
			for( int i = equality.length()-1 ; i > 0 ; i -- ) {
				char ch = temp.charAt(i);
				if( my_Random.nextBoolean() == true ) {
					switch(ch) {
					case '0':
						if( match_Amount - 3 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '7');
							match_Amount -= 3;
							Bits[i] -= 3;
						}
						break;
					case '3':
						if( match_Amount - 3 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '1');
							match_Amount -= 3;
							Bits[i] -= 3;
						}
						if( match_Amount - 2 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '7');
							match_Amount -= 2;
							Bits[i] -= 2;
						}
						break;
					case '4':
						if( match_Amount - 2 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '1');
							match_Amount -= 2;
							Bits[i] -= 2;
						}
						break;
					case '6':
						if( match_Amount - 1 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '5');
							match_Amount -= 1;
							Bits[i] -= 1;
						}
						break;
					case '7':
						if( match_Amount - 1 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '1');
							match_Amount -= 1;
							Bits[i] -= 1;
						}
						break;
					case '8':
						if( match_Amount - 2 >= old_Amount - match_Number ) {
							switch(my_Random.nextInt(2)) {
								case 0:
									temp.setCharAt(i, '5');
									break;
								case 1:
									temp.setCharAt(i, '2');
									break;
							}
							match_Amount -= 1;
							Bits[i] -= 1;
						}
						if( match_Amount - 1 >= old_Amount - match_Number ) {	
							switch(my_Random.nextInt(3)) {
								case 0:
									temp.setCharAt(i, '0');
									break;
								case 1:
									temp.setCharAt(i, '6');
									break;	
								case 2:
									temp.setCharAt(i, '9');
									break;
							}
							match_Amount -= 1;
							Bits[i] -= 1;
						}
						break;
					case '9':
						if( match_Amount - 2 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '4');
							match_Amount -= 2;
							Bits[i] -= 2;
						}
						if( match_Amount - 1 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '3');
							match_Amount -= 1;
							Bits[i] -= 1;
						}
						break;
					case '+':
						if( match_Amount - 1 >= old_Amount - match_Number ) {
							temp.setCharAt(i, '-');
							match_Amount -= 1;
							Bits[i] -= 1;
						}
						break;
					default:
						break;
					}
				}
			}
		}
		String Inequality = temp.toString();
		return Inequality;	
	}
}
