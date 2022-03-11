public class Encryptor {
    private String[][] block;
    private String message;
    private int numRows;
    private int numCols;
    private int caesarKey;
    private int rowShiftKey;
    private int colShiftKey;

    public Encryptor(String m, int ca, int r, int col) {
        message = m;
        caesarKey = ca;
        rowShiftKey = r;
        colShiftKey = col;

        int len = message.length();
        int[] factors = suggestFactors(len);
        block = new String[factors[1]][factors[0]];

        numRows = factors[1];
        numCols = factors[0];
    }

    public String encrypt() {
        encryptCaesar();
        fillBlock(message);
        encryptRowShift();
        encryptColShift();
        return message;
    }

    private void encryptCaesar() {
        String result = message;
        String clue = caesarKey + "";

        while (clue.length() != result.length()) {
            if (clue.length() > result.length()) {
                clue = clue.substring(0, clue.length() - 1);
            }
            if (clue.length() < result.length()) {
                clue += caesarKey;
            }
        }

        int j = 0;
        for (int i = 0; i < result.length(); i++) {
            char letter = result.charAt(i);
            int letterInt = (int) letter;


            if (letterInt > 64 && letterInt < 91) {
                int cue = Integer.parseInt(clue.substring(j, j + 1));
                j++;

                if (letterInt + cue > 90) {
                    letter = (char) (letterInt + cue - 26);
                }
                else {
                    letter = (char) (letterInt + cue);
                }
            }
            if (letterInt > 96 && letterInt < 123) {
                int cue = Integer.parseInt(clue.substring(j, j + 1));
                j++;

                if (letterInt + cue > 122) {
                    letter = (char) (letterInt + cue - 26);
                }
                else {
                    letter = (char) (letterInt + cue);
                }
            }

            result = result.substring(0, i) + letter + result.substring(i + 1);
        }

        message = result;
    }

    private void fillBlock(String str) {
        int count = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (count < str.length()) {
                    block[i][j] = str.substring(count, count + 1);
                    count++;
                }
            }
        }

    }

    private void encryptRowShift() {
        int shiftBy = rowShiftKey % numCols;
        for (int i = 0; i < numRows; i++) {
            String[] temp = new String[shiftBy];
            int index = 0;
            for (int j = numCols - shiftBy; j < numCols; j++) {
                temp[index] = block[i][j];
                index++;
            }

            for (int k = 0; k < temp.length; k++) {
                if (k < numCols - shiftBy) {
                    block[i][k + shiftBy] = block[i][k];
                }
                block[i][k] = temp[k];
            }
        }
        printBlock();
    }

    private void encryptColShift() {
        int shiftBy = colShiftKey % numCols;

        String[][] temp = new String[shiftBy][numRows];
        int index = 0;
        for (int j = numCols - shiftBy; j < numCols; j++) {
            String[] helper = new String[numRows];
            int helperIndex = 0;
            for (String[] row : block) {
                helper[helperIndex] = row[j];
                helperIndex++;
            }
            temp[index] = helper;
            index++;
        }

        for (int k = 0; k < temp.length; k++) {
            if (k < numCols - shiftBy) {
                for (int l = 0; l < numRows; l++) {
                    System.out.println(k + " " + block[l][k + shiftBy] + " " + block[l][k]);
                    block[l][k + shiftBy] = block[l][k];
                }

            }
            for (int l = 0; l < numRows; l++) {
                block[l][k] = temp[k][l];
            }
        }

        printBlock();
    }

    private int[] suggestFactors(int l) {
        int possible = 1;
        for (int i = 2; i < l; i++) {
            if (l % i == 0) {
                possible = i;
            }
        }

        int[] result = {possible, l / possible};
        return result;
    }

    private void printBlock() {
        for (String[] row : block) {
            for (String str : row) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }
}
