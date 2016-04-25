package cointossing;

public class Application {

	public static void main(String[] args) {
		CoinTosser ct = new CoinTosser();
		ct.tossCoin(1000000);
		//ct.contest(1000000);
		System.out.println("Alice: " + ct.getAlicesTossesCount());
		System.out.println("Bob:   " + ct.getBobsTossesCount());
	}

}
