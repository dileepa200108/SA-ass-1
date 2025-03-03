package cupcakeorderscreen;

import com.cafe.api.CupCakeOrderListener;

public class CupCakeOrderScreen implements CupCakeOrderListener {

	@Override
	public void onCupCakeOrder(String customerName, String flavor) {
		
        System.out.println("🧁 CupCake Screen - order received for " + customerName + ": " + 
                          flavor + " cupcake. Enjoy your treat!");

	}

}
