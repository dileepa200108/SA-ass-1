package com.cafe.juiceconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Audio Consumer Started: Ready to Announce Juice Orders");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Audio Consumer Stopped");
    }
}
