package helloworldchallenge.entity;

public enum CustomHeader {
    X_SESSION_ID("X-Session-Id");

    public String value;

    CustomHeader(String value) {
        this.value = value;
    }

    public String getValue(){
        return value;
    }
}
