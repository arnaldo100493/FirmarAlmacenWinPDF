// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   OpenTypeFontTableReader.java

package co.com.pdf.text.pdf.fonts.otf;

import co.com.pdf.text.io.RandomAccessSourceFactory;
import co.com.pdf.text.log.Logger;
import co.com.pdf.text.log.LoggerFactory;
import co.com.pdf.text.pdf.RandomAccessFileOrArray;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.fonts.otf:
//            FontReadingException, TableHeader, Language

public abstract class OpenTypeFontTableReader
{

    public OpenTypeFontTableReader(String fontFilePath, int tableLocation)
        throws IOException
    {
        rf = new RandomAccessFileOrArray((new RandomAccessSourceFactory()).createBestSource(fontFilePath));
        this.tableLocation = tableLocation;
    }

    public Language getSupportedLanguage()
        throws FontReadingException
    {
        Language allLangs[] = Language.values();
        for(Iterator i$ = supportedLanguages.iterator(); i$.hasNext();)
        {
            String supportedLang = (String)i$.next();
            Language arr$[] = allLangs;
            int len$ = arr$.length;
            int i$ = 0;
            while(i$ < len$) 
            {
                Language lang = arr$[i$];
                if(lang.isSupported(supportedLang))
                    return lang;
                i$++;
            }
        }

        throw new FontReadingException((new StringBuilder()).append("Unsupported languages ").append(supportedLanguages).toString());
    }

    protected final void startReadingTable()
        throws FontReadingException
    {
        try
        {
            TableHeader header = readHeader();
            readScriptListTable(tableLocation + header.scriptListOffset);
            readFeatureListTable(tableLocation + header.featureListOffset);
            readLookupListTable(tableLocation + header.lookupListOffset);
        }
        catch(IOException e)
        {
            throw new FontReadingException("Error reading font file", e);
        }
    }

    protected abstract void readSubTable(int i, int j)
        throws IOException;

    private void readLookupListTable(int lookupListTableLocation)
        throws IOException
    {
        rf.seek(lookupListTableLocation);
        int lookupCount = rf.readShort();
        List lookupTableOffsets = new ArrayList();
        for(int i = 0; i < lookupCount; i++)
        {
            int lookupTableOffset = rf.readShort();
            lookupTableOffsets.add(Integer.valueOf(lookupTableOffset));
        }

        for(int i = 0; i < lookupCount; i++)
        {
            int lookupTableOffset = ((Integer)lookupTableOffsets.get(i)).intValue();
            readLookupTable(lookupListTableLocation + lookupTableOffset);
        }

    }

    private void readLookupTable(int lookupTableLocation)
        throws IOException
    {
        rf.seek(lookupTableLocation);
        int lookupType = rf.readShort();
        rf.skipBytes(2);
        int subTableCount = rf.readShort();
        List subTableOffsets = new ArrayList();
        for(int i = 0; i < subTableCount; i++)
        {
            int subTableOffset = rf.readShort();
            subTableOffsets.add(Integer.valueOf(subTableOffset));
        }

        int subTableOffset;
        for(Iterator i$ = subTableOffsets.iterator(); i$.hasNext(); readSubTable(lookupType, lookupTableLocation + subTableOffset))
            subTableOffset = ((Integer)i$.next()).intValue();

    }

    protected final List readCoverageFormat(int coverageLocation)
        throws IOException
    {
        rf.seek(coverageLocation);
        int coverageFormat = rf.readShort();
        List glyphIds;
        if(coverageFormat == 1)
        {
            int glyphCount = rf.readShort();
            glyphIds = new ArrayList(glyphCount);
            for(int i = 0; i < glyphCount; i++)
            {
                int coverageGlyphId = rf.readShort();
                glyphIds.add(Integer.valueOf(coverageGlyphId));
            }

        } else
        if(coverageFormat == 2)
        {
            int rangeCount = rf.readShort();
            glyphIds = new ArrayList();
            for(int i = 0; i < rangeCount; i++)
                readRangeRecord(glyphIds);

        } else
        {
            throw new UnsupportedOperationException((new StringBuilder()).append("Invalid coverage format: ").append(coverageFormat).toString());
        }
        return Collections.unmodifiableList(glyphIds);
    }

    private void readRangeRecord(List glyphIds)
        throws IOException
    {
        int startGlyphId = rf.readShort();
        int endGlyphId = rf.readShort();
        int startCoverageIndex = rf.readShort();
        for(int glyphId = startGlyphId; glyphId <= endGlyphId; glyphId++)
            glyphIds.add(Integer.valueOf(glyphId));

    }

