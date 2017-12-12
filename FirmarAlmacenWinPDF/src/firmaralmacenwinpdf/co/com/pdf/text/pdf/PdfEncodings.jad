// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncodings.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.*;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf:
//            ExtraEncoding, IntHashtable

public class PdfEncodings
{
    private static class SymbolTTConversion
        implements ExtraEncoding
    {

        public byte[] charToByte(char char1, String encoding)
        {
            if((char1 & 0xff00) == 0 || (char1 & 0xff00) == 61440)
                return (new byte[] {
                    (byte)char1
                });
            else
                return new byte[0];
        }

        public byte[] charToByte(String text, String encoding)
        {
            char ch[] = text.toCharArray();
            byte b[] = new byte[ch.length];
            int ptr = 0;
            int len = ch.length;
            for(int k = 0; k < len; k++)
            {
                char c = ch[k];
                if((c & 0xff00) == 0 || (c & 0xff00) == 61440)
                    b[ptr++] = (byte)c;
            }

            if(ptr == len)
            {
                return b;
            } else
            {
                byte b2[] = new byte[ptr];
                System.arraycopy(b, 0, b2, 0, ptr);
                return b2;
            }
        }

        public String byteToChar(byte b[], String encoding)
        {
            return null;
        }

        private SymbolTTConversion()
        {
        }

    }

