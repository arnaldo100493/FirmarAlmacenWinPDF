// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   RegexFileFilter.java

package org.apache.commons.io.filefilter;

import java.io.File;
import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.io.IOCase;

// Referenced classes of package org.apache.commons.io.filefilter:
//            AbstractFileFilter

public class RegexFileFilter extends AbstractFileFilter
    implements Serializable
{

    public RegexFileFilter(String pattern)
    {
        if(pattern == null)
        {
            throw new IllegalArgumentException("Pattern is missing");
        } else
        {
            this.pattern = Pattern.compile(pattern);
            return;
        }
    }

    public RegexFileFilter(String pattern, IOCase caseSensitivity)
    {
        if(pattern == null)
            throw new IllegalArgumentException("Pattern is missing");
        int flags = 0;
        if(caseSensitivity != null && !caseSensitivity.isCaseSensitive())
            flags = 2;
        this.pattern = Pattern.compile(pattern, flags);
    }

    public RegexFileFilter(String pattern, int flags)
    {
        if(pattern == null)
        {
            throw new IllegalArgumentException("Pattern is missing");
        } else
        {
            this.pattern = Pattern.compile(pattern, flags);
            return;
        }
    }

    public RegexFileFilter(Pattern pattern)
    {
        if(pattern == null)
        {
            throw new IllegalArgumentException("Pattern is missing");
        } else
        {
            this.pattern = pattern;
            return;
        }
    }

    public boolean accept(File dir, String name)
    {
        return pattern.matcher(name).matches();
    }

    private final Pattern pattern;
}
