package com.example.producer.service;

// Importing necessary OSGi classes
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProducerActivator implements BundleActivator {

    // This method is called when the bundle starts (like starting a new process)
    @Override
    public void start(BundleContext context) throws Exception {
        // Step 1: Creating a service that implements the MessageService interface
        // In this case, we're defining what the service will do when asked for a message.
        MessageService service = new MessageService() {
            @Override
            public String getMessage() {
                // Real-life analogy: You are the service, and when asked, you provide the message
                return "Hello from Producer!"; // This is the message the producer will give when asked.
            }

			@Override
			public String sayFuckYou() {
				// TODO Auto-generated method stub
				return "Fuck you consumer";
			}
        };

        // Step 2: Register the service with the OSGi framework
        // This is like putting the service on a shelf in the store, so consumers can pick it up and use it.
        context.registerService(MessageService.class.getName(), service, null);

        // Step 3: Print a message indicating that the producer started and the service is registered
        System.out.println("Producer started and service registered.");
    }

    // This method is called when the bundle stops (like shutting down a process)
    @Override
    public void stop(BundleContext context) throws Exception {
        // Cleanup can go here if needed (for example, turning off the service when done)
        System.out.println("Producer stopped.");
    }
}
