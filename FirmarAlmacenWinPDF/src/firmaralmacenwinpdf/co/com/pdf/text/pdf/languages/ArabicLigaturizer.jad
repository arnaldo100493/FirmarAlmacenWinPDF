// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ArabicLigaturizer.java

package co.com.pdf.text.pdf.languages;

import co.com.pdf.text.pdf.BidiLine;
import co.com.pdf.text.pdf.BidiOrder;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf.languages:
//            LanguageProcessor

public class ArabicLigaturizer
    implements LanguageProcessor
{
    static class charstruct
    {

        char basechar;
        char mark1;
        char vowel;
        int lignum;
        int numshapes;

        charstruct()
        {
            numshapes = 1;
        }
    }


    static boolean isVowel(char s)
    {
        return s >= '\u064B' && s <= '\u0655' || s == '\u0670';
    }

    static char charshape(char s, int which)
    {
        if(s >= '\u0621' && s <= '\u06D3')
        {
            char c[] = (char[])maptable.get(Character.valueOf(s));
            if(c != null)
                return c[which + 1];
        } else
        if(s >= '\uFEF5' && s <= '\uFEFB')
            return (char)(s + which);
        return s;
    }

    static int shapecount(char s)
    {
        if(s >= '\u0621' && s <= '\u06D3' && !isVowel(s))
        {
            char c[] = (char[])maptable.get(Character.valueOf(s));
            if(c != null)
                return c.length - 1;
        } else
        if(s == '\u200D')
            return 4;
        return 1;
    }

    static int ligature(char newchar, charstruct oldchar)
    {
        int retval = 0;
        if(oldchar.basechar == 0)
            return 0;
        if(isVowel(newchar))
        {
            retval = 1;
            if(oldchar.vowel != 0 && newchar != '\u0651')
                retval = 2;
            switch(newchar)
            {
            case 1617: 
                if(oldchar.mark1 == 0)
                    oldchar.mark1 = '\u0651';
                else
                    return 0;
                break;

            case 1621: 
                switch(oldchar.basechar)
                {
                case 1575: 
                    oldchar.basechar = '\u0625';
                    retval = 2;
                    break;

                case 65275: 
                    oldchar.basechar = '\uFEF9';
                    retval = 2;
                    break;

                default:
                    oldchar.mark1 = '\u0655';
                    break;
                }
                break;

            case 1620: 
                switch(oldchar.basechar)
                {
                case 1575: 
                    oldchar.basechar = '\u0623';
                    retval = 2;
                    break;

                case 65275: 
                    oldchar.basechar = '\uFEF7';
                    retval = 2;
                    break;

                case 1608: 
                    oldchar.basechar = '\u0624';
                    retval = 2;
                    break;

                case 1609: 
                case 1610: 
                case 1740: 
                    oldchar.basechar = '\u0626';
                    retval = 2;
                    break;

                default:
                    oldchar.mark1 = '\u0654';
                    break;
                }
                break;

            case 1619: 
                switch(oldchar.basechar)
                {
                case 1575: 
                    oldchar.basechar = '\u0622';
                    retval = 2;
                    break;
                }
                break;

            case 1618: 
            default:
                oldchar.vowel = newchar;
                break;
            }
            if(retval == 1)
                oldchar.lignum++;
            return retval;
        }
        if(oldchar.vowel != 0)
            return 0;
        switch(oldchar.basechar)
        {
        case 1604: 
            switch(newchar)
            {
            case 1575: 
                oldchar.basechar = '\uFEFB';
                oldchar.numshapes = 2;
                retval = 3;
                break;

            case 1571: 
                oldchar.basechar = '\uFEF7';
                oldchar.numshapes = 2;
                retval = 3;
                break;

            case 1573: 
                oldchar.basechar = '\uFEF9';
                oldchar.numshapes = 2;
                retval = 3;
                break;

            case 1570: 
                oldchar.basechar = '\uFEF5';
                oldchar.numshapes = 2;
                retval = 3;
                break;
            }
            break;

        case 0: // '\0'
            oldchar.basechar = newchar;
            oldchar.numshapes = shapecount(newchar);
            retval = 1;
            break;
        }
        return retval;
    }

    static void copycstostring(StringBuffer string, charstruct s, int level)
    {
        if(s.basechar == 0)
            return;
        string.append(s.basechar);
        s.lignum--;
        if(s.mark1 != 0)
            if((level & 1) == 0)
            {
                string.append(s.mark1);
                s.lignum--;
            } else
            {
                s.lignum--;
            }
        if(s.vowel != 0)
            if((level & 1) == 0)
            {
                string.append(s.vowel);
                s.lignum--;
            } else
            {
                s.lignum--;
            }
    }

    static void doublelig(StringBuffer string, int level)
    {
        int len;
        int olen = len = string.length();
        int j = 0;
        for(int si = 1; si < olen;)
        {
            char lapresult = '\0';
            if((level & 4) != 0)
                switch(string.charAt(j))
                {
                default:
                    break;

                case 1617: 
                    switch(string.charAt(si))
                    {
                    case 1616: 
                        lapresult = '\uFC62';
                        break;

                    case 1614: 
                        lapresult = '\uFC60';
                        break;

                    case 1615: 
                        lapresult = '\uFC61';
                        break;

                    case 1612: 
                        lapresult = '\uFC5E';
                        break;

                    case 1613: 
                        lapresult = '\uFC5F';
                        break;
                    }
                    break;

                case 1616: 
                    if(string.charAt(si) == '\u0651')
                        lapresult = '\uFC62';
                    break;

                case 1614: 
                    if(string.charAt(si) == '\u0651')
                        lapresult = '\uFC60';
                    break;

                case 1615: 
                    if(string.charAt(si) == '\u0651')
                        lapresult = '\uFC61';
                    break;
                }
            if((level & 8) != 0)
                switch(string.charAt(j))
                {
                default:
                    break;

                case 65247: 
                    switch(string.charAt(si))
                    {
                    case 65182: 
                        lapresult = '\uFC3F';
                        break;

                    case 65184: 
                        lapresult = '\uFCC9';
                        break;

                    case 65186: 
                        lapresult = '\uFC40';
                        break;

                    case 65188: 
                        lapresult = '\uFCCA';
                        break;

                    case 65190: 
                        lapresult = '\uFC41';
                        break;

                    case 65192: 
                        lapresult = '\uFCCB';
                        break;

                    case 65250: 
                        lapresult = '\uFC42';
                        break;

                    case 65252: 
                        lapresult = '\uFCCC';
                        break;
                    }
                    break;

                case 65175: 
                    switch(string.charAt(si))
                    {
                    case 65184: 
                        lapresult = '\uFCA1';
                        break;

                    case 65188: 
                        lapresult = '\uFCA2';
                        break;

                    case 65192: 
                        lapresult = '\uFCA3';
                        break;
                    }
                    break;

                case 65169: 
                    switch(string.charAt(si))
                    {
                    case 65184: 
                        lapresult = '\uFC9C';
                        break;

                    case 65188: 
                        lapresult = '\uFC9D';
                        break;

                    case 65192: 
                        lapresult = '\uFC9E';
                        break;
                    }
                    break;

                case 65255: 
                    switch(string.charAt(si))
                    {
                    case 65184: 
                        lapresult = '\uFCD2';
                        break;

                    case 65188: 
                        lapresult = '\uFCD3';
                        break;

                    case 65192: 
                        lapresult = '\uFCD4';
                        break;
                    }
                    break;

                case 65256: 
                    switch(string.charAt(si))
                    {
                    case 65198: 
                        lapresult = '\uFC8A';
                        break;

                    case 65200: 
                        lapresult = '\uFC8B';
                        break;
                    }
                    break;

                case 65251: 
                    switch(string.charAt(si))
                    {
                    case 65184: 
                        lapresult = '\uFCCE';
                        break;

                    case 65188: 
                        lapresult = '\uFCCF';
                        break;

                    case 65192: 
                        lapresult = '\uFCD0';
                        break;

                    case 65252: 
                        lapresult = '\uFCD1';
                        break;
                    }
                    break;

                case 65235: 
                    switch(string.charAt(si))
                    {
                    case 65266: 
                        lapresult = '\uFC32';
                        break;
                    }
                    break;
                }
            if(lapresult != 0)
            {
                string.setCharAt(j, lapresult);
                len--;
                si++;
            } else
            {
                j++;
                string.setCharAt(j, string.charAt(si));
                si++;
            }
        }

        string.setLength(len);
    }

    static boolean connects_to_left(charstruct a)
    {
        return a.numshapes > 2;
    }

    static void shape(char text[], StringBuffer string, int level)
    {
        int p = 0;
        charstruct oldchar = new charstruct();
        charstruct curchar = new charstruct();
        int which;
        while(p < text.length) 
        {
            char nextletter = text[p++];
            int join = ligature(nextletter, curchar);
            if(join == 0)
            {
                int nc = shapecount(nextletter);
                if(nc == 1)
                    which = 0;
                else
                    which = 2;
                if(connects_to_left(oldchar))
                    which++;
                which %= curchar.numshapes;
                curchar.basechar = charshape(curchar.basechar, which);
                copycstostring(string, oldchar, level);
                oldchar = curchar;
                curchar = new charstruct();
                curchar.basechar = nextletter;
                curchar.numshapes = nc;
                curchar.lignum++;
            } else
            if(join != 1);
        }
        if(connects_to_left(oldchar))
            which = 1;
        else
            which = 0;
        which %= curchar.numshapes;
        curchar.basechar = charshape(curchar.basechar, which);
        copycstostring(string, oldchar, level);
        copycstostring(string, curchar, level);
    }

    public static int arabic_shape(char src[], int srcoffset, int srclength, char dest[], int destoffset, int destlength, int level)
    {
        char str[] = new char[srclength];
        for(int k = (srclength + srcoffset) - 1; k >= srcoffset; k--)
            str[k - srcoffset] = src[k];

        StringBuffer string = new StringBuffer(srclength);
        shape(str, string, level);
        if((level & 0xc) != 0)
            doublelig(string, level);
        System.arraycopy(string.toString().toCharArray(), 0, dest, destoffset, string.length());
        return string.length();
    }

    public static void processNumbers(char text[], int offset, int length, int options)
    {
        int limit = offset + length;
        if((options & 0xe0) != 0)
        {
            char digitBase = '0';
            switch(options & 0x100)
            {
            case 0: // '\0'
                digitBase = '\u0660';
                break;

            case 256: 
                digitBase = '\u06F0';
                break;
            }
label0:
            switch(options & 0xe0)
            {
            default:
                break;

            case 32: // ' '
            {
                int digitDelta = digitBase - 48;
                int i = offset;
                do
                {
                    if(i >= limit)
                        break label0;
                    char ch = text[i];
                    if(ch <= '9' && ch >= '0')
                        text[i] += digitDelta;
                    i++;
                } while(true);
            }

            case 64: // '@'
            {
                char digitTop = (char)(digitBase + 9);
                int digitDelta = 48 - digitBase;
                int i = offset;
                do
                {
                    if(i >= limit)
                        break label0;
                    char ch = text[i];
                    if(ch <= digitTop && ch >= digitBase)
                        text[i] += digitDelta;
                    i++;
                } while(true);
            }

            case 96: // '`'
            {
                shapeToArabicDigitsWithContext(text, 0, length, digitBase, false);
                break;
            }

            case 128: 
            {
                shapeToArabicDigitsWithContext(text, 0, length, digitBase, true);
                break;
            }
            }
        }
    }

    static void shapeToArabicDigitsWithContext(char dest[], int start, int length, char digitBase, boolean lastStrongWasAL)
    {
        digitBase -= '0';
        int limit = start + length;
        for(int i = start; i < limit; i++)
        {
            char ch = dest[i];
            switch(BidiOrder.getDirection(ch))
            {
            case 1: // '\001'
            case 2: // '\002'
            case 5: // '\005'
            case 6: // '\006'
            case 7: // '\007'
            default:
                break;

            case 0: // '\0'
            case 3: // '\003'
                lastStrongWasAL = false;
                break;

            case 4: // '\004'
                lastStrongWasAL = true;
                break;

            case 8: // '\b'
                if(lastStrongWasAL && ch <= '9')
                    dest[i] = (char)(ch + digitBase);
                break;
            }
        }

    }

    public ArabicLigaturizer()
    {
        options = 0;
        runDirection = 3;
    }

    public ArabicLigaturizer(int runDirection, int options)
    {
        this.options = 0;
        this.runDirection = 3;
        this.runDirection = runDirection;
        this.options = options;
    }

    public String process(String s)
    {
        return BidiLine.processLTR(s, runDirection, options);
    }

    public boolean isRTL()
    {
        return true;
    }

    private static HashMap maptable;
    private static final char ALEF = 1575;
    private static final char ALEFHAMZA = 1571;
    private static final char ALEFHAMZABELOW = 1573;
    private static final char ALEFMADDA = 1570;
    private static final char LAM = 1604;
    private static final char HAMZA = 1569;
    private static final char TATWEEL = 1600;
    private static final char ZWJ = 8205;
    private static final char HAMZAABOVE = 1620;
    private static final char HAMZABELOW = 1621;
    private static final char WAWHAMZA = 1572;
    private static final char YEHHAMZA = 1574;
    private static final char WAW = 1608;
    private static final char ALEFMAKSURA = 1609;
    private static final char YEH = 1610;
    private static final char FARSIYEH = 1740;
    private static final char SHADDA = 1617;
    private static final char KASRA = 1616;
    private static final char FATHA = 1614;
    private static final char DAMMA = 1615;
    private static final char MADDA = 1619;
    private static final char LAM_ALEF = 65275;
    private static final char LAM_ALEFHAMZA = 65271;
    private static final char LAM_ALEFHAMZABELOW = 65273;
    private static final char LAM_ALEFMADDA = 65269;
    private static final char chartable[][] = {
        {
            '\u0621', '\uFE80'
        }, {
            '\u0622', '\uFE81', '\uFE82'
        }, {
            '\u0623', '\uFE83', '\uFE84'
        }, {
            '\u0624', '\uFE85', '\uFE86'
        }, {
            '\u0625', '\uFE87', '\uFE88'
        }, {
            '\u0626', '\uFE89', '\uFE8A', '\uFE8B', '\uFE8C'
        }, {
            '\u0627', '\uFE8D', '\uFE8E'
        }, {
            '\u0628', '\uFE8F', '\uFE90', '\uFE91', '\uFE92'
        }, {
            '\u0629', '\uFE93', '\uFE94'
        }, {
            '\u062A', '\uFE95', '\uFE96', '\uFE97', '\uFE98'
        }, {
            '\u062B', '\uFE99', '\uFE9A', '\uFE9B', '\uFE9C'
        }, {
            '\u062C', '\uFE9D', '\uFE9E', '\uFE9F', '\uFEA0'
        }, {
            '\u062D', '\uFEA1', '\uFEA2', '\uFEA3', '\uFEA4'
        }, {
            '\u062E', '\uFEA5', '\uFEA6', '\uFEA7', '\uFEA8'
        }, {
            '\u062F', '\uFEA9', '\uFEAA'
        }, {
            '\u0630', '\uFEAB', '\uFEAC'
        }, {
            '\u0631', '\uFEAD', '\uFEAE'
        }, {
            '\u0632', '\uFEAF', '\uFEB0'
        }, {
            '\u0633', '\uFEB1', '\uFEB2', '\uFEB3', '\uFEB4'
        }, {
            '\u0634', '\uFEB5', '\uFEB6', '\uFEB7', '\uFEB8'
        }, {
            '\u0635', '\uFEB9', '\uFEBA', '\uFEBB', '\uFEBC'
        }, {
            '\u0636', '\uFEBD', '\uFEBE', '\uFEBF', '\uFEC0'
        }, {
            '\u0637', '\uFEC1', '\uFEC2', '\uFEC3', '\uFEC4'
        }, {
            '\u0638', '\uFEC5', '\uFEC6', '\uFEC7', '\uFEC8'
        }, {
            '\u0639', '\uFEC9', '\uFECA', '\uFECB', '\uFECC'
        }, {
            '\u063A', '\uFECD', '\uFECE', '\uFECF', '\uFED0'
        }, {
            '\u0640', '\u0640', '\u0640', '\u0640', '\u0640'
        }, {
            '\u0641', '\uFED1', '\uFED2', '\uFED3', '\uFED4'
        }, {
            '\u0642', '\uFED5', '\uFED6', '\uFED7', '\uFED8'
        }, {
            '\u0643', '\uFED9', '\uFEDA', '\uFEDB', '\uFEDC'
        }, {
            '\u0644', '\uFEDD', '\uFEDE', '\uFEDF', '\uFEE0'
        }, {
            '\u0645', '\uFEE1', '\uFEE2', '\uFEE3', '\uFEE4'
        }, {
            '\u0646', '\uFEE5', '\uFEE6', '\uFEE7', '\uFEE8'
        }, {
            '\u0647', '\uFEE9', '\uFEEA', '\uFEEB', '\uFEEC'
        }, {
            '\u0648', '\uFEED', '\uFEEE'
        }, {
            '\u0649', '\uFEEF', '\uFEF0', '\uFBE8', '\uFBE9'
        }, {
            '\u064A', '\uFEF1', '\uFEF2', '\uFEF3', '\uFEF4'
        }, {
            '\u0671', '\uFB50', '\uFB51'
        }, {
            '\u0679', '\uFB66', '\uFB67', '\uFB68', '\uFB69'
        }, {
            '\u067A', '\uFB5E', '\uFB5F', '\uFB60', '\uFB61'
        }, {
            '\u067B', '\uFB52', '\uFB53', '\uFB54', '\uFB55'
        }, {
            '\u067E', '\uFB56', '\uFB57', '\uFB58', '\uFB59'
        }, {
            '\u067F', '\uFB62', '\uFB63', '\uFB64', '\uFB65'
        }, {
            '\u0680', '\uFB5A', '\uFB5B', '\uFB5C', '\uFB5D'
        }, {
            '\u0683', '\uFB76', '\uFB77', '\uFB78', '\uFB79'
        }, {
            '\u0684', '\uFB72', '\uFB73', '\uFB74', '\uFB75'
        }, {
            '\u0686', '\uFB7A', '\uFB7B', '\uFB7C', '\uFB7D'
        }, {
            '\u0687', '\uFB7E', '\uFB7F', '\uFB80', '\uFB81'
        }, {
            '\u0688', '\uFB88', '\uFB89'
        }, {
            '\u068C', '\uFB84', '\uFB85'
        }, {
            '\u068D', '\uFB82', '\uFB83'
        }, {
            '\u068E', '\uFB86', '\uFB87'
        }, {
            '\u0691', '\uFB8C', '\uFB8D'
        }, {
            '\u0698', '\uFB8A', '\uFB8B'
        }, {
            '\u06A4', '\uFB6A', '\uFB6B', '\uFB6C', '\uFB6D'
        }, {
            '\u06A6', '\uFB6E', '\uFB6F', '\uFB70', '\uFB71'
        }, {
            '\u06A9', '\uFB8E', '\uFB8F', '\uFB90', '\uFB91'
        }, {
            '\u06AD', '\uFBD3', '\uFBD4', '\uFBD5', '\uFBD6'
        }, {
            '\u06AF', '\uFB92', '\uFB93', '\uFB94', '\uFB95'
        }, {
            '\u06B1', '\uFB9A', '\uFB9B', '\uFB9C', '\uFB9D'
        }, {
            '\u06B3', '\uFB96', '\uFB97', '\uFB98', '\uFB99'
        }, {
            '\u06BA', '\uFB9E', '\uFB9F'
        }, {
            '\u06BB', '\uFBA0', '\uFBA1', '\uFBA2', '\uFBA3'
        }, {
            '\u06BE', '\uFBAA', '\uFBAB', '\uFBAC', '\uFBAD'
        }, {
            '\u06C0', '\uFBA4', '\uFBA5'
        }, {
            '\u06C1', '\uFBA6', '\uFBA7', '\uFBA8', '\uFBA9'
        }, {
            '\u06C5', '\uFBE0', '\uFBE1'
        }, {
            '\u06C6', '\uFBD9', '\uFBDA'
        }, {
            '\u06C7', '\uFBD7', '\uFBD8'
        }, {
            '\u06C8', '\uFBDB', '\uFBDC'
        }, {
            '\u06C9', '\uFBE2', '\uFBE3'
        }, {
            '\u06CB', '\uFBDE', '\uFBDF'
        }, {
            '\u06CC', '\uFBFC', '\uFBFD', '\uFBFE', '\uFBFF'
        }, {
            '\u06D0', '\uFBE4', '\uFBE5', '\uFBE6', '\uFBE7'
        }, {
            '\u06D2', '\uFBAE', '\uFBAF'
        }, {
            '\u06D3', '\uFBB0', '\uFBB1'
        }
    };
    public static final int ar_nothing = 0;
    public static final int ar_novowel = 1;
    public static final int ar_composedtashkeel = 4;
    public static final int ar_lig = 8;
    public static final int DIGITS_EN2AN = 32;
    public static final int DIGITS_AN2EN = 64;
    public static final int DIGITS_EN2AN_INIT_LR = 96;
    public static final int DIGITS_EN2AN_INIT_AL = 128;
    private static final int DIGITS_RESERVED = 160;
    public static final int DIGITS_MASK = 224;
    public static final int DIGIT_TYPE_AN = 0;
    public static final int DIGIT_TYPE_AN_EXTENDED = 256;
    public static final int DIGIT_TYPE_MASK = 256;
    protected int options;
    protected int runDirection;

    static 
    {
        maptable = new HashMap();
        char arr$[][] = chartable;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            char c[] = arr$[i$];
            maptable.put(Character.valueOf(c[0]), c);
        }

    }
}
