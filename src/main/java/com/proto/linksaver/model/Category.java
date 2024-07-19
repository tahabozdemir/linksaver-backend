package com.proto.linksaver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "category")
public class Category {
    @Id
    private String id;
    private String title;
    private String emoji;
    private ArrayList<String> links;

    public Category(String title, String emoji) {
        this.title = title;
        this.emoji = emoji;
    }

    public ArrayList<String> getLinks() {
        if (links == null) {
            links = new ArrayList<>();
        }
        return links;
    }
}
