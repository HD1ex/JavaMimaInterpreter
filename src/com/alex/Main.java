package com.alex;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: Mima [filename]");
            return;
        }

        List<String> file;
        try {
            file = Files.readAllLines(Paths.get(args[0]));
        } catch (Exception ex) {
            System.out.println("Unknown file: " + args[0]);
            final String dir = System.getProperty("user.dir");
            System.out.println("current dir = " + dir);
            return;
        }
        
        List<Instruction> instructions = new ArrayList<>();
        for(String line : file)
        {
            Instruction instruction = new Instruction();
            String[] columns = line.split(" ");
            
            instruction.InstructionString = columns[0];
            if(columns.length > 1)
            {
                instruction.Argument = columns[1];
            }
            
            instructions.add(instruction);
        }
        
        Mima mima = new Mima();
        
        
        try {
            mima.execute(instructions);
            System.out.println(mima.getOutput());
            System.out.println(mima);
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
