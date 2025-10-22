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
    public static long hash(String t) {
        int h = 0;
        for (int i = 0; i < t.length(); i++)
            h = (h * RADIX + t.charAt(i));
        return h;
    }

    public static int STRCount(String sequence, String STR) {
        final int SEQUENCE_LENGTH = sequence.length();
        final int STR_LENGTH = STR.length();

        long strHash = hash(STR);
        long seqHash = hash(sequence[0, STR_LENGTH - 1]);
        for (int i = STR_LENGTH; i < SEQUENCE_LENGTH; i++)
            if (strHash == seqHash)
                // begin checking for consecutive appearances
                // whereever you are add the length of the STR
                seqHash = hash(sequence[1 + (i - STR_LENGTH), i]);

    }

}
