package guru.springframework.spring6resttemplate.client;

import guru.springframework.spring6resttemplate.model.BeerDTO;
import guru.springframework.spring6resttemplate.model.BeerDTOPageImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@RequiredArgsConstructor
@Service
public class BeerClientImpl implements BeerClient {
    private final RestTemplateBuilder restTemplateBuilder;
    private static final String GET_BEER_PATH = "/api/v1/beer";

    @Override
    public Page<BeerDTO> listBeers() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        ResponseEntity<BeerDTOPageImpl> pageResponse =
                restTemplate.getForEntity(uriBuilder.toUriString(), BeerDTOPageImpl.class);

        return pageResponse.getBody();
    }

    @Override
    public Page<BeerDTO> listBeersByName(String beerName) {
        return listBeers(beerName, null, null, null, null);
    }

    @Override
    public Page<BeerDTO> listBeersByStyle(String beerStyle) {
        return listBeers(null, beerStyle, null, null, null);
    }

    @Override
    public Page<BeerDTO> listBeers(String beerName, String beerStyle, Boolean showInventory, Integer pageNumber, Integer pageSize
    ) {
        RestTemplate restTemplate = restTemplateBuilder.build();

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromPath(GET_BEER_PATH);

        if (beerName != null) {
            uriBuilder.queryParam("beerName", beerName);
        }
        if (beerStyle != null) {
            uriBuilder.queryParam("beerStyle", beerStyle);
        }
        if (showInventory != null) {
            uriBuilder.queryParam("showInventory", showInventory);
        }
        if (pageNumber != null) {
            uriBuilder.queryParam("pageNumber", pageNumber);
        }
        if (pageSize != null) {
            uriBuilder.queryParam("pageSize", pageSize);
        }

        ResponseEntity<BeerDTOPageImpl> pageResponse =
                restTemplate.getForEntity(uriBuilder.toUriString(), BeerDTOPageImpl.class);

        return pageResponse.getBody();
    }
}
