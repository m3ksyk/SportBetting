package pl.coderslab.bets;

public class WysrywaczJsonow {

//    private ArrayList<JSONObject> todayGames = new ArrayList<>();
//    private ArrayList<JSONObject> leagues = new ArrayList<>();
//    private ArrayList<JSONObject> currentGames = new ArrayList<>();
//    private ArrayList<JSONObject> sports = new ArrayList<>();
//    private ArrayList<JSONObject> users = new ArrayList<>();

//
//    @Scheduled(cron = "0 0/5 * 1/1 * ?")
//    public void regenerateCurrent() throws JSONException {
//        scheduled faker = new scheduled();
//        currentGames.clear();
//        for (int i = 0; i < 10; i++) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("firstTeam", faker.team().name());
//            jsonObject.put("firstPoints", faker.number().randomDigitNotZero());
//            jsonObject.put("secondTeam", faker.team().name());
//            jsonObject.put("secondPoints", faker.number().randomDigitNotZero());
//            jsonObject.put("sport", faker.team().sport());
//            jsonObject.put("GameTime", faker.date().future(90, TimeUnit.MINUTES));
//            currentGames.add(jsonObject);
//        }
//    }

//    public void generateUsers() throws JSONException{
//        scheduled faker = new scheduled();
//        users.clear();
//        for (int i = 0; i < 10; i++) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("id", faker.number().randomNumber(8, true));
//            jsonObject.put("userName", faker.name().firstName() + faker.name().lastName());
//            jsonObject.put("password", faker.crypto().sha256());
//            jsonObject.put("email" , faker.internet().emailAddress());
//
//            users.add(jsonObject);
//        }
//    }

//    public ArrayList<JSONObject> getTodayGames() {
//        return todayGames;
//    }
//
//    public ArrayList<JSONObject> getCurrentGames() {
//        return currentGames;
//    }
//
//    public ArrayList<JSONObject> getLeagues() {
//        return leagues;
//    }
//
//    public ArrayList<JSONObject> getUsers() {
//        return users;
//    }
//    public ArrayList<JSONObject> getSports() {
//        return sports;
//    }
}
