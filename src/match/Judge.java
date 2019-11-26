package match;

public class Judge {
	protected Boolean flag;
	protected int []numbers;
	protected int []options;
	protected int match_Amount;
	protected String user_Answer;
	
	public void JudgeGame( Create create ) {
		match_Amount = 0;
		numbers = new int[create.numbers.length];
		options = new int[create.options.length];
		flag = false;
		JudgeEquality(create);
		flag = false;
		JudgeAmount(create);
	}
	
	private void JudgeEquality( Create create ) {
		String temp = user_Answer;
		//System.out.println(temp);
		String substr;
		int index = 0;
		int sum = 0;
		for( int j = 0 ; j < numbers.length ; j ++ ) {
			for( int i = 0 ; i < temp.length() ; i ++ ) {
				char ch = temp.charAt(i);
				if( j == numbers.length - 1 ) {
					index = temp.length();
					break;
				}
				if( ch != '+' && ch != '-' && ch != '=' ) {
					continue;
				}
				else {
					index = i;
					break;
				}
			}

			if( index < temp.length() ) {
			substr = temp.substring(0, index);
			//System.out.println(substr);
			numbers[j] = Integer.parseInt(substr);
				
			if( j < numbers.length - 2 ) { //符号比数字少两个
				String name = temp.substring(index,index+1);
				//System.out.println(name);
				options[j] = Option.getIndex(name);
				//System.out.println(options[j]);
				}
			
			temp = temp.substring(index+1);
			}
			else {
				substr = temp;
				//System.out.println(substr);
				numbers[j] = Integer.parseInt(substr);
				temp = null;
			}
		}
		
		for( int i = 0 ; i < create.hint_Number ; i ++ ) {
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
		//System.out.println(sum);
		if( sum == numbers[numbers.length-1] )//等式成立
			flag = true;
	}


	public void JudgeAmount( Create create ) {
		for( int i = 0 ; i < user_Answer.length() ; i ++ ) {
			char ch = user_Answer.charAt(i);
			switch(ch) {
			case '0':
				match_Amount += 6;
				break;
			case '1':
				match_Amount += 2;
				break;
			case '2':
				match_Amount += 5;
				break;
			case '3':
				match_Amount += 5;
				break;
			case '4':
				match_Amount += 4;
				break;
			case '5':
				match_Amount += 5;
				break;
			case '6':
				match_Amount += 6;
				break;
			case '7':
				match_Amount += 3;
				break;
			case '8':
				match_Amount += 7;
				break;
			case '9':
				match_Amount += 6;
				break;
			case '-':
				match_Amount += 1;
				break;
			case '+':
				match_Amount += 2;
				break;
			}
		}
		switch( Option.getName(create.option_Number) ) {
		case "移除":
			if( this.match_Amount == create.match_Amount - create.match_Number )
				flag = true;
			break;
		case "移动":
			if( this.match_Amount == create.match_Amount )
				flag = true;
			break;
		case "添加":
			if( this.match_Amount == create.match_Amount + create.match_Number )
				flag = true;
			break;
	}
	}

}
