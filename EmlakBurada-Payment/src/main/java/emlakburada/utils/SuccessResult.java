package emlakburada.utils;

public class SuccessResult {

    private boolean success;
    private String message;

    public SuccessResult(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}
