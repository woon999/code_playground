package ch2.item2.builder_pattern_ex;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public abstract class Pizza {
    public enum Topping { HAM, MUSHROOM, ONION, PEPPER, SAUSAGE }
    final Set<Topping> toppings;

    // 재귀적 타입 한정을 이용하는 제네릭 타입(Item 30. 이왕이면 제네릭 메서드로 만들라)
    abstract static class Builder<T extends Builder<T>>{
        EnumSet<Topping> toppings = EnumSet.noneOf(Topping.class);
        public T addTopping(Topping topping){
            toppings.add(Objects.requireNonNull(topping));
            return self();
        }

        abstract Pizza build();

        // 하위 클래스는 이 메서드를 재정의(overriding)하여
        // "this"를 반환하도록 해야 한다.
        protected abstract T self();
    }

    // (Item50.적시에 방아적 복사본을 만들라)
    // 생성자에서 받은 가변 매개변수 각각을 방어적으로 복사(defensive copy)해야 한다.
    Pizza(Builder<?> builder){
        toppings = builder.toppings.clone();
    }

    @Override
    public String toString() {
        return "Pizza{" +
                "toppings=" + toppings +
                '}';
    }
}
