package com.hongming_academic_map.hongming_academic_map.exception;

public class ResourceNotFoundException extends RuntimeException {
    private String resourceType;
    private String identifier;
    private Object identifierValue;

    public ResourceNotFoundException(Class<?> resourceType, String identifier, Object identifierValue) {
        super(String.format("%s not found with %s: '%s'", 
              resourceType.getSimpleName(), identifier, identifierValue));
        this.resourceType = resourceType.getSimpleName();
        this.identifier = identifier;
        this.identifierValue = identifierValue;
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    // 添加新的构造函数
    public ResourceNotFoundException(String resourceType, String identifier, Long identifierValue) {
        super(String.format("%s not found with %s: '%s'", resourceType, identifier, identifierValue));
        this.resourceType = resourceType;
        this.identifier = identifier;
        this.identifierValue = identifierValue;
    }

    // Getters for additional exception information
    public String getResourceType() {
        return resourceType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Object getIdentifierValue() {
        return identifierValue;
    }
}