package com.odedoyinakindele.customerdatabaseapi.payloads;

public class ApiResponse {

    private String message;
    private Object data;
    private int responseCode;

    public static class ResponseBuilder {
        private String message;
        private Object data;
        private int responseCode;

        public ResponseBuilder() {
        }

        public ResponseBuilder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ResponseBuilder setData(Object data) {
            this.data = data;
            return this;
        }



        public ResponseBuilder setResponseCode(int responseCode) {
            this.responseCode = responseCode;
            return this;
        }

        public ResponseBuilder setLogId(String logId) {
            return this;
        }

        public ApiResponse build() {
            return new ApiResponse(this);
        }
    }


    private ApiResponse(final ResponseBuilder builder) {
        this.message = builder.message;
        this.data = builder.data;
        this.responseCode = builder.responseCode;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }
}