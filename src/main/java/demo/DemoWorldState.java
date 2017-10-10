package demo;

import org.apache.commons.beanutils.BeanUtils;
import utility.ICloneable;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by Doug on 10/8/2017.
 */
public class DemoWorldState implements ICloneable<DemoWorldState> {
    public int hunger;
    public boolean food;
    public int money;
    public int bankMoney;
    public int hour;

    public int getHunger() {
        return hunger;
    }

    public void setHunger(int hunger) {
        this.hunger = hunger;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBankMoney() {
        return bankMoney;
    }

    public void setBankMoney(int bankMoney) {
        this.bankMoney = bankMoney;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    @Override
    public DemoWorldState clone() {
        DemoWorldState copy = new DemoWorldState();
        try {
            BeanUtils.copyProperties(copy, this);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return copy;
    }
}
