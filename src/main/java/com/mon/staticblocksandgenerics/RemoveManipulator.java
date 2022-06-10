package com.mon.staticblocksandgenerics;

public class RemoveManipulator {
    Manipulator manipulator;

    public RemoveManipulator(Manipulator manipulator){
        this.manipulator = manipulator;
    }

    public void remove(int index){
        manipulator.removeIndex(index);
    }
}
