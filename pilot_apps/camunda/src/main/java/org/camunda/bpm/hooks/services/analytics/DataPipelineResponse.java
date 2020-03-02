package org.camunda.bpm.extension.hooks.services.analytics;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.internal.util.ExceptionUtils;

public class DataPipelineResponse {

    private String responseCode;
    private String responseMessage;
    private String exception;
    private String stackTrace;

    public String getResponseCode() {
        return responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public String getException() {
        return exception;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStatus(IDataPipeline.ResponseStatus status) {
        this.responseCode = status.name();
        this.responseMessage = status.getMessage();
    }

    public void setStatus(IDataPipeline.ResponseStatus status, Throwable ex) {
        setStatus(status);
        this.stackTrace = ExceptionUtils.exceptionStackTraceAsString(ex);
        if(StringUtils.isNotEmpty(ex.getMessage())) {
            this.exception = ex.getMessage().length() < 4000 ? ex.getMessage() : StringUtils.substring(ex.getMessage(), 0,  4000);
        } else {
            this.exception = StringUtils.substring(getStackTrace(), 0,  4000);
        }
    }
}
