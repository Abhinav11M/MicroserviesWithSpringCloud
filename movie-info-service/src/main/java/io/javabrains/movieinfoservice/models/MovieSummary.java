package io.javabrains.movieinfoservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieSummary {

    private String id;
    private String original_title;
    private String overview;
    
}
