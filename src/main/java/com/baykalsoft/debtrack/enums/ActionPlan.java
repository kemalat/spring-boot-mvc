package com.baykalsoft.debtrack.enums;

public enum ActionPlan {
  ACTIVATE_SUSPEND_OR_CLOSED(1),
  CLOSE_DEBT_LESS_50(2);

  private int value;

  ActionPlan(int value) {
    this.value = value;
  }

  @Override
  public String toString() {
    switch (this) {
      case ACTIVATE_SUSPEND_OR_CLOSED:
        return "ACTIVATE_SUSPEND_OR_CLOSED";
      case CLOSE_DEBT_LESS_50:
        return "CLOSE_DEBT_LESS_50";

      default:
        return "unknown";
    }
  }

  public int getValue() {
    return value;
  }
}