// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlyphSubstitutionTableReader.java

package co.com.pdf.text.pdf.fonts.otf;

import co.com.pdf.text.log.Logger;
import co.com.pdf.text.pdf.Glyph;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.fonts.otf:
//            OpenTypeFontTableReader, FontReadingException

public class GlyphSubstitutionTableReader extends OpenTypeFontTableReader
{

    public GlyphSubstitutionTableReader(String fontFilePath, int gsubTableLocation, Map glyphToCharacterMap, int glyphWidthsByIndex[])
        throws IOException
    {
        super(fontFilePath, gsubTableLocation);
        this.glyphWidthsByIndex = glyphWidthsByIndex;
        this.glyphToCharacterMap = glyphToCharacterMap;
    }

    public void read()
        throws FontReadingException
    {
        rawLigatureSubstitutionMap = new LinkedHashMap();
        startReadingTable();
    }

    public Map getGlyphSubstitutionMap()
        throws FontReadingException
    {
        Map glyphSubstitutionMap = new LinkedHashMap();
        Glyph glyph;
        for(Iterator i$ = rawLigatureSubstitutionMap.keySet().iterator(); i$.hasNext(); glyphSubstitutionMap.put(glyph.chars, glyph))
        {
            Integer glyphIdToReplace = (Integer)i$.next();
            List constituentGlyphs = (List)rawLigatureSubstitutionMap.get(glyphIdToReplace);
            StringBuilder chars = new StringBuilder(constituentGlyphs.size());
            Integer constituentGlyphId;
            for(Iterator i$ = constituentGlyphs.iterator(); i$.hasNext(); chars.append(getTextFromGlyph(constituentGlyphId.intValue(), glyphToCharacterMap)))
                constituentGlyphId = (Integer)i$.next();

            glyph = new Glyph(glyphIdToReplace.intValue(), glyphWidthsByIndex[glyphIdToReplace.intValue()], chars.toString());
        }

        return Collections.unmodifiableMap(glyphSubstitutionMap);
    }

    private String getTextFromGlyph(int glyphId, Map glyphToCharacterMap)
        throws FontReadingException
    {
        StringBuilder chars = new StringBuilder(1);
        Character c = (Character)glyphToCharacterMap.get(Integer.valueOf(glyphId));
        if(c == null)
        {
            List constituentGlyphs = (List)rawLigatureSubstitutionMap.get(Integer.valueOf(glyphId));
            if(constituentGlyphs == null || constituentGlyphs.isEmpty())
                throw new FontReadingException((new StringBuilder()).append("No corresponding character or simple glyphs found for GlyphID=").append(glyphId).toString());
            int constituentGlyphId;
            for(Iterator i$ = constituentGlyphs.iterator(); i$.hasNext(); chars.append(getTextFromGlyph(constituentGlyphId, glyphToCharacterMap)))
                constituentGlyphId = ((Integer)i$.next()).intValue();

        } else
        {
            chars.append(c.charValue());
        }
        return chars.toString();
    }

    protected void readSubTable(int lookupType, int subTableLocation)
        throws IOException
    {
        if(lookupType == 1)
            readSingleSubstitutionSubtable(subTableLocation);
        else
        if(lookupType == 4)
            readLigatureSubstitutionSubtable(subTableLocation);
        else
            System.err.println((new StringBuilder()).append("LookupType ").append(lookupType).append(" is not yet handled for ").append(co/com/pdf/text/pdf/fonts/otf/GlyphSubstitutionTableReader.getSimpleName()).toString());
    }

    private void readSingleSubstitutionSubtable(int subTableLocation)
        throws IOException
    {
        rf.seek(subTableLocation);
        int substFormat = rf.readShort();
        LOG.debug((new StringBuilder()).append("substFormat=").append(substFormat).toString());
        if(substFormat == 1)
        {
            int coverage = rf.readShort();
            LOG.debug((new StringBuilder()).append("coverage=").append(coverage).toString());
            int deltaGlyphID = rf.readShort();
            LOG.debug((new StringBuilder()).append("deltaGlyphID=").append(deltaGlyphID).toString());
            List coverageGlyphIds = readCoverageFormat(subTableLocation + coverage);
            int coverageGlyphId;
            int substituteGlyphId;
            for(Iterator i$ = coverageGlyphIds.iterator(); i$.hasNext(); rawLigatureSubstitutionMap.put(Integer.valueOf(substituteGlyphId), Arrays.asList(new Integer[] {
    Integer.valueOf(coverageGlyphId)
})))
            {
                coverageGlyphId = ((Integer)i$.next()).intValue();
                substituteGlyphId = coverageGlyphId + deltaGlyphID;
            }

        } else
        if(substFormat == 2)
            System.err.println((new StringBuilder()).append("LookupType 1 :: substFormat 2 is not yet handled by ").append(co/com/pdf/text/pdf/fonts/otf/GlyphSubstitutionTableReader.getSimpleName()).toString());
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Bad substFormat: ").append(substFormat).toString());
    }

