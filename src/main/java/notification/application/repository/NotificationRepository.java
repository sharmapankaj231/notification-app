package notification.application.repository;

import notification.application.entity.Notification;
import notification.application.entity.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository()
public interface NotificationRepository extends JpaRepository<Notification,Long> {

    List<Notification> findNotificationsByStatusIsAndScheduledAtBefore(NotificationStatus status, Date date);
}
