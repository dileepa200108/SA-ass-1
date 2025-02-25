package com.example.middleware;

import org.osgi.framework.*;
import com.example.producer.service.MessageService;
import com.example.consumer.ConsumerActivator;
import java.util.Scanner;

public class MiddlewareActivator implements BundleActivator {

    private BundleContext context;

    @Override
    public void start(BundleContext context) throws Exception {
        this.context = context;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter client type (TextClient, AudioClient): ");
        String clientType = scanner.nextLine().trim();

        // Find the correct producer
        ServiceReference<?>[] references = context.getServiceReferences(MessageService.class.getName(), null);
        if (references != null) {
            for (ServiceReference<?> ref : references) {
                MessageService service = (MessageService) context.getService(ref);
                
                if (service.getProducerName().equalsIgnoreCase(clientType + "Producer")) {
                    System.out.println("Starting " + clientType + " and connecting to " + service.getProducerName());
                    
                    // Pass the correct producer to the consumer
                    launchConsumer(service);
                    return;
                }
            }
        }

        System.out.println("No matching producer found.");
    }

    private void launchConsumer(MessageService service) {
        try {
            // Start the Consumer Bundle
            Bundle[] bundles = context.getBundles();
            for (Bundle bundle : bundles) {
                if (bundle.getSymbolicName().equals("com.example.consumer")) {
                    bundle.start();

                    // Inject the correct producer into the consumer
                    ConsumerActivator consumer = (ConsumerActivator) bundle.getBundleContext().getService(bundle.getRegisteredServices()[0]);
                    consumer.setMessageService(service);
                    return;
                }
            }
            System.out.println("Consumer bundle not found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        System.out.println("Middleware stopped.");
    }
}
