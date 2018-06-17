package myclass.Action;

import myclass.Buff.PoisonBuff;
import myclass.Monster;
import myclass.User;

/**
 * Created by wrs on 2018/6/15.
 */

public class PosionBuffAct extends MyAction {
    @Override
    public void act(User user, Monster monster, int round) {
        PoisonBuff buf=new PoisonBuff();
        buf.setBuff(num);
        buf.setStartround(round);
        user.addBuff(buf);
    }
}