    private static class SymbolConversion
        implements ExtraEncoding
    {

        public byte[] charToByte(String text, String encoding)
        {
            char cc[] = text.toCharArray();
            byte b[] = new byte[cc.length];
            int ptr = 0;
            int len = cc.length;
            for(int k = 0; k < len; k++)
            {
                char c = cc[k];
                byte v = (byte)translation.get(c);
                if(v != 0)
                    b[ptr++] = v;
            }

            if(ptr == len)
            {
                return b;
            } else
            {
                byte b2[] = new byte[ptr];
                System.arraycopy(b, 0, b2, 0, ptr);
                return b2;
            }
        }

        public byte[] charToByte(char char1, String encoding)
        {
            byte v = (byte)translation.get(char1);
            if(v != 0)
                return (new byte[] {
                    v
                });
            else
                return new byte[0];
        }

        public String byteToChar(byte b[], String encoding)
        {
            return null;
        }

        private static final IntHashtable t1;
        private static final IntHashtable t2;
        private IntHashtable translation;
        private static final char table1[] = {
            ' ', '!', '\u2200', '#', '\u2203', '%', '&', '\u220B', '(', ')', 
            '*', '+', ',', '-', '.', '/', '0', '1', '2', '3', 
            '4', '5', '6', '7', '8', '9', ':', ';', '<', '=', 
            '>', '?', '\u2245', '\u0391', '\u0392', '\u03A7', '\u0394', '\u0395', '\u03A6', '\u0393', 
            '\u0397', '\u0399', '\u03D1', '\u039A', '\u039B', '\u039C', '\u039D', '\u039F', '\u03A0', '\u0398', 
            '\u03A1', '\u03A3', '\u03A4', '\u03A5', '\u03C2', '\u03A9', '\u039E', '\u03A8', '\u0396', '[', 
            '\u2234', ']', '\u22A5', '_', '\u0305', '\u03B1', '\u03B2', '\u03C7', '\u03B4', '\u03B5', 
            '\u03D5', '\u03B3', '\u03B7', '\u03B9', '\u03C6', '\u03BA', '\u03BB', '\u03BC', '\u03BD', '\u03BF', 
            '\u03C0', '\u03B8', '\u03C1', '\u03C3', '\u03C4', '\u03C5', '\u03D6', '\u03C9', '\u03BE', '\u03C8', 
            '\u03B6', '{', '|', '}', '~', '\0', '\0', '\0', '\0', '\0', 
            '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 
            '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 
            '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\u20AC', '\u03D2', 
            '\u2032', '\u2264', '\u2044', '\u221E', '\u0192', '\u2663', '\u2666', '\u2665', '\u2660', '\u2194', 
            '\u2190', '\u2191', '\u2192', '\u2193', '\260', '\261', '\u2033', '\u2265', '\327', '\u221D', 
            '\u2202', '\u2022', '\367', '\u2260', '\u2261', '\u2248', '\u2026', '\u2502', '\u2500', '\u21B5', 
            '\u2135', '\u2111', '\u211C', '\u2118', '\u2297', '\u2295', '\u2205', '\u2229', '\u222A', '\u2283', 
            '\u2287', '\u2284', '\u2282', '\u2286', '\u2208', '\u2209', '\u2220', '\u2207', '\256', '\251', 
            '\u2122', '\u220F', '\u221A', '\u2022', '\254', '\u2227', '\u2228', '\u21D4', '\u21D0', '\u21D1', 
            '\u21D2', '\u21D3', '\u25CA', '\u2329', '\0', '\0', '\0', '\u2211', '\u239B', '\u239C', 
            '\u239D', '\u23A1', '\u23A2', '\u23A3', '\u23A7', '\u23A8', '\u23A9', '\u23AA', '\0', '\u232A', 
            '\u222B', '\u2320', '\u23AE', '\u2321', '\u239E', '\u239F', '\u23A0', '\u23A4', '\u23A5', '\u23A6', 
            '\u23AB', '\u23AC', '\u23AD', '\0'
        };
        private static final char table2[] = {
            ' ', '\u2701', '\u2702', '\u2703', '\u2704', '\u260E', '\u2706', '\u2707', '\u2708', '\u2709', 
            '\u261B', '\u261E', '\u270C', '\u270D', '\u270E', '\u270F', '\u2710', '\u2711', '\u2712', '\u2713', 
            '\u2714', '\u2715', '\u2716', '\u2717', '\u2718', '\u2719', '\u271A', '\u271B', '\u271C', '\u271D', 
            '\u271E', '\u271F', '\u2720', '\u2721', '\u2722', '\u2723', '\u2724', '\u2725', '\u2726', '\u2727', 
            '\u2605', '\u2729', '\u272A', '\u272B', '\u272C', '\u272D', '\u272E', '\u272F', '\u2730', '\u2731', 
            '\u2732', '\u2733', '\u2734', '\u2735', '\u2736', '\u2737', '\u2738', '\u2739', '\u273A', '\u273B', 
            '\u273C', '\u273D', '\u273E', '\u273F', '\u2740', '\u2741', '\u2742', '\u2743', '\u2744', '\u2745', 
            '\u2746', '\u2747', '\u2748', '\u2749', '\u274A', '\u274B', '\u25CF', '\u274D', '\u25A0', '\u274F', 
            '\u2750', '\u2751', '\u2752', '\u25B2', '\u25BC', '\u25C6', '\u2756', '\u25D7', '\u2758', '\u2759', 
            '\u275A', '\u275B', '\u275C', '\u275D', '\u275E', '\0', '\0', '\0', '\0', '\0', 
            '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 
            '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', 
            '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\u2761', 
            '\u2762', '\u2763', '\u2764', '\u2765', '\u2766', '\u2767', '\u2663', '\u2666', '\u2665', '\u2660', 
            '\u2460', '\u2461', '\u2462', '\u2463', '\u2464', '\u2465', '\u2466', '\u2467', '\u2468', '\u2469', 
            '\u2776', '\u2777', '\u2778', '\u2779', '\u277A', '\u277B', '\u277C', '\u277D', '\u277E', '\u277F', 
            '\u2780', '\u2781', '\u2782', '\u2783', '\u2784', '\u2785', '\u2786', '\u2787', '\u2788', '\u2789', 
            '\u278A', '\u278B', '\u278C', '\u278D', '\u278E', '\u278F', '\u2790', '\u2791', '\u2792', '\u2793', 
            '\u2794', '\u2192', '\u2194', '\u2195', '\u2798', '\u2799', '\u279A', '\u279B', '\u279C', '\u279D', 
            '\u279E', '\u279F', '\u27A0', '\u27A1', '\u27A2', '\u27A3', '\u27A4', '\u27A5', '\u27A6', '\u27A7', 
            '\u27A8', '\u27A9', '\u27AA', '\u27AB', '\u27AC', '\u27AD', '\u27AE', '\u27AF', '\0', '\u27B1', 
            '\u27B2', '\u27B3', '\u27B4', '\u27B5', '\u27B6', '\u27B7', '\u27B8', '\u27B9', '\u27BA', '\u27BB', 
            '\u27BC', '\u27BD', '\u27BE', '\0'
        };

        static 
        {
            t1 = new IntHashtable();
            t2 = new IntHashtable();
            for(int k = 0; k < table1.length; k++)
            {
                int v = table1[k];
                if(v != 0)
                    t1.put(v, k + 32);
            }

            for(int k = 0; k < table2.length; k++)
            {
                int v = table2[k];
                if(v != 0)
                    t2.put(v, k + 32);
            }

        }

        SymbolConversion(boolean symbol)
        {
            if(symbol)
                translation = t1;
            else
                translation = t2;
        }
    }

