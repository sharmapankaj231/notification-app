package notification.application;

import lombok.AllArgsConstructor;
import notification.application.Email.EmailSendService;
import notification.application.dto.EmailDetailsDto;
import notification.application.dto.NotificationDto;
import notification.application.entity.Notification;
import notification.application.entity.NotificationStatus;
import notification.application.kafka.KafkaProducerService;
import notification.application.repository.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;
import java.util.List;

@Service()
@AllArgsConstructor()
public class NotificationService {

    private final EmailSendService emailSendService;
    private final Environment environment;
    private NotificationRepository notificationRepository;
    private KafkaProducerService kafkaProducerService;
    private ObjectMapper objectMapper;

    public List<Notification> getAll(){
        return this.notificationRepository.findAll();
    }

    public String create(NotificationDto dto){
        kafkaProducerService.sendMessage(objectMapper.writeValueAsString(dto));
        return "Notification Created";
    }

    public Notification update(Notification dto){
        return this.notificationRepository.save(dto);
    }

    public Notification save(NotificationDto dto){
        ModelMapper mapper= new ModelMapper();
        Notification newObj= mapper.map(dto,Notification.class);
        return this.notificationRepository.save(newObj);
    }

    public void sendNotification(NotificationDto dto){
        EmailDetailsDto emailDetailsDto=new EmailDetailsDto();
        emailDetailsDto.setSubject(dto.getNotificationType());
        emailDetailsDto.setMessageBody(dto.getMessage());
        emailDetailsDto.setRecipient(environment.getProperty("sender.mail.id"));
        String res=emailSendService.sendMail(emailDetailsDto);
        if(res.equals("Mail send")){
            dto.setStatus(NotificationStatus.SENT);
            this.save(dto);
        }
        else{
            dto.setStatus(NotificationStatus.FAILURE);
            this.save(dto);
        }
        System.out.println("res "+res);
    }
}
