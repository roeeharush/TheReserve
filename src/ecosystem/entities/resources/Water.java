package ecosystem.entities.resources;
import ecosystem.core.Position;
import ecosystem.interfaces.Consumable;

public class Water extends Resource implements Consumable {
    private static final double NUTRITION_VALUE = 100.0;

    public Water(Position position){
        super(position,'W',true);
    }

    public double getNutritionValue(){
        return NUTRITION_VALUE;
    }

    public boolean onConsumed(){
        return true;
    }

    @Override
    public String toString(){
        return super.toString();
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(o instanceof Water other)
            return super.equals(o);
        return false;

    }

}
