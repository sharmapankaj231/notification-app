package notification.application.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import notification.application.entity.NotificationStatus;

import java.util.Date;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class NotificationDto {

    private String notificationName;

    private String notificationType;

    private String message;

    private String notificationTime;

    private NotificationStatus status;

    @Nullable
    private Date scheduledAt;
}
