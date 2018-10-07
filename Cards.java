package HuangSiyuan;

import HuangSiyuan.*;

public class Cards{
	/**
	 * suit of Card
	 */
	public Cards(Card[] initial_cards){
		card = initial_cards; 	// initial

		/* ************************ */
		//	sort(Card[]) begin
		for(int i = 0; i < card.length; i++)
			for(int j = i + 1; j < card.length; j++)
				if(card[i].compareToWithColor(card[j]) > 0){
					Card tmp = card[i];
					card[i] = card[j];
					card[j] = tmp;
				}
		//	sort(Card[]) end
		/* *********************** */

		kind = card.length == 0 ? 0 : 1;

		for(int i = 1; i < card.length; i++)			//	calculate variable "kind"
			if(card[i].compareTo(card[i - 1]) != 0)
				kind = kind + 1;

		each = new int[kind];			
		each_value = new int[kind];
		value = new int[kind];

		for(int i = 0, j = 0; i < card.length; i++){	//	initial of each[], each_value[], value[]
			if(i != 0 && card[i].compareTo(card[i - 1]) != 0)
				j = j + 1;
			each[j] = each[j] + 1;
			each_value[j] = value[j] = card[i].getValue();
		}

		/* ************************ */
		//	sort(each), sort(each_value), sort(value) begin
		for(int i = 0; i < each.length; i++)
			for(int j = i + 1; j < each.length; j++)
				if(each[i] > each[j] || (each[i] == each[j] && each_value[i] > each_value[j])){
					int tmp = each[i];
					each[i] = each[j];
					each[j] = tmp;
					int _mp = each_value[i];
					each_value[i] = each_value[j];
					each_value[j] = _mp;
				}
		for(int i = 0; i < value.length; i++)
			for(int j = i + 1; j < value.length; j++)
				if(value[i] > value[j]){
					int tmp = value[i];
					value[i] = value[j];
					value[j] = tmp;
				}
		//	sort(each), sort(each_value), sort(value) end
		/* ************************ */
	}

	private int kind;    			//	number of different cards（卡牌的种数）

	//	relate to variable "kind"
	public boolean isEmpty(){
		return kind == 0 ? true : false;
	}
	public int getKind(){
		return kind;
	}

	private Card[] card; 			//	array of Card（卡牌的数组）
	private int[] each;				// 	total of each kinds（每种卡牌的数量）	 
	private int[] each_value;		//	value of each kinds（每种卡牌的价值）
	private int[] value;			//	array of card value（卡牌价值的数组） 

	/* ************************************ */
	//	don't mention it~
	public boolean sameEach(int[] input){
		if(input.length != each.length)
			return false;
		for(int i = 0; i < input.length; i++)
			if(input[i] != each[i])
				return false;
		return true;
	}

	public boolean isPair(){
		if(kind < 2)
			return false;
		return each_value[kind - 1] - each_value[kind - 2] == 1;
	}
	/* ************************************ */

	public int king(){
		if(kind < 1)
			return 0;
		if(kind == 1)
			return each_value[0] >= 14 ? 1 : 0;
		else{
			if(each_value[kind - 1] >= 14)
				return each_value[kind - 2] >= 14 ? 2 : 1;
			return 0;
		}
	}

	public int length(){
		return card.length;
	}
	public int getValue(){
		if(kind == 0)
			return 0;
		return each_value[kind - 1];
	}
	public int getColor(){
		int value = getValue();
		int result = 0;
		for(int i = 0; i < card.length; i++)
			if(card[i].getValue() == value)
				result = Math.max(result, card[i].getColor());
		return result;
	}

	public boolean isSequence(){
		if(kind <= 1)
			return true;
		for(int i = 1; i < value.length; i++)
			if(value[i] - value[i - 1] > 1)
				return false;
		return true;
	}
	private boolean isBomb(){
		if(kind == 1)
			return card.length == 4;
		else if(kind == 2)
			return king() == 2;
		return false;
	}

	/* ************************* */
	//	comparison here
	//
	//	if(a == {} AND b == {}):
	//		a > b
	public boolean biggerThan(Cards before){
		if(before.isEmpty() || (!before.isBomb() && isBomb()) || king() == 2)
			return true;
		return getValue() > before.getValue() ? true : false;
	}
	public boolean biggerThanWithColor(Cards before){
		if(before.isEmpty() || (!before.isBomb() && isBomb()) || king() == 2)
			return true;
		if(getValue() < before.getValue())
			return false;
		else if(getValue() == before.getValue())
			return getColor() > before.getColor();
		return true;
	}

	public boolean smallerThan(Cards before){
		return before.biggerThan(new Cards(card));
	}
	public boolean smallerThanWithColor(Cards before){
		return before.biggerThanWithColor(new Cards(card));
	}

	public boolean equals(Cards before){
		return !smallerThan(before) && !biggerThan(before);
	}
	public boolean equalsWithColor(Cards before){
		return !smallerThanWithColor(before) && !biggerThanWithColor(before);
	}

	public int compareTo(Cards before){
		if(biggerThan(before))
			return 1;
		return equals(before) ? 0 : -1;
	}
	public int compareToWithColor(Cards before){
		if(biggerThanWithColor(before))
			return 1;
		return equalsWithColor(before) ? 0 : -1;
	}
	/* ************************** */

	//	generate next class Cards
	public Cards next(int[] remove){
		int length = card.length;
		for(int i = 0; i < remove.length; i++)
			if(remove[i] != 0)
				length -= 1;
		Card[] result = new Card[length];
		for(int i = 0, j = 0; i < card.length; i++)
			if(remove[i] == 0)
				result[j++] = card[i];
		return new Cards(result);
	}

	//	push a Card[]
	public Cards merge(Card[] input){
		Card[] buffer = new Card[length() + input.length];
		for(int i = 0; i < card.length; i++)
			buffer[i] = card[i];
		for(int i = card.length; i < buffer.length; i++)
			buffer[i] = input[i - card.length];
		return new Cards(buffer);
	}
	//	push a Cards
	public Cards merge(Cards input){
		return input.merge(toArrayOfCard());
	}

	public Card[] toArrayOfCard(){
		return card;
	}
}
