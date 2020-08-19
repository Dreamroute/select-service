package com.github.dreamroute.util;

/**
 * @author w.dehai
 */
public class SelectServiceException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 4917807215132180524L;
    
    public SelectServiceException() {
        super();
    }
    public SelectServiceException(String message) {
        super(message);
    }
    public SelectServiceException(String message, Throwable cause) {
        super(message, cause);
    }
    public SelectServiceException(Throwable cause) {
        super(cause);
    }
}
