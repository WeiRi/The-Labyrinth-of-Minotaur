package myclass.Action;

import myclass.Monster;
import myclass.User;

/**
 * Created by wrs on 2018/6/15.
 */

public class NormalAct extends MyAction {
    @Override
    public void act(User user, Monster monster,int round) {
        monster.Attack(user);
    }
}
