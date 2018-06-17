package myclass;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import myclass.Buff.*;

/**
 * Created by wrs on 2018/6/15.
 */

public class User {
    int life;
    int attack;
    int defence;
    List<AttackBuff> attackBuffs;
    List<DefenceBuff> defenceBuffs;
    PoisonBuff poisonBuff;
    public  User(int newlife,int attack,int de){
        this.life=newlife;
        this.attack=attack;
        this.defence=de;
        attackBuffs=new ArrayList<AttackBuff>();
        defenceBuffs=new ArrayList<DefenceBuff>();
        //poisonBuff=new PoisonBuff();
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
    public boolean IsAlive(){//true表示活着，false死了
        if(life>0)
            return true;
        return false;
    }
    public void Attack(Monster monster){
        int realattack=attack;
        if(attackBuffs.toArray().length>0){
            for(int i=0;i<attackBuffs.toArray().length;i++){
                realattack+=attackBuffs.get(i).getBuff();
            }
        }
        monster.Defence(realattack);
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
    public void SetAttack(int num){
        if(num>0)
            attack=num;
        else
            attack=0;
        Log.d("设置用户攻击力",attack+"");
    }
    public  int getLife(){
        return life;
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
    public void clearBuff(){
        if(attackBuffs.toArray().length>0)
            attackBuffs=new ArrayList<AttackBuff>();
        if(defenceBuffs.toArray().length>0)
            defenceBuffs=new ArrayList<DefenceBuff>();
        poisonBuff=null;
    }
}
