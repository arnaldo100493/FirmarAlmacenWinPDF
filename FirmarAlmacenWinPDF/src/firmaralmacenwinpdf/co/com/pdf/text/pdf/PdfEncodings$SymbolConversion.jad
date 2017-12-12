// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncodings.java

package co.com.pdf.text.pdf;


// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable, ExtraEncoding, PdfEncodings

private static class PdfEncodings$SymbolConversion
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

    PdfEncodings$SymbolConversion(boolean symbol)
    {
        if(symbol)
            translation = t1;
        else
            translation = t2;
    }
}
