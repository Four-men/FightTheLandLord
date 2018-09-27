package HuangSiyuan;

import HuangSiyuan.*;

public class Player{
	Player(Cards initial_suit){
		suit = initial_suit;
	}

	private Cards suit;
	public Cards[] before;

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
}
