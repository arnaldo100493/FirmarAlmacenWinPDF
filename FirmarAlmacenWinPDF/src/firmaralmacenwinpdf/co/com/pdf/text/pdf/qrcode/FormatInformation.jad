// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FormatInformation.java

package co.com.pdf.text.pdf.qrcode;


// Referenced classes of package co.com.pdf.text.pdf.qrcode:
//            ErrorCorrectionLevel

final class FormatInformation
{

    private FormatInformation(int formatInfo)
    {
        errorCorrectionLevel = ErrorCorrectionLevel.forBits(formatInfo >> 3 & 3);
        dataMask = (byte)(formatInfo & 7);
    }

    static int numBitsDiffering(int a, int b)
    {
        a ^= b;
        return BITS_SET_IN_HALF_BYTE[a & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 4 & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 8 & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 12 & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 16 & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 20 & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 24 & 0xf] + BITS_SET_IN_HALF_BYTE[a >>> 28 & 0xf];
    }

    static FormatInformation decodeFormatInformation(int maskedFormatInfo1, int maskedFormatInfo2)
    {
        FormatInformation formatInfo = doDecodeFormatInformation(maskedFormatInfo1, maskedFormatInfo2);
        if(formatInfo != null)
            return formatInfo;
        else
            return doDecodeFormatInformation(maskedFormatInfo1 ^ 0x5412, maskedFormatInfo2 ^ 0x5412);
    }

    private static FormatInformation doDecodeFormatInformation(int maskedFormatInfo1, int maskedFormatInfo2)
    {
        int bestDifference = 0x7fffffff;
        int bestFormatInfo = 0;
        for(int i = 0; i < FORMAT_INFO_DECODE_LOOKUP.length; i++)
        {
            int decodeInfo[] = FORMAT_INFO_DECODE_LOOKUP[i];
            int targetInfo = decodeInfo[0];
            if(targetInfo == maskedFormatInfo1 || targetInfo == maskedFormatInfo2)
                return new FormatInformation(decodeInfo[1]);
            int bitsDifference = numBitsDiffering(maskedFormatInfo1, targetInfo);
            if(bitsDifference < bestDifference)
            {
                bestFormatInfo = decodeInfo[1];
                bestDifference = bitsDifference;
            }
            if(maskedFormatInfo1 == maskedFormatInfo2)
                continue;
            bitsDifference = numBitsDiffering(maskedFormatInfo2, targetInfo);
            if(bitsDifference < bestDifference)
            {
                bestFormatInfo = decodeInfo[1];
                bestDifference = bitsDifference;
            }
        }

        if(bestDifference <= 3)
            return new FormatInformation(bestFormatInfo);
        else
            return null;
    }

    ErrorCorrectionLevel getErrorCorrectionLevel()
    {
        return errorCorrectionLevel;
    }

    byte getDataMask()
    {
        return dataMask;
    }

    public int hashCode()
    {
        return errorCorrectionLevel.ordinal() << 3 | dataMask;
    }

    public boolean equals(Object o)
    {
        if(!(o instanceof FormatInformation))
        {
            return false;
        } else
        {
            FormatInformation other = (FormatInformation)o;
            return errorCorrectionLevel == other.errorCorrectionLevel && dataMask == other.dataMask;
        }
    }

    private static final int FORMAT_INFO_MASK_QR = 21522;
    private static final int FORMAT_INFO_DECODE_LOOKUP[][] = {
        {
            21522, 0
        }, {
            20773, 1
        }, {
            24188, 2
        }, {
            23371, 3
        }, {
            17913, 4
        }, {
            16590, 5
        }, {
            20375, 6
        }, {
            19104, 7
        }, {
            30660, 8
        }, {
            29427, 9
        }, {
            32170, 10
        }, {
            30877, 11
        }, {
            26159, 12
        }, {
            25368, 13
        }, {
            27713, 14
        }, {
            26998, 15
        }, {
            5769, 16
        }, {
            5054, 17
        }, {
            7399, 18
        }, {
            6608, 19
        }, {
            1890, 20
        }, {
            597, 21
        }, {
            3340, 22
        }, {
            2107, 23
        }, {
            13663, 24
        }, {
            12392, 25
        }, {
            16177, 26
        }, {
            14854, 27
        }, {
            9396, 28
        }, {
            8579, 29
        }, {
            11994, 30
        }, {
            11245, 31
        }
    };
    private static final int BITS_SET_IN_HALF_BYTE[] = {
        0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 
        2, 3, 2, 3, 3, 4
    };
    private final ErrorCorrectionLevel errorCorrectionLevel;
    private final byte dataMask;

}
