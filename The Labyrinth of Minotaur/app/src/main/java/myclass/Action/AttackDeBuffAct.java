package myclass.Action;

import myclass.Buff.AttackBuff;
import myclass.Buff.AttackDeBuff;
import myclass.Monster;
import myclass.User;

/**
 * Created by wrs on 2018/6/15.
 */

public class AttackDeBuffAct extends MyAction {
    @Override
    public void act(User user, Monster monster, int round) {
        AttackDeBuff buf= new AttackDeBuff();
        buf.setBuff(num);
        buf.setStartround(round);
        user.addBuff(buf);
    }
}
