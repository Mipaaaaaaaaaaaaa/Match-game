package match;

public class API {

	protected IOstream iostream = new IOstream();
	protected Judge judge = new Judge();
	protected Create create = new Create();
	
	public void Progress() throws Exception {
		Boolean restart_Flag = false;
		iostream.CreateGame( create );
		restart_Flag = iostream.JudgeGame( judge );
		if( restart_Flag == true ) {
		judge.JudgeGame( create );
		}
		//两种情况:既没有查看正确答案，又没有输入正确答案，进入循环
		while( restart_Flag == true && judge.flag == false ) {
			//System.out.println( judge.user_Answer );
			iostream.Failed();
			restart_Flag = iostream.JudgeGame( judge );
			judge.JudgeGame( create );
			}
		if( restart_Flag == false ) {//查看正确答案退出的循环
			iostream.Answer( create );
		}
		else if( judge.flag == true ) {
			iostream.Success();
		}
	}
	
}
