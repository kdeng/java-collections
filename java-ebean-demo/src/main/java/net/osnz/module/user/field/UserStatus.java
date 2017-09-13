package net.osnz.module.user.field;

import com.avaje.ebean.annotation.EnumMapping;

@EnumMapping(nameValuePairs="NEW=N, INACTIVATED=I, ACTIVATED=A, BLOCKED=B")
public enum UserStatus {
  NEW,
  INACTIVATED,
  ACTIVATED,
  BLOCKED;

  @SuppressWarnings("unused")
  public boolean isNew() {
    return this == NEW;
  }

  @SuppressWarnings("unused")
  public boolean isActivated() {
    return this == ACTIVATED;
  }

  @SuppressWarnings("unused")
  public boolean isInactivated() {
    return this == INACTIVATED;
  }

  @SuppressWarnings("unused")
  public boolean isBlocked() {
    return this == BLOCKED;
  }


}
