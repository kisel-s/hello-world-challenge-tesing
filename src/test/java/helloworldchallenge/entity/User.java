package helloworldchallenge.entity;

import com.google.gson.Gson;
import lombok.*;

@Getter
@Setter
@Builder(toBuilder = true)
@ToString
@EqualsAndHashCode
public class User {

    private String username;
    private String fullName;
    private String sessionId;

    public String toJson() {
        return new Gson().toJson(this);
    }
}
