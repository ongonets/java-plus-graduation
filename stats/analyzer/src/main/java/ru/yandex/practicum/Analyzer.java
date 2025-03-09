package ru.yandex.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ConfigurableApplicationContext;
import ru.yandex.practicum.processor.UserActionProcessor;
import ru.yandex.practicum.processor.EventSimilarityProcessor;

@SpringBootApplication
@ConfigurationPropertiesScan
public class Analyzer {
    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                SpringApplication.run(Analyzer.class, args);




        EventSimilarityProcessor eventSimilarityProcessor =
                context.getBean(EventSimilarityProcessor.class);

        final UserActionProcessor userActionProcessor =
                context.getBean(UserActionProcessor.class);

        Runtime.getRuntime().addShutdownHook(new Thread(eventSimilarityProcessor::stop));
        Runtime.getRuntime().addShutdownHook(new Thread(userActionProcessor::stop));


        Thread hubEventsThread = new Thread(userActionProcessor);
        hubEventsThread.setName("UserActionProcessorThread");
        hubEventsThread.start();


        eventSimilarityProcessor.start();
    }
}
