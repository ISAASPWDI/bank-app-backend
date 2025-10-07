package com.beko.DemoBank_v1.controllers;

import com.beko.DemoBank_v1.helpers.HTML;
import com.beko.DemoBank_v1.helpers.Token;
import com.beko.DemoBank_v1.mailMessenger.MailMessenger;
import com.beko.DemoBank_v1.models.User;
import com.beko.DemoBank_v1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.*;

@RestController
public class RegisterController {

    private final UserRepository userRepository;
    private final MailMessenger mailMessenger;

    @Autowired
    public RegisterController(UserRepository userRepository, MailMessenger mailMessenger) {
        this.userRepository = userRepository;
        this.mailMessenger = mailMessenger;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(
            @Valid @RequestBody User user,
            BindingResult bindingResult,
            @RequestParam("confirm_password") String confirmPassword) {

        String firstName = user.getFirst_name();
        String lastName = user.getLast_name();
        String email = user.getEmail();
        String password = user.getPassword();

        if (bindingResult.hasErrors() && confirmPassword.isEmpty()) {
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errorMessages);
        }

        // TODO: CHECK FOR PASSWORD MATCH
        if (!password.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Las contraseñas no coinciden.");
        }

        // TODO: GET TOKEN STRING
        String token = Token.generateToken();

        // TODO: GENERATE RANDOM CODE
        Random rand = new Random();
        int bound = 123;
        int code = bound * rand.nextInt(bound);

        // TODO: GET EMAIL HTML BODY
        String emailBody = HTML.htmlEmailTemplate(token, Integer.toString(code));

        // TODO: HASH PASSWORD
        String hashed_password = BCrypt.hashpw(password, BCrypt.gensalt());

        // TODO: REGISTER USER
        userRepository.registerUser(firstName, lastName, email, hashed_password, token, Integer.toString(code));

        // TODO: SEND EMAIL NOTIFICATION
        try {
            mailMessenger.sendHtmlEmail("stivensaliaga@gmail.com", email, "Verify Account", emailBody);
        } catch (MessagingException e) {
            throw new RuntimeException("Error al enviar correo de verificación", e);
        }

        // TODO: RETURN REGISTRATION SUCCESS
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Registro exitoso. Por favor revisa tu correo y verifica tu cuenta.");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }
}
