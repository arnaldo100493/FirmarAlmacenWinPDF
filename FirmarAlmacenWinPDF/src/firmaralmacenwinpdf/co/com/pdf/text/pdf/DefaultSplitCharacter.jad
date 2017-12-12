// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   DefaultSplitCharacter.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.SplitCharacter;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfChunk

public class DefaultSplitCharacter
    implements SplitCharacter
{

    public DefaultSplitCharacter()
    {
    }

    public boolean isSplitCharacter(int start, int current, int end, char cc[], PdfChunk ck[])
    {
        char c = getCurrentCharacter(current, cc, ck);
        if(c <= ' ' || c == '-' || c == '\u2010')
            return true;
        if(c < '\u2002')
            return false;
        else
            return c >= '\u2002' && c <= '\u200B' || c >= '\u2E80' && c < '\uD7A0' || c >= '\uF900' && c < '\uFB00' || c >= '\uFE30' && c < '\uFE50' || c >= '\uFF61' && c < '\uFFA0';
    }

    protected char getCurrentCharacter(int current, char cc[], PdfChunk ck[])
    {
        if(ck == null)
            return cc[current];
        else
            return (char)ck[Math.min(current, ck.length - 1)].getUnicodeEquivalent(cc[current]);
    }

    public static final SplitCharacter DEFAULT = new DefaultSplitCharacter();

}
