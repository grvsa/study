package items;

public abstract class Item implements Comparable<Item>{
    private String name;
    private int weight;
    private int value;

    public String getName() {
        return name;
    }

    public int getWeight() {
        return weight;
    }

    public int getValue() {
        return value;
    }

    public Item(String name, int weight, int value) {
        this.name = name;
        this.weight = weight;
        this.value = value;
    }

    public int compareTo(Item o) {
        if (o == null){
            return 1;
        }
        if (o == this){
            return 0;
        }
        if (value != o.getValue()){
            return value - o.getValue();
        }else if (weight != o.getWeight()){
            return weight - o.getWeight();
        }else{
            return name.compareTo(o.getName());
        }
    }

    @Override
    public String toString() {
        return name + " вес: " + weight + " цена: " + value;
    }
}
