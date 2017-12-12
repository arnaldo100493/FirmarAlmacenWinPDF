// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GlyphPositioningTableReader.java

package co.com.pdf.text.pdf.fonts.otf;

import co.com.pdf.text.log.Logger;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.fonts.otf:
//            OpenTypeFontTableReader, FontReadingException

public class GlyphPositioningTableReader extends OpenTypeFontTableReader
{
    static class PosLookupRecord
    {

        final int sequenceIndex;
        final int lookupListIndex;

        public PosLookupRecord(int sequenceIndex, int lookupListIndex)
        {
            this.sequenceIndex = sequenceIndex;
            this.lookupListIndex = lookupListIndex;
        }
    }

    static class MarkRecord
    {

        final int markClass;
        final int markAnchorOffset;

        public MarkRecord(int markClass, int markAnchorOffset)
        {
            this.markClass = markClass;
            this.markAnchorOffset = markAnchorOffset;
        }
    }


    public GlyphPositioningTableReader(String fontFilePath, int gposTableLocation)
        throws IOException
    {
        super(fontFilePath, gposTableLocation);
    }

    public void read()
        throws FontReadingException
    {
        startReadingTable();
    }

    protected void readSubTable(int lookupType, int subTableLocation)
        throws IOException
    {
        if(lookupType == 1)
            readLookUpType_1(subTableLocation);
        else
        if(lookupType == 4)
            readLookUpType_4(subTableLocation);
        else
        if(lookupType == 8)
            readLookUpType_8(subTableLocation);
        else
            System.err.println((new StringBuilder()).append("The lookupType ").append(lookupType).append(" is not yet supported by ").append(co/com/pdf/text/pdf/fonts/otf/GlyphPositioningTableReader.getSimpleName()).toString());
    }

    private void readLookUpType_1(int lookupTableLocation)
        throws IOException
    {
        rf.seek(lookupTableLocation);
        int posFormat = rf.readShort();
        if(posFormat == 1)
        {
            LOG.debug("Reading `Look Up Type 1, Format 1` ....");
            int coverageOffset = rf.readShort();
            int valueFormat = rf.readShort();
            if((valueFormat & 1) == 1)
            {
                int xPlacement = rf.readShort();
                LOG.debug((new StringBuilder()).append("xPlacement=").append(xPlacement).toString());
            }
            if((valueFormat & 2) == 2)
            {
                int yPlacement = rf.readShort();
                LOG.debug((new StringBuilder()).append("yPlacement=").append(yPlacement).toString());
            }
            List glyphCodes = readCoverageFormat(lookupTableLocation + coverageOffset);
            LOG.debug((new StringBuilder()).append("glyphCodes=").append(glyphCodes).toString());
        } else
        {
            System.err.println((new StringBuilder()).append("The PosFormat ").append(posFormat).append(" for `LookupType 1` is not yet supported by ").append(co/com/pdf/text/pdf/fonts/otf/GlyphPositioningTableReader.getSimpleName()).toString());
        }
    }

    private void readLookUpType_4(int lookupTableLocation)
        throws IOException
    {
        rf.seek(lookupTableLocation);
        int posFormat = rf.readShort();
        if(posFormat == 1)
        {
            LOG.debug("Reading `Look Up Type 4, Format 1` ....");
            int markCoverageOffset = rf.readShort();
            int baseCoverageOffset = rf.readShort();
            int classCount = rf.readShort();
            int markArrayOffset = rf.readShort();
            int baseArrayOffset = rf.readShort();
            List markCoverages = readCoverageFormat(lookupTableLocation + markCoverageOffset);
            LOG.debug((new StringBuilder()).append("markCoverages=").append(markCoverages).toString());
            List baseCoverages = readCoverageFormat(lookupTableLocation + baseCoverageOffset);
            LOG.debug((new StringBuilder()).append("baseCoverages=").append(baseCoverages).toString());
            readMarkArrayTable(lookupTableLocation + markArrayOffset);
            readBaseArrayTable(lookupTableLocation + baseArrayOffset, classCount);
        } else
        {
            System.err.println((new StringBuilder()).append("The posFormat ").append(posFormat).append(" is not supported by ").append(co/com/pdf/text/pdf/fonts/otf/GlyphPositioningTableReader.getSimpleName()).toString());
        }
    }

    private void readLookUpType_8(int lookupTableLocation)
        throws IOException
    {
        rf.seek(lookupTableLocation);
        int posFormat = rf.readShort();
        if(posFormat == 3)
        {
            LOG.debug("Reading `Look Up Type 8, Format 3` ....");
            readChainingContextPositioningFormat_3(lookupTableLocation);
        } else
        {
            System.err.println((new StringBuilder()).append("The posFormat ").append(posFormat).append(" for `Look Up Type 8` is not supported by ").append(co/com/pdf/text/pdf/fonts/otf/GlyphPositioningTableReader.getSimpleName()).toString());
        }
    }

