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
    private static final int RADIX = 4;

    // Horners method
    public static long hash(String segment) {
        long seqHash = 0;
        for (int i = 0; i < segment.length(); i++) {
            seqHash = (seqHash * RADIX + segment.charAt(i));
        }
        return seqHash;
    }
    // Rabin-Karp Fingerprint Algorithm
    public static int STRCount(String sequence, String STR) {
        final int SEQUENCE_LENGTH = sequence.length();
        final int STR_LENGTH = STR.length();
        int longestRun = 0;
        int currentRun = 0;
        long power = 1;
        for (int i = 0; i < STR.length() - 1; i++) {
            power = (RADIX * power);
        }

        long strHash = hash(STR);
        long seqHash = hash(sequence.substring(0, STR_LENGTH));
        for (int i = STR_LENGTH - 1; i < SEQUENCE_LENGTH; i++) {
            if (strHash == seqHash) {
                // begin checking for consecutive appearances
                currentRun++;
                if (currentRun > longestRun) {
                    longestRun = currentRun;
                }
                if (!(STR_LENGTH + i + 1 >= SEQUENCE_LENGTH)) {
                    String next_chunk = sequence.substring(i + 1, i + STR_LENGTH + 1);
                    seqHash = hash(next_chunk);
                    i += STR_LENGTH - 1;
                }
                else {
                    return longestRun;
                }
            }
            else {
                if (currentRun > 0) {
                    int lower = i - STR_LENGTH*2 + 2;
                    String tricky_chunk = sequence.substring(lower, lower + STR_LENGTH);
                    seqHash = hash(tricky_chunk);
                    i = i - STR_LENGTH;
                }
                else if (i + 1 < SEQUENCE_LENGTH) {
                    seqHash = (seqHash - (sequence.charAt(i - STR_LENGTH + 1) * power)) * 4 + sequence.charAt(i + 1);
                }
                currentRun = 0;
            }

        }
        return longestRun;
        // tricky: if another match is within current match starting, then keep track of the past length and then check the new pattern, adding them and checking
    }
}