    private static class Cp437Conversion
        implements ExtraEncoding
    {

        public byte[] charToByte(String text, String encoding)
        {
            char cc[] = text.toCharArray();
            byte b[] = new byte[cc.length];
            int ptr = 0;
            int len = cc.length;
            for(int k = 0; k < len; k++)
            {
                char c = cc[k];
                if(c < '\200')
                {
                    b[ptr++] = (byte)c;
                    continue;
                }
                byte v = (byte)c2b.get(c);
                if(v != 0)
                    b[ptr++] = v;
            }

            if(ptr == len)
            {
                return b;
            } else
            {
                byte b2[] = new byte[ptr];
                System.arraycopy(b, 0, b2, 0, ptr);
                return b2;
            }
        }

        public byte[] charToByte(char char1, String encoding)
        {
            if(char1 < '\200')
                return (new byte[] {
                    (byte)char1
                });
            byte v = (byte)c2b.get(char1);
            if(v != 0)
                return (new byte[] {
                    v
                });
            else
                return new byte[0];
        }

        public String byteToChar(byte b[], String encoding)
        {
            int len = b.length;
            char cc[] = new char[len];
            int ptr = 0;
            for(int k = 0; k < len; k++)
            {
                int c = b[k] & 0xff;
                if(c < 32)
                    continue;
                if(c < 128)
                {
                    cc[ptr++] = (char)c;
                } else
                {
                    char v = table[c - 128];
                    cc[ptr++] = v;
                }
            }

            return new String(cc, 0, ptr);
        }

        private static IntHashtable c2b;
        private static final char table[] = {
            '\307', '\374', '\351', '\342', '\344', '\340', '\345', '\347', '\352', '\353', 
            '\350', '\357', '\356', '\354', '\304', '\305', '\311', '\346', '\306', '\364', 
            '\366', '\362', '\373', '\371', '\377', '\326', '\334', '\242', '\243', '\245', 
            '\u20A7', '\u0192', '\341', '\355', '\363', '\372', '\361', '\321', '\252', '\272', 
            '\277', '\u2310', '\254', '\275', '\274', '\241', '\253', '\273', '\u2591', '\u2592', 
            '\u2593', '\u2502', '\u2524', '\u2561', '\u2562', '\u2556', '\u2555', '\u2563', '\u2551', '\u2557', 
            '\u255D', '\u255C', '\u255B', '\u2510', '\u2514', '\u2534', '\u252C', '\u251C', '\u2500', '\u253C', 
            '\u255E', '\u255F', '\u255A', '\u2554', '\u2569', '\u2566', '\u2560', '\u2550', '\u256C', '\u2567', 
            '\u2568', '\u2564', '\u2565', '\u2559', '\u2558', '\u2552', '\u2553', '\u256B', '\u256A', '\u2518', 
            '\u250C', '\u2588', '\u2584', '\u258C', '\u2590', '\u2580', '\u03B1', '\337', '\u0393', '\u03C0', 
            '\u03A3', '\u03C3', '\265', '\u03C4', '\u03A6', '\u0398', '\u03A9', '\u03B4', '\u221E', '\u03C6', 
            '\u03B5', '\u2229', '\u2261', '\261', '\u2265', '\u2264', '\u2320', '\u2321', '\367', '\u2248', 
            '\260', '\u2219', '\267', '\u221A', '\u207F', '\262', '\u25A0', '\240'
        };

        static 
        {
            c2b = new IntHashtable();
            for(int k = 0; k < table.length; k++)
                c2b.put(table[k], k + 128);

        }

        private Cp437Conversion()
        {
        }

    }

