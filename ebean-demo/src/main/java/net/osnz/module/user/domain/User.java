package net.osnz.module.user.domain;

import net.osnz.module.user.field.UserRole;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "default_cw_member", schema = "OSNZ")
public class User extends BaseEntity {

	@Column(name = "mobile", nullable = false, unique = true, length = 20)
	private String mobile;

	@Column(name = "email", nullable = true, length = 50)
	private String email = "";

	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "date_verified_email", nullable = true)
	private Date dateVerifiedEmail;

	@Column(name = "verified_email", nullable = false, length = 1)
	private boolean verifiedEmail;

	@Column(name = "role")
	private UserRole role = UserRole.MEMBER;

	@Column(name = "CREDIT_POINT", nullable = false)
	private int creditPoint = 0;

	@Column(name = "pending_Expense", nullable = false, scale = 2, precision = 12)
	private BigDecimal pendingExpense = new BigDecimal("0");

	@Column(name = "total_expense", nullable = false, scale = 2, precision = 12)
	private BigDecimal totalExpense = new BigDecimal("0");


	@SuppressWarnings("unused")
	public User() {

	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public UserRole getRole() {
		return this.role;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setDateVerifiedEmail(Date dateVerifiedEmail) {
		this.dateVerifiedEmail = dateVerifiedEmail;
	}

	public Date getDateVerifiedEmail() {
		return this.dateVerifiedEmail;
	}

	public void setVerifiedEmail(boolean verifiedEmail) {
		this.verifiedEmail = verifiedEmail;
	}

	public boolean isVerifiedEmail() {
		return this.verifiedEmail;
	}

	public void setCreditPoint(int creditPoint) {
		this.creditPoint = creditPoint;
	}

	public int getCreditPoint() {
		return this.creditPoint;
	}

	public void setPendingExpense(BigDecimal pendingExpense) {
		this.pendingExpense = pendingExpense;
	}

	public BigDecimal getPendingExpense() {
		return this.pendingExpense;
	}

	public void setTotalExpense(BigDecimal totalExpense) {
		this.totalExpense = totalExpense;
	}

	public BigDecimal getTotalExpense() {
		return this.totalExpense;
	}

	@Override
	public String toString() {
		return this.id + ". " + " (mobile=" + this.mobile + ", email=" + this.email + ", role=" + this.role + ") - " + this.getStatus();
	}


}
