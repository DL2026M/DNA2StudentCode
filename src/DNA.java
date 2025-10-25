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
    private static final int RADIX = 4;

    public static long hash(String segment) {
        long seqHash = 0;
        // Calculating the hash for a given segment using Horner's method
        for (int i = 0; i < segment.length(); i++) {
            seqHash = (seqHash * RADIX + segment.charAt(i));
        }
        return seqHash;
    }

    public static int STRCount(String sequence, String STR) {
        final int SEQUENCE_LENGTH = sequence.length();
        final int STR_LENGTH = STR.length();
        int longestRun = 0;
        int currentRun = 0;
        long power = 1;

        for (int i = 0; i < STR_LENGTH - 1; i++) {
            power = (RADIX * power);
        }

        long strHash = hash(STR);
        long seqHash = hash(sequence.substring(0, STR_LENGTH));
        for (int i = STR_LENGTH - 1; i < SEQUENCE_LENGTH; i++) {
            // Monte Carlo Algorithm - Not checking for collisions as they are extremely rare and would require linear
            // time to do so
            if (strHash == seqHash) {
                currentRun++;
                if (currentRun > longestRun) {
                    longestRun = currentRun;
                }
                if (!(STR_LENGTH + i + 1 > SEQUENCE_LENGTH)) {
                    // Shifting over by one short-tandem repeat length and recalculating the new hash
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
                    // If a run has just ended, then the index is adjusted to restart checking at the start of the failed
                    // short-tandem sequence
                    int lower = i - STR_LENGTH * 2 + 2;
                    String tricky_chunk = sequence.substring(lower, lower + STR_LENGTH);
                    seqHash = hash(tricky_chunk);
                    i = i - STR_LENGTH;
                }
                else if (i + 1 < SEQUENCE_LENGTH) {
                    // Shifting the hash to the right by one term
                    seqHash = (seqHash - (sequence.charAt(i - STR_LENGTH + 1) * power)) * 4 + sequence.charAt(i + 1);
                }
                currentRun = 0;
            }
        }
        return longestRun;
    }
}
