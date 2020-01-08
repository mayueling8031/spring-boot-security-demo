package com.sans.common.exception;

/**
 * FEBS系统内部异常
 *
 * @author MrBird
 */
public class BlogException extends RuntimeException  {

    private static final long serialVersionUID = -994962710559017255L;

    public BlogException(String message) {
        super(message);
    }
}
