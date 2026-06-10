package notification.application;

import notification.application.dto.NotificationDto;
import notification.application.entity.Notification;
import notification.application.entity.NotificationStatus;
import notification.application.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public class Scheduler {

    private final NotificationRepository notificationRepository;
    private final NotificationService notificationService;

    public Scheduler(NotificationRepository notificationRepository, NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
        this.notificationService = notificationService;
    }

    @Scheduled(cron = "1 * * * * *")
    public void scheduleNotification(){
        List<Notification> l=notificationRepository.findNotificationsByStatusIsAndScheduledAtBefore(NotificationStatus.PENDING,new Date());
        l.forEach((Notification record) -> {
            ModelMapper mapper= new ModelMapper();
            NotificationDto dto=mapper.map(record,NotificationDto.class);
            this.notificationService.sendNotification(dto);
            record.setStatus(NotificationStatus.SENT);
            this.notificationService.update(record);
        });
    }
}
