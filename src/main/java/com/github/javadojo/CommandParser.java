package com.github.javadojo;

import java.util.HashMap;

public class CommandParser {
    

    private HashMap<Character, Command> map;

    public CommandParser() {
        map = new HashMap<>();
        for (Command command : Command.values()) {
            map.put(command.symbol, command);
        }
    }
    
    public void executesCommands(String commands, MarsRover receiver) {
        for (char commandChar : commands.toCharArray()) {
            Command command = map.get(Character.valueOf(commandChar));
            if(command == null) {
                throw new IllegalArgumentException("Cannot parse initial commands");
            }
            command.execute(receiver);
        }
    }
    
    enum Command {
        MOVE_FORWARD(Character.valueOf('s')) {
            @Override
            void execute(MarsRover marsRover) {
                marsRover.moveForward();
                
            }
        },
        TURN_LEFT(Character.valueOf('l')) {
            @Override
            void execute(MarsRover marsRover) {
                marsRover.turnLeft();
                
            }
        },
        TURN_RIGHT(Character.valueOf('r')) {
            @Override
            void execute(MarsRover marsRover) {
                marsRover.turnRight();
                
            }
        },
        TAKE_SAMPLE(Character.valueOf('S')) {
            @Override
            void execute(MarsRover marsRover) {
                marsRover.takeSample();
                
            }
        };
        
        private final Character symbol;
        Command(Character symbol) {
            this.symbol = symbol;
        }
        
        abstract void execute(MarsRover marsRover);
    }

}
