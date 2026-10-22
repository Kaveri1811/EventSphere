package com.booking_service.booking_service.events;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class BookingEventProducer {

    private final KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate;

    private static final String TOPIC = "booking-created";

    public BookingEventProducer(
            KafkaTemplate<String, BookingCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void publishBookingCreated(BookingCreatedEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.getBookingId().toString(),
                event
        );
    }
}
