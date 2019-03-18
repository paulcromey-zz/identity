package com.cromey.identity;

public class ErrorResponse {

  private Integer code;
  private String message;
  private String reason;
  private String detail;

  /**
   * This is the standard error response.
   * 
   * @param code the http status
   * @param reason the http reason
   * @param message a custom message
   * @param detail further detail
   */
  public ErrorResponse(Integer code, String reason, String message, String detail) {
    this.code = code;
    this.reason = reason;
    this.message = message;
    this.detail = detail;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getReason() {
    return reason;
  }

  public void setReason(String reason) {
    this.reason = reason;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

}
