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
				if(random.nextInt()==1){
					dead.set(j, dead.get(j)+1);
					alreadyDead++;
					if(participants==alreadyDead){
						break;
					}
				}
			}
		}
		for (Integer integer : dead) {
			System.out.println(integer);
		}
	}
	
	public static void runRR(){
		double aliceDead = 0.0;
		double bobDead = 0.0;
		double ongoingGame = 1 - aliceDead - bobDead;
		System.out.println("Round \tAlice dies \t\tAlice dead \t\tBob dies \t\tBob dead \t\tOngoing game");
		for(int i=0; i<15; i++){
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
	
	public static void runRRBloody(int participants, int rounds){
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
				if(participants==1)
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

}
