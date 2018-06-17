package myclass;

import myclass.Buff.Buff;

/**
 * Created by wrs on 2018/6/15.
 */

public class Item {
    int num;
    Buff buff;

    public void setNum(int num) {
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public Buff getBuff() {
        return buff;
    }

    public void setBuff(Buff buff) {
        this.buff = buff;
    }
}
