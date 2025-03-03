package cupcakeorderscreen;

import org.osgi.framework.BundleActivator;

import org.osgi.framework.BundleContext;
import com.cafe.api.CupCakeOrderListener;


public class Activator implements BundleActivator {
    @Override
    public void start(BundleContext context) throws Exception {
        // Register CupCakeOrderListener service
        System.out.println("✅ CupCake Order Screen - Ready to Display CupCake Orders");
        
        CupCakeOrderListener cupCakeOrderScreen = new CupCakeOrderScreen();
        context.registerService(CupCakeOrderListener.class, cupCakeOrderScreen, null);
    }
    
    @Override
    public void stop(BundleContext context) throws Exception {
        // Unregister the CupCakeOrderListener service if necessary
        System.out.println("❌ CupCake Order Screen Stopped");
    }
}