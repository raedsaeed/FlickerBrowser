package com.example.raed.flickrbrowser.objects;

/**
 * Created by Raed Saeed on 18/09/2020.
 */
public class Result<T> {
    private Status status;
    private T data;
    private Throwable error;

    public Result() {
        status = Status.LOADING;
    }

    private Result(Status status, T data, Throwable error) {
        this.status = status;
        this.data = data;
        this.error = error;
    }

    public static <T> Result<T> stateLoading() {
        return new Result<>(Status.LOADING, null, null);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static <T> Result<T> stateSuccess(T data) {
        return new Result<>(Status.SUCCESS, data, null);
    }

    @SuppressWarnings("UnusedReturnValue")
    public static <T> Result<T> stateError(Throwable throwable) {
        return new Result<>(Status.ERROR, null, throwable);
    }

    public static <T> Result<T> stateLoadingWithLocal(T data) {
        return new Result<>(Status.LOADING, data, null);
    }

    public Status getStatus() {
        return status;
    }

    public Result<T> loading() {
        this.status = Status.LOADING;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result<T> setData(T data) {
        this.data = data;
        status = Status.SUCCESS;
        return this;
    }

    public Throwable getError() {
        return error;
    }

    public Result<T> setError(Throwable error) {
        status = Status.ERROR;
        this.error = error;
        return this;
    }

    public enum Status {
        LOADING,
        SUCCESS,
        ERROR
    }
}