    private static class WingdingsConversion
        implements ExtraEncoding
    {

        public byte[] charToByte(char char1, String encoding)
        {
            if(char1 == ' ')
                return (new byte[] {
                    (byte)char1
                });
            if(char1 >= '\u2701' && char1 <= '\u27BE')
            {
                byte v = table[char1 - 9984];
                if(v != 0)
                    return (new byte[] {
                        v
                    });
            }
            return new byte[0];
        }

        public byte[] charToByte(String text, String encoding)
        {
            char cc[] = text.toCharArray();
            byte b[] = new byte[cc.length];
            int ptr = 0;
            int len = cc.length;
            for(int k = 0; k < len; k++)
            {
                char c = cc[k];
                if(c == ' ')
                {
                    b[ptr++] = (byte)c;
                    continue;
                }
                if(c < '\u2701' || c > '\u27BE')
                    continue;
                byte v = table[c - 9984];
                if(v != 0)
                    b[ptr++] = v;
            }

            if(ptr == len)
            {
                return b;
            } else
            {
                byte b2[] = new byte[ptr];
                System.arraycopy(b, 0, b2, 0, ptr);
                return b2;
            }
        }

        public String byteToChar(byte b[], String encoding)
        {
            return null;
        }

        private static final byte table[] = {
            0, 35, 34, 0, 0, 0, 41, 62, 81, 42, 
            0, 0, 65, 63, 0, 0, 0, 0, 0, -4, 
            0, 0, 0, -5, 0, 0, 0, 0, 0, 0, 
            86, 0, 88, 89, 0, 0, 0, 0, 0, 0, 
            0, 0, -75, 0, 0, 0, 0, 0, -74, 0, 
            0, 0, -83, -81, -84, 0, 0, 0, 0, 0, 
            0, 0, 0, 124, 123, 0, 0, 0, 84, 0, 
            0, 0, 0, 0, 0, 0, 0, -90, 0, 0, 
            0, 113, 114, 0, 0, 0, 117, 0, 0, 0, 
            0, 0, 0, 125, 126, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, -116, -115, 
            -114, -113, -112, -111, -110, -109, -108, -107, -127, -126, 
            -125, -124, -123, -122, -121, -120, -119, -118, -116, -115, 
            -114, -113, -112, -111, -110, -109, -108, -107, -24, 0, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0, -24, -40, 0, 0, -60, -58, 0, 0, -16, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, -36, 
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
            0
        };


        private WingdingsConversion()
        {
        }

    }


    public PdfEncodings()
    {
    }

