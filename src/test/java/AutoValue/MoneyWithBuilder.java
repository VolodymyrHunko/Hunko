package AutoValue;

import com.google.auto.value.AutoValue;

@AutoValue
public abstract class MoneyWithBuilder {
    public abstract String getMoney();
    public abstract int getAmount();

    static Builder builder(){
        return new AutoValue_MoneyWithBuilder.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder{
        abstract Builder setMoney(String money);
        abstract Builder setAmount(int amount);
        abstract MoneyWithBuilder build();
    }
}
