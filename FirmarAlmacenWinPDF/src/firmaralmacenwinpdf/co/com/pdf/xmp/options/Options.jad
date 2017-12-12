// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Options.java

package co.com.pdf.xmp.options;

import co.com.pdf.xmp.XMPException;
import java.util.HashMap;
import java.util.Map;

public abstract class Options
{

    public Options()
    {
        options = 0;
        optionNames = null;
    }

    public Options(int options)
        throws XMPException
    {
        this.options = 0;
        optionNames = null;
        assertOptionsValid(options);
        setOptions(options);
    }

    public void clear()
    {
        options = 0;
    }

    public boolean isExactly(int optionBits)
    {
        return getOptions() == optionBits;
    }

    public boolean containsAllOptions(int optionBits)
    {
        return (getOptions() & optionBits) == optionBits;
    }

    public boolean containsOneOf(int optionBits)
    {
        return (getOptions() & optionBits) != 0;
    }

    protected boolean getOption(int optionBit)
    {
        return (options & optionBit) != 0;
    }

    public void setOption(int optionBits, boolean value)
    {
        options = value ? options | optionBits : options & ~optionBits;
    }

    public int getOptions()
    {
        return options;
    }

    public void setOptions(int options)
        throws XMPException
    {
        assertOptionsValid(options);
        this.options = options;
    }

    public boolean equals(Object obj)
    {
        return getOptions() == ((Options)obj).getOptions();
    }

    public int hashCode()
    {
        return getOptions();
    }

    public String getOptionsString()
    {
        if(options != 0)
        {
            StringBuffer sb = new StringBuffer();
            int oneLessBit;
            for(int theBits = options; theBits != 0; theBits = oneLessBit)
            {
                oneLessBit = theBits & theBits - 1;
                int singleBit = theBits ^ oneLessBit;
                String bitName = getOptionName(singleBit);
                sb.append(bitName);
                if(oneLessBit != 0)
                    sb.append(" | ");
            }

            return sb.toString();
        } else
        {
            return "<none>";
        }
    }

    public String toString()
    {
        return (new StringBuilder()).append("0x").append(Integer.toHexString(options)).toString();
    }

    protected abstract int getValidOptions();

    protected abstract String defineOptionName(int i);

    protected void assertConsistency(int i)
        throws XMPException
    {
    }

    private void assertOptionsValid(int options)
        throws XMPException
    {
        int invalidOptions = options & ~getValidOptions();
        if(invalidOptions == 0)
            assertConsistency(options);
        else
            throw new XMPException((new StringBuilder()).append("The option bit(s) 0x").append(Integer.toHexString(invalidOptions)).append(" are invalid!").toString(), 103);
    }

    private String getOptionName(int option)
    {
        Map optionsNames = procureOptionNames();
        Integer key = new Integer(option);
        String result = (String)optionsNames.get(key);
        if(result == null)
        {
            result = defineOptionName(option);
            if(result != null)
                optionsNames.put(key, result);
            else
                result = "<option name not defined>";
        }
        return result;
    }

    private Map procureOptionNames()
    {
        if(optionNames == null)
            optionNames = new HashMap();
        return optionNames;
    }

    private int options;
    private Map optionNames;
}
