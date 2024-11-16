package org.example.jwt.Model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "assignment")
public class Assignment {

    @Id
    private String userId;
    private String task;
    private String admin;
}