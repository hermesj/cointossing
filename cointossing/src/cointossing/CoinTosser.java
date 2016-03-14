package cointossing;

import java.util.Random;

public class CoinTosser {
	
	private int n=1;
	private double alicesAverage =0.0;
	private double bobsAverage =0.0;

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

	public double getAlicesTossesCount() {
		return alicesAverage;
	}

	public double getBobsTossesCount() {
		return bobsAverage;
	}

}
