package myclass.Items;

import myclass.Buff.DefenceBuff;
import myclass.Item;

/**
 * Created by wrs on 2018/6/16.
 */

public class DefenceItem extends Item {
    public DefenceItem(){
        setNum(0);
        DefenceBuff buff=new DefenceBuff();
        buff.setBuff(3);
        buff.setStartround(1);
        setBuff(buff);
    }
}
