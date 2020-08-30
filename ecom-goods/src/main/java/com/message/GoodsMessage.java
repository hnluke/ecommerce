package com.message;

import com.model.Goods;
import com.model.Users;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class GoodsMessage implements Serializable {
    public final static long serialVersionUID = 1004;

    private String messName;         // 消息名称:"购买", "取消购买"
    private Integer buyNum;          // 购买数量
    private Goods good;              // 商品对象
    private Users users;             // 用户对象

    public String getMessName() {
        return messName;
    }

    public void setMessName(String messName) {
        this.messName = messName;
    }

    public Goods getGood() {
        return good;
    }

    public void setGood(Goods good) {
        this.good = good;
    }


    public Integer getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Integer buyNum) {
        this.buyNum = buyNum;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "GoodsMessage{" +
                "messName='" + messName + '\'' +
                ", buyNum=" + buyNum +
                ", good=" + good +
                ", users=" + users +
                '}';
    }
}
