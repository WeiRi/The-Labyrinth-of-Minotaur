package myclass.Action;

import myclass.Buff.AttackBuff;
import myclass.Buff.DefenceBuff;
import myclass.Monster;
import myclass.User;

/**
 * Created by wrs on 2018/6/15.
 */

public class DefenceBuffAct extends MyAction {
    @Override
    public void act(User user, Monster monster, int round) {
        DefenceBuff buf=new DefenceBuff();
        buf.setBuff(num);
        buf.setStartround(round);
        monster.addBuff(buf);
    }
}
