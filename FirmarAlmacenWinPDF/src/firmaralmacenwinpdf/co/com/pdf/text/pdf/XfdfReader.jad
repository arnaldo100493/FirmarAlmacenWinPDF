// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   XfdfReader.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.xml.simpleparser.SimpleXMLDocHandler;
import co.com.pdf.text.xml.simpleparser.SimpleXMLParser;
import java.io.*;
import java.util.*;

public class XfdfReader
    implements SimpleXMLDocHandler
{

    public XfdfReader(String filename)
        throws IOException
    {
        FileInputStream fin;
        foundRoot = false;
        fieldNames = new Stack();
        fieldValues = new Stack();
        fin = null;
        fin = new FileInputStream(filename);
        SimpleXMLParser.parse(this, fin);
        try
        {
            if(fin != null)
                fin.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_80;
        Exception exception;
        exception;
        try
        {
            if(fin != null)
                fin.close();
        }
        catch(Exception e) { }
        throw exception;
    }

    public XfdfReader(byte xfdfIn[])
        throws IOException
    {
        this(((InputStream) (new ByteArrayInputStream(xfdfIn))));
    }

    public XfdfReader(InputStream is)
        throws IOException
    {
        foundRoot = false;
        fieldNames = new Stack();
        fieldValues = new Stack();
        SimpleXMLParser.parse(this, is);
    }

    public HashMap getFields()
    {
        return fields;
    }

    public String getField(String name)
    {
        return (String)fields.get(name);
    }

    public String getFieldValue(String name)
    {
        String field = (String)fields.get(name);
        if(field == null)
            return null;
        else
            return field;
    }

    public List getListValues(String name)
    {
        return (List)listFields.get(name);
    }

    public String getFileSpec()
    {
        return fileSpec;
    }

    public void startElement(String tag, Map h)
    {
        if(!foundRoot)
        {
            if(!tag.equals("xfdf"))
                throw new RuntimeException(MessageLocalization.getComposedMessage("root.element.is.not.xfdf.1", new Object[] {
                    tag
                }));
            foundRoot = true;
        }
        if(!tag.equals("xfdf"))
            if(tag.equals("f"))
                fileSpec = (String)h.get("href");
            else
            if(tag.equals("fields"))
            {
                fields = new HashMap();
                listFields = new HashMap();
            } else
            if(tag.equals("field"))
            {
                String fName = (String)h.get("name");
                fieldNames.push(fName);
            } else
            if(tag.equals("value"))
                fieldValues.push("");
    }

    public void endElement(String tag)
    {
        if(tag.equals("value"))
        {
            String fName = "";
            for(int k = 0; k < fieldNames.size(); k++)
                fName = (new StringBuilder()).append(fName).append(".").append((String)fieldNames.elementAt(k)).toString();

            if(fName.startsWith("."))
                fName = fName.substring(1);
            String fVal = (String)fieldValues.pop();
            String old = (String)fields.put(fName, fVal);
            if(old != null)
            {
                List l = (List)listFields.get(fName);
                if(l == null)
                {
                    l = new ArrayList();
                    l.add(old);
                }
                l.add(fVal);
                listFields.put(fName, l);
            }
        } else
        if(tag.equals("field") && !fieldNames.isEmpty())
            fieldNames.pop();
    }

    public void startDocument()
    {
        fileSpec = "";
    }

    public void endDocument()
    {
    }

    public void text(String str)
    {
        if(fieldNames.isEmpty() || fieldValues.isEmpty())
        {
            return;
        } else
        {
            String val = (String)fieldValues.pop();
            val = (new StringBuilder()).append(val).append(str).toString();
            fieldValues.push(val);
            return;
        }
    }

    private boolean foundRoot;
    private final Stack fieldNames;
    private final Stack fieldValues;
    HashMap fields;
    protected HashMap listFields;
    String fileSpec;
}
