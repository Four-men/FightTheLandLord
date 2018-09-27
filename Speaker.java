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
	private final String[][] COLOR_NAME = new String[][] {{"方块", "Diamond"}, {"梅花", "Club"}, {"红桃", "Heart"}, {"黑桃", "Spade"}};
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

	public void print(Card input){
		System.out.printf("%s %d", COLOR_NAME[input.color][language], input.value);
	}
	public void println(Card input){
		System.out.printf("%s %d\n", COLOR_NAME[input.color][language], input.value);
	}
	
	public void print(Cards input){
		System.out.print(CARDS_NAME[new Judge().getId(input)][language]);
	}
	public void println(Cards input){
		System.out.println(CARDS_NAME[new Judge().getId(input)][language]);
	}
}
