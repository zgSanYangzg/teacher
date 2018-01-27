package org.tyrest.human.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by ttsw on 2017/1/11.
 */
@Entity
@Table(name = "human_social_user")
public class SocialUser extends BaseEntity {
    private String userNo;
    private String userName;
    private Integer userSex;
    private String userIdcard;
    private String userPhone;
    private Date userBirthday;
    private String userNatinoal;

    @Column(name = "USER_NO", unique = false, nullable = true)
    public String getUserNo() { return userNo; }
    public void setUserNo(String userNo) { this.userNo = userNo; }

    @Column(name = "USER_NAME", unique = false, nullable = true)
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }

    @Column(name = "USER_SEX", unique = false, nullable = true)
    public Integer getUserSex() { return userSex; }
    public void setUserSex(Integer userSex) { this.userSex = userSex; }

    @Column(name = "USER_IDCARD", unique = false, nullable = true)
    public String getUserIdcard() { return userIdcard; }
    public void setUserIdcard(String userIdcard) { this.userIdcard = userIdcard; }

    @Column(name = "USER_PHONE", unique = false, nullable = true)
    public String getUserPhone() { return userPhone; }
    public void setUserPhone(String userPhone) { this.userPhone = userPhone; }

    @Column(name = "USER_BIRTHDAY", unique = false, nullable = true)
    public Date getUserBirthday() { return userBirthday; }
    public void setUserBirthday(Date userBirthday) { this.userBirthday = userBirthday; }

    @Column(name = "USER_NATINOAL", unique = false, nullable = true)
    public String getUserNatinoal() { return userNatinoal; }
    public void setUserNatinoal(String userNatinoal) { this.userNatinoal = userNatinoal; }

}
