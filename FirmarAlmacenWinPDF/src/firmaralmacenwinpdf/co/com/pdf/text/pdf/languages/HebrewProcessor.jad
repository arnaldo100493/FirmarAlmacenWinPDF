// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   HebrewProcessor.java

package co.com.pdf.text.pdf.languages;

import co.com.pdf.text.pdf.BidiLine;

// Referenced classes of package co.com.pdf.text.pdf.languages:
//            LanguageProcessor

public class HebrewProcessor
    implements LanguageProcessor
{

    public HebrewProcessor()
    {
        runDirection = 3;
    }

    public HebrewProcessor(int runDirection)
    {
        this.runDirection = 3;
        this.runDirection = runDirection;
    }

    public String process(String s)
    {
        return BidiLine.processLTR(s, runDirection, 0);
    }

    public boolean isRTL()
    {
        return true;
    }

    protected int runDirection;
}
