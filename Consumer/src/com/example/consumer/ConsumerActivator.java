package com.example.consumer;

// Importing necessary OSGi classes
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import com.example.producer.service.MessageService;

public class ConsumerActivator implements BundleActivator {

    // This method is called when the bundle starts (like starting a new process)
    @Override
    public void start(BundleContext context) throws Exception {

        // Step 1: Trying to get a reference to the service called MessageService
        // Think of this as looking for a specific item in a store. The store (OSGi) has many items, 
        // and we need to find the "MessageService" item to use it.
        ServiceReference<MessageService> serviceReference = context.getServiceReference(MessageService.class);

        // Step 2: If we found the service (MessageService), we proceed to use it
        if (serviceReference != null) {

            // Step 3: Using the service (it's like picking the item we found and using it)
            MessageService messageService = context.getService(serviceReference);
            
            // Step 4: If the service is available, we use it
            if (messageService != null) {
                // Real-life analogy: Imagine the service is a speaker, and it says something when we use it.
                // We are simply printing the message it gives us.
                System.out.println("Consumer received message: " + messageService.getMessage());
                System.out.println("Consumer received message: " + messageService.sayFuckYou());
            }
        } else {
            // If we didn't find the service, we print a message saying it's unavailable
            System.out.println("MessageService not available.");
        }
    }

    // This method is called when the bundle stops (like shutting down a process)
    @Override
    public void stop(BundleContext context) throws Exception {
        // Cleanup can go here if needed (for example, turning off a device when done)
        System.out.println("Consumer stopped.");
    }
}
