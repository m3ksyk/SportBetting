package pl.coderslab.bets.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;

@Service
@JsonIgnoreProperties(ignoreUnknown = true)
public class StandingsDto {
    @JsonProperty("league_name")
    private String leagueName;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("overall_league_position")
    private int position;

    public StandingsDto() {
    }

    public StandingsDto(String leagueName, String teamName, int position) {
        this.leagueName = leagueName;
        this.teamName = teamName;
        this.position = position;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "StandingsDto: " +
                "leagueName= " + leagueName +
                ", teamName= " + teamName +
                ", position= " + position;
    }
}