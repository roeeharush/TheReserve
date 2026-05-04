
package ecosystem.core;


public class Position {
    private int row;
    private int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public boolean setRow(int row) {
        if ((row >= 0)) {
            this.row = row;
            return true;
        }
        return false;
    }

    public boolean setCol(int col) {
        if ((col >= 0)) {
            this.col = col;
            return true;
        }
        return false;
    }

    public boolean setCoordinates(int row, int col) {
        if ((col >= 0 && row >= 0)) {
            this.row = row;
            this.col = col;
            return true;
        }
        return false;
    }

    public int getRow(){return this.row;};
    public int getCol(){return this.col;};



    public int distanceTo(Position other) {
        if (other == null)
            return -1;
        return Math.abs((other.col - this.col)) + Math.abs((other.row - this.row));
    }


    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof Position other) {
            return (other.col == this.col) && (other.row == this.row);
        }
        return false;
    }




    @Override
    public String toString(){
        return "(" + this.row + "," + this.col + ")";
    }
}


