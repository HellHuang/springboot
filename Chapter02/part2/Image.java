package com.example.images.images.Chapter02;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Image {
    public String id;
    public String name;
    public Image(String id,String name)
    {
        this.id =id;
        this.name =name;
    }
}