    private void readChainingContextPositioningFormat_3(int lookupTableLocation)
        throws IOException
    {
        int backtrackGlyphCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("backtrackGlyphCount=").append(backtrackGlyphCount).toString());
        List backtrackGlyphOffsets = new ArrayList(backtrackGlyphCount);
        for(int i = 0; i < backtrackGlyphCount; i++)
        {
            int backtrackGlyphOffset = rf.readShort();
            backtrackGlyphOffsets.add(Integer.valueOf(backtrackGlyphOffset));
        }

        int inputGlyphCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("inputGlyphCount=").append(inputGlyphCount).toString());
        List inputGlyphOffsets = new ArrayList(inputGlyphCount);
        for(int i = 0; i < inputGlyphCount; i++)
        {
            int inputGlyphOffset = rf.readShort();
            inputGlyphOffsets.add(Integer.valueOf(inputGlyphOffset));
        }

        int lookaheadGlyphCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("lookaheadGlyphCount=").append(lookaheadGlyphCount).toString());
        List lookaheadGlyphOffsets = new ArrayList(lookaheadGlyphCount);
        for(int i = 0; i < lookaheadGlyphCount; i++)
        {
            int lookaheadGlyphOffset = rf.readShort();
            lookaheadGlyphOffsets.add(Integer.valueOf(lookaheadGlyphOffset));
        }

        int posCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("posCount=").append(posCount).toString());
        List posLookupRecords = new ArrayList(posCount);
        for(int i = 0; i < posCount; i++)
        {
            int sequenceIndex = rf.readShort();
            int lookupListIndex = rf.readShort();
            LOG.debug((new StringBuilder()).append("sequenceIndex=").append(sequenceIndex).append(", lookupListIndex=").append(lookupListIndex).toString());
            posLookupRecords.add(new PosLookupRecord(sequenceIndex, lookupListIndex));
        }

        List backtrackGlyphs;
        for(Iterator i$ = backtrackGlyphOffsets.iterator(); i$.hasNext(); LOG.debug((new StringBuilder()).append("backtrackGlyphs=").append(backtrackGlyphs).toString()))
        {
            int backtrackGlyphOffset = ((Integer)i$.next()).intValue();
            backtrackGlyphs = readCoverageFormat(lookupTableLocation + backtrackGlyphOffset);
        }

        List inputGlyphs;
        for(Iterator i$ = inputGlyphOffsets.iterator(); i$.hasNext(); LOG.debug((new StringBuilder()).append("inputGlyphs=").append(inputGlyphs).toString()))
        {
            int inputGlyphOffset = ((Integer)i$.next()).intValue();
            inputGlyphs = readCoverageFormat(lookupTableLocation + inputGlyphOffset);
        }

        List lookaheadGlyphs;
        for(Iterator i$ = lookaheadGlyphOffsets.iterator(); i$.hasNext(); LOG.debug((new StringBuilder()).append("lookaheadGlyphs=").append(lookaheadGlyphs).toString()))
        {
            int lookaheadGlyphOffset = ((Integer)i$.next()).intValue();
            lookaheadGlyphs = readCoverageFormat(lookupTableLocation + lookaheadGlyphOffset);
        }

    }

    private void readMarkArrayTable(int markArrayLocation)
        throws IOException
    {
        rf.seek(markArrayLocation);
        int markCount = rf.readShort();
        List markRecords = new ArrayList();
        for(int i = 0; i < markCount; i++)
            markRecords.add(readMarkRecord());

        MarkRecord markRecord;
        for(Iterator i$ = markRecords.iterator(); i$.hasNext(); readAnchorTable(markArrayLocation + markRecord.markAnchorOffset))
            markRecord = (MarkRecord)i$.next();

    }

    private MarkRecord readMarkRecord()
        throws IOException
    {
        int markClass = rf.readShort();
        int markAnchorOffset = rf.readShort();
        return new MarkRecord(markClass, markAnchorOffset);
    }

    private void readAnchorTable(int anchorTableLocation)
        throws IOException
    {
        rf.seek(anchorTableLocation);
        int anchorFormat = rf.readShort();
        if(anchorFormat != 1)
            System.err.println((new StringBuilder()).append("The extra features of the AnchorFormat ").append(anchorFormat).append(" will not be used").toString());
        int x = rf.readShort();
        int y = rf.readShort();
    }

    private void readBaseArrayTable(int baseArrayTableLocation, int classCount)
        throws IOException
    {
        rf.seek(baseArrayTableLocation);
        int baseCount = rf.readShort();
        Set baseAnchors = new HashSet();
        for(int i = 0; i < baseCount; i++)
        {
            for(int k = 0; k < classCount; k++)
            {
                int baseAnchor = rf.readShort();
                baseAnchors.add(Integer.valueOf(baseAnchor));
            }

        }

        int baseAnchor;
        for(Iterator i$ = baseAnchors.iterator(); i$.hasNext(); readAnchorTable(baseArrayTableLocation + baseAnchor))
            baseAnchor = ((Integer)i$.next()).intValue();

    }
}
