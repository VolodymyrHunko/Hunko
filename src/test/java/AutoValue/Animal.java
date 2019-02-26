package AutoValue;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class Animal {
    abstract String getName();
    abstract int getLegs();

    static Animal create(String name, int legs) {
        return new AutoValue_Animal(name, legs);
    }
}


