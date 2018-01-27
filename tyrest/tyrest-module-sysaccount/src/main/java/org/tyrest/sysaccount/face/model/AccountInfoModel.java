package org.tyrest.sysaccount.face.model;

import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * Created by Administrator on 2016/12/5.
 */
public class AccountInfoModel  extends BaseModel{


    private String agencyCode;
    private Long userId;
    private String accountNo;
    private int balance;
    private String accountType;
    private String accountStatus;
    private String paymentPassword;
    private int cumulativeBalance;
    private Long updateVersion;
    private Date createTime;

    public String getAgencyCode() {
        return agencyCode;
    }

    public void setAgencyCode(String agencyCode) {
        this.agencyCode = agencyCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getPaymentPassword() {
        return paymentPassword;
    }

    public void setPaymentPassword(String paymentPassword) {
        this.paymentPassword = paymentPassword;
    }

    public int getCumulativeBalance() {
        return cumulativeBalance;
    }

    public void setCumulativeBalance(int cumulativeBalance) {
        this.cumulativeBalance = cumulativeBalance;
    }

    public Long getUpdateVersion() {
        return updateVersion;
    }

    public void setUpdateVersion(Long updateVersion) {
        this.updateVersion = updateVersion;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
