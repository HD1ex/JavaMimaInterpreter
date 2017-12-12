package com.alex;

public class Instruction
{
    public String InstructionString;
    public String Argument;

    @Override
    public String toString() {
        return InstructionString + "\t" + (Argument == null ? "" : Argument);
    }
}
