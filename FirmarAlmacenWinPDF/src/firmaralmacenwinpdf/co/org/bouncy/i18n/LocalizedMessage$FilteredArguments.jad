// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LocalizedMessage.java

package co.org.bouncy.i18n;

import co.org.bouncy.i18n.filter.Filter;
import co.org.bouncy.i18n.filter.TrustedInput;
import co.org.bouncy.i18n.filter.UntrustedInput;
import co.org.bouncy.i18n.filter.UntrustedUrlInput;
import java.util.Locale;

// Referenced classes of package co.org.bouncy.i18n:
//            LocaleString, LocalizedMessage

protected class LocalizedMessage$FilteredArguments
{

    public boolean isEmpty()
    {
        return unpackedArgs.length == 0;
    }

    public Object[] getArguments()
    {
        return arguments;
    }

    public Object[] getFilteredArgs(Locale locale)
    {
        Object result[] = new Object[unpackedArgs.length];
        for(int i = 0; i < unpackedArgs.length; i++)
        {
            Object arg;
            if(filteredArgs[i] != null)
            {
                arg = filteredArgs[i];
            } else
            {
                arg = unpackedArgs[i];
                if(isLocaleSpecific[i])
                {
                    arg = ((LocaleString)arg).getLocaleString(locale);
                    arg = filter(argFilterType[i], arg);
                } else
                {
                    arg = filter(argFilterType[i], arg);
                    filteredArgs[i] = arg;
                }
            }
            result[i] = arg;
        }

        return result;
    }

    private Object filter(int type, Object obj)
    {
        if(filter != null)
        {
            Object o = null != obj ? obj : "null";
            switch(type)
            {
            case 0: // '\0'
                return o;

            case 1: // '\001'
                return filter.doFilter(o.toString());

            case 2: // '\002'
                return filter.doFilterUrl(o.toString());
            }
            return null;
        } else
        {
            return obj;
        }
    }

    public Filter getFilter()
    {
        return filter;
    }

    public void setFilter(Filter filter)
    {
        if(filter != this.filter)
        {
            for(int i = 0; i < unpackedArgs.length; i++)
                filteredArgs[i] = null;

        }
        this.filter = filter;
    }

    protected static final int NO_FILTER = 0;
    protected static final int FILTER = 1;
    protected static final int FILTER_URL = 2;
    protected Filter filter;
    protected boolean isLocaleSpecific[];
    protected int argFilterType[];
    protected Object arguments[];
    protected Object unpackedArgs[];
    protected Object filteredArgs[];
    final LocalizedMessage this$0;

    LocalizedMessage$FilteredArguments()
    {
        this(new Object[0]);
    }

    LocalizedMessage$FilteredArguments(Object args[])
    {
        this$0 = LocalizedMessage.this;
        super();
        filter = null;
        arguments = args;
        unpackedArgs = new Object[args.length];
        filteredArgs = new Object[args.length];
        isLocaleSpecific = new boolean[args.length];
        argFilterType = new int[args.length];
        for(int i = 0; i < args.length; i++)
        {
            if(args[i] instanceof TrustedInput)
            {
                unpackedArgs[i] = ((TrustedInput)args[i]).getInput();
                argFilterType[i] = 0;
            } else
            if(args[i] instanceof UntrustedInput)
            {
                unpackedArgs[i] = ((UntrustedInput)args[i]).getInput();
                if(args[i] instanceof UntrustedUrlInput)
                    argFilterType[i] = 2;
                else
                    argFilterType[i] = 1;
            } else
            {
                unpackedArgs[i] = args[i];
                argFilterType[i] = 1;
            }
            isLocaleSpecific[i] = unpackedArgs[i] instanceof LocaleString;
        }

    }
}
