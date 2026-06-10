package notification.application.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jdk.jfr.Timestamp;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Primary;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity()
@Getter()
@Setter()
public class Notification {

    @Id
    @GeneratedValue()
    private Long id;

    private String notificationName;

    private String notificationType;

    private String message;

    private NotificationStatus status;

    @Nullable
    private Date scheduledAt;
}
