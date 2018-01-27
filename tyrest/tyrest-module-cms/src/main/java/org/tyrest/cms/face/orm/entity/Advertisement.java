package org.tyrest.cms.face.orm.entity;

import org.tyrest.core.mysql.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * <pre>
 *
 *  Lexing
 *  File: Advertisement.java
 *
 *  Lexing, Inc.
 *  Copyright (C): 2015
 *
 *  Description:
 *  TODO
 *
 *  Notes:
 *  $Id: Advertisement.java 31101200-9 2014-10-14 16:43:51Z Lexing\yangbochao $
 *
 *  Revision History
 *  &lt;Date&gt;,			&lt;Who&gt;,			&lt;What&gt;
 *  2015年10月26日		yangbochao		Initial.
 *
 * </pre>
 */
@Entity
@Table(name = "wbsj_advertisement")
public class Advertisement extends BaseEntity {


    private static final long serialVersionUID = 2327161655726693168L;


    private String imgUrl;

    private String callUrl;

    private String newWindowTitle;

    private String fileName;

    private String dispalyAlias;

    private String enable;

    private Integer adType;

    private Timestamp availableStart;

    private Timestamp availableEnd;

    private String targetType;

    private Long targetSequenceNbr;


    // Constructors

    /**
     * default constructor
     */
    public Advertisement() {
    }


    @Column(name = "IMG_URL", nullable = false)
    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Column(name = "CALL_URL")
    public String getCallUrl() {
        return this.callUrl;
    }

    public void setCallUrl(String callUrl) {
        this.callUrl = callUrl;
    }

    @Column(name = "NEW_WINDOW_TITLE", length = 50)
    public String getNewWindowTitle() {
        return this.newWindowTitle;
    }

    public void setNewWindowTitle(String newWindowTitle) {
        this.newWindowTitle = newWindowTitle;
    }

    @Column(name = "FILE_NAME", unique = true, nullable = false)
    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Column(name = "DISPALY_ALIAS", length = 50)
    public String getDispalyAlias() {
        return this.dispalyAlias;
    }

    public void setDispalyAlias(String dispalyAlias) {
        this.dispalyAlias = dispalyAlias;
    }

    @Column(name = "ENABLE", length = 1)
    public String getEnable() {
        return this.enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    @Column(name = "AD_TYPE")
    public Integer getAdType() {
        return this.adType;
    }

    public void setAdType(Integer adType) {
        this.adType = adType;
    }

    @Column(name = "AVAILABLE_START", length = 19)
    public Timestamp getAvailableStart() {
        return this.availableStart;
    }

    public void setAvailableStart(Timestamp availableStart) {
        this.availableStart = availableStart;
    }

    @Column(name = "AVAILABLE_END", length = 19)
    public Timestamp getAvailableEnd() {
        return this.availableEnd;
    }

    public void setAvailableEnd(Timestamp availableEnd) {
        this.availableEnd = availableEnd;
    }

    @Column(name = "TARGET_TYPE", nullable = false, length = 16)
    public String getTargetType() {
        return this.targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    @Column(name = "TARGET_SEQUENCE_NBR", nullable = false)
    public Long getTargetSequenceNbr() {
        return this.targetSequenceNbr;
    }

    public void setTargetSequenceNbr(Long targetSequenceNbr) {
        this.targetSequenceNbr = targetSequenceNbr;
    }


}