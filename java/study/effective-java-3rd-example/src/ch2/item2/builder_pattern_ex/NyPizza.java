package ch2.item2.builder_pattern_ex;

import java.util.Objects;

// 뉴욕 피자 - 크기(size) 매개변수 필수
public class NyPizza extends Pizza{
    public enum Size { SMALL, MEDIUM, LARGE }
    private final Size size;

    public static class Builder extends Pizza.Builder<Builder>{
        private final Size size;

        public Builder(Size size){
            this.size = Objects.requireNonNull(size);
        }

        @Override
        public NyPizza build() {
            return new NyPizza(this);
        }

        @Override
        protected Builder self() { return this; }
    }
    private NyPizza(Builder builder){
        super(builder);
        size = builder.size;
    }

    @Override
    public String toString() {
        return "NyPizza{" +
                super.toString()+
                ", size=" + size +
                '}';
    }
}
