package com.baykalsoft.debtrack.enums;

public enum ResponseCode {
  UNKNOWN(0),
  SUCCESS(200),
  ACCOUNT_PAUSED(201),
  ALREADY_EXISTS(300),
  WEAK_PASSWORD(301),
  UNAUTHORIZED(401),
  ALREADY_REGISTERED(402),
  WRONG_CREDENTIALS(403),
  SESSION_EXPIRED(405),
  NOT_FOUND(404),
  PERMISSION_DENIED(409),
  EXPIRED(503),
  PASSWORD_EXPIRED(504),
  SET_PASSWORD(505),
  SERVER_ERROR(500);

  private int value;

  ResponseCode(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    switch (this) {
      case SUCCESS:
        return "success";
      case ACCOUNT_PAUSED:
        return "Yor account paused. Please, contact administrator ";
      case ALREADY_EXISTS:
        return "already exists ";
      case WEAK_PASSWORD:
        return "weak password ";
      case UNAUTHORIZED:
        return "not_authorised";
      case PERMISSION_DENIED:
        return "permission_denied";
      case WRONG_CREDENTIALS:
        return "wrong_credentials";
      case ALREADY_REGISTERED:
        return "already_registered";
      case NOT_FOUND:
        return "not_found";
      case SET_PASSWORD:
        return "set password";
      case SESSION_EXPIRED:
        return "session expired";
      case EXPIRED:
        return "expired";
      case PASSWORD_EXPIRED:
        return "password expired";
      case SERVER_ERROR:
        return "server_error";
      case UNKNOWN:
      default:
        return "unknown";
    }
  }

  public int getValue() {
    return value;
  }
}