package myclass.Buff;

import myclass.*;

/**
 * Created by wrs on 2018/6/15.
 */

public class AttackDeBuff extends AttackBuff {
    int buff;
    @Override
    public void setBuff(int buff) {
        this.buff = buff;
    }
    @Override
    public int getBuff() {
        return buff;
    }
}
