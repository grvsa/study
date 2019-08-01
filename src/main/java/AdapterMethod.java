import java.lang.reflect.Method;

public class AdapterMethod implements Comparable<AdapterMethod>{
    private Method method;
    private int priority;

    public AdapterMethod(Method method, int priority) {
        this.method = method;
        this.priority = priority;
    }

    public Method getMethod() {
        return method;
    }

    public int getPriority() {
        return priority;
    }

    public int compareTo(AdapterMethod o) {
        return this.priority - o.getPriority();
    }
}
