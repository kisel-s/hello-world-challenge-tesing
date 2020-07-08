package framework;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.FileReader;

public class BaseEntity {

    public static final String filePath = "api.json";

    public static JsonObject getData(String memberName){
        File jsonFile;
        JsonObject jsonObject = null;
        try {
            jsonFile = new File((BaseEntity.class.getClassLoader().getResource(filePath).toURI()).getPath().toLowerCase());
            jsonObject = new Gson().fromJson(new FileReader(jsonFile), JsonObject.class).getAsJsonObject(memberName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getPath(String memberName) {
        return getData(memberName).get("url").getAsString();
    }
    public static String getBody(String memberName) {
        return getData(memberName).get("body").getAsString();
    }
    public static String getResponse(String memberName) {
        return getData(memberName).get("response").getAsString();
    }
}
