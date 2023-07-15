package ch2.item2.builder_pattern_ex;

// 칼초네 피자 - 크기(size) 매개변수 필수
public class CalzonePizza extends Pizza{
    public final boolean sauceInside;

    public static class Builder extends Pizza.Builder<Builder>{
        private boolean sauceInside = false; // 기본값

        public Builder sauceInside(){
            sauceInside = true;
            return this;
        }

        @Override
        public CalzonePizza build() {
            return new CalzonePizza(this);
        }

        @Override
        protected Builder self() { return this; }
    }
    private CalzonePizza(Builder builder){
        super(builder);
        sauceInside = builder.sauceInside;
    }

    @Override
    public String toString() {
        return "CalzonePizza{" +
                super.toString()+
                ", sauceInside=" + sauceInside +
                '}';
    }
}
