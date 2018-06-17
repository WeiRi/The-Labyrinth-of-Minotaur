package myclass.Action;

import myclass.Buff.AttackBuff;
import myclass.Buff.DefenceDeBuff;
import myclass.Monster;
import myclass.User;

/**
 * Created by wrs on 2018/6/15.
 */

public class DefenceDeBuffAct extends MyAction {
    @Override
    public void act(User user, Monster monster, int round) {
        DefenceDeBuff buf=new DefenceDeBuff();
        buf.setBuff(num);
        buf.setStartround(round);
        user.addBuff(buf);
    }
}
