package imdb.challenge.movies.clients;

import imdb.challenge.movies.models.Items;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "itemsClient", url = "https://imdb-api.com/en/API/Top250Movies/k_mg8yxqlz")
public interface ItemsClient {

    @GetMapping
    Items findAll();
}
