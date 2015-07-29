package footprint.baixing.com.footprint.data;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class ApiResult<T> {
    private int ok = 0;
    private String error;
    private T data;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}