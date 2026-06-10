package notification.application;

import lombok.AllArgsConstructor;
import notification.application.dto.NotificationDto;
import notification.application.entity.Notification;
import notification.application.kafka.KafkaProducerService;
import notification.application.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller()
@ResponseBody()
@AllArgsConstructor()
public class NotificationController {

    private NotificationService notificationService;

    @PostMapping("/create")
    public String create(@RequestBody() NotificationDto dto) {
        return this.notificationService.create(dto);
    }

    @GetMapping("/all")
    public List<Notification> getAll() {
        return this.notificationService.getAll();
    }
}
