package net.osnz.module.user.field;

import com.avaje.ebean.annotation.EnumMapping;

@EnumMapping(nameValuePairs="INACTIVATED=0, ACTIVATED=1")
public enum ModelStatus {

  INACTIVATED,
  ACTIVATED;

  @SuppressWarnings("unused")
  public boolean isActivated() {
    return this == ACTIVATED;
  }

  @SuppressWarnings("unused")
  public boolean isInactivated() {
    return this == INACTIVATED;
  }

}
