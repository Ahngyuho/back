package user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginResDto {
    boolean result;
    private String token;

    public LoginResDto() {
    }

    public LoginResDto(boolean result, String token) {
        this.result = result;
        this.token = token;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
