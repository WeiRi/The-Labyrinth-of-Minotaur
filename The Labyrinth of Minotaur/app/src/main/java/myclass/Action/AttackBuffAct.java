package myclass.Action;

import myclass.Buff.AttackBuff;
import myclass.Monster;
import myclass.User;

/**
 * Created by wrs on 2018/6/15.
 */

public class AttackBuffAct extends MyAction {
    @Override
    public void act(User user, Monster monster, int round) {
        AttackBuff buf=new AttackBuff();
        buf.setBuff(num);
        buf.setStartround(round);
        monster.addBuff(buf);
    }
}
