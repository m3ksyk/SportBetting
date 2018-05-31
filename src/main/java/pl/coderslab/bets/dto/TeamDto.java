package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Game;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Sport;

import java.util.Set;

@Data
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamDto {
    @JsonProperty("team_id")
    private long id;

    @JsonProperty("team_name")
    String teamName;

    @JsonProperty("team_league")
    private League league;

    @JsonProperty("team_sport")
    private Sport sport;

    @JsonProperty("team_games")
    private Set<Game> games;

    @JsonProperty("team_win")
    private int gamesWon;
    @JsonProperty("team_lose")
    private int gamesLost;

    @JsonProperty("team_draw")
    private int draws;

    @JsonProperty("team_standing")
    private int tableStanding;
}
