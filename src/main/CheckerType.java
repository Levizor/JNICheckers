package main;

public enum CheckerType {
    Blank(0, ""),
    Blackman(1, ""),
    Whiteman(2, ""),
    Blackking(3, ""),
    Whiteking(4, "");

    public final int order;
    public final String path;

    CheckerType(int order, String path) {
        this.path = path;
        this.order = order;
    }

    public static CheckerType fromOrder(int order) {
        for(CheckerType checkerType : CheckerType.values()) {
            if(checkerType.order == order) {
                return checkerType;
            }
        }
        return CheckerType.Blank;
    }
}
