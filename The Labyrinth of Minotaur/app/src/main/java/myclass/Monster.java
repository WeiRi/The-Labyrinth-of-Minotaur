package myclass;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import myclass.Action.MyAction;
import myclass.Buff.AttackBuff;
import myclass.Buff.AttackDeBuff;
import myclass.Buff.Buff;
import myclass.Buff.DefenceBuff;
import myclass.Buff.DefenceDeBuff;
import myclass.Buff.PoisonBuff;

/**
 * Created by wrs on 2018/6/15.
 */

public class Monster {
    int life;
    int attack;
    int defence;
    List<AttackBuff> attackBuffs;
    List<DefenceBuff> defenceBuffs;
    PoisonBuff poisonBuff;
    List<MyAction> actions;
    public  Monster(int newlife,int attack,int defence){
        this.life=newlife;
        this.attack=attack;
        this.defence=defence;
        attackBuffs=new ArrayList<AttackBuff>();
        defenceBuffs=new ArrayList<DefenceBuff>();
        actions=new ArrayList<MyAction>();
    }
    public boolean IsAlive(){//true表示活着，false死了
        if(life>0)
            return true;
        return false;
    }
    public void Attack(User user){
        int realattack=attack;
        if(attackBuffs.toArray().length>0){
            for(int i=0;i<attackBuffs.toArray().length;i++){
                realattack+=attackBuffs.get(i).getBuff();
            }
        }
        user.Defence(realattack);
    }
    public void Defence(int num){
        int real=num;
        int def=defence;
        for(int i=0;i<defenceBuffs.toArray().length;i++){
            def+=defenceBuffs.get(i).getBuff();
        }
        if(def<0)
            def=0;
        real-=def;
        if(real>0){
            if(life>real)
                life-=real;
            else
                life=0;
        }
    }
    public  int getLife(){
        return life;
    }
    public void addBuff(Buff buff){
        Log.d("buffname",buff.getClass().getSimpleName());
        String name=buff.getClass().getSimpleName();
        if(name.equals("PoisonBuff")){
            poisonBuff=(PoisonBuff)buff;
        }
        if(name.equals("AttackBuff")){
            attackBuffs.add((AttackBuff)buff);
        }
        if(name.equals("AttackDeBuff")){
            attackBuffs.add((AttackDeBuff)buff);
        }
        if(name.equals("DefenceBuff")){
            defenceBuffs.add((DefenceBuff)buff);
        }
        if(name.equals("DefenceDeBuff")){
            defenceBuffs.add((DefenceDeBuff)buff);
        }
    }
    public void setDefence(int defence) {
        this.defence = defence;
    }

    public List<AttackBuff> getAttackBuffs() {
        return attackBuffs;
    }

    public List<DefenceBuff> getDefenceBuffs() {
        return defenceBuffs;
    }

    public PoisonBuff getPoisonBuff() {
        return poisonBuff;
    }
    public void addAct(MyAction action){
        actions.add(action);
    }
    public int getActCounts(){
        return actions.toArray().length;
    }
    public String act(User user,int round){
        int now=round%getActCounts();
        if(round<=getActCounts())
            now=round-1;
        else if(now==0)
            now=getActCounts()-1;
        else
            now--;
        actions.get(now).act(user,this,round);
        String str="";
        switch (actions.get(now).getClass().getSimpleName()){
            case "AttackBuffAct":
                str="怪物给自己加持了攻击Buff！\n";
                break;
            case "AttackDeBuffAct":
                str="怪物给你套上了攻击DeBuff！\n";
                break;
            case "DefenceBuffAct":
                str="怪物给自己加持了防御Buff！\n";
                break;
            case "DefenceDeBuffAct":
                str="怪物给你套上了防御DeBuff！\n";
                break;
            case "NormalAct":
                str="怪物向你发动了攻击！\n";
                break;
            case "PosionBuffAct":
                str="怪物对你使用了毒，你中毒了！\n";
                break;
        }
        Log.d("monster act",str);
        return str;
    }
    public int getAttack() {
        return attack;
    }

    public int getDefence() {
        return defence;
    }
    public void checkBuff(int round){
        for(int i=0;i<attackBuffs.toArray().length;i++){
            if(i==attackBuffs.toArray().length)
                break;
            AttackBuff act=attackBuffs.get(i);
            if(round-act.getStartround()>2){
                attackBuffs.remove(i);
                i--;
            }
            else
                break;
        }
        for (int i=0;i<defenceBuffs.toArray().length;i++){
            if(i==defenceBuffs.toArray().length)
                break;
            DefenceBuff buf=defenceBuffs.get(i);
            if(round-buf.getStartround()>2){
                defenceBuffs.remove(i);
                i--;
            }
        }
        if(poisonBuff!=null){
            if(round-poisonBuff.getStartround()>2)
                poisonBuff=null;
        }
    }

    public void setLife(int life) {
        this.life = life;
    }
}
