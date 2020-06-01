package io.javabrains.moviecatalogservice.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CatalogItem {
    private String name;
    private String desc;
    private int rating;
}
