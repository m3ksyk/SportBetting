package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Service;
import pl.coderslab.bets.entity.Bet;
import pl.coderslab.bets.entity.Sport;
import pl.coderslab.bets.entity.Team;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GameDto {
    @JsonProperty("game_id")
    private long id;
    @JsonProperty("game_sport")
    private Sport sport;
    @JsonProperty("game_start")
    private Timestamp start;
    @JsonProperty("game_end")
    private Timestamp end;
    @JsonProperty("game_home_team")
    private Team homeTeam;
    @JsonProperty("game_away_team")
    private Team awayTeam;
    @JsonProperty("game_winner")
    private Team winner;
    @JsonProperty("game_draw")
    private boolean drawn;
    @JsonProperty("game_home_team_score")
    private int homeTeamScore;
    @JsonProperty("game_away_team_score")
    private int awayTeamScore;
    @JsonProperty("game_home_team_win_odd")
    private double homeTeamWinOdd;
    @JsonProperty("game_home_team_win_or_draw_odd")
    private double homeTeamWinOrDrawOdd;
    @JsonProperty("game_home_team_win_odd")
    private double awayTeamWinOdd;
    @JsonProperty("game_away_team_win_or_draw_odd")
    private double awayTeamWinOrDrawOdd;
    @JsonProperty("game_draw_odd")
    private double DrawOdd;
    @JsonProperty("game_status")
    private String status;
}
