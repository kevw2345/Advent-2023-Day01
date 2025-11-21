import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ArrayList<String> lines = getFileData("src/data");

        int partOneAnswer = 0;
        int partTwoAnswer = 0;
        for (int i = 0; i < lines.size(); i++) {
            partOneAnswer += getPartOneNumber(lines.get(i));
            partTwoAnswer += getPartTwoNumber(lines.get(i));
        }

        System.out.println("Part one answer: " + partOneAnswer);
        System.out.println("Part two answer: " + partTwoAnswer);
    }

    public static int getPartOneNumber(String line) {
        int i = 0;
        char dig1 = ' ';
        char dig2 = ' ';
        while (dig2 == ' ' || dig1 == ' ') {
            if (dig1 == ' ' && Character.isDigit(line.charAt(i))) {
                dig1 = line.charAt(i);
            }
            char backChar = line.charAt(line.length() - 1 - i);
            if (dig2 == ' ' && Character.isDigit(backChar)) {
                dig2 = backChar;
            }
            i++;
        }
        String stringNum = Character.toString(dig1) + Character.toString(dig2);
        System.out.println(stringNum);
        return Integer.parseInt(stringNum);
    }

    public static int getPartTwoNumber(String line) {
        int i = 0;
        char dig1 = ' ';
        char dig2 = ' ';
        while (dig2 == ' ' || dig1 == ' ') {
            if (dig1 == ' ') {
                if (Character.isDigit(line.charAt(i))) {
                    dig1 = line.charAt(i);
                } else if (checkForTextNumber(line, line.charAt(i)) != -1) {
                    dig1 = (char)(checkForTextNumber(line, line.charAt(i)));
                }
            }
            char backChar = line.charAt(line.length() - 1 - i);
            if (dig2 == ' ') {
                if (Character.isDigit(backChar)) {
                    dig2 = backChar;
                } else if (checkForTextNumber(line, backChar) != -1) {
                    dig2 = (char)(checkForTextNumber(line, backChar));
                }
            }
            i++;
        }
        String stringNum = Character.toString(dig1) + Character.toString(dig2);
        System.out.println(stringNum);
        return Integer.parseInt(stringNum);
    }

    public static int checkForTextNumber(String line, int idx) {
        HashMap<String, Integer> textNumbers = new HashMap<>();
        textNumbers.put("zero", 0);
        textNumbers.put("one", 1);
        textNumbers.put("two", 2);
        textNumbers.put("three", 3);
        textNumbers.put("four", 4);
        textNumbers.put("five", 5);
        textNumbers.put("six", 6);
        textNumbers.put("seven", 7);
        textNumbers.put("eight", 8);
        textNumbers.put("nine", 9);
        for (String key : textNumbers.keySet()) {
            if (idx + key.length() <= line.length() && line.substring(idx, idx + key.length()).equals(key)) {
                return textNumbers.get(key);
            }
        }
        //number not found
        return -1;
    }
    public static ArrayList<String> getFileData(String fileName) {
        ArrayList<String> fileData = new ArrayList<String>();
        try {
            File f = new File(fileName);
            Scanner s = new Scanner(f);
            while (s.hasNextLine()) {
                String line = s.nextLine();
                if (!line.equals(""))
                    fileData.add(line);
            }
            return fileData;
        }
        catch (FileNotFoundException e) {
            return fileData;
        }
    }

}