package com.rozarltd.module.betfairapi.service;

public abstract class ServiceResponse<T> {

    private ServiceResponse() {
    }

    public static <T> ServiceResponse<T> error(ServiceError apiError) {
        return (ServiceResponse<T>) new Error(apiError);
    }

    public static <T> ServiceResponse<T> response(T response) {
        if(ServiceError.class.isAssignableFrom(response.getClass())) {
            throw new IllegalArgumentException();
        }

        return new Response<T>(response);
    }

    public abstract boolean isError();
    public abstract T getResponse();
    public abstract ServiceError getError();

    private static final class Response<T> extends ServiceResponse<T> {
        private T response;

        private Response(T response) {
            this.response = response;
        }

        @Override
        public boolean isError() {
            return false;
        }

        @Override
        public T getResponse() {
            return response;
        }

        @Override
        public ServiceError getError() {
            throw new IllegalStateException("not an error");
        }
    }

    private static final class Error extends ServiceResponse<Object> {
        private ServiceError error;

        private Error(ServiceError error) {
            this.error = error;
        }

        @Override
        public boolean isError() {
            return true;
        }

        @Override
        public Object getResponse() {
            throw new IllegalStateException("not a response");
        }

        @Override
        public ServiceError getError() {
            return error;
        }
    }
}
