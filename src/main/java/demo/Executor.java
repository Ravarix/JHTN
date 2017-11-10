package demo;

import htn.*;
import htn.tasks.*;
import jexl.JexlPredicate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Doug on 10/8/2017.
 */
public class Executor {

    public static void main(String[] args){
        DemoWorldState worldState = new DemoWorldState();

        PrimitiveTask<DemoWorldState> eatFood = new PrimitiveTask<>("eatFood",
                ws -> ws.food,
//                new JexlPredicate<>("food"),
                ws -> {
                    ws.hunger = 0;
                    ws.food = false;
                });

        PrimitiveTask<DemoWorldState> buyFood = new PrimitiveTask<>("buyFood",
                ws -> ws.money >= 10,
                ws -> {
                    ws.food = true;
                    ws.money -= 10;
                });

        ArgTask<DemoWorldState, Integer> getMoney = new ArgTask<>("getMoney",
                (ws, i) -> ws.bankMoney >= i,
                (ws, i) -> {
                    ws.bankMoney -= i;
                    ws.money += i;
                });

        BiArgTask<DemoWorldState, Integer, Integer> passTimeArgd = new BiArgTask<>("passTime",
                (ws, i1, i2) -> {
                    ws.hunger += i1;
                    ws.bankMoney += i2;
                });

        PrimitiveTask<DemoWorldState> passTime = new PrimitiveTask<>("passTime",
                ws -> {ws.hunger += 3; ws.bankMoney += 2;});
//                new JexlConsumer<>("{hunger += 3, bankMoney += 2}"));

        ComplexTask<DemoWorldState> getFood = new ComplexTask<>("getFood",
                new Method<>(
                    ws -> ws.money >= 10,
                    buyFood),
                new Method<>(
                    ws -> true,
                    getMoney.with(ws -> ws.bankMoney), buyFood));

        ComplexTask<DemoWorldState> eat = new ComplexTask<>("eat",
                new Method<>(
                    ws -> ws.food,
                    eatFood),
                new Method<>(
                    ws -> !ws.food,
                    getFood, eatFood));


        ComplexTask<DemoWorldState> bePerson = new ComplexTask<>("root",
                new Method<>(
                    ws -> ws.hunger > 5,
                    eat),
                new Method<>(
                    ws -> ws.hour >= 9 && ws.hour <= 17,
                    passTimeArgd.with(1, 2) //work
                ),
                new Method<>(
                    ws -> ws.hour > 22 || ws.hour <= 6,
                    passTimeArgd.with(0, 0) //sleep
                ),
                new Method<>(
                    ws -> true,
                    passTimeArgd.with(1, 0))); //game

        Domain<DemoWorldState> personDomain = new Domain<>(bePerson);
        PlanGenerator<DemoWorldState> pg = new PlanGenerator<>(personDomain);

        worldState.bankMoney = 20;
        worldState.hour = 5;

        long time = System.nanoTime();
        final int steps = 100;
        for(int i = 0; i < steps; ++i){
            System.out.println("Hour "+worldState.hour);
            List<PrimitiveTask<DemoWorldState>> taskList = pg.findPlan(worldState);
            System.out.println(taskList.stream().map(Task::toString).collect(Collectors.joining(" - ")));
            //execute plan
            for(PrimitiveTask<DemoWorldState> task : taskList)
                task.apply(worldState);
            //step time
            worldState.hour = (worldState.hour + 1) % 24;
        }
        System.out.println((System.nanoTime() - time) / steps / 1000000.0);
    }
}
