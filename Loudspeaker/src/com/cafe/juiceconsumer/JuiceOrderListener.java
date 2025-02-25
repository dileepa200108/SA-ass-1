package com.cafe.juiceconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.cafe.juice.JuiceService;

public class JuiceOrderListener implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        // Register the JuiceService listener to announce orders
        ServiceReference<JuiceService> juiceRef = context.getServiceReference(JuiceService.class);
        if (juiceRef != null) {
            JuiceService juiceService = context.getService(juiceRef);
            juiceService.makeJuice("Emily Brown");
            System.out.println("Announcing Juice Order on Speaker: Juice for Emily Brown");
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Juice Consumer service stopped");
    }
}
