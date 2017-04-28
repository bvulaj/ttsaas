package com.redhat.ttsaas.email;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Flags;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.redhat.ttsaas.controller.TextToSpeechController;
import com.redhat.ttsaas.model.TextToSynthesize;

@Component
public class EmailToSpeech {

    @Autowired
    private TextToSpeechController textToSpeechController;

    @Scheduled(fixedRate = 5000)
    public void checkForEmail() throws MessagingException {
        Folder folder = null;
        Store store = null;

        try {
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties);

            store = emailSession.getStore("imaps");
            store.connect("imap.gmail.com", "myemail@gmail.com", "******");

            folder = store.getFolder("INBOX");
            folder.open(Folder.READ_ONLY);

            for(Message email : folder.getMessages()) {
                if (!email.isSet(Flags.Flag.SEEN)) {
                    continue;
                }

                try {
                    textToSpeechController.readText(
                            new TextToSynthesize(email.getSubject()));
                } catch (MessagingException | IOException e) {
                    e.printStackTrace();
                }
            }

                /*System.out.println("Subject: " + message.getSubject());
                System.out.println("From: " + message.getFrom()[0]);
                System.out.println("Text: " + message.getContent().toString());*/
        }
        finally {
            if (folder != null) {
                folder.close(true);
            }
            if (store != null) {
                store.close();
            }
        }
    }

}
