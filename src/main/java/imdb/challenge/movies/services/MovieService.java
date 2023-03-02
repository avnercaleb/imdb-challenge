package imdb.challenge.movies.services;

import imdb.challenge.movies.models.Movie;
import imdb.challenge.movies.clients.ItemsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private ItemsClient itemsClient;

    public List<Movie> getAll() {
        return itemsClient.findAll().getItems();
    }

    public Movie getOne(String id) {
        Optional<Movie> movie = itemsClient.findAll().getItems()
                .stream().filter(x -> x.getId().equals(id)).findFirst();

        return movie.get();
    }
}