// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IndicGlyphRepositioner.java

package co.com.pdf.text.pdf.languages;

import co.com.pdf.text.pdf.Glyph;
import java.util.List;

// Referenced classes of package co.com.pdf.text.pdf.languages:
//            GlyphRepositioner

abstract class IndicGlyphRepositioner
    implements GlyphRepositioner
{

    IndicGlyphRepositioner()
    {
    }

    public void repositionGlyphs(List glyphList)
    {
        for(int i = 0; i < glyphList.size(); i++)
        {
            Glyph glyph = (Glyph)glyphList.get(i);
            Glyph nextGlyph = getNextGlyph(glyphList, i);
            if(nextGlyph != null && getCharactersToBeShiftedLeftByOnePosition().contains(nextGlyph.chars))
            {
                glyphList.set(i, nextGlyph);
                glyphList.set(i + 1, glyph);
                i++;
            }
        }

    }

    abstract List getCharactersToBeShiftedLeftByOnePosition();

    private Glyph getNextGlyph(List glyphs, int currentIndex)
    {
        if(currentIndex + 1 < glyphs.size())
            return (Glyph)glyphs.get(currentIndex + 1);
        else
            return null;
    }
}
