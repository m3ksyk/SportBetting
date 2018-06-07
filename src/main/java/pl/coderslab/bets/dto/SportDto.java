package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import pl.coderslab.bets.entity.League;

import javax.persistence.*;
import java.util.Set;

/**
 */

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SportDto {
    @JsonProperty("sport_id")
    private long id;
    @JsonProperty("sport_name")
    private String name;

}
