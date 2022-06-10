package com.mon.staticblocksandgenerics;

public class AddingManipulator {
    Manipulator manipulator;

    public AddingManipulator(Manipulator manipulator){
        this.manipulator = manipulator;
    }

    public void add(int index, int val){

        manipulator.addAtIndex(index, val);

    }
}
