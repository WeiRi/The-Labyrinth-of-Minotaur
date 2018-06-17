package myclass.Items;

import myclass.Buff.Buff;
import myclass.Item;

/**
 * Created by wrs on 2018/6/16.
 */

public class LifeItem extends Item {
    int life;
    public LifeItem(){
        setNum(0);
        setBuff(new Buff());
        life=10;
    }

    public int getLife() {
        return life;
    }
}
