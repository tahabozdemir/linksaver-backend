package com.proto.linksaver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "link")
public class Link {
    @Id
    private String id;
    private String title;
    private String url;
    private Boolean isFavorite;
    private Boolean isDelete;

    public Link(String title, String url, Boolean isFavorite, Boolean isDelete) {
        this.title = title;
        this.url = url;
        this.isFavorite = isFavorite;
        this.isDelete = isDelete;
    }
}
