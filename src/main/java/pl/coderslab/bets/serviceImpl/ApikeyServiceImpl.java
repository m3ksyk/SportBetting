package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Apikey;
import pl.coderslab.bets.repository.ApikeyRepository;
import pl.coderslab.bets.service.ApikeyService;

import java.util.List;

@Service
public class ApikeyServiceImpl implements ApikeyService {

    @Autowired
    ApikeyRepository apikeyRepository;

    @Override
    public void save(Apikey apikey) {
        apikeyRepository.save(apikey);
    }

    @Override
    public List<Apikey> findAllApikeys() {
        return apikeyRepository.findAll();
    }

    @Override
    public Apikey findApiKeyByValue(String value) {
        return apikeyRepository.findByValueEquals(value);
    }
}
