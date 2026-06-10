package notification.application.Email;

import notification.application.dto.EmailDetailsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSendService implements EmailService{

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private Environment environment;

    @Override
    public String sendMail(EmailDetailsDto emailDetailsDto) {
        try{
            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom(environment.getProperty("spring.mail.username"));
            mailMessage.setTo(emailDetailsDto.getRecipient());
            mailMessage.setSubject(emailDetailsDto.getSubject());
            mailMessage.setText(emailDetailsDto.getMessageBody());
            javaMailSender.send(mailMessage);
            return "Mail send";
        }catch (Error e){
            System.err.println(e);
            return "Error";
        }
    }
}
