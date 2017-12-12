// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SysoLogger.java

package co.com.pdf.text.log;

import java.io.PrintStream;

// Referenced classes of package co.com.pdf.text.log:
//            Logger, Level

public class SysoLogger
    implements Logger
{

    public SysoLogger()
    {
        this(1);
    }

    public SysoLogger(int packageReduce)
    {
        shorten = packageReduce;
    }

    protected SysoLogger(String klass, int shorten)
    {
        this.shorten = shorten;
        name = klass;
    }

    public Logger getLogger(Class klass)
    {
        return new SysoLogger(klass.getName(), shorten);
    }

    public Logger getLogger(String name)
    {
        return new SysoLogger("[itext]", 0);
    }

    public boolean isLogging(Level level)
    {
        return true;
    }

    public void warn(String message)
    {
        System.out.println(String.format("%s WARN  %s", new Object[] {
            shorten(name), message
        }));
    }

    private String shorten(String className)
    {
        if(shorten != 0)
        {
            StringBuilder target = new StringBuilder();
            String name = className;
            for(int fromIndex = className.indexOf('.'); fromIndex != -1; fromIndex = name.indexOf('.'))
            {
                int parseTo = fromIndex >= shorten ? shorten : fromIndex;
                target.append(name.substring(0, parseTo));
                target.append('.');
                name = name.substring(fromIndex + 1);
            }

            target.append(className.substring(className.lastIndexOf('.') + 1));
            return target.toString();
        } else
        {
            return className;
        }
    }

    public void trace(String message)
    {
        System.out.println(String.format("%s TRACE %s", new Object[] {
            shorten(name), message
        }));
    }

    public void debug(String message)
    {
        System.out.println(String.format("%s DEBUG %s", new Object[] {
            shorten(name), message
        }));
    }

    public void info(String message)
    {
        System.out.println(String.format("%s INFO  %s", new Object[] {
            shorten(name), message
        }));
    }

    public void error(String message)
    {
        System.out.println(String.format("%s ERROR %s", new Object[] {
            name, message
        }));
    }

    public void error(String message, Exception e)
    {
        System.out.println(String.format("%s ERROR %s", new Object[] {
            name, message
        }));
        e.printStackTrace(System.out);
    }

    private String name;
    private final int shorten;
}
