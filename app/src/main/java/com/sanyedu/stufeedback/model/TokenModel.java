package com.sanyedu.stufeedback.model;

public class TokenModel extends BaseModel {
   private String code;
   private String randomKey;
   private String token;

    public TokenModel(String code, String randomKey, String token) {
        this.code = code;
        this.randomKey = randomKey;
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRandomKey() {
        return randomKey;
    }

    public void setRandomKey(String randomKey) {
        this.randomKey = randomKey;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenModel{" +
                "code='" + code + '\'' +
                ", randomKey='" + randomKey + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
