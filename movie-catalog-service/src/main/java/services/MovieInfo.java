package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import io.javabrains.moviecatalogservice.models.CatalogItem;
import io.javabrains.moviecatalogservice.models.Movie;
import io.javabrains.moviecatalogservice.models.Rating;

@Service
public class MovieInfo {

	@Autowired
	private RestTemplate restTemplate;
	
	// BulkHead pattern : allocate different thread pool for different resources/apis
	@HystrixCommand(fallbackMethod = "getFallBackCatalogItem", 
			threadPoolKey = "movieInfoPool",
			threadPoolProperties = {
				@HystrixProperty(name = "coreSize", value = "20"),
				@HystrixProperty(name = "maxQueueSize", value = "10")
			}
		)
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);

		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}

	public CatalogItem getFallBackCatalogItem(Rating rating) {
		return new CatalogItem("Movie name not found", "", rating.getRating());
	}
	
}