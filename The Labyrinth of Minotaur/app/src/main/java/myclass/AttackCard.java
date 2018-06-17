package myclass;

import android.util.Log;

/**
 * Created by wrs on 2018/6/15.
 */

public class AttackCard extends Card {
    int attack;
    public void SetAttack(int num){
        attack=num;
        Log.d("设置卡牌攻击力",attack+"");
    }
    public void run(User user,Monster monster){
        user.Attack(monster);
    }
}
