package com.vamsi.async;

import java.util.List;

public class FutureReturnClass implements Runnable{

    List<Integer> output;

    public FutureReturnClass(List<Integer> output) {
        this.output = output;
    }

    @Override
    public void run() {
        System.out.println("In FutureReturnClass");
        output.add(200);
    }

    public List<Integer> getOutput() {
        return output;
    }

    public void setOutput(List<Integer> output) {
        this.output = output;
    }
}
