package pl.coderslab.bets.service;

import pl.coderslab.bets.entity.Sport;

import java.util.List;

public interface SportService {
    Sport save(Sport sport);

    Sport findSportByName(String name);

    List<Sport> findAllSports();
}
