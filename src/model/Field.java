package model;

public class Field {
    private Symbol owner;
    private boolean accesible; 

    private Field(Symbol symbol) {
        owner = symbol;
        accesible = true;
    }

    public static Field getDefault() {
        return new Field(Symbol.X);
    }

    public Symbol getOwner() {
        return owner;
    }

    public void setOwner(Symbol owner) {
        this.owner = owner;
    }
    
    public boolean getAccesible() {
        return accesible;
    }

    public void setAcessible(boolean accesible) {
        this.accesible = accesible;
    }
    @Override
    public String toString() {
        return owner.toString();
    }

    public enum Symbol {
        X, O, NONE
    }
}