    private void readLigatureSubstitutionSubtable(int ligatureSubstitutionSubtableLocation)
        throws IOException
    {
        rf.seek(ligatureSubstitutionSubtableLocation);
        int substFormat = rf.readShort();
        LOG.debug((new StringBuilder()).append("substFormat=").append(substFormat).toString());
        if(substFormat != 1)
            throw new IllegalArgumentException("The expected SubstFormat is 1");
        int coverage = rf.readShort();
        LOG.debug((new StringBuilder()).append("coverage=").append(coverage).toString());
        int ligSetCount = rf.readShort();
        List ligatureOffsets = new ArrayList(ligSetCount);
        for(int i = 0; i < ligSetCount; i++)
        {
            int ligatureOffset = rf.readShort();
            ligatureOffsets.add(Integer.valueOf(ligatureOffset));
        }

        List coverageGlyphIds = readCoverageFormat(ligatureSubstitutionSubtableLocation + coverage);
        if(ligSetCount != coverageGlyphIds.size())
            throw new IllegalArgumentException("According to the OpenTypeFont specifications, the coverage count should be equal to the no. of LigatureSetTables");
        for(int i = 0; i < ligSetCount; i++)
        {
            int coverageGlyphId = ((Integer)coverageGlyphIds.get(i)).intValue();
            int ligatureOffset = ((Integer)ligatureOffsets.get(i)).intValue();
            LOG.debug((new StringBuilder()).append("ligatureOffset=").append(ligatureOffset).toString());
            readLigatureSetTable(ligatureSubstitutionSubtableLocation + ligatureOffset, coverageGlyphId);
        }

    }

    private void readLigatureSetTable(int ligatureSetTableLocation, int coverageGlyphId)
        throws IOException
    {
        rf.seek(ligatureSetTableLocation);
        int ligatureCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("ligatureCount=").append(ligatureCount).toString());
        List ligatureOffsets = new ArrayList(ligatureCount);
        for(int i = 0; i < ligatureCount; i++)
        {
            int ligatureOffset = rf.readShort();
            ligatureOffsets.add(Integer.valueOf(ligatureOffset));
        }

        int ligatureOffset;
        for(Iterator i$ = ligatureOffsets.iterator(); i$.hasNext(); readLigatureTable(ligatureSetTableLocation + ligatureOffset, coverageGlyphId))
            ligatureOffset = ((Integer)i$.next()).intValue();

    }

    private void readLigatureTable(int ligatureTableLocation, int coverageGlyphId)
        throws IOException
    {
        rf.seek(ligatureTableLocation);
        int ligGlyph = rf.readShort();
        LOG.debug((new StringBuilder()).append("ligGlyph=").append(ligGlyph).toString());
        int compCount = rf.readShort();
        List glyphIdList = new ArrayList();
        glyphIdList.add(Integer.valueOf(coverageGlyphId));
        for(int i = 0; i < compCount - 1; i++)
        {
            int glyphId = rf.readShort();
            glyphIdList.add(Integer.valueOf(glyphId));
        }

        LOG.debug((new StringBuilder()).append("glyphIdList=").append(glyphIdList).toString());
        List previousValue = (List)rawLigatureSubstitutionMap.put(Integer.valueOf(ligGlyph), glyphIdList);
        if(previousValue != null)
            LOG.warn((new StringBuilder()).append("!!!!!!!!!!glyphId=").append(ligGlyph).append(",\npreviousValue=").append(previousValue).append(",\ncurrentVal=").append(glyphIdList).toString());
    }

    private final int glyphWidthsByIndex[];
    private final Map glyphToCharacterMap;
    private Map rawLigatureSubstitutionMap;
}
