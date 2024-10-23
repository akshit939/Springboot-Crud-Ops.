package com.example.journal.service;

import com.example.journal.Repository.journalEntryRepository;
import com.example.journal.entity.User;
import com.example.journal.entity.journalEntry;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@Component
public class journalEntryService {
    @Autowired
    private journalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;
@Transactional
    public void saveEntry(journalEntry journalEntry, String username){
       try {
           User user = userService.findByusername(username);
           journalEntry.setDate(LocalDateTime.now());
           journalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
       userService.saveEntry(user);
}catch(Exception e){
           System.out.println(e);
       throw new RuntimeException("An error occur while saving the Entry");
       }
}


    public void saveEntry(journalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
}

    public List<journalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<journalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }

    public void deleteById(ObjectId id, String username){
        User user=userService.findByusername(username);
            user.getJournalEntries().removeIf(x->x.getId().equals(id));
            userService.saveEntry(user);
        journalEntryRepository.deleteById(id);}


}


