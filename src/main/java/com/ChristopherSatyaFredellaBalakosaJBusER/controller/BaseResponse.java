package com.ChristopherSatyaFredellaBalakosaJBusER.controller;
/**
 * BaseResponse class
 * Represents a generic response structure used to convey the outcome of operations in the application. It contains three main components: success status, a message, and
 * an optional payload.
 * @author Christopher Satya
 */
public class BaseResponse<T> {
    public boolean success; //A boolean flag indicating the success status of the operation.
    public String message; //A string message providing additional information about the operation's result.
    public T payload; //An optional payload data associated with the response, which can vary in type.
    /**
     * Constructs a `BaseResponse` object with the specified success status, message, and payload.
     * @param success Indicates whether the operation was successful (true) or not (false).
     * @param message Provides a human-readable message describing the result or any error messages.
     * @param payload An optional data payload associated with the response, which can vary in type.
     */
    public BaseResponse(boolean success, String message, T payload) {
        this.success = success;
        this.message = message;
        this.payload = payload;
    }
}
