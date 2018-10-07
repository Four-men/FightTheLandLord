package HuangSiyuan;

import java.util.Random;
import HuangSiyuan.*;

public class Deck{
	public Deck(){
		init(1, true);
	}
	public Deck(int initial_deck_number, boolean with_king){
		init(initial_deck_number, with_king);
	}
	public Deck(Card[] input){
		card = input;
		remain_poker = card.length;
	}
	public Deck(Cards input){
		card = input.toArrayOfCard();
		remain_poker = card.length;
	}
	
	private Card[] card;
	private int remain_poker;

	private void erase(int pos){
		for(int i = pos; i < remain_poker - 1; i++)
			card[i] = card[i + 1];
		remain_poker -= 1;
	}

	private void init(int initial_deck_number, boolean with_king){
		card = new Card[initial_deck_number * 52 + 2 * initial_deck_number * (with_king ? 1 : 0)];
		remain_poker = initial_deck_number * 52 + 2 * initial_deck_number * (with_king ? 1 : 0);
		for(int t = 0; t < initial_deck_number; t++){
			for(int i = 0; i < 13; i++)
				for(int j = 0; j < 4; j++)
					card[52 * t + j + 4 * i] = new Card(j + 1, i + 1);
			if(with_king){
				card[52 * (t + 1) + 2 * t] = new Card(14);
				card[52 * (t + 1) + 2 * t + 1] = new Card(15);
			}
		}
	}

	public Cards deal(int total){
		if(remain_poker == 0)
			return new Cards(new Card[0]);
		Card[] store = new Card[total];
		Random rand = new Random();
		for(int i = 0; i < total; i++){
			int pos = rand.nextInt(remain_poker);
			store[i] = card[pos];
			erase(pos);
		}
		return new Cards(store);
	}
}
