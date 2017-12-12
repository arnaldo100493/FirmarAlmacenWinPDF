// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Hyphenator.java

package co.com.pdf.text.pdf.hyphenation;

import co.com.pdf.text.io.StreamUtil;
import java.io.*;
import java.util.Hashtable;

// Referenced classes of package co.com.pdf.text.pdf.hyphenation:
//            HyphenationTree, Hyphenation

public class Hyphenator
{

    public Hyphenator(String lang, String country, int leftMin, int rightMin)
    {
        hyphenTree = null;
        remainCharCount = 2;
        pushCharCount = 2;
        hyphenTree = getHyphenationTree(lang, country);
        remainCharCount = leftMin;
        pushCharCount = rightMin;
    }

    public static HyphenationTree getHyphenationTree(String lang, String country)
    {
        String key = lang;
        if(country != null && !country.equals("none"))
            key = (new StringBuilder()).append(key).append("_").append(country).toString();
        if(hyphenTrees.containsKey(key))
            return (HyphenationTree)hyphenTrees.get(key);
        if(hyphenTrees.containsKey(lang))
            return (HyphenationTree)hyphenTrees.get(lang);
        HyphenationTree hTree = getResourceHyphenationTree(key);
        if(hTree == null)
            hTree = getFileHyphenationTree(key);
        if(hTree != null)
            hyphenTrees.put(key, hTree);
        return hTree;
    }

    public static HyphenationTree getResourceHyphenationTree(String key)
    {
        InputStream stream;
        HyphenationTree hTree;
        try
        {
            stream = StreamUtil.getResourceStream((new StringBuilder()).append("com/itextpdf/text/pdf/hyphenation/hyph/").append(key).append(".xml").toString());
            if(stream == null && key.length() > 2)
                stream = StreamUtil.getResourceStream((new StringBuilder()).append("com/itextpdf/text/pdf/hyphenation/hyph/").append(key.substring(0, 2)).append(".xml").toString());
            if(stream == null)
                return null;
        }
        catch(Exception e)
        {
            return null;
        }
        hTree = new HyphenationTree();
        hTree.loadSimplePatterns(stream);
        return hTree;
    }

    public static HyphenationTree getFileHyphenationTree(String key)
    {
        InputStream stream;
        File hyphenFile;
        try
        {
            if(hyphenDir == null)
                return null;
        }
        catch(Exception e)
        {
            return null;
        }
        stream = null;
        hyphenFile = new File(hyphenDir, (new StringBuilder()).append(key).append(".xml").toString());
        if(hyphenFile.canRead())
            stream = new FileInputStream(hyphenFile);
        if(stream == null && key.length() > 2)
        {
            hyphenFile = new File(hyphenDir, (new StringBuilder()).append(key.substring(0, 2)).append(".xml").toString());
            if(hyphenFile.canRead())
                stream = new FileInputStream(hyphenFile);
        }
        if(stream == null)
            return null;
        HyphenationTree hTree = new HyphenationTree();
        hTree.loadSimplePatterns(stream);
        return hTree;
    }

    public static Hyphenation hyphenate(String lang, String country, String word, int leftMin, int rightMin)
    {
        HyphenationTree hTree = getHyphenationTree(lang, country);
        if(hTree == null)
            return null;
        else
            return hTree.hyphenate(word, leftMin, rightMin);
    }

    public static Hyphenation hyphenate(String lang, String country, char word[], int offset, int len, int leftMin, int rightMin)
    {
        HyphenationTree hTree = getHyphenationTree(lang, country);
        if(hTree == null)
            return null;
        else
            return hTree.hyphenate(word, offset, len, leftMin, rightMin);
    }

    public void setMinRemainCharCount(int min)
    {
        remainCharCount = min;
    }

    public void setMinPushCharCount(int min)
    {
        pushCharCount = min;
    }

    public void setLanguage(String lang, String country)
    {
        hyphenTree = getHyphenationTree(lang, country);
    }

    public Hyphenation hyphenate(char word[], int offset, int len)
    {
        if(hyphenTree == null)
            return null;
        else
            return hyphenTree.hyphenate(word, offset, len, remainCharCount, pushCharCount);
    }

    public Hyphenation hyphenate(String word)
    {
        if(hyphenTree == null)
            return null;
        else
            return hyphenTree.hyphenate(word, remainCharCount, pushCharCount);
    }

    public static String getHyphenDir()
    {
        return hyphenDir;
    }

    public static void setHyphenDir(String _hyphenDir)
    {
        hyphenDir = _hyphenDir;
    }

    private static Hashtable hyphenTrees = new Hashtable();
    private HyphenationTree hyphenTree;
    private int remainCharCount;
    private int pushCharCount;
    private static final String defaultHyphLocation = "com/itextpdf/text/pdf/hyphenation/hyph/";
    private static String hyphenDir = "";

}
