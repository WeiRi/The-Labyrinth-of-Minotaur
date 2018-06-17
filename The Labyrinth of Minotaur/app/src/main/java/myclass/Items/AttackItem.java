package myclass.Items;

import myclass.Buff.AttackBuff;
import myclass.Item;

/**
 * Created by wrs on 2018/6/16.
 */

public class AttackItem extends Item {
    public AttackItem(){
        setNum(0);
        AttackBuff buff=new AttackBuff();
        buff.setStartround(1);
        buff.setBuff(3);
        setBuff(buff);
    }
}
