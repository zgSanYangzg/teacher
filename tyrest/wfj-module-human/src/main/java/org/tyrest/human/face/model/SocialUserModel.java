package org.tyrest.human.face.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tyrest.core.mysql.BaseModel;

import java.util.Date;

/**
 * Created by ttsw on 2017/1/11.
 */
@Document
public class SocialUserModel extends BaseModel
{
    @Id
    private String userNo;
    private String userName;
    private Integer userSex;
    private String userIdcard;
    private String userPhone;
    private Date userBirthday;
    private String userNatinoal;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getUserSex() {
        return userSex;
    }

    public void setUserSex(Integer userSex) {
        this.userSex = userSex;
    }

    public String getUserIdcard() {
        return userIdcard;
    }

    public void setUserIdcard(String userIdcard) {
        this.userIdcard = userIdcard;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getUserBirthday() { return userBirthday;  }

    public void setUserBirthday(Date userBirthday) {
        this.userBirthday = userBirthday;
    }

    public String getUserNatinoal() {
        return userNatinoal;
    }

    public void setUserNatinoal(String userNatinoal) {
        this.userNatinoal = userNatinoal;
    }
}
