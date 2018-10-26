package com.time.qq.bean;

import com.time.social.common.bean.UserInfo;

/**
 * qq用户信息
 *
 * @author suiguozhen on 18/10/26
 */
public class QQInfo extends UserInfo {
    private String ret;
    private String msg;
    private String figureUrl;
    private String figureUrl1;
    private String figureUrl2;
    private String figureUrlQQ1;
    private String figureUrlQQ2;
    private String gender;

    public QQInfo(String ret, String msg, String nickName, String figureUrl, String figureUrl1, String figureUrl2, String figureUrlQQ1, String figureUrlQQ2, String gender) {
        this.ret = ret;
        this.msg = msg;
        this.setNickName(nickName);
        this.figureUrl = figureUrl;
        this.figureUrl1 = figureUrl1;
        this.figureUrl2 = figureUrl2;
        this.figureUrlQQ1 = figureUrlQQ1;
        this.figureUrlQQ2 = figureUrlQQ2;
        this.gender = gender;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getFigureUrl() {
        return figureUrl;
    }

    public void setFigureUrl(String figureUrl) {
        this.figureUrl = figureUrl;
    }

    public String getFigureUrl1() {
        return figureUrl1;
    }

    public void setFigureUrl1(String figureUrl1) {
        this.figureUrl1 = figureUrl1;
    }

    public String getFigureUrl2() {
        return figureUrl2;
    }

    public void setFigureUrl2(String figureUrl2) {
        this.figureUrl2 = figureUrl2;
    }

    public String getFigureUrlQQ1() {
        return figureUrlQQ1;
    }

    public void setFigureUrlQQ1(String figureUrlQQ1) {
        this.figureUrlQQ1 = figureUrlQQ1;
    }

    public String getFigureUrlQQ2() {
        return figureUrlQQ2;
    }

    public void setFigureUrlQQ2(String figureUrlQQ2) {
        this.figureUrlQQ2 = figureUrlQQ2;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
