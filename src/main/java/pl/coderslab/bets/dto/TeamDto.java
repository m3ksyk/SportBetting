package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.User;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

/**
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto{
    @JsonProperty("team_id")
    private long id;
    @JsonProperty("team_name")
    private String name;
    @JsonProperty("team_league")
    private League league;
    @JsonProperty("team_sport")
    private Sport sport;
    @JsonProperty("team_games_won")
    private int gamesWon;
    @JsonProperty("team_games_lost")
    private int gamesLost;
    @JsonProperty("team_draws")
    private int draws;
    @JsonProperty("team_goals_scored")
    private int goalsScored;
    @JsonProperty("team_goals_lost")
    private int goalsLost;
    @JsonProperty("team_table_standing")
    private int tableStanding;

}

