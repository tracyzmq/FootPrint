package footprint.baixing.com.footprint.data;

/**
 * Created by zhangtracy on 15/7/25.
 */
public class ApiResult<T> {
    private int ok = 0;
    private int error;
    private String errmsg;
    private T data;

    public int getOk() {
        return ok;
    }

    public void setOk(int ok) {
        this.ok = ok;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}