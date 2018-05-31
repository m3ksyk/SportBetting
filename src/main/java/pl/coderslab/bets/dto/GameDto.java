package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.Date;
@Data
@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {
    @JsonProperty("match_id")
    private long id;
    @JsonProperty("sport_name")
    private String sportName;
//    @JsonProperty("league_name")
//    private String leagueName;
    @JsonProperty("match_start")
    private String start;
    @JsonProperty("match_end")
    private String end;
    @JsonProperty("match_hometeam_name")
    private String homeTeamName;
    @JsonProperty("match_hometeam_score")
    private int homeTeamScore;
    @JsonProperty("match_awayteam_name")
    private String awayTeamName;
    @JsonProperty("match_awayteam_score")
    private int awayTeamScore;

    @JsonProperty("match_hometeam_odd")
    private int homeTeamOdd;

    @JsonProperty("match_awayteam_odd")
    private int awayTeamOdd;

    @JsonProperty("match_status")
    private String status;

}