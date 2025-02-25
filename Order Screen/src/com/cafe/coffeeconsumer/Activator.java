package com.cafe.coffeeconsumer;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    @Override
    public void start(BundleContext context) throws Exception {
        System.out.println("Text Consumer Started: Ready to Display Coffee Orders");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Text Consumer Stopped");
    }
}
