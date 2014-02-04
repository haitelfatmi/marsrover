package com.github.javadojo;

import java.util.ArrayList;
import java.util.List;


public class StepAdapter {
    
     public static List<Step> adapt(String operations){
        List<Step> steps  = new ArrayList<Step>();
        operations = shift(operations);
        for (char c : operations.toCharArray()) {
            switch (c) {
            case 's':
                steps.add(Step.GO_AHEAD);
                break;
            case 'r':
                steps.add(Step.TURN_RIGHT);
                break;
            case 'l':
                steps.add(Step.TURN_LEFT);
                break;

            }
        }
        return steps;
    }

    private static String shift(String command) {
        command =  command.substring(1);
        return command;
    }

}
