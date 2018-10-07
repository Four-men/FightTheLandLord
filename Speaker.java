package HuangSiyuan;

import HuangSiyuan.*;

public class Speaker{
	Speaker(){
		language = 0;
	}
	Speaker(int initial_language){
		language = initial_language;
	}

	private final int language;
	private final String[][] CARD_COLOR_NAME = new String[][] {
		{"", ""}, 
		{"方块", "Diamond"}, 
		{"梅花", "Club"}, 
		{"红桃", "Heart"}, 
		{"黑桃", "Spade"}
	};
	private final String[][] CARD_VALUE_NAME = new String[][] {
		{"未知", "Unknown"},
		{"A", "A"},
		{"2", "2"},
		{"3", "3"},
		{"4", "4"},
		{"5", "5"},
		{"6", "6"},
		{"7", "7"},
		{"8", "8"},
		{"9", "9"},
		{"10", "10"},
		{"J", "J"},
		{"Q", "Q"},
		{"K", "K"},
		{"小王", "Queen"},
		{"大王", "King"}
	};
	private final String[][] CARDS_NAME = new String[][]{
		{"不出"},							// 	0
		{"单张"},								
		{"对子"},                              
		{"王炸"},
		{"三带"},
		{"炸弹"},							//	5
		{"三带一"},                      
		{"三带二"},
		{"五顺子"},
		{"四带一对"},						//	10
		{"飞机"},
		{"四带二"},						
		{"三连对"},
		{"六顺子"},
		{"七顺子"},
		{"四带两对"},						//	15
		{"飞机带一对"},					
		{"飞机带两张"},
		{"四连对"},
		{"八顺子"},
		{"九顺子"},						//	20
		{"飞机带两对"},
		{"五连对"},
		{"十顺子"},
		{"十一顺子"},
		{"六连对"},						//	25
		{"十二顺子"},	
		{"十三顺子"}
	}; 

	/* ********************* */
	public void println(){
		System.out.println();
	}

	public void print(Card input){
		System.out.printf("%3s%3s", CARD_COLOR_NAME[input.color][language], CARD_VALUE_NAME[input.value][language]);
	}
	public void println(Card input){
		System.out.printf("%3s%3s\n", CARD_COLOR_NAME[input.color][language], CARD_VALUE_NAME[input.value][language]);
	}
	
	public void print(Cards input){
		System.out.print(CARDS_NAME[new Judge().getId(input)][language]);
	}
	public void println(Cards input){
		System.out.println(CARDS_NAME[new Judge().getId(input)][language]);
	}
	
	public void println(Card[] input){
		for(int i = 0; i < input.length; i++)
			System.out.printf("%2d:%3s%3s\n", i, CARD_COLOR_NAME[input[i].color][language], CARD_VALUE_NAME[input[i].value][language]);
	}

	public void println(Player input){
		println(input.getSuit().toArrayOfCard());
	}
	/* ********************* */

	public void gameTable(){
		String[] prompt = new String[] {
			"请输入整数进入游戏",
			"Play game by entering a integer"
		};
		String[][] str = new String[][]
		{
			{"斗地主", "Fight the landlord"}
		};
		for(int i = 0; i < str.length; i++)
			System.out.println(i + " : " + str[i][language]);
		System.out.print(prompt[language] + ": ");
	}

	public void goodbye(){
		String[] str = new String[] {
			"再见，祝您游戏愉快",
			"Goodbye and welcome bye"
		};
		System.out.println(str[language]);
	}

	/* ********************* */
	//	in-game prompt
	public void gameStart(){
		String[] str = new String[] {
			"游戏开始",
			"Game start"
		};
		System.out.println(str[language]);
	}

	public void star(boolean bool){
		if(bool == true)
			System.out.print("*");
	}

	public void playerId(int index){
		String[] str = new String[] {
			"玩家 " + index, 
			"Player " + index
		};
		System.out.println(str[language]);
	}
	public void playerId(int index, boolean landlord){
		star(landlord);
		playerId(index);
	}

	public void playerScore(Player input){
		String[] str = new String[] {
			"得分: " + input.score,
			"Score: " + input.score
		};
		System.out.println(str[language]);
	}

	public void chooseLandlord(){
		String[] str = new String[] {
			"输入非0成为地主",
			"Enter non-zero integer to be the landlord"
		};
		System.out.println(str[language]);
	}

	public void isYourTurn(){
		String[] str = new String[] {
			"现在是您的回合了，请出牌",
			"Your turn now"
		};
		System.out.println(str[language]);
	}

	public void congratulation(){
		String[] str = new String[] {
			"恭喜你，获得了胜利",
			"You win, congratulation"
		};
		System.out.println(str[language]);
	}

	public void errorPlay(){
		String[] str = new String[] {
			"您出的牌不符合规则，请重新出牌",
			"The poker-cards that you played are not vaild, play carefully please"
		};
		System.out.println(str[language]);
	}

	public void quitPrompt(){
		String[] str = new String[] {
			"输入quit退出游戏",
			"Enter \"quit\" to quit"
		};
		System.out.println(str[language]);
	}
	/* ********************* */
}
