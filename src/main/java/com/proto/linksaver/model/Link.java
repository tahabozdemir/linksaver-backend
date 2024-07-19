package com.proto.linksaver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Document(collection = "link")
public class Link {
    @Id
    private String id;
    private String userId;
    private String categoryId;
    private String title;
    private String url;
    private Boolean isFavorite;

    public Link(String categoryId, String title, String url, Boolean isFavorite) {
        this.categoryId = categoryId;
        this.title = title;
        this.url = url;
        this.isFavorite = isFavorite;
    }
}
