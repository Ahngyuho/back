package common;

public enum BaseResponseStatus {
    SUCCESS(true, 1000, "요청에 성공하였습니다.");

    private final boolean isSuccess;
    private final int code;
    private final String message;
    // Enum 생성자

    BaseResponseStatus(boolean isSuccess, int code, String message) {
        this.isSuccess = isSuccess;
        this.code = code;
        this.message = message;
    }
    // Getter 메서드

    public boolean isSuccess() {
        return isSuccess;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
