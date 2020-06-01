package io.javabrains.moviecatalogservice.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.UserRating;
import services.MovieInfo;
import services.UserRatingsInfo;

@RestController
@RequestMapping("/catalog")
@ComponentScan("services")
public class CatalogResource {

//    @Autowired
//    private RestTemplate restTemplate;

//    @Autowired
//    WebClient.Builder webClientBuilder;
    
    @Autowired
    private UserRatingsInfo ratingInfo;
    
    @Autowired
    private MovieInfo movieInfo;

    @RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

    	UserRating userRating = ratingInfo.getUserRating(userId);
//		UserRating userRating = restTemplate.getForObject("http://localhost:8083/ratingsdata/user/" + userId,
//				UserRating.class);

		return userRating.getRatings().stream()
				.map(x -> movieInfo.getCatalogItem(x))
				.collect(Collectors.toList());
	}

//    @HystrixCommand(fallbackMethod = "getFallBackUserRating")
//	public UserRating getUserRating(String userId) {
//    	return restTemplate.getForObject("http://ratings-data-service/ratingsdata/user/" + userId, UserRating.class);
//	}
	
//    @HystrixCommand(fallbackMethod = "getFallBackCatalogItem")
//	public CatalogItem getCatalogItem(Rating rating) {
//          Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
////			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
//		
//          return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
//	}

//	public UserRating getFallBackUserRating(String userId) {
//		return new UserRating(userId, Arrays.asList(new Rating("0", 0)));
//	}

//	public CatalogItem getFallBackCatalogItem(Rating rating) {
//		return new CatalogItem("Movie name not found", "", rating.getRating());
//	}
}

/*
Alternative WebClient way
Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/"+ rating.getMovieId())
.retrieve().bodyToMono(Movie.class).block();
*/