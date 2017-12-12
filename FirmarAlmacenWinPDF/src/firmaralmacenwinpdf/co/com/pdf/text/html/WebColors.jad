// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   WebColors.java

package co.com.pdf.text.html;

import co.com.pdf.text.BaseColor;
import co.com.pdf.text.error_messages.MessageLocalization;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WebColors extends HashMap
{

    public WebColors()
    {
    }

    private static boolean missingHashColorFormat(String colStr)
    {
        int len = colStr.length();
        if(len == 3 || len == 6)
        {
            String match = (new StringBuilder()).append("[0-9a-f]{").append(len).append("}").toString();
            return colStr.matches(match);
        } else
        {
            return false;
        }
    }

    public static BaseColor getRGBColor(String name)
        throws IllegalArgumentException
    {
        int c[] = {
            0, 0, 0, 255
        };
        name = name.toLowerCase();
        boolean colorStrWithoutHash = missingHashColorFormat(name);
        if(name.startsWith("#") || colorStrWithoutHash)
        {
            if(!colorStrWithoutHash)
                name = name.substring(1);
            if(name.length() == 3)
            {
                String s = name.substring(0, 1);
                c[0] = Integer.parseInt((new StringBuilder()).append(s).append(s).toString(), 16);
                String s2 = name.substring(1, 2);
                c[1] = Integer.parseInt((new StringBuilder()).append(s2).append(s2).toString(), 16);
                String s3 = name.substring(2);
                c[2] = Integer.parseInt((new StringBuilder()).append(s3).append(s3).toString(), 16);
                return new BaseColor(c[0], c[1], c[2], c[3]);
            }
            if(name.length() == 6)
            {
                c[0] = Integer.parseInt(name.substring(0, 2), 16);
                c[1] = Integer.parseInt(name.substring(2, 4), 16);
                c[2] = Integer.parseInt(name.substring(4), 16);
                return new BaseColor(c[0], c[1], c[2], c[3]);
            } else
            {
                throw new IllegalArgumentException(MessageLocalization.getComposedMessage("unknown.color.format.must.be.rgb.or.rrggbb", new Object[0]));
            }
        }
        if(name.startsWith("rgb("))
        {
            StringTokenizer tok = new StringTokenizer(name, "rgb(), \t\r\n\f");
            for(int k = 0; k < 3; k++)
            {
                String v = tok.nextToken();
                if(v.endsWith("%"))
                    c[k] = (Integer.parseInt(v.substring(0, v.length() - 1)) * 255) / 100;
                else
                    c[k] = Integer.parseInt(v);
                if(c[k] < 0)
                {
                    c[k] = 0;
                    continue;
                }
                if(c[k] > 255)
                    c[k] = 255;
            }

            return new BaseColor(c[0], c[1], c[2], c[3]);
        }
        if(!NAMES.containsKey(name))
        {
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("color.not.found", new String[] {
                name
            }));
        } else
        {
            c = (int[])NAMES.get(name);
            return new BaseColor(c[0], c[1], c[2], c[3]);
        }
    }

    private static final long serialVersionUID = 0x312991fdb898ade0L;
    public static final WebColors NAMES;

    static 
    {
        NAMES = new WebColors();
        NAMES.put("aliceblue", new int[] {
            240, 248, 255, 255
        });
        NAMES.put("antiquewhite", new int[] {
            250, 235, 215, 255
        });
        NAMES.put("aqua", new int[] {
            0, 255, 255, 255
        });
        NAMES.put("aquamarine", new int[] {
            127, 255, 212, 255
        });
        NAMES.put("azure", new int[] {
            240, 255, 255, 255
        });
        NAMES.put("beige", new int[] {
            245, 245, 220, 255
        });
        NAMES.put("bisque", new int[] {
            255, 228, 196, 255
        });
        NAMES.put("black", new int[] {
            0, 0, 0, 255
        });
        NAMES.put("blanchedalmond", new int[] {
            255, 235, 205, 255
        });
        NAMES.put("blue", new int[] {
            0, 0, 255, 255
        });
        NAMES.put("blueviolet", new int[] {
            138, 43, 226, 255
        });
        NAMES.put("brown", new int[] {
            165, 42, 42, 255
        });
        NAMES.put("burlywood", new int[] {
            222, 184, 135, 255
        });
        NAMES.put("cadetblue", new int[] {
            95, 158, 160, 255
        });
        NAMES.put("chartreuse", new int[] {
            127, 255, 0, 255
        });
        NAMES.put("chocolate", new int[] {
            210, 105, 30, 255
        });
        NAMES.put("coral", new int[] {
            255, 127, 80, 255
        });
        NAMES.put("cornflowerblue", new int[] {
            100, 149, 237, 255
        });
        NAMES.put("cornsilk", new int[] {
            255, 248, 220, 255
        });
        NAMES.put("crimson", new int[] {
            220, 20, 60, 255
        });
        NAMES.put("cyan", new int[] {
            0, 255, 255, 255
        });
        NAMES.put("darkblue", new int[] {
            0, 0, 139, 255
        });
        NAMES.put("darkcyan", new int[] {
            0, 139, 139, 255
        });
        NAMES.put("darkgoldenrod", new int[] {
            184, 134, 11, 255
        });
        NAMES.put("darkgray", new int[] {
            169, 169, 169, 255
        });
        NAMES.put("darkgreen", new int[] {
            0, 100, 0, 255
        });
        NAMES.put("darkkhaki", new int[] {
            189, 183, 107, 255
        });
        NAMES.put("darkmagenta", new int[] {
            139, 0, 139, 255
        });
        NAMES.put("darkolivegreen", new int[] {
            85, 107, 47, 255
        });
        NAMES.put("darkorange", new int[] {
            255, 140, 0, 255
        });
        NAMES.put("darkorchid", new int[] {
            153, 50, 204, 255
        });
        NAMES.put("darkred", new int[] {
            139, 0, 0, 255
        });
        NAMES.put("darksalmon", new int[] {
            233, 150, 122, 255
        });
        NAMES.put("darkseagreen", new int[] {
            143, 188, 143, 255
        });
        NAMES.put("darkslateblue", new int[] {
            72, 61, 139, 255
        });
        NAMES.put("darkslategray", new int[] {
            47, 79, 79, 255
        });
        NAMES.put("darkturquoise", new int[] {
            0, 206, 209, 255
        });
        NAMES.put("darkviolet", new int[] {
            148, 0, 211, 255
        });
        NAMES.put("deeppink", new int[] {
            255, 20, 147, 255
        });
        NAMES.put("deepskyblue", new int[] {
            0, 191, 255, 255
        });
        NAMES.put("dimgray", new int[] {
            105, 105, 105, 255
        });
        NAMES.put("dodgerblue", new int[] {
            30, 144, 255, 255
        });
        NAMES.put("firebrick", new int[] {
            178, 34, 34, 255
        });
        NAMES.put("floralwhite", new int[] {
            255, 250, 240, 255
        });
        NAMES.put("forestgreen", new int[] {
            34, 139, 34, 255
        });
        NAMES.put("fuchsia", new int[] {
            255, 0, 255, 255
        });
        NAMES.put("gainsboro", new int[] {
            220, 220, 220, 255
        });
        NAMES.put("ghostwhite", new int[] {
            248, 248, 255, 255
        });
        NAMES.put("gold", new int[] {
            255, 215, 0, 255
        });
        NAMES.put("goldenrod", new int[] {
            218, 165, 32, 255
        });
        NAMES.put("gray", new int[] {
            128, 128, 128, 255
        });
        NAMES.put("green", new int[] {
            0, 128, 0, 255
        });
        NAMES.put("greenyellow", new int[] {
            173, 255, 47, 255
        });
        NAMES.put("honeydew", new int[] {
            240, 255, 240, 255
        });
        NAMES.put("hotpink", new int[] {
            255, 105, 180, 255
        });
        NAMES.put("indianred", new int[] {
            205, 92, 92, 255
        });
        NAMES.put("indigo", new int[] {
            75, 0, 130, 255
        });
        NAMES.put("ivory", new int[] {
            255, 255, 240, 255
        });
        NAMES.put("khaki", new int[] {
            240, 230, 140, 255
        });
        NAMES.put("lavender", new int[] {
            230, 230, 250, 255
        });
        NAMES.put("lavenderblush", new int[] {
            255, 240, 245, 255
        });
        NAMES.put("lawngreen", new int[] {
            124, 252, 0, 255
        });
        NAMES.put("lemonchiffon", new int[] {
            255, 250, 205, 255
        });
        NAMES.put("lightblue", new int[] {
            173, 216, 230, 255
        });
        NAMES.put("lightcoral", new int[] {
            240, 128, 128, 255
        });
        NAMES.put("lightcyan", new int[] {
            224, 255, 255, 255
        });
        NAMES.put("lightgoldenrodyellow", new int[] {
            250, 250, 210, 255
        });
        NAMES.put("lightgreen", new int[] {
            144, 238, 144, 255
        });
        NAMES.put("lightgrey", new int[] {
            211, 211, 211, 255
        });
        NAMES.put("lightpink", new int[] {
            255, 182, 193, 255
        });
        NAMES.put("lightsalmon", new int[] {
            255, 160, 122, 255
        });
        NAMES.put("lightseagreen", new int[] {
            32, 178, 170, 255
        });
        NAMES.put("lightskyblue", new int[] {
            135, 206, 250, 255
        });
        NAMES.put("lightslategray", new int[] {
            119, 136, 153, 255
        });
        NAMES.put("lightsteelblue", new int[] {
            176, 196, 222, 255
        });
        NAMES.put("lightyellow", new int[] {
            255, 255, 224, 255
        });
        NAMES.put("lime", new int[] {
            0, 255, 0, 255
        });
        NAMES.put("limegreen", new int[] {
            50, 205, 50, 255
        });
        NAMES.put("linen", new int[] {
            250, 240, 230, 255
        });
        NAMES.put("magenta", new int[] {
            255, 0, 255, 255
        });
        NAMES.put("maroon", new int[] {
            128, 0, 0, 255
        });
        NAMES.put("mediumaquamarine", new int[] {
            102, 205, 170, 255
        });
        NAMES.put("mediumblue", new int[] {
            0, 0, 205, 255
        });
        NAMES.put("mediumorchid", new int[] {
            186, 85, 211, 255
        });
        NAMES.put("mediumpurple", new int[] {
            147, 112, 219, 255
        });
        NAMES.put("mediumseagreen", new int[] {
            60, 179, 113, 255
        });
        NAMES.put("mediumslateblue", new int[] {
            123, 104, 238, 255
        });
        NAMES.put("mediumspringgreen", new int[] {
            0, 250, 154, 255
        });
        NAMES.put("mediumturquoise", new int[] {
            72, 209, 204, 255
        });
        NAMES.put("mediumvioletred", new int[] {
            199, 21, 133, 255
        });
        NAMES.put("midnightblue", new int[] {
            25, 25, 112, 255
        });
        NAMES.put("mintcream", new int[] {
            245, 255, 250, 255
        });
        NAMES.put("mistyrose", new int[] {
            255, 228, 225, 255
        });
        NAMES.put("moccasin", new int[] {
            255, 228, 181, 255
        });
        NAMES.put("navajowhite", new int[] {
            255, 222, 173, 255
        });
        NAMES.put("navy", new int[] {
            0, 0, 128, 255
        });
        NAMES.put("oldlace", new int[] {
            253, 245, 230, 255
        });
        NAMES.put("olive", new int[] {
            128, 128, 0, 255
        });
        NAMES.put("olivedrab", new int[] {
            107, 142, 35, 255
        });
        NAMES.put("orange", new int[] {
            255, 165, 0, 255
        });
        NAMES.put("orangered", new int[] {
            255, 69, 0, 255
        });
        NAMES.put("orchid", new int[] {
            218, 112, 214, 255
        });
        NAMES.put("palegoldenrod", new int[] {
            238, 232, 170, 255
        });
        NAMES.put("palegreen", new int[] {
            152, 251, 152, 255
        });
        NAMES.put("paleturquoise", new int[] {
            175, 238, 238, 255
        });
        NAMES.put("palevioletred", new int[] {
            219, 112, 147, 255
        });
        NAMES.put("papayawhip", new int[] {
            255, 239, 213, 255
        });
        NAMES.put("peachpuff", new int[] {
            255, 218, 185, 255
        });
        NAMES.put("peru", new int[] {
            205, 133, 63, 255
        });
        NAMES.put("pink", new int[] {
            255, 192, 203, 255
        });
        NAMES.put("plum", new int[] {
            221, 160, 221, 255
        });
        NAMES.put("powderblue", new int[] {
            176, 224, 230, 255
        });
        NAMES.put("purple", new int[] {
            128, 0, 128, 255
        });
        NAMES.put("red", new int[] {
            255, 0, 0, 255
        });
        NAMES.put("rosybrown", new int[] {
            188, 143, 143, 255
        });
        NAMES.put("royalblue", new int[] {
            65, 105, 225, 255
        });
        NAMES.put("saddlebrown", new int[] {
            139, 69, 19, 255
        });
        NAMES.put("salmon", new int[] {
            250, 128, 114, 255
        });
        NAMES.put("sandybrown", new int[] {
            244, 164, 96, 255
        });
        NAMES.put("seagreen", new int[] {
            46, 139, 87, 255
        });
        NAMES.put("seashell", new int[] {
            255, 245, 238, 255
        });
        NAMES.put("sienna", new int[] {
            160, 82, 45, 255
        });
        NAMES.put("silver", new int[] {
            192, 192, 192, 255
        });
        NAMES.put("skyblue", new int[] {
            135, 206, 235, 255
        });
        NAMES.put("slateblue", new int[] {
            106, 90, 205, 255
        });
        NAMES.put("slategray", new int[] {
            112, 128, 144, 255
        });
        NAMES.put("snow", new int[] {
            255, 250, 250, 255
        });
        NAMES.put("springgreen", new int[] {
            0, 255, 127, 255
        });
        NAMES.put("steelblue", new int[] {
            70, 130, 180, 255
        });
        NAMES.put("tan", new int[] {
            210, 180, 140, 255
        });
        NAMES.put("teal", new int[] {
            0, 128, 128, 255
        });
        NAMES.put("thistle", new int[] {
            216, 191, 216, 255
        });
        NAMES.put("tomato", new int[] {
            255, 99, 71, 255
        });
        NAMES.put("transparent", new int[] {
            0, 0, 0, 0
        });
        NAMES.put("turquoise", new int[] {
            64, 224, 208, 255
        });
        NAMES.put("violet", new int[] {
            238, 130, 238, 255
        });
        NAMES.put("wheat", new int[] {
            245, 222, 179, 255
        });
        NAMES.put("white", new int[] {
            255, 255, 255, 255
        });
        NAMES.put("whitesmoke", new int[] {
            245, 245, 245, 255
        });
        NAMES.put("yellow", new int[] {
            255, 255, 0, 255
        });
        NAMES.put("yellowgreen", new int[] {
            154, 205, 50, 255
        });
    }
}
