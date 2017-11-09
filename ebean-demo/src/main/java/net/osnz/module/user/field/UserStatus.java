package net.osnz.module.user.field;

import io.ebean.annotation.EnumValue;

public enum UserStatus {
	@EnumValue("N")
	NEW,
	@EnumValue("I")
	INACTIVATED,
	@EnumValue("A")
	ACTIVATED,
	@EnumValue("B")
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
