package com.darwinbox.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    private String message;
    private String last_update;
    private Object data;
    private Object extras;
    private Long total_result;
    private Long total_page;

    public Response() {}

    public Response(Object data) {
        this.data = data;
    }

    public Response(Long total_result, Object data) {
        this.total_result = total_result;
        this.data = data;
    }

    public Response(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public Response(Long total_result, Long total_page, Object data) {
        this.total_result = total_result;
        this.total_page = total_page;
        this.data = data;
    }

    public Response(String message, Long total_result, Long total_page, Object data) {
        this.message = message;
        this.total_result = total_result;
        this.total_page = total_page;
        this.data = data;
    }
    
    public Response(String last_update,String message, Long total_result, Long total_page, Object data) {
    	this.last_update=last_update;
        this.message = message;
        this.total_result = total_result;
        this.total_page = total_page;
        this.data = data;
    }

    public Response(Object data, Object extras, Long total_result, Long total_page) {
        this.data = data;
        this.extras = extras;
        this.total_result = total_result;
        this.total_page = total_page;
    }

    public Response(Object data, Object extras) {
        this.data = data;
        this.extras = extras;
    }
}
