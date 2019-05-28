package com.stackroute.muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Document(collection = "Muzix")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Muzix {

    @Id

    private int trackId;

    private String trackName;

    private String comments;

}
