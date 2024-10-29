package main;

public enum StateType {
    Moved(0),
    Blackwon(1),
    Whitewon(2),
    Tie(3);
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
