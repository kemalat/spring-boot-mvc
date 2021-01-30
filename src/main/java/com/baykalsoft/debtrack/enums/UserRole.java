package com.baykalsoft.debtrack.enums;


public enum UserRole {

  ADMIN(1),
  ACTUATOR(2),
  USER(3);

  private int value;

  UserRole(int value) {
    this.value = value;
  }

  }
