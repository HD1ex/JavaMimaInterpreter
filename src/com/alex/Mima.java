package com.alex;

import java.util.Dictionary;
import java.util.List;

public class Mima {
    private Dictionary<String, Integer> memory = new Dictionary<String, int>();
    private int accu;

    public String getAccu() {
        int a = accu;
        if ((accu & 0x800000) > 0) {
            a = ~a;
            a += 1;
            a &= 0xFFFFF;
        }

        return a + "\t\t" + getBits(accu);
    }

    static String getBits(int value) {
        String output = "";
        for (int i = 8; i < 32; i++) {
            if ((value >> (31 - i) & 1) > 0)
                output += "1";
            else
                output += "0";
        }
        return output;
    }

    private void checkArgumentForNumber(String arg, int line, Integer number) throws Exception {
        try {
            Integer.parseInt(arg))
        }catch (Exception ex)
        {
            throw new Exception("Value at line " + " has to be a number.");
        }
        if (number > 0xFFFFFF)
            throw new Exception("Loaded value at line " + line + " is bigger than the max allowed value (0xFFFFF).");
    }

    private void checkAddress(String address, int line) {
        line += 1;
        if (!memory.Keys.Contains < String > (address))
            throw new Exception("The value in memory at address " + address + " wasn't initialized (Line " + line + ")");
    }

    public void execute(List<Instruction> instructions) throws Exception {
        for (int line = 0; line < instructions.size(); line++) {
            Instruction instruction = instructions.get(line);
            int currentLine = line + 1;

            Integer number = 0;
            switch (instruction.InstructionString) {
                case "HALT":
                    return;
                case "LDC":
                    checkArgumentForNumber(instruction.Argument, line, number);
                    accu = number;
                    accu &= 0xFFFFF;
                    break;
                case "NOT":
                    accu = ~accu;
                    accu &= 0xFFFFFF;
                    break;
                case "STV":
                    //checkArgumentForNumber(instruction.Argument, line, out number);
                    memory[instruction.Argument] = accu;
                    break;
                case "LDV":
                    checkAddress(instruction.Argument, line);
                    accu = memory[instruction.Argument];
                    break;
                case "ADD":
                    checkAddress(instruction.Argument, line);
                    accu += memory[instruction.Argument];
                    break;
                case "RAR":
                    if ((accu & 1) == 1) {
                        accu >>= 1;
                        accu |= 0x800000;
                    } else {
                        accu >>= 1;
                    }
                    break;
                case "JMP":
                    checkArgumentForNumber(instruction.Argument, line, number);
                    line = number - 2;
                    break;
                case "JMN":
                    checkArgumentForNumber(instruction.Argument, line, number);
                    if ((accu & 0x800000) != 0)
                        line = number - 2;
                    break;
                case "AND":
                    checkAddress(instruction.Argument, line);
                    accu &= memory[instruction.Argument];
                    break;
                case "OR":
                    checkAddress(instruction.Argument, line);
                    accu |= memory[instruction.Argument];
                    break;
                case "XOR":
                    checkAddress(instruction.Argument, line);
                    accu ^= memory[instruction.Argument];
                    break;
                case "EQL":
                    checkAddress(instruction.Argument, line);
                    if (accu == memory[instruction.Argument])
                        accu = -1;
                    else
                        accu = 0;
                    break;
                case "STIV":
                    checkAddress(instruction.Argument, line);
                    memory[memory[instruction.Argument].toString()] = accu;
                    break;
                case "LDIV":
                    checkAddress(instruction.Argument, line);
                    checkAddress(memory[instruction.Argument].toString(), line);
                    accu = memory[memory[instruction.Argument].toString()];
                    break;
                default:
                    throw new Exception("Unknown instruction '" + instruction.InstructionString + "'(Line " + ++line + ").");
            }

            System.out.println("line: " + currentLine + " accu: " + getAccu());
        }

        throw new Exception("Unknown code reached!");
    }
}
