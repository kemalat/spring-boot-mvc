package com.baykalsoft.debtrack.enums;


public enum UserStatus {

  PENDING(0),
  VERIFY(1),
  ACTIVE(2),
  DISABLED(3),
  DELETED(4);

  private int value;

  UserStatus(int value) {
    this.value = value;
  }

  }
