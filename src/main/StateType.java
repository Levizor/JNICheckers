package main;

public enum StateType {
    Moved(0),
    Captured(1),
    Blackwon(2),
    Whitewon(3),
    Tie(4);
    public  final  int order;

    StateType(int i){
        order=i;
    }
    public static StateType fromOrder(int order) {
        for(StateType stateType : StateType.values()) {
            if(stateType.order == order) {
                return stateType;
            }
        }
        return null;
    }
}
