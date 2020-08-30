package com.message;

import com.model.Deliver;

import java.io.Serializable;

public class DeliverMessage implements Serializable {
    public final static long serialVersionUID = 1007L;

    private String messDeli;        // 消息名称: "派送成功", "派送退回"
    private Deliver deliver;        // Deliver对象

    public String getMessDeli() {
        return messDeli;
    }

    public void setMessDeli(String messDeli) {
        this.messDeli = messDeli;
    }

    public Deliver getDeliver() {
        return deliver;
    }

    public void setDeliver(Deliver deliver) {
        this.deliver = deliver;
    }

    @Override
    public String toString() {
        return "DeliverMessage{" +
                "messDeli='" + messDeli + '\'' +
                ", deliver=" + deliver +
                '}';
    }
}
