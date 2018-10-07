package HuangSiyuan;

import HuangSiyuan.*;

public class Judge{
	Judge(){
		type = 0;
	}
	Judge(int initial_type){
		type = initial_type;
	}

	private int type;				//	type of poker game（卡牌游戏的类型）
	private Deck deck;

	private int[][] rule = new int[][] {
		{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27}
	};
	private int[][] bomb = new int[][] {
		{3, 5}
	};
	private String[] type_name = new String[] {
		"Fight the landlord"
	};
	private boolean check(Cards card, int index){
		switch(index){
			case 0:
				return card.length() == 0;
				//	不出
			case 1:
				return card.length() == 1;
				//	单张
			case 2:
				return card.length() == 2 && card.getKind() == 1;
				//	对子
			case 3:
				return card.length() == 2 && card.getKind() == 2 && card.king() == 2;
				//	王炸
			case 4:
				return card.length() == 3 && card.getKind() == 1;
				//	三带
			case 5:
				return card.length() == 4 && card.getKind() == 1;
				//	炸弹
			case 6:
				return card.length() == 4 && card.getKind() == 2 && card.sameEach(new int[]{1, 3});
				//	三带一
			case 7:
				return card.length() == 5 && card.getKind() == 2 && card.sameEach(new int[]{2, 3});
				//	三带二
			case 8:
				return card.length() == 5 && card.getKind() == 5 && card.isSequence() && card.king() == 0;
				//	五顺子
			case 9:
				return card.length() == 6 && card.getKind() == 2 && card.sameEach(new int[]{2, 4});
				//	四带一对
			case 10:
				return card.length() == 6 && card.getKind() == 2 && card.sameEach(new int[]{3, 3}) && card.isPair();
				//	飞机
			case 11:
				return card.length() == 6 && card.getKind() == 3 && card.sameEach(new int[]{1, 1, 4});
				//	四带二
			case 12:
				return card.length() == 6 && card.getKind() == 3 && card.isSequence() && card.sameEach(new int[]{2, 2, 2});
				//	三连对
			case 13:
				return card.length() == 6 && card.getKind() == 6 && card.isSequence() && card.king() == 0;
				//	六顺子
			case 14:
				return card.length() == 7 && card.getKind() == 7 && card.isSequence() && card.king() == 0;
				// 	七顺子
			case 15:
				return card.length() == 8 && card.getKind() == 3 && card.sameEach(new int[]{2, 2, 4});
				//	四带两对
			case 16:
				return card.length() == 8 && card.getKind() == 3 &&	card.sameEach(new int[]{2, 3, 3}) && card.isPair();
				//	飞机带一对
			case 17:
				return card.length() == 8 && card.getKind() == 4 && card.sameEach(new int[]{1, 1, 3, 3}) && card.isPair();
				//	飞机带两张
			case 18:
				return card.length() == 8 && card.getKind() == 4 && card.isSequence() && card.sameEach(new int[]{2, 2, 2, 2});
				//	四连对
			case 19:
				return card.length() == 8 && card.getKind() == 8 && card.isSequence() && card.king() == 0;
				//	八顺子
			case 20:
				return card.length() == 9 && card.getKind() == 9 && card.isSequence() && card.king() == 0; 
				//	九顺子
			case 21:
				return card.length() == 10 && card.getKind() == 4 && card.sameEach(new int[]{2, 2, 3, 3}) && card.isPair();
				//	飞机带两对
			case 22:
				return card.length() == 10 && card.getKind() == 5 && card.isSequence() && card.sameEach(new int[]{2, 2, 2, 2, 2});
				//	五连对
			case 23:
				return card.length() == 10 && card.getKind() == 10 && card.isSequence() && card.king() == 0;
				//	十顺子
			case 24:
				return card.length() == 11 && card.getKind() == 11 && card.isSequence() && card.king() == 0; 
				//	十一顺子
			case 25:
				return card.length() == 12 && card.getKind() == 6 && card.isSequence() && card.sameEach(new int[]{2, 2, 2, 2, 2, 2});
				//	六连对
			case 26:
				return card.length() == 12 && card.getKind() == 12 && card.isSequence() && card.king() == 0;
				//	十二顺子
			case 27:
				return card.length() == 13 && card.getKind() == 13 && card.isSequence() && card.king() == 0;
				//	十三顺子
			default:
				return false;
		}
	}

	public void shuffle(){
		switch(type){
			case 0:
				deck = new Deck(1, true);
				break;
			default:
				break;
		}
	}

	public Cards deal(int total){
		return deck.deal(total);
	}

	public boolean isVaild(Cards input){
		for(int i = 0; i < rule[type].length; i++)
			if(check(input, rule[type][i]))
				return true;
		return false;
	}

	public int getId(Cards input){
		for(int i = 0; i < rule[type].length; i++)
			if(check(input, rule[type][i]))
				return i;
		return -1;
	}

	public boolean isBomb(Cards input){
		int id = getId(input);
		for(int i = 0; i < bomb[type].length; i++)
			if(id == bomb[type][i])
				return true;
		return false;
	}

	/* ************************ */
	//	comparison	zone
	public int compare(Card a, Card b){
		return a.compareTo(b);
	}
	public int compare(Cards a, Cards b){
		return a.compareTo(b);
	}

	public int compareWithColor(Card a, Card b){
		return a.compareToWithColor(b);
	}
	public int compareWithColor(Cards a, Cards b){
		return a.compareToWithColor(b);
	}
	/* ************************ */
}
