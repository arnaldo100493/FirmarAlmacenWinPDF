// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FontDetails.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.Utilities;
import co.com.pdf.text.pdf.fonts.otf.Language;
import co.com.pdf.text.pdf.languages.BanglaGlyphRepositioner;
import co.com.pdf.text.pdf.languages.GlyphRepositioner;
import co.com.pdf.text.pdf.languages.IndicCompositeCharacterComparator;
import java.io.UnsupportedEncodingException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            IntHashtable, CJKFont, TrueTypeFontUnicode, ArrayBasedStringTokenizer, 
//            Glyph, PdfName, PdfIndirectReference, BaseFont, 
//            PdfEncodings, PdfWriter

class FontDetails
{

    FontDetails(PdfName fontName, PdfIndirectReference indirectReference, BaseFont baseFont)
    {
        subset = true;
        this.fontName = fontName;
        this.indirectReference = indirectReference;
        this.baseFont = baseFont;
        fontType = baseFont.getFontType();
        switch(fontType)
        {
        case 0: // '\0'
        case 1: // '\001'
            shortTag = new byte[256];
            break;

        case 2: // '\002'
            cjkTag = new IntHashtable();
            cjkFont = (CJKFont)baseFont;
            break;

        case 3: // '\003'
            longTag = new HashMap();
            ttu = (TrueTypeFontUnicode)baseFont;
            symbolic = baseFont.isFontSpecific();
            break;
        }
    }

    PdfIndirectReference getIndirectReference()
    {
        return indirectReference;
    }

    PdfName getFontName()
    {
        return fontName;
    }

    BaseFont getBaseFont()
    {
        return baseFont;
    }

    byte[] convertToBytes(String text)
    {
        byte b[] = null;
        fontType;
        JVM INSTR tableswitch 0 5: default 551
    //                   0 53
    //                   1 53
    //                   2 97
    //                   3 233
    //                   4 221
    //                   5 44;
           goto _L1 _L2 _L2 _L3 _L4 _L5 _L6
_L1:
        break; /* Loop/switch isn't completed */
_L6:
        return baseFont.convertToBytes(text);
_L2:
        int len;
        b = baseFont.convertToBytes(text);
        len = b.length;
        for(int k = 0; k < len; k++)
            shortTag[b[k] & 0xff] = 1;

        break; /* Loop/switch isn't completed */
_L3:
        len = text.length();
        if(cjkFont.isIdentity())
        {
            for(int k = 0; k < len; k++)
                cjkTag.put(text.charAt(k), 0);

        } else
        {
            for(int k = 0; k < len; k++)
            {
                int val;
                if(Utilities.isSurrogatePair(text, k))
                {
                    val = Utilities.convertToUtf32(text, k);
                    k++;
                } else
                {
                    val = text.charAt(k);
                }
                cjkTag.put(cjkFont.getCidCode(val), 0);
            }

        }
        b = cjkFont.convertToBytes(text);
        break; /* Loop/switch isn't completed */
_L5:
        b = baseFont.convertToBytes(text);
        break; /* Loop/switch isn't completed */
_L4:
        char glyph[];
        int i;
        len = text.length();
        int metrics[] = null;
        glyph = new char[len];
        i = 0;
        if(!symbolic) goto _L8; else goto _L7
_L7:
        b = PdfEncodings.convertToBytes(text, "symboltt");
        len = b.length;
        for(int k = 0; k < len; k++)
        {
            int metrics[] = ttu.getMetricsTT(b[k] & 0xff);
            if(metrics != null)
            {
                longTag.put(Integer.valueOf(metrics[0]), new int[] {
                    metrics[0], metrics[1], ttu.getUnicodeDifferences(b[k] & 0xff)
                });
                glyph[i++] = (char)metrics[0];
            }
        }

          goto _L9
_L8:
        if(canApplyGlyphSubstitution())
            return convertToBytesAfterGlyphSubstitution(text);
        for(int k = 0; k < len; k++)
        {
            int val;
            if(Utilities.isSurrogatePair(text, k))
            {
                val = Utilities.convertToUtf32(text, k);
                k++;
            } else
            {
                val = text.charAt(k);
            }
            int metrics[] = ttu.getMetricsTT(val);
            if(metrics == null)
                continue;
            int m0 = metrics[0];
            Integer gl = Integer.valueOf(m0);
            if(!longTag.containsKey(gl))
                longTag.put(gl, new int[] {
                    m0, metrics[1], val
                });
            glyph[i++] = (char)m0;
        }

_L9:
        String s = new String(glyph, 0, i);
        b = s.getBytes("UnicodeBigUnmarked");
        break; /* Loop/switch isn't completed */
        UnsupportedEncodingException e;
        e;
        throw new ExceptionConverter(e);
        return b;
    }

    private boolean canApplyGlyphSubstitution()
    {
        return fontType == 3 && ttu.getGlyphSubstitutionMap() != null;
    }

