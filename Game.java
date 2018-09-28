package HuangSiyuan;

import java.util.Scanner;
import HuangSiyuan.*;

public class Game{
	static public void main(String[] args){
		Scanner input = new Scanner(System.in);
		Speaker output = new Speaker(0);
		output.gameTable();
		int index = input.nextInt();
		switch(index){
			case 0:
				new FightTheLandlord().work();
				break;
			default:
				break;
		}
		output.goodbye();
		input.close();
	}
}
