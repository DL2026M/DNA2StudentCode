/**
 * DNA
 * <p>
 * A puzzle created by Zach Blick
 * for Adventures in Algorithms
 * at Menlo School in Atherton, CA
 *</p>
 * <p>
 * Completed by: David Lutch
 *</p>
 */

public class DNA {

    /**
     * TODO: Complete this function, STRCount(), to return longest consecutive run of STR in sequence.
     */
    public static int STRCount(String sequence, String STR) {
        int tracker = 0;
        int currentRun = 0;
        int longestRun = 0;
        int sequenceLength = sequence.length();
        int STRlength = STR.length();
        int index = 0;

        for (int i = 0; i <= sequenceLength - STRlength; i++) {
            index = i;
            for (int j = 0; j <= STRlength - 1; j++) {
                if (index >= sequenceLength || sequence.charAt(index) != STR.charAt(j)) {
                    tracker = 0;
                    currentRun = 0;
                    break;
                }
                else {
                    tracker++;
                    index++;
                    // Match found
                    if (tracker == STRlength) {
                        currentRun++;
                        if (currentRun > longestRun) {
                            longestRun = currentRun;
                        }
                        tracker = 0;
                        j = -1;
                    }
                }
            }

        }
        return longestRun;
    }
}
