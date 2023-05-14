package com.gestresmat.springjwt.controllers;

import com.gestresmat.springjwt.models.Message;
import com.gestresmat.springjwt.security.services.MessageService;
import com.gestresmat.springjwt.security.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageService messageService;
    private final UserService userService;

    public MessageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Message>> getAllMessages(){
        List<Message> messages= messageService.findAllMessage();
        return new ResponseEntity<>(messages, HttpStatus.OK);

    }
    @GetMapping("/find/{id}")
    public ResponseEntity<List<Message>> getMessagesByRecepteurID(@PathVariable(value = "id") Long id){
        List<Message> messages= messageService.findMessageByRecepteur(id);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping("/creer/{idR}/{idE}")
    // wa att rah dak count li drti ga3 ma kjdama  att tchoof
    public ResponseEntity<Message> creerMessage(@RequestBody Message message,@PathVariable(value = "idR") List<Long> idR,@PathVariable(value = "idE") Long idE) {
        System.out.println(message);
        Message message1 = messageService.creerMessage(idR,message.getContent(),message.getSubject(),idE);
        return ResponseEntity.ok(message1);
    }
    @PostMapping("/seen/{id}")
    public ResponseEntity<Message> marquer(@PathVariable(value = "id") Long id){

        Message message1 = messageService.markAsSeen(id);


        return ResponseEntity.ok(message1);
    }




}
