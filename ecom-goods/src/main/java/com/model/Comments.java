package com.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Date;

@Repository
public class Comments implements Serializable {
    public final static long serialVersionUID = 1030L;
    private Integer commId;
    private Integer commGdId;
    private String commText;
    private Goods goods;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date commDate;

    public Integer getCommId() {
        return commId;
    }

    public void setCommId(Integer commId) {
        this.commId = commId;
    }

    public Integer getCommGdId() {
        return commGdId;
    }

    public void setCommGdId(Integer commGdId) {
        this.commGdId = commGdId;
    }

    public String getCommText() {
        return commText;
    }

    public void setCommText(String commText) {
        this.commText = commText;
    }

    public Date getCommDate() {
        return commDate;
    }

    public void setCommDate(Date commDate) {
        this.commDate = commDate;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "commId=" + commId +
                ", commGdId=" + commGdId +
                ", commText='" + commText + '\'' +
                ", goods=" + goods +
                ", commDate=" + commDate +
                '}';
    }
}
