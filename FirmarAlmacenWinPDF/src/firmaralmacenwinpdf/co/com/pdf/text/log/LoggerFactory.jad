// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   LoggerFactory.java

package co.com.pdf.text.log;


// Referenced classes of package co.com.pdf.text.log:
//            NoOpLogger, Logger

public class LoggerFactory
{

    public static Logger getLogger(Class klass)
    {
        return myself.logger.getLogger(klass);
    }

    public static Logger getLogger(String name)
    {
        return myself.logger.getLogger(name);
    }

    public static LoggerFactory getInstance()
    {
        return myself;
    }

    private LoggerFactory()
    {
        logger = new NoOpLogger();
    }

    public void setLogger(Logger logger)
    {
        this.logger = logger;
    }

    public Logger logger()
    {
        return logger;
    }

    private static LoggerFactory myself = new LoggerFactory();
    private Logger logger;

}
