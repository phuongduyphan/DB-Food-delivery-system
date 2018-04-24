public abstract class DBObj<T> {
    public static T create(T t) {
        throw new Error("Has not been implemented in subclass");
    }
}
