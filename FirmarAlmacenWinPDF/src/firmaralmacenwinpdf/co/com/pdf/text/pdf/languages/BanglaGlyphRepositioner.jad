// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BanglaGlyphRepositioner.java

package co.com.pdf.text.pdf.languages;

import co.com.pdf.text.pdf.Glyph;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.languages:
//            IndicGlyphRepositioner

public class BanglaGlyphRepositioner extends IndicGlyphRepositioner
{

    public BanglaGlyphRepositioner(Map cmap31, Map glyphSubstitutionMap)
    {
        this.cmap31 = cmap31;
        this.glyphSubstitutionMap = glyphSubstitutionMap;
    }

    public void repositionGlyphs(List glyphList)
    {
        for(int i = 0; i < glyphList.size(); i++)
        {
            Glyph glyph = (Glyph)glyphList.get(i);
            if(glyph.chars.equals("\u09CB"))
            {
                handleOKaarAndOUKaar(i, glyphList, '\u09C7', '\u09BE');
                continue;
            }
            if(glyph.chars.equals("\u09CC"))
                handleOKaarAndOUKaar(i, glyphList, '\u09C7', '\u09D7');
        }

        super.repositionGlyphs(glyphList);
    }

    public List getCharactersToBeShiftedLeftByOnePosition()
    {
        return Arrays.asList(CHARCTERS_TO_BE_SHIFTED_LEFT_BY_1);
    }

    private void handleOKaarAndOUKaar(int currentIndex, List glyphList, char first, char second)
    {
        Glyph g1 = getGlyph(first);
        Glyph g2 = getGlyph(second);
        glyphList.set(currentIndex, g1);
        glyphList.add(currentIndex + 1, g2);
    }

    private Glyph getGlyph(char c)
    {
        Glyph glyph = (Glyph)glyphSubstitutionMap.get(String.valueOf(c));
        if(glyph != null)
        {
            return glyph;
        } else
        {
            int metrics[] = (int[])cmap31.get(Integer.valueOf(c));
            int glyphCode = metrics[0];
            int glyphWidth = metrics[1];
            return new Glyph(glyphCode, glyphWidth, String.valueOf(c));
        }
    }

    private static final String CHARCTERS_TO_BE_SHIFTED_LEFT_BY_1[] = {
        "\u09BF", "\u09C7", "\u09C8"
    };
    private final Map cmap31;
    private final Map glyphSubstitutionMap;

}
