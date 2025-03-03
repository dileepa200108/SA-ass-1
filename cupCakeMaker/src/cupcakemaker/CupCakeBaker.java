package cupcakemaker;

import com.cafe.api.CupCakeService;

public class CupCakeBaker implements CupCakeService {

	@Override
	public void makeCupCake(String customerName, String flavor) {
		 System.out.println("🧁 CupCake Baker is making a " + flavor + " cupcake for " + customerName);

	}

}