    private void readScriptListTable(int scriptListTableLocationOffset)
        throws IOException
    {
        rf.seek(scriptListTableLocationOffset);
        int scriptCount = rf.readShort();
        Map scriptRecords = new HashMap(scriptCount);
        for(int i = 0; i < scriptCount; i++)
            readScriptRecord(scriptListTableLocationOffset, scriptRecords);

        List supportedLanguages = new ArrayList(scriptCount);
        String scriptName;
        for(Iterator i$ = scriptRecords.keySet().iterator(); i$.hasNext(); supportedLanguages.add(scriptName))
        {
            scriptName = (String)i$.next();
            readScriptTable(((Integer)scriptRecords.get(scriptName)).intValue());
        }

        this.supportedLanguages = Collections.unmodifiableList(supportedLanguages);
    }

    private void readScriptRecord(int scriptListTableLocationOffset, Map scriptRecords)
        throws IOException
    {
        String scriptTag = rf.readString(4, "utf-8");
        int scriptOffset = rf.readShort();
        scriptRecords.put(scriptTag, Integer.valueOf(scriptListTableLocationOffset + scriptOffset));
    }

    private void readScriptTable(int scriptTableLocationOffset)
        throws IOException
    {
        rf.seek(scriptTableLocationOffset);
        int defaultLangSys = rf.readShort();
        int langSysCount = rf.readShort();
        if(langSysCount > 0)
        {
            Map langSysRecords = new LinkedHashMap(langSysCount);
            for(int i = 0; i < langSysCount; i++)
                readLangSysRecord(langSysRecords);

            String langSysTag;
            for(Iterator i$ = langSysRecords.keySet().iterator(); i$.hasNext(); readLangSysTable(scriptTableLocationOffset + ((Integer)langSysRecords.get(langSysTag)).intValue()))
                langSysTag = (String)i$.next();

        }
        readLangSysTable(scriptTableLocationOffset + defaultLangSys);
    }

    private void readLangSysRecord(Map langSysRecords)
        throws IOException
    {
        String langSysTag = rf.readString(4, "utf-8");
        int langSys = rf.readShort();
        langSysRecords.put(langSysTag, Integer.valueOf(langSys));
    }

    private void readLangSysTable(int langSysTableLocationOffset)
        throws IOException
    {
        rf.seek(langSysTableLocationOffset);
        int lookupOrderOffset = rf.readShort();
        LOG.debug((new StringBuilder()).append("lookupOrderOffset=").append(lookupOrderOffset).toString());
        int reqFeatureIndex = rf.readShort();
        LOG.debug((new StringBuilder()).append("reqFeatureIndex=").append(reqFeatureIndex).toString());
        int featureCount = rf.readShort();
        List featureListIndices = new ArrayList(featureCount);
        for(int i = 0; i < featureCount; i++)
            featureListIndices.add(Short.valueOf(rf.readShort()));

        LOG.debug((new StringBuilder()).append("featureListIndices=").append(featureListIndices).toString());
    }

    private void readFeatureListTable(int featureListTableLocationOffset)
        throws IOException
    {
        rf.seek(featureListTableLocationOffset);
        int featureCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("featureCount=").append(featureCount).toString());
        Map featureRecords = new LinkedHashMap(featureCount);
        for(int i = 0; i < featureCount; i++)
            featureRecords.put(rf.readString(4, "utf-8"), Short.valueOf(rf.readShort()));

        String featureName;
        for(Iterator i$ = featureRecords.keySet().iterator(); i$.hasNext(); readFeatureTable(featureListTableLocationOffset + ((Short)featureRecords.get(featureName)).shortValue()))
        {
            featureName = (String)i$.next();
            LOG.debug((new StringBuilder()).append("*************featureName=").append(featureName).toString());
        }

    }

    private void readFeatureTable(int featureTableLocationOffset)
        throws IOException
    {
        rf.seek(featureTableLocationOffset);
        int featureParamsOffset = rf.readShort();
        LOG.debug((new StringBuilder()).append("featureParamsOffset=").append(featureParamsOffset).toString());
        int lookupCount = rf.readShort();
        LOG.debug((new StringBuilder()).append("lookupCount=").append(lookupCount).toString());
        List lookupListIndices = new ArrayList(lookupCount);
        for(int i = 0; i < lookupCount; i++)
            lookupListIndices.add(Short.valueOf(rf.readShort()));

    }

    private TableHeader readHeader()
        throws IOException
    {
        rf.seek(tableLocation);
        int version = rf.readInt();
        int scriptListOffset = rf.readUnsignedShort();
        int featureListOffset = rf.readUnsignedShort();
        int lookupListOffset = rf.readUnsignedShort();
        TableHeader header = new TableHeader(version, scriptListOffset, featureListOffset, lookupListOffset);
        return header;
    }

    protected static final Logger LOG = LoggerFactory.getLogger(co/com/pdf/text/pdf/fonts/otf/OpenTypeFontTableReader);
    protected final RandomAccessFileOrArray rf;
    protected final int tableLocation;
    private List supportedLanguages;

}
