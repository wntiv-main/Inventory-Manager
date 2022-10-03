package net.wntiv.inventorymanager;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unused")
public class Promise<T> {
    private enum State {
        INCOMPLETE,
        SUCCESS,
        FAIL
    }
    private List<Callback<T>> successListeners = new ArrayList<>();
    private List<Callback<T>> failureListeners = new ArrayList<>();
    private T result;
    private State state = State.INCOMPLETE;

    public Promise(Function<T> func) {
        func.call(this::resolve, this::reject);
    }

    private void resolve(T response) {
        state = State.SUCCESS;
        result = response;
        successListeners.forEach(tCallback -> tCallback.call(response));
        successListeners = null;
        failureListeners = null;
    }

    private void reject(T response) {
        state = State.FAIL;
        result = response;
        failureListeners.forEach(tCallback -> tCallback.call(response));
        successListeners = null;
        failureListeners = null;
    }

    public Promise<T> onSuccess(Callback<T> callback) {
        switch (state) {
            case SUCCESS:
                callback.call(result);
                break;
            case INCOMPLETE:
                successListeners.add(callback);
                break;
            default:
        }
        return this;
    }

    public Promise<T> onFailure(Callback<T> callback) {
        switch (state) {
            case SUCCESS:
                callback.call(result);
                break;
            case INCOMPLETE:
                successListeners.add(callback);
                break;
            default:
        }
        return this;
    }

    public Promise<T> then(Callback<T> res) {return onSuccess(res);}
    public Promise<T> then(Callback<T> res, Callback<T> rej) {
        switch (state) {
            case SUCCESS:
                res.call(result);
                break;
            case FAIL:
                rej.call(result);
                break;
            case INCOMPLETE:
                successListeners.add(res);
                failureListeners.add(rej);
                break;
            default:
        }
        return this;
    }

    public Promise<T> onFinally(Callback<T> callback) {
        switch (state) {
            case SUCCESS:
            case FAIL:
                callback.call(result);
                break;
            case INCOMPLETE:
                successListeners.add(callback);
                failureListeners.add(callback);
                break;
            default:
        }
        return this;
    }

    @FunctionalInterface
    public interface Function<T> {
        void call(Callback<T> res, Callback<T> rej);
    }

    @FunctionalInterface
    public interface Callback<T> {
        void call(T result);
    }
}
