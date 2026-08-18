package com.lucas.notificationservice.service;

import com.lucas.notificationservice.dto.TaskNotificationRequest;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;
    private final TemplateEngine templateEngine;

    @Value("${application.mail.from-email}")
    private String fromEmail;

    public void sendTaskCreatedEmail(TaskNotificationRequest request) {
        try {
            // 1. Prepara o contexto com as variáveis que o HTML/Thymeleaf precisa
            Context context = new Context();
            context.setVariable("recipientName", request.recipientName());
            context.setVariable("taskTitle", request.taskTitle());
            context.setVariable("taskDescription", request.taskDescription());

            // 2. Processa o HTML Thymeleaf transformando em uma String legível
            String htmlContent = templateEngine.process("task-created", context);

            // 3. Monta a mensagem de e-mail (MIME para suportar HTML)
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromEmail);
            helper.setTo(request.recipientEmail());
            helper.setSubject("Nova Tarefa Atribuída: " + request.taskTitle());
            helper.setText(htmlContent, true); // true indica que o conteúdo é HTML

            // 4. Envia o e-mail
            mailSender.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException("Falha ao enviar e-mail de notificação", e);
        }
    }
}