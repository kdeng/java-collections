package net.osnz.module.user.field;

import io.ebean.annotation.EnumValue;

public enum ModelStatus {

	@EnumValue("0") INACTIVATED,
	@EnumValue("1") ACTIVATED;

	@SuppressWarnings("unused")
	public boolean isActivated() {
		return this == ACTIVATED;
	}

	@SuppressWarnings("unused")
	public boolean isInactivated() {
		return this == INACTIVATED;
	}

}
