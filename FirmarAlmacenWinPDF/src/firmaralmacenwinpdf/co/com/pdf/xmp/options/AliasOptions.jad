// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   AliasOptions.java

package co.com.pdf.xmp.options;

import co.com.pdf.xmp.XMPException;

// Referenced classes of package co.com.pdf.xmp.options:
//            Options, PropertyOptions

public final class AliasOptions extends Options
{

    public AliasOptions()
    {
    }

    public AliasOptions(int options)
        throws XMPException
    {
        super(options);
    }

    public boolean isSimple()
    {
        return getOptions() == 0;
    }

    public boolean isArray()
    {
        return getOption(512);
    }

    public AliasOptions setArray(boolean value)
    {
        setOption(512, value);
        return this;
    }

    public boolean isArrayOrdered()
    {
        return getOption(1024);
    }

    public AliasOptions setArrayOrdered(boolean value)
    {
        setOption(1536, value);
        return this;
    }

    public boolean isArrayAlternate()
    {
        return getOption(2048);
    }

    public AliasOptions setArrayAlternate(boolean value)
    {
        setOption(3584, value);
        return this;
    }

    public boolean isArrayAltText()
    {
        return getOption(4096);
    }

    public AliasOptions setArrayAltText(boolean value)
    {
        setOption(7680, value);
        return this;
    }

    public PropertyOptions toPropertyOptions()
        throws XMPException
    {
        return new PropertyOptions(getOptions());
    }

    protected String defineOptionName(int option)
    {
        switch(option)
        {
        case 0: // '\0'
            return "PROP_DIRECT";

        case 512: 
            return "ARRAY";

        case 1024: 
            return "ARRAY_ORDERED";

        case 2048: 
            return "ARRAY_ALTERNATE";

        case 4096: 
            return "ARRAY_ALT_TEXT";
        }
        return null;
    }

    protected int getValidOptions()
    {
        return 7680;
    }

    public static final int PROP_DIRECT = 0;
    public static final int PROP_ARRAY = 512;
    public static final int PROP_ARRAY_ORDERED = 1024;
    public static final int PROP_ARRAY_ALTERNATE = 2048;
    public static final int PROP_ARRAY_ALT_TEXT = 4096;
}
