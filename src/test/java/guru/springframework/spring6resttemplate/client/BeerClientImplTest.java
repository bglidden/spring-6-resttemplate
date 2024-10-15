package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerStyle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BeerClientImplTest {

    @Autowired
    BeerClient beerClient;

    @Test
    void listBeersNoName() {
        beerClient.listBeers();
    }

    @Test
    void listBeersWithName() {
        beerClient.listBeers("ALE", null, null, null, null);
    }

    @Test
    void listBeersWithStyle() {
        beerClient.listBeers(null, BeerStyle.STOUT.toString(), null, null, null);
    }
}