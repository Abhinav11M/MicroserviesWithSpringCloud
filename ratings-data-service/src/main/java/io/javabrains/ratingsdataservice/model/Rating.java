package io.javabrains.ratingsdataservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Rating {

    private String movieId;
    private int rating;

}
