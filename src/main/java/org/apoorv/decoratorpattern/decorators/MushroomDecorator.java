package org.apoorv.decoratorpattern.decorators;

import org.apoorv.decoratorpattern.component.BasePizza;

public class MushroomDecorator extends ToppingDecorator{
    private final BasePizza basePizza;

    public MushroomDecorator(BasePizza basePizza) {
        this.basePizza = basePizza;
    }

    @Override
    public int cost() {
        return 15+this.basePizza.cost();
    }
}
