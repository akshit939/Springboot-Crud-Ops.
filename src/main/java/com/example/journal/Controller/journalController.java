package com.example.journal.Controller;

import com.example.journal.entity.User;
import com.example.journal.entity.journalEntry;
import com.example.journal.service.UserService;
import com.example.journal.service.journalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalController {
@Autowired
private journalEntryService journalEntryService;
@Autowired
private UserService userService;

    @GetMapping("{username}")
    public ResponseEntity<?>getAlljournalEntriesOfUser(@PathVariable String username){
   User user=userService.findByusername(username);
        List<journalEntry> all=user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("{username}")
    public ResponseEntity<journalEntry>createEntry(@RequestBody journalEntry myEntry, @PathVariable String username) {
        try {
            journalEntryService.saveEntry(myEntry,username);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }
    @GetMapping("id/{myId}")
    public ResponseEntity<?> getjournalEntryById(@PathVariable ObjectId myId){
        Optional<journalEntry> journalEntry=journalEntryService.findById(myId);
        if(journalEntry.isPresent()){
        return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deletejournalEntryById(@PathVariable ObjectId myId,@PathVariable String username){
        journalEntryService.deleteById(myId,username);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   @PutMapping("id/{username}/{myId}")
    public ResponseEntity<?> updatejournalEntryById
    (@PathVariable ObjectId myId,
     @RequestBody journalEntry newEntry,
     @PathVariable String username){

        journalEntry old =journalEntryService.findById(myId).orElse( null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent(): old.getContent());
            journalEntryService.saveEntry(old);
            return new ResponseEntity<>(old, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    }


