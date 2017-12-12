// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   SharedFileInputStream.java

package co.org.bouncy.mail.smime.util;

import java.io.*;
import java.util.*;
import javax.mail.internet.SharedInputStream;

public class SharedFileInputStream extends FilterInputStream
    implements SharedInputStream
{

    public SharedFileInputStream(String fileName)
        throws IOException
    {
        this(new File(fileName));
    }

    public SharedFileInputStream(File file)
        throws IOException
    {
        this(file, 0L, file.length());
    }

    private SharedFileInputStream(File file, long start, long length)
        throws IOException
    {
        super(new BufferedInputStream(new FileInputStream(file)));
        _subStreams = new LinkedList();
        _parent = null;
        _file = file;
        _start = start;
        _length = length;
        in.skip(start);
    }

    private SharedFileInputStream(SharedFileInputStream parent, long start, long length)
        throws IOException
    {
        super(new BufferedInputStream(new FileInputStream(parent._file)));
        _subStreams = new LinkedList();
        _parent = parent;
        _file = parent._file;
        _start = start;
        _length = length;
        in.skip(start);
    }

    public long getPosition()
    {
        return _position;
    }

    public InputStream newStream(long start, long finish)
    {
        try
        {
            SharedFileInputStream stream;
            if(finish < 0L)
            {
                if(_length > 0L)
                    stream = new SharedFileInputStream(this, _start + start, _length - start);
                else
                if(_length == 0L)
                    stream = new SharedFileInputStream(this, _start + start, 0L);
                else
                    stream = new SharedFileInputStream(this, _start + start, -1L);
            } else
            {
                stream = new SharedFileInputStream(this, _start + start, finish - start);
            }
            _subStreams.add(stream);
            return stream;
        }
        catch(IOException e)
        {
            throw new IllegalStateException((new StringBuilder()).append("unable to create shared stream: ").append(e).toString());
        }
    }

    public int read(byte buf[])
        throws IOException
    {
        return read(buf, 0, buf.length);
    }

    public int read(byte buf[], int off, int len)
        throws IOException
    {
        int count = 0;
        if(len == 0)
            return 0;
        do
        {
            if(count >= len)
                break;
            int ch = read();
            if(ch < 0)
                break;
            buf[off + count] = (byte)ch;
            count++;
        } while(true);
        if(count == 0)
            return -1;
        else
            return count;
    }

    public int read()
        throws IOException
    {
        if(_position == _length)
        {
            return -1;
        } else
        {
            _position++;
            return in.read();
        }
    }

    public boolean markSupported()
    {
        return true;
    }

    public long skip(long n)
        throws IOException
    {
        long count;
        for(count = 0L; count != n && read() >= 0; count++);
        return count;
    }

    public void mark(int readLimit)
    {
        _markedPosition = _position;
        in.mark(readLimit);
    }

    public void reset()
        throws IOException
    {
        _position = _markedPosition;
        in.reset();
    }

    public SharedFileInputStream getRoot()
    {
        if(_parent != null)
            return _parent.getRoot();
        else
            return this;
    }

    public void dispose()
        throws IOException
    {
        for(Iterator it = _subStreams.iterator(); it.hasNext();)
            try
            {
                ((SharedFileInputStream)it.next()).dispose();
            }
            catch(IOException e) { }

        in.close();
    }

    private final SharedFileInputStream _parent;
    private final File _file;
    private final long _start;
    private final long _length;
    private long _position;
    private long _markedPosition;
    private List _subStreams;
}
