package com.example.journal.entity;


import com.mongodb.lang.NonNull;
import lombok.Data;

import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection= "users")
@Data
@NoArgsConstructor
public class User {

        @Id
        private ObjectId id;
        @Indexed(unique = true)
        @NonNull
        private String username;
        @NonNull
        private String password;
        private Date date;

        @DBRef
        private List<journalEntry>journalEntries=new ArrayList<>();
        private List<String>Roles;
    }
