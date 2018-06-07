package pl.coderslab.bets.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Sport;

import javax.persistence.*;
import java.util.Set;

/**
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class LeagueDto {
    @JsonProperty("league_id")
    private long id;
    @JsonProperty("league_name")
    private String name;
    @JsonProperty("league_sport")
    private Sport sport;

    public String toString() {
        return name;
    }
}
