package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Apikey;

import java.util.List;

public interface ApikeyService {
    void save(Apikey apikey);

    List<Apikey> findAllApikeys();

    Apikey findApiKeyByValue(String value);
}
