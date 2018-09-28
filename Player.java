package HuangSiyuan;

import HuangSiyuan.*;

public class Player{
	Player(){
		score = 0;
	}
	Player(Cards initial_suit){
		score = 0;
		suit = initial_suit;
	}

	private Cards suit;
	public int score;
	public Cards[] before;

	public Cards getSuit(){
		return suit;
	}

	public void read(Cards input){
		suit = input;
	}

	public int size(){
		return suit.toArrayOfCard().length;
	}

	public boolean win(){
		return suit.isEmpty();
	}

	public Cards playCards(int[] remove){
		int[] play = new int[suit.length()];
		for(int i = 0; i < suit.length(); i++)
			play[i] = 1 ^ remove[i];
		return suit.next(play);
	}

	public void erase(int[] remove){
		suit = suit.next(remove);
	}

	public void push(Cards input){
		suit = suit.merge(input);
	}
}
