package com.gestresmat.springjwt.security.services;

import com.gestresmat.springjwt.models.*;
import com.gestresmat.springjwt.repository.MessageRepository;
import com.gestresmat.springjwt.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    public MessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    public Message markAsSeen(Long id){
        Message message = messageRepository.findMessageById(id);
        message.setSeen(1);
        messageRepository.save(message);
        return message;
    }

    public List<Message> findAllMessage(){
        return messageRepository.findAllByOrderByIdDesc();
    }
    public Message addMessage(Message message){
        return messageRepository.save(message);
    }

    public List<Message> findMessageByRecepteur(Long id){return messageRepository.findMessageByRecepteurId(id);}


    public Message creerMessage(List<Long> idsRecepteurs, String subject,String content,Long idEmetteur) {
        List<User> recepteurs = userRepository.findAllById(idsRecepteurs);
        System.out.println(content);
        User emetteur = userRepository.findUserById(idEmetteur);
        Message message = new Message(subject,content,recepteurs,emetteur);
        message.setContent(content);
        message.setSubject(subject);

        /** appelOffre = appelOffreRepo.save(appelOffre);

         for (Besoin besoin : besoins) {
         Besoin b = besoinRepo.findById(besoin.getId()).orElseThrow(() -> new RuntimeException("Besoin non trouvé"));
         b.setAppelOffre(appelOffre);
         besoinRepo.save(b);
         }

         appelOffre.setBesoins(besoins);

         return appelOffre;**/
        messageRepository.save(message);
        return message;
    }

}
