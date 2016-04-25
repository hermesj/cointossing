package cointossing;

import java.util.Random;


/**
 * A class that simulates a sequence of coin tosses.
 * Alice wins, when Tail follows Head
 * Bob wins, when Head follows Head
 * The average counts are the counts of tosses, Alice and Bob needed to win.
 * @author jhermes
 *
 */
public class CoinTosser{
	
	private int n=1;
	private double alicesAverage =0.0;
	private double bobsAverage =0.0;
	

	/**
	 * Tosses a coin until each Alice and Bob win the round for i rounds
	 * @param i Number of rounds
	 */
	public void tossCoin(int i) {
		Random random = new Random(0);
		for(;n<i;n++){
			int aliceDone = 0;
			int bobsDone = 0;
			int tosses =1;
			while(true){
				boolean head = random.nextBoolean();
				if(head){
											
					if(aliceDone==0)
						aliceDone=1;
					if(bobsDone==1){
						bobsDone = 2;
						bobsAverage = (bobsAverage*(n-1) + (tosses))/n;
						if(aliceDone==2)
							break;
					}
					
					if(bobsDone==0)
						bobsDone=1;
					
				}
				else{
					if(aliceDone==1){
						aliceDone = 2;
						alicesAverage = (alicesAverage*(n-1) + (tosses))/n; 
						if(bobsDone==2)
							break;
					}
					if(bobsDone==1)
						bobsDone=0;
					
				}
				tosses++;
			}
		}
	}
	
	public void contest(int games){
		Random random = new Random(0);
		for(int i=0; i<games; i++){
			boolean previousHead= false;
			while(true){
				boolean head = random.nextBoolean();
				if(head){
					if(previousHead){
						bobsAverage+=1.0;
						break;
					}
					previousHead = true;
				}
				else{
					if(previousHead){
						alicesAverage+=1.0;
						break;
					}
					previousHead = false;
				}
			}
		}
	}

	/** Returns the average tosses Alice needed to win
	 * @return average tosses Alice needed to win
	 */
	public double getAlicesTossesCount() {
		return alicesAverage;
	}

	/** Returns the average tosses Bob needed to win
	 * @return average tosses Bob needed to win
	 */
	public double getBobsTossesCount() {
		return bobsAverage;
	}

}
