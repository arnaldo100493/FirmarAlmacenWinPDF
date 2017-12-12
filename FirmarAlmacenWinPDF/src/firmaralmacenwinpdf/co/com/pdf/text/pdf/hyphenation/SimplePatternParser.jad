// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SimplePatternParser.java

package co.com.pdf.text.pdf.hyphenation;

import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.xml.simpleparser.SimpleXMLDocHandler;
import co.com.pdf.text.xml.simpleparser.SimpleXMLParser;
import java.io.*;
import java.util.*;

// Referenced classes of package co.com.pdf.text.pdf.hyphenation:
//            Hyphen, PatternConsumer

public class SimplePatternParser
    implements SimpleXMLDocHandler, PatternConsumer
{

    public SimplePatternParser()
    {
        token = new StringBuffer();
        hyphenChar = '-';
    }

    public void parse(InputStream stream, PatternConsumer consumer)
    {
        this.consumer = consumer;
        try
        {
            SimpleXMLParser.parse(this, stream);
        }
        catch(IOException e)
        {
            throw new ExceptionConverter(e);
        }
        try
        {
            stream.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_45;
        Exception exception1;
        exception1;
        try
        {
            stream.close();
        }
        catch(Exception e) { }
        throw exception1;
    }

    protected static String getPattern(String word)
    {
        StringBuffer pat = new StringBuffer();
        int len = word.length();
        for(int i = 0; i < len; i++)
            if(!Character.isDigit(word.charAt(i)))
                pat.append(word.charAt(i));

        return pat.toString();
    }

    protected ArrayList normalizeException(ArrayList ex)
    {
        ArrayList res = new ArrayList();
        for(int i = 0; i < ex.size(); i++)
        {
            Object item = ex.get(i);
            if(item instanceof String)
            {
                String str = (String)item;
                StringBuffer buf = new StringBuffer();
                for(int j = 0; j < str.length(); j++)
                {
                    char c = str.charAt(j);
                    if(c != hyphenChar)
                    {
                        buf.append(c);
                    } else
                    {
                        res.add(buf.toString());
                        buf.setLength(0);
                        char h[] = new char[1];
                        h[0] = hyphenChar;
                        res.add(new Hyphen(new String(h), null, null));
                    }
                }

                if(buf.length() > 0)
                    res.add(buf.toString());
            } else
            {
                res.add(item);
            }
        }

        return res;
    }

    protected String getExceptionWord(ArrayList ex)
    {
        StringBuffer res = new StringBuffer();
        for(int i = 0; i < ex.size(); i++)
        {
            Object item = ex.get(i);
            if(item instanceof String)
            {
                res.append((String)item);
                continue;
            }
            if(((Hyphen)item).noBreak != null)
                res.append(((Hyphen)item).noBreak);
        }

        return res.toString();
    }

    protected static String getInterletterValues(String pat)
    {
        StringBuffer il = new StringBuffer();
        String word = (new StringBuilder()).append(pat).append("a").toString();
        int len = word.length();
        for(int i = 0; i < len; i++)
        {
            char c = word.charAt(i);
            if(Character.isDigit(c))
            {
                il.append(c);
                i++;
            } else
            {
                il.append('0');
            }
        }

        return il.toString();
    }

    public void endDocument()
    {
    }

    public void endElement(String tag)
    {
        if(token.length() > 0)
        {
            String word = token.toString();
            switch(currElement)
            {
            case 1: // '\001'
                consumer.addClass(word);
                break;

            case 2: // '\002'
                exception.add(word);
                exception = normalizeException(exception);
                consumer.addException(getExceptionWord(exception), (ArrayList)exception.clone());
                break;

            case 3: // '\003'
                consumer.addPattern(getPattern(word), getInterletterValues(word));
                break;
            }
            if(currElement != 4)
                token.setLength(0);
        }
        if(currElement == 4)
            currElement = 2;
        else
            currElement = 0;
    }

    public void startDocument()
    {
    }

    public void startElement(String tag, Map h)
    {
        if(tag.equals("hyphen-char"))
        {
            String hh = (String)h.get("value");
            if(hh != null && hh.length() == 1)
                hyphenChar = hh.charAt(0);
        } else
        if(tag.equals("classes"))
            currElement = 1;
        else
        if(tag.equals("patterns"))
            currElement = 3;
        else
        if(tag.equals("exceptions"))
        {
            currElement = 2;
            exception = new ArrayList();
        } else
        if(tag.equals("hyphen"))
        {
            if(token.length() > 0)
                exception.add(token.toString());
            exception.add(new Hyphen((String)h.get("pre"), (String)h.get("no"), (String)h.get("post")));
            currElement = 4;
        }
        token.setLength(0);
    }

    public void text(String str)
    {
        StringTokenizer tk = new StringTokenizer(str);
        do
        {
            if(!tk.hasMoreTokens())
                break;
            String word = tk.nextToken();
            switch(currElement)
            {
            case 1: // '\001'
                consumer.addClass(word);
                break;

            case 2: // '\002'
                exception.add(word);
                exception = normalizeException(exception);
                consumer.addException(getExceptionWord(exception), (ArrayList)exception.clone());
                exception.clear();
                break;

            case 3: // '\003'
                consumer.addPattern(getPattern(word), getInterletterValues(word));
                break;
            }
        } while(true);
    }

    public void addClass(String c)
    {
        System.out.println((new StringBuilder()).append("class: ").append(c).toString());
    }

    public void addException(String w, ArrayList e)
    {
        System.out.println((new StringBuilder()).append("exception: ").append(w).append(" : ").append(e.toString()).toString());
    }

    public void addPattern(String p, String v)
    {
        System.out.println((new StringBuilder()).append("pattern: ").append(p).append(" : ").append(v).toString());
    }

    int currElement;
    PatternConsumer consumer;
    StringBuffer token;
    ArrayList exception;
    char hyphenChar;
    SimpleXMLParser parser;
    static final int ELEM_CLASSES = 1;
    static final int ELEM_EXCEPTIONS = 2;
    static final int ELEM_PATTERNS = 3;
    static final int ELEM_HYPHEN = 4;
}
