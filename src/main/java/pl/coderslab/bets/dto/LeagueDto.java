package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Team;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.Set;

@Data
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueDto {
    @JsonProperty("league_id")
    private long id;
    @JsonProperty("league_name")
    private String name;

    //sprawdzic czy sie nie zesra
    @JsonProperty("league_teams")
    private Set<Team> teams;

}

