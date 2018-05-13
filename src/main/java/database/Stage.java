package database;

import java.util.ArrayList;
import java.util.TreeMap;

public class Stage {
    public static String DEFAULT_STAGE = "Received";
    public static TreeMap<String,Stage> stages;
    private String StageStr;

    public Stage(String stageStr) {
        StageStr = stageStr;
    }

    public Stage() {
        StageStr = DEFAULT_STAGE;
    }
    public static void loadStages() {
        stages = new TreeMap<>();
        ArrayList<String> strings = Database.getInstance().getStages();
        for (String str : strings) {
            stages.put(str, new Stage(str));
        }
    }

    public String getStageStr() {
        return StageStr;
    }
}
