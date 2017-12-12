// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfPageLabels.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.factories.RomanAlphabetFactory;
import co.com.pdf.text.factories.RomanNumberFactory;
import java.io.IOException;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfDictionary, PdfString, PdfNumber, PdfObject, 
//            PdfName, PdfReader, PdfNumberTree, PdfWriter

public class PdfPageLabels
{
    public static class PdfPageLabelFormat
    {

        public int physicalPage;
        public int numberStyle;
        public String prefix;
        public int logicalPage;

        public PdfPageLabelFormat(int physicalPage, int numberStyle, String prefix, int logicalPage)
        {
            this.physicalPage = physicalPage;
            this.numberStyle = numberStyle;
            this.prefix = prefix;
            this.logicalPage = logicalPage;
        }
    }


    public PdfPageLabels()
    {
        map = new HashMap();
        addPageLabel(1, 0, null, 1);
    }

    public void addPageLabel(int page, int numberStyle, String text, int firstPage)
    {
        if(page < 1 || firstPage < 1)
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("in.a.page.label.the.page.numbers.must.be.greater.or.equal.to.1", new Object[0]));
        PdfDictionary dic = new PdfDictionary();
        if(numberStyle >= 0 && numberStyle < numberingStyle.length)
            dic.put(PdfName.S, numberingStyle[numberStyle]);
        if(text != null)
            dic.put(PdfName.P, new PdfString(text, "UnicodeBig"));
        if(firstPage != 1)
            dic.put(PdfName.ST, new PdfNumber(firstPage));
        map.put(Integer.valueOf(page - 1), dic);
    }

    public void addPageLabel(int page, int numberStyle, String text)
    {
        addPageLabel(page, numberStyle, text, 1);
    }

    public void addPageLabel(int page, int numberStyle)
    {
        addPageLabel(page, numberStyle, null, 1);
    }

    public void addPageLabel(PdfPageLabelFormat format)
    {
        addPageLabel(format.physicalPage, format.numberStyle, format.prefix, format.logicalPage);
    }

    public void removePageLabel(int page)
    {
        if(page <= 1)
        {
            return;
        } else
        {
            map.remove(Integer.valueOf(page - 1));
            return;
        }
    }

    public PdfDictionary getDictionary(PdfWriter writer)
    {
        try
        {
            return PdfNumberTree.writeTree(map, writer);
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
    }

    public static String[] getPageLabels(PdfReader reader)
    {
        int n = reader.getNumberOfPages();
        PdfDictionary dict = reader.getCatalog();
        PdfDictionary labels = (PdfDictionary)PdfReader.getPdfObjectRelease(dict.get(PdfName.PAGELABELS));
        if(labels == null)
            return null;
        String labelstrings[] = new String[n];
        HashMap numberTree = PdfNumberTree.readTree(labels);
        int pagecount = 1;
        String prefix = "";
        char type = 'D';
        for(int i = 0; i < n; i++)
        {
            Integer current = Integer.valueOf(i);
            if(numberTree.containsKey(current))
            {
                PdfDictionary d = (PdfDictionary)PdfReader.getPdfObjectRelease((PdfObject)numberTree.get(current));
                if(d.contains(PdfName.ST))
                    pagecount = ((PdfNumber)d.get(PdfName.ST)).intValue();
                else
                    pagecount = 1;
                if(d.contains(PdfName.P))
                    prefix = ((PdfString)d.get(PdfName.P)).toUnicodeString();
                if(d.contains(PdfName.S))
                    type = ((PdfName)d.get(PdfName.S)).toString().charAt(1);
                else
                    type = 'e';
            }
            switch(type)
            {
            default:
                labelstrings[i] = (new StringBuilder()).append(prefix).append(pagecount).toString();
                break;

            case 82: // 'R'
                labelstrings[i] = (new StringBuilder()).append(prefix).append(RomanNumberFactory.getUpperCaseString(pagecount)).toString();
                break;

            case 114: // 'r'
                labelstrings[i] = (new StringBuilder()).append(prefix).append(RomanNumberFactory.getLowerCaseString(pagecount)).toString();
                break;

            case 65: // 'A'
                labelstrings[i] = (new StringBuilder()).append(prefix).append(RomanAlphabetFactory.getUpperCaseString(pagecount)).toString();
                break;

            case 97: // 'a'
                labelstrings[i] = (new StringBuilder()).append(prefix).append(RomanAlphabetFactory.getLowerCaseString(pagecount)).toString();
                break;

            case 101: // 'e'
                labelstrings[i] = prefix;
                break;
            }
            pagecount++;
        }

        return labelstrings;
    }

    public static PdfPageLabelFormat[] getPageLabelFormats(PdfReader reader)
    {
        PdfDictionary dict = reader.getCatalog();
        PdfDictionary labels = (PdfDictionary)PdfReader.getPdfObjectRelease(dict.get(PdfName.PAGELABELS));
        if(labels == null)
            return null;
        HashMap numberTree = PdfNumberTree.readTree(labels);
        Integer numbers[] = new Integer[numberTree.size()];
        numbers = (Integer[])numberTree.keySet().toArray(numbers);
        Arrays.sort(numbers);
        PdfPageLabelFormat formats[] = new PdfPageLabelFormat[numberTree.size()];
        for(int k = 0; k < numbers.length; k++)
        {
            Integer key = numbers[k];
            PdfDictionary d = (PdfDictionary)PdfReader.getPdfObjectRelease((PdfObject)numberTree.get(key));
            int pagecount;
            if(d.contains(PdfName.ST))
                pagecount = ((PdfNumber)d.get(PdfName.ST)).intValue();
            else
                pagecount = 1;
            String prefix;
            if(d.contains(PdfName.P))
                prefix = ((PdfString)d.get(PdfName.P)).toUnicodeString();
            else
                prefix = "";
            int numberStyle;
            if(d.contains(PdfName.S))
            {
                char type = ((PdfName)d.get(PdfName.S)).toString().charAt(1);
                switch(type)
                {
                case 82: // 'R'
                    numberStyle = 1;
                    break;

                case 114: // 'r'
                    numberStyle = 2;
                    break;

                case 65: // 'A'
                    numberStyle = 3;
                    break;

                case 97: // 'a'
                    numberStyle = 4;
                    break;

                default:
                    numberStyle = 0;
                    break;
                }
            } else
            {
                numberStyle = 5;
            }
            formats[k] = new PdfPageLabelFormat(key.intValue() + 1, numberStyle, prefix, pagecount);
        }

        return formats;
    }

    public static final int DECIMAL_ARABIC_NUMERALS = 0;
    public static final int UPPERCASE_ROMAN_NUMERALS = 1;
    public static final int LOWERCASE_ROMAN_NUMERALS = 2;
    public static final int UPPERCASE_LETTERS = 3;
    public static final int LOWERCASE_LETTERS = 4;
    public static final int EMPTY = 5;
    static PdfName numberingStyle[];
    private HashMap map;

    static 
    {
        numberingStyle = (new PdfName[] {
            PdfName.D, PdfName.R, new PdfName("r"), PdfName.A, new PdfName("a")
        });
    }
}
