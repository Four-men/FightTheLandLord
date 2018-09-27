package HuangSiyuan;

import HuangSiyuan.*;

public class Card{
	/**
	 * poker card
	 */
	Card(int initial_value){
		color = 0;
		value = initial_value;
	}
	Card(int initial_color, int initial_value){
		color = initial_color;
		value = initial_value;
	}
	int color; 
	//	0 : Diamond
	//	1 : Club
	//	2 : Heart
	//	3 : Spade
	int value; 	
	//	[1, 15]
	private final int[] VALUE_LEVEL = new int[] {0, 12, 13, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 14, 15};
	public int compareToWithColor(Card rhs){
		//	return value(a) - value(b)
		if(value == rhs.value)
			return color - rhs.color; 
		return VALUE_LEVEL[value] - VALUE_LEVEL[rhs.value];
	}
	public int compareTo(Card rhs){
		//	return value(a) - value(b)
		return VALUE_LEVEL[value] - VALUE_LEVEL[rhs.value]; 
	}
	public int getColor(){
		return color;
	}
	public int getValue(){
		return VALUE_LEVEL[value];
	}
}
