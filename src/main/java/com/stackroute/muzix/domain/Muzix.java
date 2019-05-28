package com.stackroute.muzix.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Document(collection = "Muzix")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Component
public class Muzix {

    @Id
    //@Value("${trackId:7}")
    private int trackId;
    //@Value("${trackName:polio}")
    private String trackName;
    //@Value("${com}")
    private String comments;

}
