package russianroulette;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RussianRoulette {
	
	public static void monteCarlo(int participants, int rounds){
		List<Integer> dead = new ArrayList<Integer>();
		for(int i=0; i<participants; i++){
			dead.add(0);
		}
		int alreadyDead = 0;
		Random random = new Random(0);
		for(int i=0; i<rounds; i++){
			for(int j=0; j<participants;j++){
				int fire = random.nextInt(6); 
				if(fire==0){
					int deaths = dead.get(j)+1;
					dead.set(j, deaths);
					alreadyDead++;
					if(participants-1==alreadyDead){
						break;
					}
				}
			}
		}
		for (Integer integer : dead) {
			System.out.println(integer);
		}
	}
	
	public static void runRR(int rounds){
		double aliceDead = 0.0;
		double bobDead = 0.0;
		double ongoingGame = 1 - aliceDead - bobDead;
		System.out.println("Round \tAlice dies \t\tAlice dead \t\tBob dies \t\tBob dead \t\tOngoing game");
		for(int i=0; i<rounds; i++){
			double aliceDies = ongoingGame * (1.0/6.0);
			aliceDead += aliceDies;
			ongoingGame = 1 - aliceDead - bobDead;
			
			double bobDies = ongoingGame * (1.0/6.0);
			bobDead += bobDies; 
			ongoingGame = 1 - aliceDead - bobDead;
			
			System.out.println((i+1) + "\t" + aliceDies + "\t" + aliceDead + "\t" + bobDies + "\t" + bobDead + "\t" + ongoingGame);
			
		}
	}
	
	public static void runRR(int participants, int rounds){
		List<List<Double>> dyingProbs = new LinkedList<List<Double>>();
		for(int i=0; i<participants; i++){
			dyingProbs.add(new ArrayList<Double>());
		}
		double ongoingGame = 1.0;
		for(int i=0; i<rounds; i++){
			for (List<Double> list : dyingProbs) {
				double someoneDies = ongoingGame * (1.0/6.0);
				if(i==0){
					list.add(someoneDies);
				}
				else{
					list.add(list.get(i-1) + someoneDies);
				}
				ongoingGame -= someoneDies;				
			}
		}	
		int i=1;
		for (List<Double> list : dyingProbs) {
			System.out.print("Person " + i++ + "\t");
			for (Double double1 : list) {
				System.out.print(double1 + "\t");
			}
			System.out.println();
		}
		
	}
	
	public static double getOngoingGameProb(int participants, int round){
		System.out.println("Part: " + participants + " round: " + round);
		if(round<participants){
			return 1.0;
		}
		double ongoingGameProb = 0.0;
		for(int i=0; i<participants; i++){
			long binom = binom(round,i);
			double iProb = binom * Math.pow((1.0/6.0), i) * Math.pow((5.0/6.0), round-i);
			System.out.println(iProb);
			ongoingGameProb+=iProb;
		}
		System.out.println("Returning: " + ongoingGameProb);
		return ongoingGameProb;
	}
	
	public static long binom(int n, final int k) {
	    final int min = (k < n - k ? k : n - k);
	    long bin = 1;
	    for (int i = 1; i <= min; i++) {
	      bin *= n;
	      // geht immer genau, da n * (n-1) * ... immer durch das
	      // entsprechende i teilbar ist
	      bin /= i;
	      n--;
	    }
	    return bin;
	 }
	//p(0) = (n über 0) * (1/6)^0 * (5/6)^(n-0)
		//	P(1) = (n über 1) * (1/6)^1 * (5/6)^(n-1)
			//p(2) = (n über 2) * (1/6)^2 * (5/6)^(n-2)
			//p(k) = (n über k) * (1/6)^k * (5/6)^(n-k)
			//p(8) = (n über 8) * (1/6)^8 * (5/6)^(n-8)


	
	public static void runRRBloody(int participants, int rounds){
		List<List<Double>> dyingProbs = new LinkedList<List<Double>>();
		for(int i=0; i<participants; i++){
			dyingProbs.add(new ArrayList<Double>());
		}
		double ongoingGame = 1.0;
		int j=0;
		for(int i=0; i<rounds; i++){
			
			for (List<Double> list : dyingProbs) {
				j++;
				ongoingGame = getOngoingGameProb(participants, j);
				double someoneDies = ongoingGame * (1.0/6.0);
				if(i==0){
					list.add(someoneDies);
				}
				else{
					list.add(list.get(i-1) + someoneDies);
				}
				//if(participants==1)
				//ongoingGame -= someoneDies;				
			}
		}	
		int i=1;
		for (List<Double> list : dyingProbs) {
			System.out.print("Person " + i++ + "\t");
			for (Double double1 : list) {
				System.out.print(double1 + "\t");
			}
			System.out.println();
		}
		
	}

}
