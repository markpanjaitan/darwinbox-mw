
package com.darwinbox.enums;

public enum ErrorCodeEnum {

	DARWINBOX_LOCATION_ERROR(ErrorCodeEnum.ERROR_CODE+1,"Internal Error");

    public static final String ERROR_CODE="01000";

    private String code;
    private String message;

    private ErrorCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}