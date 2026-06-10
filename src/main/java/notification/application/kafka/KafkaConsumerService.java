package notification.application.kafka;

import notification.application.NotificationService;
import notification.application.dto.NotificationDto;
import notification.application.entity.Notification;
import notification.application.entity.NotificationStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

@Service
public class KafkaConsumerService {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "notification-topic")
    public void getMessage(String message){
        NotificationDto dtoObject=objectMapper.readValue(message,NotificationDto.class);
        System.out.println("Message Received: "+message);
        if(dtoObject.getScheduledAt()== null) {
            notificationService.sendNotification(dtoObject);
        }
        else{
            dtoObject.setStatus(NotificationStatus.PENDING);
            Notification res=notificationService.save(dtoObject);
            System.out.println("Record saved for scheduling: "+res);
        }
    }
}
