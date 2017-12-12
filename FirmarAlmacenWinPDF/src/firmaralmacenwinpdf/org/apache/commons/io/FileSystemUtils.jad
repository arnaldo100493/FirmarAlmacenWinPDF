// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileSystemUtils.java

package org.apache.commons.io;

import java.io.*;
import java.util.*;

// Referenced classes of package org.apache.commons.io:
//            IOExceptionWithCause, FilenameUtils, ThreadMonitor, IOUtils

public class FileSystemUtils
{

    public FileSystemUtils()
    {
    }

    /**
     * @deprecated Method freeSpace is deprecated
     */

    public static long freeSpace(String path)
        throws IOException
    {
        return INSTANCE.freeSpaceOS(path, OS, false, -1L);
    }

    public static long freeSpaceKb(String path)
        throws IOException
    {
        return freeSpaceKb(path, -1L);
    }

    public static long freeSpaceKb(String path, long timeout)
        throws IOException
    {
        return INSTANCE.freeSpaceOS(path, OS, true, timeout);
    }

    public static long freeSpaceKb()
        throws IOException
    {
        return freeSpaceKb(-1L);
    }

    public static long freeSpaceKb(long timeout)
        throws IOException
    {
        return freeSpaceKb((new File(".")).getAbsolutePath(), timeout);
    }

    long freeSpaceOS(String path, int os, boolean kb, long timeout)
        throws IOException
    {
        if(path == null)
            throw new IllegalArgumentException("Path must not be empty");
        switch(os)
        {
        case 1: // '\001'
            return kb ? freeSpaceWindows(path, timeout) / 1024L : freeSpaceWindows(path, timeout);

        case 2: // '\002'
            return freeSpaceUnix(path, kb, false, timeout);

        case 3: // '\003'
            return freeSpaceUnix(path, kb, true, timeout);

        case 0: // '\0'
            throw new IllegalStateException("Unsupported operating system");
        }
        throw new IllegalStateException("Exception caught when determining operating system");
    }

    long freeSpaceWindows(String path, long timeout)
        throws IOException
    {
        path = FilenameUtils.normalize(path, false);
        if(path.length() > 0 && path.charAt(0) != '"')
            path = (new StringBuilder()).append("\"").append(path).append("\"").toString();
        String cmdAttribs[] = {
            "cmd.exe", "/C", (new StringBuilder()).append("dir /a /-c ").append(path).toString()
        };
        List lines = performCommand(cmdAttribs, 0x7fffffff, timeout);
        for(int i = lines.size() - 1; i >= 0; i--)
        {
            String line = (String)lines.get(i);
            if(line.length() > 0)
                return parseDir(line, path);
        }

        throw new IOException((new StringBuilder()).append("Command line 'dir /-c' did not return any info for path '").append(path).append("'").toString());
    }

    long parseDir(String line, String path)
        throws IOException
    {
        int bytesStart = 0;
        int bytesEnd = 0;
        int j = line.length() - 1;
        do
        {
            if(j < 0)
                break;
            char c = line.charAt(j);
            if(Character.isDigit(c))
            {
                bytesEnd = j + 1;
                break;
            }
            j--;
        } while(true);
        do
        {
            if(j < 0)
                break;
            char c = line.charAt(j);
            if(!Character.isDigit(c) && c != ',' && c != '.')
            {
                bytesStart = j + 1;
                break;
            }
            j--;
        } while(true);
        if(j < 0)
            throw new IOException((new StringBuilder()).append("Command line 'dir /-c' did not return valid info for path '").append(path).append("'").toString());
        StringBuilder buf = new StringBuilder(line.substring(bytesStart, bytesEnd));
        for(int k = 0; k < buf.length(); k++)
            if(buf.charAt(k) == ',' || buf.charAt(k) == '.')
                buf.deleteCharAt(k--);

        return parseBytes(buf.toString(), path);
    }

    long freeSpaceUnix(String path, boolean kb, boolean posix, long timeout)
        throws IOException
    {
        if(path.length() == 0)
            throw new IllegalArgumentException("Path must not be empty");
        String flags = "-";
        if(kb)
            flags = (new StringBuilder()).append(flags).append("k").toString();
        if(posix)
            flags = (new StringBuilder()).append(flags).append("P").toString();
        String cmdAttribs[] = flags.length() <= 1 ? (new String[] {
            DF, path
        }) : (new String[] {
            DF, flags, path
        });
        List lines = performCommand(cmdAttribs, 3, timeout);
        if(lines.size() < 2)
            throw new IOException((new StringBuilder()).append("Command line '").append(DF).append("' did not return info as expected ").append("for path '").append(path).append("'- response was ").append(lines).toString());
        String line2 = (String)lines.get(1);
        StringTokenizer tok = new StringTokenizer(line2, " ");
        if(tok.countTokens() < 4)
        {
            if(tok.countTokens() == 1 && lines.size() >= 3)
            {
                String line3 = (String)lines.get(2);
                tok = new StringTokenizer(line3, " ");
            } else
            {
                throw new IOException((new StringBuilder()).append("Command line '").append(DF).append("' did not return data as expected ").append("for path '").append(path).append("'- check path is valid").toString());
            }
        } else
        {
            tok.nextToken();
        }
        tok.nextToken();
        tok.nextToken();
        String freeSpace = tok.nextToken();
        return parseBytes(freeSpace, path);
    }

