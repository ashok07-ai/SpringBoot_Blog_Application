package com.blog_application.app.BlogApplication.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String _resourceName, String _fieldName, long _fieldValue) {
        super(String.format("%s not found with %s: %d", _resourceName, _fieldName, _fieldValue));
        this.resourceName = _resourceName;
        this.fieldName = _fieldName;
        this.fieldValue = _fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String _resourceName) {
        this.resourceName = _resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String _fieldName) {
        this.fieldName = _fieldName;
    }

    public long getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(long _fieldValue) {
        this.fieldValue = _fieldValue;
    }
}
