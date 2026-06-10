package notification.application.Email;

import notification.application.dto.EmailDetailsDto;

public interface EmailService {

    String sendMail(EmailDetailsDto emailDetailsDto);
}