    private byte[] convertToBytesAfterGlyphSubstitution(String text)
        throws UnsupportedEncodingException
    {
        if(!canApplyGlyphSubstitution())
            throw new IllegalArgumentException("Make sure the font type if TTF Unicode and a valid GlyphSubstitutionTable exists!");
        Map glyphSubstitutionMap = ttu.getGlyphSubstitutionMap();
        Set compositeCharacters = new TreeSet(new IndicCompositeCharacterComparator());
        compositeCharacters.addAll(glyphSubstitutionMap.keySet());
        ArrayBasedStringTokenizer tokenizer = new ArrayBasedStringTokenizer((String[])compositeCharacters.toArray(new String[0]));
        String tokens[] = tokenizer.tokenize(text);
        List glyphList = new ArrayList(50);
        String arr$[] = tokens;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String token = arr$[i$];
            Glyph subsGlyph = (Glyph)glyphSubstitutionMap.get(token);
            if(subsGlyph != null)
            {
                glyphList.add(subsGlyph);
                continue;
            }
            char arr$[] = token.toCharArray();
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                char c = arr$[i$];
                int metrics[] = ttu.getMetricsTT(c);
                int glyphCode = metrics[0];
                int glyphWidth = metrics[1];
                glyphList.add(new Glyph(glyphCode, glyphWidth, String.valueOf(c)));
            }

        }

        GlyphRepositioner glyphRepositioner = getGlyphRepositioner();
        if(glyphRepositioner != null)
            glyphRepositioner.repositionGlyphs(glyphList);
        char charEncodedGlyphCodes[] = new char[glyphList.size()];
        for(int i = 0; i < glyphList.size(); i++)
        {
            Glyph glyph = (Glyph)glyphList.get(i);
            charEncodedGlyphCodes[i] = (char)glyph.code;
            Integer glyphCode = Integer.valueOf(glyph.code);
            if(!longTag.containsKey(glyphCode))
                longTag.put(glyphCode, new int[] {
                    glyph.code, glyph.width, glyph.chars.charAt(0)
                });
        }

        return (new String(charEncodedGlyphCodes)).getBytes("UnicodeBigUnmarked");
    }

    private GlyphRepositioner getGlyphRepositioner()
    {
        Language language = ttu.getSupportedLanguage();
        if(language == null)
            throw new IllegalArgumentException((new StringBuilder()).append("The supported language field cannot be null in ").append(ttu.getClass().getName()).toString());
        static class _cls1
        {

            static final int $SwitchMap$co$com$pdf$text$pdf$fonts$otf$Language[];

            static 
            {
                $SwitchMap$co$com$pdf$text$pdf$fonts$otf$Language = new int[Language.values().length];
                try
                {
                    $SwitchMap$co$com$pdf$text$pdf$fonts$otf$Language[Language.BENGALI.ordinal()] = 1;
                }
                catch(NoSuchFieldError ex) { }
            }
        }

        switch(_cls1..SwitchMap.co.com.pdf.text.pdf.fonts.otf.Language[language.ordinal()])
        {
        case 1: // '\001'
            return new BanglaGlyphRepositioner(Collections.unmodifiableMap(ttu.cmap31), ttu.getGlyphSubstitutionMap());
        }
        return null;
    }

    public void writeFont(PdfWriter writer)
    {
        try
        {
            switch(fontType)
            {
            case 4: // '\004'
            default:
                break;

            case 5: // '\005'
                baseFont.writeFont(writer, indirectReference, null);
                break;

            case 0: // '\0'
            case 1: // '\001'
                int firstChar;
                for(firstChar = 0; firstChar < 256 && shortTag[firstChar] == 0; firstChar++);
                int lastChar;
                for(lastChar = 255; lastChar >= firstChar && shortTag[lastChar] == 0; lastChar--);
                if(firstChar > 255)
                {
                    firstChar = 255;
                    lastChar = 255;
                }
                baseFont.writeFont(writer, indirectReference, new Object[] {
                    Integer.valueOf(firstChar), Integer.valueOf(lastChar), shortTag, Boolean.valueOf(subset)
                });
                break;

            case 2: // '\002'
                baseFont.writeFont(writer, indirectReference, new Object[] {
                    cjkTag
                });
                break;

            case 3: // '\003'
                baseFont.writeFont(writer, indirectReference, new Object[] {
                    longTag, Boolean.valueOf(subset)
                });
                break;
            }
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public boolean isSubset()
    {
        return subset;
    }

    public void setSubset(boolean subset)
    {
        this.subset = subset;
    }

    PdfIndirectReference indirectReference;
    PdfName fontName;
    BaseFont baseFont;
    TrueTypeFontUnicode ttu;
    CJKFont cjkFont;
    byte shortTag[];
    HashMap longTag;
    IntHashtable cjkTag;
    int fontType;
    boolean symbolic;
    protected boolean subset;
}
