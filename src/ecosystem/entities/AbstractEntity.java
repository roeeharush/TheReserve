package ecosystem.entities;
import ecosystem.core.Position;

public abstract class  AbstractEntity {
    private Position position;
    private char symbol;
    private boolean alive = true;
    private static final String symbolsValid = "LRDTFXW";

    public AbstractEntity(Position position, char symbol, boolean alive) {
        if (!setPosition(position))
            this.position = new Position(0,0);

         if(!setSymbol(symbol))
             this.symbol = 'N'; // none

        this.alive = alive;
    }

    public char getSymbol() {
        return symbol;
    }
      public Position getPosition() {
        return position;
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean setPosition(Position position) {
        if (position == null)
            return false;
        this.position = new Position(position.getRow(),position.getCol());
        return true;
    }

    protected boolean setSymbol(char symbol) {
        int index = symbolsValid.indexOf(symbol);
        if (index != -1) {
            this.symbol = symbol;
            return true;
        }
        return false;
    }

    public boolean setAlive(boolean alive) {
        if ((this.alive) && (!alive)) {
            this.alive = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Entity type: " + getSymbol() +" Position:" + getPosition().toString()  + " Alive state " + isAlive();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof AbstractEntity other)
            return this.alive == other.alive && this.position.equals(other.position) && this.symbol == other.symbol;
        return false;
    }
}
