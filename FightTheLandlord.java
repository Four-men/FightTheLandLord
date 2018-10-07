package HuangSiyuan;

import java.util.Scanner;
import java.util.Random;
import java.lang.Character;
import HuangSiyuan.*;

public class FightTheLandlord{
	FightTheLandlord(){
		LANGUAGE = 0;
	}
	FightTheLandlord(int initial_language){
		LANGUAGE = initial_language;
	}
	private final int LANGUAGE;
	private final int TYPE = 0;

	private Player[] player = new Player[] {new Player(), new Player(), new Player()};
	private Judge jd = new Judge(TYPE);
	private Cards hidden;
	private Speaker output;

	private int dealer;
	private int landlord;

	private void initial(){
		output = new Speaker(LANGUAGE);
		Random rand = new Random();
		dealer = rand.nextInt(3);
		for(int i = 0; i < player.length; i++)
			player[i] = new Player();
	}

	public void work(){
		Scanner input = new Scanner(System.in);
		initial();
		while(true){
			output.gameStart();
			jd.shuffle();
			for(int i = 0; i < player.length; i++)
				player[i].initial(jd.deal(17));
			for(int i = 0; i < player.length; i++){
				output.playerId(i);
				output.playerScore(player[i]);
			}
			hidden = jd.deal(3);
			landlord = -1;
			for(int i = dealer, j = 0; j < 3; i = (i + 1) % 3, j++){
				output.playerId(i, i == landlord);
				output.chooseLandlord();
				int sign = input.nextInt();
				input.nextLine();
				if(sign != 0){
					landlord = i;
					break;
				}
			}
			if(landlord < 0)
				continue;
			player[landlord].push(hidden);

			Cards before = new Cards(new Card[] {});
			int attacker = landlord;
			int times = 1;
			for(int i = landlord; ; i = (i + 1) % 3){
				output.playerId(i, i == landlord);
				output.println(player[i]);
				output.isYourTurn();
				while(true){
					String str = input.nextLine();
					byte[] abs = str.getBytes();
					int[] store = new int[player[i].size()];
					for(int j = 0; j < abs.length; j++)
						if(Character.isDigit(abs[j])){
							int val = 0;
							while(j < abs.length && Character.isDigit(abs[j])){
								val = val * 10 + abs[j] - '0';
								j += 1;
							}
							if(val < store.length)
								store[val] = 1;
						}
					Cards current = player[i].playCards(store);
					if(!jd.isVaild(current) || (i != attacker && str.length() > 0 && jd.compare(current, before) <= 0)){
						output.errorPlay();
						continue;
					}
					player[i].erase(store);
					output.println(current);
					output.println(current.toArrayOfCard());
					if(str.length() > 0 || i == attacker){
						attacker = i;
						before = current;
					}
					break;
				}
				if(player[i].win()){
					if(i == landlord){
						player[i].gain(6 * (int)Math.pow(2, times));
						player[(i + 1) % 3].gain(-3 * (int)Math.pow(2, times));
						player[(i + 2) % 3].gain(-3 * (int)Math.pow(2, times));
					}
					else{
						player[landlord].gain(-6 * (int)Math.pow(2, times));
						player[(landlord + 1) % 3].gain(3 * (int)Math.pow(2, times));
						player[(landlord + 2) % 3].gain(3 * (int)Math.pow(2, times));
					}
					output.playerId(i, i == landlord);
					output.congratulation();
					dealer = i;
					break;
				}
			}
			output.quitPrompt();
			String tmp = input.next();
			if(tmp.equals("quit"))
				break;
		}
		input.close();
	}
}