    long parseBytes(String freeSpace, String path)
        throws IOException
    {
        try
        {
            long bytes = Long.parseLong(freeSpace);
            if(bytes < 0L)
                throw new IOException((new StringBuilder()).append("Command line '").append(DF).append("' did not find free space in response ").append("for path '").append(path).append("'- check path is valid").toString());
            else
                return bytes;
        }
        catch(NumberFormatException ex)
        {
            throw new IOExceptionWithCause((new StringBuilder()).append("Command line '").append(DF).append("' did not return numeric data as expected ").append("for path '").append(path).append("'- check path is valid").toString(), ex);
        }
    }

    List performCommand(String cmdAttribs[], int max, long timeout)
        throws IOException
    {
        List lines;
        Process proc;
        InputStream in;
        OutputStream out;
        InputStream err;
        BufferedReader inr;
        lines = new ArrayList(20);
        proc = null;
        in = null;
        out = null;
        err = null;
        inr = null;
        List list;
        try
        {
            Thread monitor = ThreadMonitor.start(timeout);
            proc = openProcess(cmdAttribs);
            in = proc.getInputStream();
            out = proc.getOutputStream();
            err = proc.getErrorStream();
            inr = new BufferedReader(new InputStreamReader(in));
            for(String line = inr.readLine(); line != null && lines.size() < max; line = inr.readLine())
            {
                line = line.toLowerCase(Locale.ENGLISH).trim();
                lines.add(line);
            }

            proc.waitFor();
            ThreadMonitor.stop(monitor);
            if(proc.exitValue() != 0)
                throw new IOException((new StringBuilder()).append("Command line returned OS error code '").append(proc.exitValue()).append("' for command ").append(Arrays.asList(cmdAttribs)).toString());
            if(lines.isEmpty())
                throw new IOException((new StringBuilder()).append("Command line did not return any info for command ").append(Arrays.asList(cmdAttribs)).toString());
            list = lines;
        }
        catch(InterruptedException ex)
        {
            throw new IOExceptionWithCause((new StringBuilder()).append("Command line threw an InterruptedException for command ").append(Arrays.asList(cmdAttribs)).append(" timeout=").append(timeout).toString(), ex);
        }
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
        IOUtils.closeQuietly(err);
        IOUtils.closeQuietly(inr);
        if(proc != null)
            proc.destroy();
        return list;
        Exception exception;
        exception;
        IOUtils.closeQuietly(in);
        IOUtils.closeQuietly(out);
        IOUtils.closeQuietly(err);
        IOUtils.closeQuietly(inr);
        if(proc != null)
            proc.destroy();
        throw exception;
    }

    Process openProcess(String cmdAttribs[])
        throws IOException
    {
        return Runtime.getRuntime().exec(cmdAttribs);
    }

    private static final FileSystemUtils INSTANCE = new FileSystemUtils();
    private static final int INIT_PROBLEM = -1;
    private static final int OTHER = 0;
    private static final int WINDOWS = 1;
    private static final int UNIX = 2;
    private static final int POSIX_UNIX = 3;
    private static final int OS;
    private static final String DF;

    static 
    {
        int os = 0;
        String dfPath = "df";
        try
        {
            String osName = System.getProperty("os.name");
            if(osName == null)
                throw new IOException("os.name not found");
            osName = osName.toLowerCase(Locale.ENGLISH);
            if(osName.indexOf("windows") != -1)
                os = 1;
            else
            if(osName.indexOf("linux") != -1 || osName.indexOf("mpe/ix") != -1 || osName.indexOf("freebsd") != -1 || osName.indexOf("irix") != -1 || osName.indexOf("digital unix") != -1 || osName.indexOf("unix") != -1 || osName.indexOf("mac os x") != -1)
                os = 2;
            else
            if(osName.indexOf("sun os") != -1 || osName.indexOf("sunos") != -1 || osName.indexOf("solaris") != -1)
            {
                os = 3;
                dfPath = "/usr/xpg4/bin/df";
            } else
            if(osName.indexOf("hp-ux") != -1 || osName.indexOf("aix") != -1)
                os = 3;
            else
                os = 0;
        }
        catch(Exception ex)
        {
            os = -1;
        }
        OS = os;
        DF = dfPath;
    }
}
