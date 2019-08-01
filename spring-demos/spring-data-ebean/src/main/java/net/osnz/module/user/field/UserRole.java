package net.osnz.module.user.field;

import io.ebean.annotation.EnumValue;

public enum UserRole {

  @EnumValue("M")
  MEMBER("Member"),

  @EnumValue("E")
  EDITOR("Editor"),

  @EnumValue("P")
  PUBLISHER("Publisher"),

  @EnumValue("A")
  ADMIN("Admin");

  private final String title;

  private UserRole(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return this.title;
  }


}
