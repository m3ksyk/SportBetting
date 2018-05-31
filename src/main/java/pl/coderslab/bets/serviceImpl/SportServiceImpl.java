package pl.coderslab.bets.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.repository.SportRepository;
import pl.coderslab.bets.service.SportService;

import java.util.List;

@Service
public class SportServiceImpl implements SportService {

    @Autowired
    SportRepository sportRepository;
    @Override
    public Sport save(Sport sport) {
        return sportRepository.save(sport);
    }

    @Override
    public Sport findSportByName(String name) {
        return sportRepository.findByNameEquals(name);
    }

    @Override
    public List<Sport> findAllSports() {
        return sportRepository.findAll();
    }
}
