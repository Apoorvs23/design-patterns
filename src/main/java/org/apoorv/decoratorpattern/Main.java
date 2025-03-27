package org.apoorv.decoratorpattern;

import org.apoorv.decoratorpattern.component.BasePizza;
import org.apoorv.decoratorpattern.component.FarmhousePizza;
import org.apoorv.decoratorpattern.decorators.ExtraCheeseDecorator;
import org.apoorv.decoratorpattern.decorators.MushroomDecorator;

public class Main {
    public static void main(String[] args) {
        // Farmhouse Pizza with extra cheese and mushroom
        BasePizza mushroomExtraCheeseFarmhousePizza = new MushroomDecorator(new ExtraCheeseDecorator(new FarmhousePizza()));
        System.out.println(mushroomExtraCheeseFarmhousePizza.cost()); // 200+10+15
    }
}
