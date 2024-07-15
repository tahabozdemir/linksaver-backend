package com.proto.linksaver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String email;
    private ArrayList<String> categories;

    public User(String id, String email) {
        this.id = id;
        this.email = email;
    }

    public ArrayList<String> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }
}
