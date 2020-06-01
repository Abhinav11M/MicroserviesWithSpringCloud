package io.javabrains.movieinfoservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Movie {
    private String movieId;
    private String name;
    private String description;
}
