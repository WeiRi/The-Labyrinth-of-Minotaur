package com.wrgroup.homework;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.*;
import android.widget.TextView;

import java.util.List;

import myclass.*;
import myclass.Action.AttackBuffAct;
import myclass.Action.AttackDeBuffAct;
import myclass.Action.DefenceBuffAct;
import myclass.Action.DefenceDeBuffAct;
import myclass.Action.NormalAct;
import myclass.Action.PosionBuffAct;
import myclass.Buff.AttackBuff;
import myclass.Buff.AttackDeBuff;
import myclass.Buff.DefenceBuff;
import myclass.Buff.DefenceDeBuff;
import myclass.Buff.PoisonBuff;
import myclass.Items.AttackItem;
import myclass.Items.DefenceItem;
import myclass.Items.LifeItem;

public class SelectActivity extends AppCompatActivity {
    User user;
    AttackItem attackItem;
    DefenceItem defenceItem;
    LifeItem lifeItem;
    Monster monster;
    int total,round;
    boolean gg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);
        total=1;
        user=new User(100,30,28);
        attackItem=new AttackItem();
        defenceItem=new DefenceItem();
        lifeItem=new LifeItem();
        attackItem.setNum(1);
        defenceItem.setNum(1);
        lifeItem.setNum(3);
        setText();
    }
    public void StartFight(View view){
        round=1;
        setMonster();
        setContentView(R.layout.activity_fight);
        setInfos();
    }
    public void Card1(View view){
        String str="回合信息:\n";
        TextView memo=(TextView)this.findViewById(R.id.memo);
        str+="你对怪物发动了攻击！\n";
        user.SetAttack(30);
        user.Attack(monster);
        str+=monster.act(user,round);
        round++;
        Poison();
        user.checkBuff(round);
        monster.checkBuff(round);
        setInfos();
        memo.setText(str);
    }
    public void Card2(View view){
        String str="回合信息:\n";
        TextView memo=(TextView)this.findViewById(R.id.memo);
        str+="你为自己加上了攻击Buff！\n";
        AttackBuff buff=new AttackBuff();
        buff.setStartround(round);
        buff.setBuff(5);
        user.addBuff(buff);
        setInfos();
        str+=monster.act(user,round);
        round++;
        Poison();
        user.checkBuff(round);
        monster.checkBuff(round);
        setInfos();
        memo.setText(str);
    }
    public void Card3(View view){
        String str="回合信息:\n";
        TextView memo=(TextView)this.findViewById(R.id.memo);
        str+="你给怪物套上了攻击DeBuff！\n";
        AttackDeBuff buf=new AttackDeBuff();
        buf.setBuff(-5);
        buf.setStartround(round);
        monster.addBuff(buf);
        setInfos();
        str+=monster.act(user,round);
        round++;
        Poison();
        user.checkBuff(round);
        monster.checkBuff(round);
        setInfos();
        memo.setText(str);
    }
    public void Card4(View view){
        String str="回合信息:\n";
        TextView memo=(TextView)this.findViewById(R.id.memo);
        str+="你为自己加持了防御Buff！\n";
        DefenceBuff buf=new DefenceBuff();
        buf.setBuff(5);
        buf.setStartround(round);
        user.addBuff(buf);
        setInfos();
        str+=monster.act(user,round);
        round++;
        Poison();
        user.checkBuff(round);
        monster.checkBuff(round);
        setInfos();
        memo.setText(str);
    }
    public void Card5(View view){
        String str="回合信息:\n";
        TextView memo=(TextView)this.findViewById(R.id.memo);
        str+="你给怪物加上了防御DeBuff！\n";
        DefenceDeBuff buf= new DefenceDeBuff();
        buf.setBuff(-7);
        buf.setStartround(round);
        monster.addBuff(buf);
        setInfos();
        str+=monster.act(user,round);
        round++;
        Poison();
        user.checkBuff(round);
        monster.checkBuff(round);
        setInfos();
        memo.setText(str);
    }
    public void Card6(View view){
        String str="回合信息:\n";
        TextView memo=(TextView)this.findViewById(R.id.memo);
        str+="你对怪物使用了毒，怪物中毒了！\n";
        PoisonBuff buf=new PoisonBuff();
        buf.setBuff(-10);
        buf.setStartround(round);
        monster.addBuff(buf);
        setInfos();
        str+=monster.act(user,round);
        round++;
        Poison();
        user.checkBuff(round);
        monster.checkBuff(round);
        setInfos();
        memo.setText(str);
    }
    public void setInfos(){
        TextView roundtext=(TextView)this.findViewById(R.id.roundtext);
        roundtext.setText("第"+round+"回合");
        TextView monsterlife=(TextView)this.findViewById(R.id.monsterlife);
        monsterlife.setText(monster.getLife()+"");
        TextView userlife=(TextView)this.findViewById(R.id.userlife);
        userlife.setText(user.getLife()+"");
        TextView userbuff=(TextView)this.findViewById(R.id.userbuff);
        String userbuffs="";
        userbuffs+=("攻击力:"+user.getAttack()+"\n");
        userbuffs+=("防御力:"+user.getDefence()+"\n");
        List<AttackBuff> userattackbuff=user.getAttackBuffs();
        for(int i=0;i<userattackbuff.toArray().length;i++){
            if(userattackbuff.get(i).getClass().getSimpleName().equals("AttackBuff"))
                userbuffs+=("攻击buff:"+userattackbuff.get(i).getBuff()+"\n");
            else
                userbuffs+=("攻击debuff:"+userattackbuff.get(i).getBuff()+"\n");
        }
        List<DefenceBuff> userdefencebuff=user.getDefenceBuffs();
        for (int i=0;i<userdefencebuff.toArray().length;i++){
            if(userdefencebuff.get(i).getClass().getSimpleName().equals("DefenceBuff"))
                userbuffs+=("防御buff:"+userdefencebuff.get(i).getBuff()+"\n");
            else
                userbuffs+=("防御debuff:"+userdefencebuff.get(i).getBuff()+"\n");
        }
        if(user.getPoisonBuff()!=null){
            userbuffs+=("毒"+user.getPoisonBuff().getBuff()+"\n");
        }
        userbuff.setText(userbuffs);
        TextView monsterbuff=(TextView)this.findViewById(R.id.monsterbuff);
        String monsterbuffs="";
        monsterbuffs+=("攻击力:"+monster.getAttack()+"\n");
        monsterbuffs+=("防御力:"+monster.getDefence()+"\n");
        List<AttackBuff> monsterattackbuff=monster.getAttackBuffs();
        for(int i=0;i<monsterattackbuff.toArray().length;i++){
            if(monsterattackbuff.get(i).getClass().getSimpleName().equals("AttackBuff"))
                monsterbuffs+=("攻击buff:"+monsterattackbuff.get(i).getBuff()+"\n");
            else
                monsterbuffs+=("攻击debuff:"+monsterattackbuff.get(i).getBuff()+"\n");
        }
        List<DefenceBuff> monsterdefencebuff=monster.getDefenceBuffs();
        for (int i=0;i<monsterdefencebuff.toArray().length;i++){
            if(monsterdefencebuff.get(i).getClass().getSimpleName().equals("DefenceBuff"))
                monsterbuffs+=("防御buff:"+monsterdefencebuff.get(i).getBuff()+"\n");
            else
                monsterbuffs+=("防御debuff:"+monsterdefencebuff.get(i).getBuff()+"\n");
        }
        if(monster.getPoisonBuff()!=null){
            monsterbuffs+=("毒"+monster.getPoisonBuff().getBuff()+"\n");
        }
        monsterbuff.setText(monsterbuffs);
        if(monster.getLife()==0){
            gg=true;
            GameOver();
        }
        else if(user.getLife()==0) {
            gg=false;
            GameOver();
        }
    }
    public String Poison(){
        String str="";
        if(user.getPoisonBuff()!=null){
            int life=user.getLife();
            int buf=user.getPoisonBuff().getBuff();
            if(buf+life<=0)
                user.setLife(0);
            else
                user.setLife(life+buf);
        }
        if(monster.getPoisonBuff()!=null){
            int life=monster.getLife();
            int buf=monster.getPoisonBuff().getBuff();
            if(buf+life<=0)
                monster.setLife(0);
            else
                monster.setLife(life+buf);
        }
        return str;
    }
    public void setOringinAction(int i){
        if(i==1)
        {
            NormalAct normalAct=new NormalAct();
            monster.addAct(normalAct);
            AttackBuffAct attackBuffAct=new AttackBuffAct();
            attackBuffAct.setNum(3);
            monster.addAct(attackBuffAct);
            monster.addAct(normalAct);
            AttackDeBuffAct attackDeBuffAct=new AttackDeBuffAct();
            attackDeBuffAct.setNum(-2);
            monster.addAct(attackDeBuffAct);
        }else if(i==2){
            NormalAct normalAct=new NormalAct();
            monster.addAct(normalAct);
            AttackBuffAct attackBuffAct=new AttackBuffAct();
            attackBuffAct.setNum(5);
            monster.addAct(attackBuffAct);
            monster.addAct(normalAct);
            AttackDeBuffAct attackDeBuffAct=new AttackDeBuffAct();
            attackDeBuffAct.setNum(-4);
            monster.addAct(attackDeBuffAct);
            monster.addAct(normalAct);
            DefenceBuffAct defenceBuffAct=new DefenceBuffAct();
            defenceBuffAct.setNum(6);
            monster.addAct(defenceBuffAct);
        }else{
            NormalAct normalAct=new NormalAct();
            monster.addAct(normalAct);
            AttackBuffAct attackBuffAct=new AttackBuffAct();
            attackBuffAct.setNum(6);
            monster.addAct(attackBuffAct);
            monster.addAct(normalAct);
            AttackDeBuffAct attackDeBuffAct=new AttackDeBuffAct();
            attackDeBuffAct.setNum(-4);
            monster.addAct(attackDeBuffAct);
            monster.addAct(normalAct);
            DefenceBuffAct defenceBuffAct=new DefenceBuffAct();
            defenceBuffAct.setNum(3);
            monster.addAct(defenceBuffAct);
            monster.addAct(normalAct);
            DefenceDeBuffAct deBuffAct=new DefenceDeBuffAct();
            deBuffAct.setNum(-7);
            monster.addAct(deBuffAct);
            monster.addAct(normalAct);
            PosionBuffAct posionBuffAct=new PosionBuffAct();
            posionBuffAct.setNum(-3);
            monster.addAct(posionBuffAct);
        }
    }
    public void GameOver(){
        if(gg)
            switch (total){
                case 1:
                    this.setContentView(R.layout.activity_gameover);
                    TextView ggtext1=(TextView)this.findViewById(R.id.gameovertext);
                    ggtext1.setText("您成功的击败了怪物，并从它的身上搜出一些药剂\n获得物品:\n5瓶生命药剂！");
                    lifeItem.setNum(lifeItem.getNum()+5);
                    total++;
                    break;
                case 3:
                    this.setContentView(R.layout.activity_gameover);
                    TextView ggtext3=(TextView)this.findViewById(R.id.gameovertext);
                    ggtext3.setText("您费尽周折之后击败了怪物，并获得了它守护的宝物\n获得物品:\n5瓶生命药剂\n2瓶愤怒药剂\n2瓶石化药剂！");
                    lifeItem.setNum(lifeItem.getNum()+5);
                    attackItem.setNum(attackItem.getNum()+2);
                    defenceItem.setNum(defenceItem.getNum()+2);
                    total++;
                    break;
                case 5:
                    this.setContentView(R.layout.activity_gameover);
                    TextView ggtext5=(TextView)this.findViewById(R.id.gameovertext);
                    ggtext5.setText("恭喜您击败了米诺陶诺斯！\n" +
                            "岛屿因您而恢复了安宁！");
                    break;
            }
        else{
            this.setContentView(R.layout.activity_gameover);
            TextView ggtext=(TextView)this.findViewById(R.id.gameovertext);
            ggtext.setText("您被怪物击败了，请重新开始挑战！");
        }
    }
    public void GameOverButton(View view){
        if(gg){
            if(total==5){
                Intent myintent=new Intent(SelectActivity.this,MainActivity.class);
                startActivity(myintent);
            }else{
                user.clearBuff();
                if(total==2) {
                    this.setContentView(R.layout.activity_event);
                    TextView etext1=(TextView)this.findViewById(R.id.eventtext);
                    etext1.setText("第二关\n您在迷宫中被绊倒了。用火把照明找过去之后您发现那是一个宝箱\n获得物品:\n3瓶愤怒药剂");
                    attackItem.setNum(attackItem.getNum()+3);
                    total++;
                }else if(total==4){
                    this.setContentView(R.layout.activity_event);
                    TextView etext2=(TextView)this.findViewById(R.id.eventtext);
                    etext2.setText("第四关\n您遇到了守卫的谜语人，猜对谜语后他让开了道路，并送给您一些礼物\n获得物品:\n3瓶生命药剂");
                    lifeItem.setNum(lifeItem.getNum()+3);
                    total++;
                }
            }
        }else{
            Intent myintent=new Intent(SelectActivity.this,MainActivity.class);
            startActivity(myintent);
        }
    }
    public void EventButton(View view){
        this.setContentView(R.layout.activity_select);
        setText();
    }
    public void setText(){
        TextView lifetext=(TextView)this.findViewById(R.id.selectlife);
        lifetext.setText("当前血量："+user.getLife());
        TextView totaltext=(TextView)this.findViewById(R.id.totaltext);
        totaltext.setText("第"+total+"关");
        TextView attacktext=(TextView)this.findViewById(R.id.attackitem);
        attacktext.setText("愤怒药剂："+attackItem.getNum()+"瓶");
        TextView defencetext=(TextView)this.findViewById(R.id.defenceitem);
        defencetext.setText("石化药剂："+defenceItem.getNum()+"瓶");
        TextView lifeitemtext=(TextView)this.findViewById(R.id.lifeitem);
        lifeitemtext.setText("生命药剂："+lifeItem.getNum()+"瓶");
    }
    public void AttackItemButton(View view){
        if(attackItem.getNum()>0){
            attackItem.setNum(attackItem.getNum()-1);
            user.addBuff(attackItem.getBuff());
            setText();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setIcon()
            builder.setTitle("使用成功");
            builder.setMessage("您获得了攻击增益效果！");
            builder.setPositiveButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setIcon()
            builder.setTitle("使用失败");
            builder.setMessage("药剂数量不足！");
            builder.setPositiveButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }
    public void DefenceItemButton(View view){
        if(defenceItem.getNum()>0){
            defenceItem.setNum(defenceItem.getNum()-1);
            user.addBuff(defenceItem.getBuff());
            setText();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setIcon()
            builder.setTitle("使用成功");
            builder.setMessage("您获得了防御增益效果！");
            builder.setPositiveButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setIcon()
            builder.setTitle("使用失败");
            builder.setMessage("药剂数量不足！");
            builder.setPositiveButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }
    public void LifeItemButton(View view){
        if(lifeItem.getNum()>0){
            lifeItem.setNum(lifeItem.getNum()-1);
            user.setLife(user.getLife()+lifeItem.getLife());
            setText();
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setIcon()
            builder.setTitle("使用成功");
            builder.setMessage("您的血量回复了！");
            builder.setPositiveButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }else{
            AlertDialog.Builder builder=new AlertDialog.Builder(this);
            //builder.setIcon()
            builder.setTitle("使用失败");
            builder.setMessage("药剂数量不足！");
            builder.setPositiveButton("关闭",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
            AlertDialog alertDialog=builder.create();
            alertDialog.show();
        }
    }
    public void setMonster(){
        switch (total){
            case 1:
                monster=new Monster(100,30,10);
                setOringinAction(1);
                break;
            case 3:
                monster=new Monster(150,35,20);
                setOringinAction(2);
                break;
            case 5:
                monster=new Monster(200,40,26);
                setOringinAction(3);
                break;
        }
    }
}
