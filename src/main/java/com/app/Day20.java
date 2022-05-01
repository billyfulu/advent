package com.app;

import java.util.ArrayList;
import java.util.List;

public class Day20 {

    static String ENHANCEMENT_ALGORITHM = "..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..##" +
            "#..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###" +
            ".######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#." +
            ".#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#....." +
            ".#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.." +
            "...####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#....." +
            "..##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#";

    static String INPUT =
            "...............\n" +
                    "...............\n" +
                    "...............\n" +
                    "...............\n" +
                    "...............\n" +
                    ".....#..#......\n" +
                    ".....#.........\n" +
                    ".....##..#.....\n" +
                    ".......#.......\n" +
                    ".......###.....\n" +
                    "...............\n" +
                    "...............\n" +
                    "...............\n" +
                    "...............\n" +
                    "...............";

    String pixelToBinary(String pixel) {
        StringBuilder binary = new StringBuilder();
        for (char aChar : pixel.toCharArray()) {
            if (aChar == '.') {
                binary.append("0");
            } else if (aChar == '#') {
                binary.append("1");
            } else {
                throw new IllegalArgumentException("character: " + aChar + " is not allowed");
            }
        }
        return binary.toString();
    }

    private char[][] convertInputTo2DArray(String input) {
        String[] inputRows = input.split("\n");
        char[][] rows = new char[inputRows.length][inputRows[0].length()];
        for (int i = 0; i < inputRows.length; i++) {
            rows[i] = inputRows[i].toCharArray();
        }
        return rows;
    }

    private String getPixelToConvert(char[][] x, int startI, int startJ) {
        StringBuilder pattern = new StringBuilder();
        int max = 3;
        for (int i = startI; i < startI + max; i++) {
            char[] row = x[i];
            for (int j = startJ; j < startJ + max; j++) {
                char pixel = row[j];
                pattern.append(pixel);
            }
        }
        return pattern.toString();
    }

    void solve() {
        System.out.println("\n================ INPUT ===================\n");
        System.out.println(INPUT);
        System.out.println("\n===================================\n");
        List<String> outputList = getOutputString(INPUT);
        System.out.println("\n===================================\n");
        String input = getInputFromOutput(outputList);

        getOutputString(input);

    }

    private List<String> getOutputString(String input1) {
        char[][] inputArray = convertInputTo2DArray(input1);
        char[][] dOutputArray = get2DOutputArray(inputArray);

        List<String> outputList = getOutputList(dOutputArray);
        printOutPut(outputList);
        return outputList;
    }

    private void printOutPut(List<String> outputList) {
        for (String output : outputList) {
            System.out.println(output);
        }
    }

    private String getInputFromOutput(List<String> outputList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (String output : outputList) {
            stringBuilder.append(output).append("\n");
        }
        return stringBuilder.toString();
    }


    private List<String> getOutputList(char[][] dOutputArray) {
        List<String> output = new ArrayList<>();
        for (char[] outputLine : dOutputArray) {
            output.add(String.valueOf(outputLine));
        }
        return output;
    }

    private char[][] get2DOutputArray(char[][] x) {
        char[][] output = new char[x.length][x[0].length];

        output[0] = x[0];
        output[x.length - 1] = x[x.length - 1];
        for (int i = 1; i < x.length - 1; i++) {
            char[] row = x[i];
            char[] outputRow = new char[row.length];
            outputRow[0] = row[0];
            outputRow[row.length - 1] = row[row.length - 1];
            for (int j = 1; j < row.length - 1; j++) {
                String pixels = getPixelToConvert(x, i - 1, j - 1);
                String binary = pixelToBinary(pixels);
                int decimal = binaryToDecimal(binary);
                char outPutPixel = getOutPutPixel(decimal);
                outputRow[j] = outPutPixel;
            }
            output[i] = outputRow;
        }

        return output;
    }

    private char getOutPutPixel(int decimal) {
        char[] algorithm = ENHANCEMENT_ALGORITHM.toCharArray();
        return algorithm[decimal];
    }

    int binaryToDecimal(String binary) {
        return Integer.parseInt(binary, 2);
    }

    public static void main(String[] args) {
        Day20 day20 = new Day20();
        day20.solve();
    }
}
