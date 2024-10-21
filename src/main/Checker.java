package main;

public class Checker {
    public static Checker[] checkers = new Checker[5];

    public static void fillCheckers() {
        for (int i = 1; i < checkers.length; i++) {
            checkers[i]=new Checker(CheckerType.fromOrder(i));
        }
    }

    CheckerType checkerType;

    public Checker(CheckerType type) {
        checkerType=type;
        loadTexture(type.path);
    }

    public void loadTexture(String path){}

    public String toString() {
        return checkerType.toString();
    }

    public static void main(String[] args) {
        fillCheckers();
        for(Checker checker:checkers){
            System.out.println(checker);
        }
    }
}
