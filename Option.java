package match;

public enum Option {
    DELETE("移除",1),MOVE("移动",2),ADD("添加",3),PLUS("+",4),MINUS("-",5);
	//成员变量
	private String name;
	private int index;
	//构造方法
	private Option( String name, int index ) {
		this.name = name;
		this.index = index;
	}
	//普通方法
	public static String getName( int index ) {
		for( Option op : Option.values() ) {
			if( op.getIndex() == index ) {
				return op.name;
			}
		}
		return null;
	}
	public static int getIndex( String name ) {
		for( Option op : Option.values() ) {
			if( op.getName().equals(name) ) {
				return op.index;
			}
		}
		return -1;
	}
	//getter
	public String getName() {
		return name;
	}
	public int getIndex() {
		return index;
	}
	//setter
	public void setName( String name ) {
		this.name = name;
	}
	public void setIndex( int index ) {
		this.index = index;
	}
}
