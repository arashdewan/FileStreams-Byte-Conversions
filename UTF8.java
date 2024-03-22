import java.util.Arrays;




public class UTF8 {
    public static void main(String[] args) {
        System.out.println(testByteCount(false));
        System.out.println(testToBytes(false));
    }




    public static int byteCount(int codePoint) {
        if (codePoint < 0 ) {
            return 0;
        }

        // Four cases which return 1, 2, 3, and 4, respectively...
        if (codePoint <= 0x7F) {
            return 1;
        } else if (codePoint <= 0x7FF) {
            return 2;
        } else if (codePoint <= 0xFFFF) {
            return 3;
        } else if (codePoint <= 0x10FFFF) {
            return 4;
        } 



        return 0; // For large code points which cannot be encoded.
    }




    public static byte[] toBytes(int codePoint) {
        int count = byteCount(codePoint);


        byte[] b = new byte[count];


        // Four cases...
        if (count == 1) {
            b[0] = (byte) codePoint;
        } else if (count == 2) {
            b[0] = (byte) (codePoint / 64 + 192);
            b[1] = (byte) (codePoint % 64 + 128);
        } else if (count == 3) {
            b[0] = (byte) (codePoint / 4096 + 224);
            b[1] = (byte) (codePoint / 64 % 64 + 128);
            b[2] = (byte) (codePoint % 64 + 128);
        } else if (count == 4) {
            b[0] = (byte) (codePoint / 262144 + 240);
            b[1] = (byte) (codePoint / 4096 % 64 + 128);
            b[2] = (byte) (codePoint / 64 % 64 + 128);
            b[3] = (byte) (codePoint % 64 + 128);
        }



        return b;
    }


    private static boolean testByteCount(boolean print) {
        boolean bool = true;


        int[] codePoints = new int[] {
            'x', 'y', 'u', '~',
            230, 1345, 1488, 1551,
            8450, 8477, 8484, 64289,
            119046, 119047, 127934, 128512
        };

        for (int i = 0; i < 4; ++i) {
            for (int j = 0; j < 4; ++j) {
                bool &= (byteCount(codePoints[4*i + j]) == (i + 1));
            }
        }


        if (print) {
            for (int codePoint : codePoints) {
                System.out.print(Character.toChars(codePoint));
            }
            System.out.println();
        }


        return bool;
    }




    private static boolean testToBytes(boolean print) {
        boolean bool = true;


        int[] codePoints = new int[] {
            'x', 'y', 'u', '~',
            230, 1345, 1488, 1551,
            8450, 8477, 8484, 64289,
            119046, 119047, 127934, 128512
        };

        byte[] bytes = new byte [] {
            120, 121, 117, 126,
            -61, -90, -43, -127, -41, -112, -40, -113,
            -30, -124, -126, -30, -124, -99, -30, -124, -92, -17, -84, -95,
            -16, -99, -124, -122, -16, -99, -124, -121, -16, -97, -114, -66, -16, -97, -104, -128
        };


        bool &= Arrays.equals(toBytes(codePoints[ 0]), new byte[] { bytes[ 0] });
        bool &= Arrays.equals(toBytes(codePoints[ 1]), new byte[] { bytes[ 1] });
        bool &= Arrays.equals(toBytes(codePoints[ 2]), new byte[] { bytes[ 2] });
        bool &= Arrays.equals(toBytes(codePoints[ 3]), new byte[] { bytes[ 3] });

        bool &= Arrays.equals(toBytes(codePoints[ 4]), new byte[] { bytes[ 4], bytes[ 5] });
        bool &= Arrays.equals(toBytes(codePoints[ 5]), new byte[] { bytes[ 6], bytes[ 7] });
        bool &= Arrays.equals(toBytes(codePoints[ 6]), new byte[] { bytes[ 8], bytes[ 9] });
        bool &= Arrays.equals(toBytes(codePoints[ 7]), new byte[] { bytes[10], bytes[11] });

        bool &= Arrays.equals(toBytes(codePoints[ 8]), new byte[] { bytes[12], bytes[13], bytes[14] });
        bool &= Arrays.equals(toBytes(codePoints[ 9]), new byte[] { bytes[15], bytes[16], bytes[17] });
        bool &= Arrays.equals(toBytes(codePoints[10]), new byte[] { bytes[18], bytes[19], bytes[20] });
        bool &= Arrays.equals(toBytes(codePoints[11]), new byte[] { bytes[21], bytes[22], bytes[23] });

        bool &= Arrays.equals(toBytes(codePoints[12]), new byte[] { bytes[24], bytes[25], bytes[26], bytes[27] });
        bool &= Arrays.equals(toBytes(codePoints[13]), new byte[] { bytes[28], bytes[29], bytes[30], bytes[31] });
        bool &= Arrays.equals(toBytes(codePoints[14]), new byte[] { bytes[32], bytes[33], bytes[34], bytes[35] });
        bool &= Arrays.equals(toBytes(codePoints[15]), new byte[] { bytes[36], bytes[37], bytes[38], bytes[39] });


        if (print) {
            for (int codePoint : codePoints) {
                System.out.print(Character.toChars(codePoint));
            }
            System.out.println();
        }


        return bool;
    }
}