    public static final byte[] convertToBytes(String text, String encoding)
    {
        if(text == null)
            return new byte[0];
        if(encoding == null || encoding.length() == 0)
        {
            int len = text.length();
            byte b[] = new byte[len];
            for(int k = 0; k < len; k++)
                b[k] = (byte)text.charAt(k);

            return b;
        }
        ExtraEncoding extra = (ExtraEncoding)extraEncodings.get(encoding.toLowerCase());
        if(extra != null)
        {
            byte b[] = extra.charToByte(text, encoding);
            if(b != null)
                return b;
        }
        IntHashtable hash = null;
        if(encoding.equals("Cp1252"))
            hash = winansi;
        else
        if(encoding.equals("PDF"))
            hash = pdfEncoding;
        if(hash != null)
        {
            char cc[] = text.toCharArray();
            int len = cc.length;
            int ptr = 0;
            byte b[] = new byte[len];
            int c = 0;
            for(int k = 0; k < len; k++)
            {
                char char1 = cc[k];
                if(char1 < '\200' || char1 > '\240' && char1 <= '\377')
                    c = char1;
                else
                    c = hash.get(char1);
                if(c != 0)
                    b[ptr++] = (byte)c;
            }

            if(ptr == len)
            {
                return b;
            } else
            {
                byte b2[] = new byte[ptr];
                System.arraycopy(b, 0, b2, 0, ptr);
                return b2;
            }
        }
        if(encoding.equals("UnicodeBig"))
        {
            char cc[] = text.toCharArray();
            int len = cc.length;
            byte b[] = new byte[cc.length * 2 + 2];
            b[0] = -2;
            b[1] = -1;
            int bptr = 2;
            for(int k = 0; k < len; k++)
            {
                char c = cc[k];
                b[bptr++] = (byte)(c >> 8);
                b[bptr++] = (byte)(c & 0xff);
            }

            return b;
        }
        try
        {
            Charset cc = Charset.forName(encoding);
            CharsetEncoder ce = cc.newEncoder();
            ce.onUnmappableCharacter(CodingErrorAction.IGNORE);
            CharBuffer cb = CharBuffer.wrap(text.toCharArray());
            ByteBuffer bb = ce.encode(cb);
            bb.rewind();
            int lim = bb.limit();
            byte br[] = new byte[lim];
            bb.get(br);
            return br;
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static final byte[] convertToBytes(char char1, String encoding)
    {
        if(encoding == null || encoding.length() == 0)
            return (new byte[] {
                (byte)char1
            });
        ExtraEncoding extra = (ExtraEncoding)extraEncodings.get(encoding.toLowerCase());
        if(extra != null)
        {
            byte b[] = extra.charToByte(char1, encoding);
            if(b != null)
                return b;
        }
        IntHashtable hash = null;
        if(encoding.equals("Cp1252"))
            hash = winansi;
        else
        if(encoding.equals("PDF"))
            hash = pdfEncoding;
        if(hash != null)
        {
            int c = 0;
            if(char1 < '\200' || char1 > '\240' && char1 <= '\377')
                c = char1;
            else
                c = hash.get(char1);
            if(c != 0)
                return (new byte[] {
                    (byte)c
                });
            else
                return new byte[0];
        }
        if(encoding.equals("UnicodeBig"))
        {
            byte b[] = new byte[4];
            b[0] = -2;
            b[1] = -1;
            b[2] = (byte)(char1 >> 8);
            b[3] = (byte)(char1 & 0xff);
            return b;
        }
        try
        {
            Charset cc = Charset.forName(encoding);
            CharsetEncoder ce = cc.newEncoder();
            ce.onUnmappableCharacter(CodingErrorAction.IGNORE);
            CharBuffer cb = CharBuffer.wrap(new char[] {
                char1
            });
            ByteBuffer bb = ce.encode(cb);
            bb.rewind();
            int lim = bb.limit();
            byte br[] = new byte[lim];
            bb.get(br);
            return br;
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static final String convertToString(byte bytes[], String encoding)
    {
        if(bytes == null)
            return "";
        if(encoding == null || encoding.length() == 0)
        {
            char c[] = new char[bytes.length];
            for(int k = 0; k < bytes.length; k++)
                c[k] = (char)(bytes[k] & 0xff);

            return new String(c);
        }
        ExtraEncoding extra = (ExtraEncoding)extraEncodings.get(encoding.toLowerCase());
        if(extra != null)
        {
            String text = extra.byteToChar(bytes, encoding);
            if(text != null)
                return text;
        }
        char ch[] = null;
        if(encoding.equals("Cp1252"))
            ch = winansiByteToChar;
        else
        if(encoding.equals("PDF"))
            ch = pdfEncodingByteToChar;
        if(ch != null)
        {
            int len = bytes.length;
            char c[] = new char[len];
            for(int k = 0; k < len; k++)
                c[k] = ch[bytes[k] & 0xff];

            return new String(c);
        }
        try
        {
            return new String(bytes, encoding);
        }
        catch(UnsupportedEncodingException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static boolean isPdfDocEncoding(String text)
    {
        if(text == null)
            return true;
        int len = text.length();
        for(int k = 0; k < len; k++)
        {
            char char1 = text.charAt(k);
            if(char1 >= '\200' && (char1 <= '\240' || char1 > '\377') && !pdfEncoding.containsKey(char1))
                return false;
        }

        return true;
    }

    public static void addExtraEncoding(String name, ExtraEncoding enc)
    {
        synchronized(extraEncodings)
        {
            HashMap newEncodings = (HashMap)extraEncodings.clone();
            newEncodings.put(name.toLowerCase(), enc);
            extraEncodings = newEncodings;
        }
    }

    static final char winansiByteToChar[] = {
        '\0', '\001', '\002', '\003', '\004', '\005', '\006', '\007', '\b', '\t', 
        '\n', '\013', '\f', '\r', '\016', '\017', '\020', '\021', '\022', '\023', 
        '\024', '\025', '\026', '\027', '\030', '\031', '\032', '\033', '\034', '\035', 
        '\036', '\037', ' ', '!', '"', '#', '$', '%', '&', '\'', 
        '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', 
        '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', 
        '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 
        'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 
        'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
        'x', 'y', 'z', '{', '|', '}', '~', '\177', '\u20AC', '\uFFFD', 
        '\u201A', '\u0192', '\u201E', '\u2026', '\u2020', '\u2021', '\u02C6', '\u2030', '\u0160', '\u2039', 
        '\u0152', '\uFFFD', '\u017D', '\uFFFD', '\uFFFD', '\u2018', '\u2019', '\u201C', '\u201D', '\u2022', 
        '\u2013', '\u2014', '\u02DC', '\u2122', '\u0161', '\u203A', '\u0153', '\uFFFD', '\u017E', '\u0178', 
        '\240', '\241', '\242', '\243', '\244', '\245', '\246', '\247', '\250', '\251', 
        '\252', '\253', '\254', '\255', '\256', '\257', '\260', '\261', '\262', '\263', 
        '\264', '\265', '\266', '\267', '\270', '\271', '\272', '\273', '\274', '\275', 
        '\276', '\277', '\300', '\301', '\302', '\303', '\304', '\305', '\306', '\307', 
        '\310', '\311', '\312', '\313', '\314', '\315', '\316', '\317', '\320', '\321', 
        '\322', '\323', '\324', '\325', '\326', '\327', '\330', '\331', '\332', '\333', 
        '\334', '\335', '\336', '\337', '\340', '\341', '\342', '\343', '\344', '\345', 
        '\346', '\347', '\350', '\351', '\352', '\353', '\354', '\355', '\356', '\357', 
        '\360', '\361', '\362', '\363', '\364', '\365', '\366', '\367', '\370', '\371', 
        '\372', '\373', '\374', '\375', '\376', '\377'
    };
    static final char pdfEncodingByteToChar[] = {
        '\0', '\001', '\002', '\003', '\004', '\005', '\006', '\007', '\b', '\t', 
        '\n', '\013', '\f', '\r', '\016', '\017', '\020', '\021', '\022', '\023', 
        '\024', '\025', '\026', '\027', '\030', '\031', '\032', '\033', '\034', '\035', 
        '\036', '\037', ' ', '!', '"', '#', '$', '%', '&', '\'', 
        '(', ')', '*', '+', ',', '-', '.', '/', '0', '1', 
        '2', '3', '4', '5', '6', '7', '8', '9', ':', ';', 
        '<', '=', '>', '?', '@', 'A', 'B', 'C', 'D', 'E', 
        'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 
        'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 
        'Z', '[', '\\', ']', '^', '_', '`', 'a', 'b', 'c', 
        'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 
        'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 
        'x', 'y', 'z', '{', '|', '}', '~', '\177', '\u2022', '\u2020', 
        '\u2021', '\u2026', '\u2014', '\u2013', '\u0192', '\u2044', '\u2039', '\u203A', '\u2212', '\u2030', 
        '\u201E', '\u201C', '\u201D', '\u2018', '\u2019', '\u201A', '\u2122', '\uFB01', '\uFB02', '\u0141', 
        '\u0152', '\u0160', '\u0178', '\u017D', '\u0131', '\u0142', '\u0153', '\u0161', '\u017E', '\uFFFD', 
        '\u20AC', '\241', '\242', '\243', '\244', '\245', '\246', '\247', '\250', '\251', 
        '\252', '\253', '\254', '\255', '\256', '\257', '\260', '\261', '\262', '\263', 
        '\264', '\265', '\266', '\267', '\270', '\271', '\272', '\273', '\274', '\275', 
        '\276', '\277', '\300', '\301', '\302', '\303', '\304', '\305', '\306', '\307', 
        '\310', '\311', '\312', '\313', '\314', '\315', '\316', '\317', '\320', '\321', 
        '\322', '\323', '\324', '\325', '\326', '\327', '\330', '\331', '\332', '\333', 
        '\334', '\335', '\336', '\337', '\340', '\341', '\342', '\343', '\344', '\345', 
        '\346', '\347', '\350', '\351', '\352', '\353', '\354', '\355', '\356', '\357', 
        '\360', '\361', '\362', '\363', '\364', '\365', '\366', '\367', '\370', '\371', 
        '\372', '\373', '\374', '\375', '\376', '\377'
    };
    static final IntHashtable winansi;
    static final IntHashtable pdfEncoding;
    static HashMap extraEncodings = new HashMap();

    static 
    {
        winansi = new IntHashtable();
        pdfEncoding = new IntHashtable();
        for(int k = 128; k < 161; k++)
        {
            char c = winansiByteToChar[k];
            if(c != '\uFFFD')
                winansi.put(c, k);
        }

        for(int k = 128; k < 161; k++)
        {
            char c = pdfEncodingByteToChar[k];
            if(c != '\uFFFD')
                pdfEncoding.put(c, k);
        }

        addExtraEncoding("Wingdings", new WingdingsConversion());
        addExtraEncoding("Symbol", new SymbolConversion(true));
        addExtraEncoding("ZapfDingbats", new SymbolConversion(false));
        addExtraEncoding("SymbolTT", new SymbolTTConversion());
        addExtraEncoding("Cp437", new Cp437Conversion());
    }
}
