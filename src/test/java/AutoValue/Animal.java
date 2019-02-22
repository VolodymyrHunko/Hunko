package AutoValue;


@AutoValue
abstract class Animal {
    abstract String name();
    abstract int legs();

    static Animal create (String name, int legs){

        return new AutoValue_Animal(name, legs);
    }

}
