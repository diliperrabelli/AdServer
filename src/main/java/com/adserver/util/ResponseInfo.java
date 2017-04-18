package com.adserver.util;

public class ResponseInfo {
    private String statusCode;
    private String statusDetail;
    private Object data;
    private String errorCode;
    private String errorMessage;

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getStatusDetail() {
        return this.statusDetail;
    }

    public Object getData() {
        return this.data;
    }

    public void setStatus(String statusCode) {
        this.statusCode = statusCode;
    }

    public void setStatusDetail(String statusDetail) {
        this.statusDetail = statusDetail;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage.toString();
    }
}
