package imdb.challenge.movies.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Items {

    List<Movie> items = new ArrayList<>();

    public List<Movie> getItems() {
        return items;
    }
    public void setItems(List<Movie> items) {
        this.items = items;
    }
}
