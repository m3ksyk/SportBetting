package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.League;
import pl.coderslab.bets.entity.Team;

import java.util.Set;

@Data
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class SportDto {
    @JsonProperty("sport_id")
    private long id;
    @JsonProperty("sport_name")
    private String name;
    @JsonProperty("sport_leagues")
    private Set<League> leagues;
    @JsonProperty("sport_teams")
    private Set<Team> teams;

}
