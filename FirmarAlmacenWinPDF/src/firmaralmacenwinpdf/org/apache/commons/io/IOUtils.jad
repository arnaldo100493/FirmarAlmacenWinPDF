// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   IOUtils.java

package org.apache.commons.io;

import java.io.*;
import java.net.*;
import java.nio.channels.Selector;
import java.nio.charset.Charset;
import java.util.*;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.commons.io.output.StringBuilderWriter;

// Referenced classes of package org.apache.commons.io:
//            LineIterator, Charsets

public class IOUtils
{

    public IOUtils()
    {
    }

    public static void close(URLConnection conn)
    {
        if(conn instanceof HttpURLConnection)
            ((HttpURLConnection)conn).disconnect();
    }

    public static void closeQuietly(Reader input)
    {
        closeQuietly(((Closeable) (input)));
    }

    public static void closeQuietly(Writer output)
    {
        closeQuietly(((Closeable) (output)));
    }

    public static void closeQuietly(InputStream input)
    {
        closeQuietly(((Closeable) (input)));
    }

    public static void closeQuietly(OutputStream output)
    {
        closeQuietly(((Closeable) (output)));
    }

    public static void closeQuietly(Closeable closeable)
    {
        try
        {
            if(closeable != null)
                closeable.close();
        }
        catch(IOException ioe) { }
    }

    public static void closeQuietly(Socket sock)
    {
        if(sock != null)
            try
            {
                sock.close();
            }
            catch(IOException ioe) { }
    }

    public static void closeQuietly(Selector selector)
    {
        if(selector != null)
            try
            {
                selector.close();
            }
            catch(IOException ioe) { }
    }

    public static void closeQuietly(ServerSocket sock)
    {
        if(sock != null)
            try
            {
                sock.close();
            }
            catch(IOException ioe) { }
    }

    public static InputStream toBufferedInputStream(InputStream input)
        throws IOException
    {
        return ByteArrayOutputStream.toBufferedInputStream(input);
    }

    public static BufferedReader toBufferedReader(Reader reader)
    {
        return (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
    }

    public static byte[] toByteArray(InputStream input)
        throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output);
        return output.toByteArray();
    }

    public static byte[] toByteArray(InputStream input, long size)
        throws IOException
    {
        if(size > 0x7fffffffL)
            throw new IllegalArgumentException((new StringBuilder()).append("Size cannot be greater than Integer max value: ").append(size).toString());
        else
            return toByteArray(input, (int)size);
    }

    public static byte[] toByteArray(InputStream input, int size)
        throws IOException
    {
        if(size < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Size must be equal or greater than zero: ").append(size).toString());
        if(size == 0)
            return new byte[0];
        byte data[] = new byte[size];
        int offset;
        int readed;
        for(offset = 0; offset < size && (readed = input.read(data, offset, size - offset)) != -1; offset += readed);
        if(offset != size)
            throw new IOException((new StringBuilder()).append("Unexpected readed size. current: ").append(offset).append(", excepted: ").append(size).toString());
        else
            return data;
    }

    public static byte[] toByteArray(Reader input)
        throws IOException
    {
        return toByteArray(input, Charset.defaultCharset());
    }

    public static byte[] toByteArray(Reader input, Charset encoding)
        throws IOException
    {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        copy(input, output, encoding);
        return output.toByteArray();
    }

    public static byte[] toByteArray(Reader input, String encoding)
        throws IOException
    {
        return toByteArray(input, Charsets.toCharset(encoding));
    }

    /**
     * @deprecated Method toByteArray is deprecated
     */

    public static byte[] toByteArray(String input)
        throws IOException
    {
        return input.getBytes();
    }

    public static byte[] toByteArray(URI uri)
        throws IOException
    {
        return toByteArray(uri.toURL());
    }

    public static byte[] toByteArray(URL url)
        throws IOException
    {
        URLConnection conn = url.openConnection();
        byte abyte0[] = toByteArray(conn);
        close(conn);
        return abyte0;
        Exception exception;
        exception;
        close(conn);
        throw exception;
    }

    public static byte[] toByteArray(URLConnection urlConn)
        throws IOException
    {
        InputStream inputStream = urlConn.getInputStream();
        byte abyte0[] = toByteArray(inputStream);
        inputStream.close();
        return abyte0;
        Exception exception;
        exception;
        inputStream.close();
        throw exception;
    }

    public static char[] toCharArray(InputStream is)
        throws IOException
    {
        return toCharArray(is, Charset.defaultCharset());
    }

    public static char[] toCharArray(InputStream is, Charset encoding)
        throws IOException
    {
        CharArrayWriter output = new CharArrayWriter();
        copy(is, output, encoding);
        return output.toCharArray();
    }

    public static char[] toCharArray(InputStream is, String encoding)
        throws IOException
    {
        return toCharArray(is, Charsets.toCharset(encoding));
    }

    public static char[] toCharArray(Reader input)
        throws IOException
    {
        CharArrayWriter sw = new CharArrayWriter();
        copy(input, sw);
        return sw.toCharArray();
    }

    public static String toString(InputStream input)
        throws IOException
    {
        return toString(input, Charset.defaultCharset());
    }

    public static String toString(InputStream input, Charset encoding)
        throws IOException
    {
        StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw, encoding);
        return sw.toString();
    }

    public static String toString(InputStream input, String encoding)
        throws IOException
    {
        return toString(input, Charsets.toCharset(encoding));
    }

    public static String toString(Reader input)
        throws IOException
    {
        StringBuilderWriter sw = new StringBuilderWriter();
        copy(input, sw);
        return sw.toString();
    }

    public static String toString(URI uri)
        throws IOException
    {
        return toString(uri, Charset.defaultCharset());
    }

    public static String toString(URI uri, Charset encoding)
        throws IOException
    {
        return toString(uri.toURL(), Charsets.toCharset(encoding));
    }

    public static String toString(URI uri, String encoding)
        throws IOException
    {
        return toString(uri, Charsets.toCharset(encoding));
    }

    public static String toString(URL url)
        throws IOException
    {
        return toString(url, Charset.defaultCharset());
    }

    public static String toString(URL url, Charset encoding)
        throws IOException
    {
        InputStream inputStream = url.openStream();
        String s = toString(inputStream, encoding);
        inputStream.close();
        return s;
        Exception exception;
        exception;
        inputStream.close();
        throw exception;
    }

    public static String toString(URL url, String encoding)
        throws IOException
    {
        return toString(url, Charsets.toCharset(encoding));
    }

    /**
     * @deprecated Method toString is deprecated
     */

    public static String toString(byte input[])
        throws IOException
    {
        return new String(input);
    }

    public static String toString(byte input[], String encoding)
        throws IOException
    {
        return new String(input, Charsets.toCharset(encoding));
    }

    public static List readLines(InputStream input)
        throws IOException
    {
        return readLines(input, Charset.defaultCharset());
    }

    public static List readLines(InputStream input, Charset encoding)
        throws IOException
    {
        InputStreamReader reader = new InputStreamReader(input, Charsets.toCharset(encoding));
        return readLines(((Reader) (reader)));
    }

    public static List readLines(InputStream input, String encoding)
        throws IOException
    {
        return readLines(input, Charsets.toCharset(encoding));
    }

    public static List readLines(Reader input)
        throws IOException
    {
        BufferedReader reader = toBufferedReader(input);
        List list = new ArrayList();
        for(String line = reader.readLine(); line != null; line = reader.readLine())
            list.add(line);

        return list;
    }

    public static LineIterator lineIterator(Reader reader)
    {
        return new LineIterator(reader);
    }

    public static LineIterator lineIterator(InputStream input, Charset encoding)
        throws IOException
    {
        return new LineIterator(new InputStreamReader(input, Charsets.toCharset(encoding)));
    }

    public static LineIterator lineIterator(InputStream input, String encoding)
        throws IOException
    {
        return lineIterator(input, Charsets.toCharset(encoding));
    }

    public static InputStream toInputStream(CharSequence input)
    {
        return toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(CharSequence input, Charset encoding)
    {
        return toInputStream(input.toString(), encoding);
    }

    public static InputStream toInputStream(CharSequence input, String encoding)
        throws IOException
    {
        return toInputStream(input, Charsets.toCharset(encoding));
    }

    public static InputStream toInputStream(String input)
    {
        return toInputStream(input, Charset.defaultCharset());
    }

    public static InputStream toInputStream(String input, Charset encoding)
    {
        return new ByteArrayInputStream(input.getBytes(Charsets.toCharset(encoding)));
    }

    public static InputStream toInputStream(String input, String encoding)
        throws IOException
    {
        byte bytes[] = input.getBytes(Charsets.toCharset(encoding));
        return new ByteArrayInputStream(bytes);
    }

    public static void write(byte data[], OutputStream output)
        throws IOException
    {
        if(data != null)
            output.write(data);
    }

    public static void write(byte data[], Writer output)
        throws IOException
    {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(byte data[], Writer output, Charset encoding)
        throws IOException
    {
        if(data != null)
            output.write(new String(data, Charsets.toCharset(encoding)));
    }

    public static void write(byte data[], Writer output, String encoding)
        throws IOException
    {
        write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(char data[], Writer output)
        throws IOException
    {
        if(data != null)
            output.write(data);
    }

    public static void write(char data[], OutputStream output)
        throws IOException
    {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(char data[], OutputStream output, Charset encoding)
        throws IOException
    {
        if(data != null)
            output.write((new String(data)).getBytes(Charsets.toCharset(encoding)));
    }

    public static void write(char data[], OutputStream output, String encoding)
        throws IOException
    {
        write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(CharSequence data, Writer output)
        throws IOException
    {
        if(data != null)
            write(data.toString(), output);
    }

    public static void write(CharSequence data, OutputStream output)
        throws IOException
    {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(CharSequence data, OutputStream output, Charset encoding)
        throws IOException
    {
        if(data != null)
            write(data.toString(), output, encoding);
    }

    public static void write(CharSequence data, OutputStream output, String encoding)
        throws IOException
    {
        write(data, output, Charsets.toCharset(encoding));
    }

    public static void write(String data, Writer output)
        throws IOException
    {
        if(data != null)
            output.write(data);
    }

    public static void write(String data, OutputStream output)
        throws IOException
    {
        write(data, output, Charset.defaultCharset());
    }

    public static void write(String data, OutputStream output, Charset encoding)
        throws IOException
    {
        if(data != null)
            output.write(data.getBytes(Charsets.toCharset(encoding)));
    }

    public static void write(String data, OutputStream output, String encoding)
        throws IOException
    {
        write(data, output, Charsets.toCharset(encoding));
    }

    /**
     * @deprecated Method write is deprecated
     */

    public static void write(StringBuffer data, Writer output)
        throws IOException
    {
        if(data != null)
            output.write(data.toString());
    }

    /**
     * @deprecated Method write is deprecated
     */

    public static void write(StringBuffer data, OutputStream output)
        throws IOException
    {
        write(data, output, (String)null);
    }

    /**
     * @deprecated Method write is deprecated
     */

    public static void write(StringBuffer data, OutputStream output, String encoding)
        throws IOException
    {
        if(data != null)
            output.write(data.toString().getBytes(Charsets.toCharset(encoding)));
    }

    public static void writeLines(Collection lines, String lineEnding, OutputStream output)
        throws IOException
    {
        writeLines(lines, lineEnding, output, Charset.defaultCharset());
    }

    public static void writeLines(Collection lines, String lineEnding, OutputStream output, Charset encoding)
        throws IOException
    {
        if(lines == null)
            return;
        if(lineEnding == null)
            lineEnding = LINE_SEPARATOR;
        Charset cs = Charsets.toCharset(encoding);
        for(Iterator i$ = lines.iterator(); i$.hasNext(); output.write(lineEnding.getBytes(cs)))
        {
            Object line = i$.next();
            if(line != null)
                output.write(line.toString().getBytes(cs));
        }

    }

    public static void writeLines(Collection lines, String lineEnding, OutputStream output, String encoding)
        throws IOException
    {
        writeLines(lines, lineEnding, output, Charsets.toCharset(encoding));
    }

    public static void writeLines(Collection lines, String lineEnding, Writer writer)
        throws IOException
    {
        if(lines == null)
            return;
        if(lineEnding == null)
            lineEnding = LINE_SEPARATOR;
        for(Iterator i$ = lines.iterator(); i$.hasNext(); writer.write(lineEnding))
        {
            Object line = i$.next();
            if(line != null)
                writer.write(line.toString());
        }

    }

    public static int copy(InputStream input, OutputStream output)
        throws IOException
    {
        long count = copyLarge(input, output);
        if(count > 0x7fffffffL)
            return -1;
        else
            return (int)count;
    }

    public static long copyLarge(InputStream input, OutputStream output)
        throws IOException
    {
        return copyLarge(input, output, new byte[4096]);
    }

    public static long copyLarge(InputStream input, OutputStream output, byte buffer[])
        throws IOException
    {
        long count = 0L;
        for(int n = 0; -1 != (n = input.read(buffer));)
        {
            output.write(buffer, 0, n);
            count += n;
        }

        return count;
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length)
        throws IOException
    {
        return copyLarge(input, output, inputOffset, length, new byte[4096]);
    }

    public static long copyLarge(InputStream input, OutputStream output, long inputOffset, long length, byte buffer[])
        throws IOException
    {
        if(inputOffset > 0L)
            skipFully(input, inputOffset);
        if(length == 0L)
            return 0L;
        int bufferLength = buffer.length;
        int bytesToRead = bufferLength;
        if(length > 0L && length < (long)bufferLength)
            bytesToRead = (int)length;
        long totalRead = 0L;
        do
        {
            int read;
            if(bytesToRead <= 0 || -1 == (read = input.read(buffer, 0, bytesToRead)))
                break;
            output.write(buffer, 0, read);
            totalRead += read;
            if(length > 0L)
                bytesToRead = (int)Math.min(length - totalRead, bufferLength);
        } while(true);
        return totalRead;
    }

    public static void copy(InputStream input, Writer output)
        throws IOException
    {
        copy(input, output, Charset.defaultCharset());
    }

    public static void copy(InputStream input, Writer output, Charset encoding)
        throws IOException
    {
        InputStreamReader in = new InputStreamReader(input, Charsets.toCharset(encoding));
        copy(((Reader) (in)), output);
    }

    public static void copy(InputStream input, Writer output, String encoding)
        throws IOException
    {
        copy(input, output, Charsets.toCharset(encoding));
    }

    public static int copy(Reader input, Writer output)
        throws IOException
    {
        long count = copyLarge(input, output);
        if(count > 0x7fffffffL)
            return -1;
        else
            return (int)count;
    }

    public static long copyLarge(Reader input, Writer output)
        throws IOException
    {
        return copyLarge(input, output, new char[4096]);
    }

    public static long copyLarge(Reader input, Writer output, char buffer[])
        throws IOException
    {
        long count = 0L;
        for(int n = 0; -1 != (n = input.read(buffer));)
        {
            output.write(buffer, 0, n);
            count += n;
        }

        return count;
    }

    public static long copyLarge(Reader input, Writer output, long inputOffset, long length)
        throws IOException
    {
        return copyLarge(input, output, inputOffset, length, new char[4096]);
    }

    public static long copyLarge(Reader input, Writer output, long inputOffset, long length, char buffer[])
        throws IOException
    {
        if(inputOffset > 0L)
            skipFully(input, inputOffset);
        if(length == 0L)
            return 0L;
        int bytesToRead = buffer.length;
        if(length > 0L && length < (long)buffer.length)
            bytesToRead = (int)length;
        long totalRead = 0L;
        do
        {
            int read;
            if(bytesToRead <= 0 || -1 == (read = input.read(buffer, 0, bytesToRead)))
                break;
            output.write(buffer, 0, read);
            totalRead += read;
            if(length > 0L)
                bytesToRead = (int)Math.min(length - totalRead, buffer.length);
        } while(true);
        return totalRead;
    }

    public static void copy(Reader input, OutputStream output)
        throws IOException
    {
        copy(input, output, Charset.defaultCharset());
    }

    public static void copy(Reader input, OutputStream output, Charset encoding)
        throws IOException
    {
        OutputStreamWriter out = new OutputStreamWriter(output, Charsets.toCharset(encoding));
        copy(input, ((Writer) (out)));
        out.flush();
    }

    public static void copy(Reader input, OutputStream output, String encoding)
        throws IOException
    {
        copy(input, output, Charsets.toCharset(encoding));
    }

    public static boolean contentEquals(InputStream input1, InputStream input2)
        throws IOException
    {
        if(!(input1 instanceof BufferedInputStream))
            input1 = new BufferedInputStream(input1);
        if(!(input2 instanceof BufferedInputStream))
            input2 = new BufferedInputStream(input2);
        int ch2;
        for(int ch = input1.read(); -1 != ch; ch = input1.read())
        {
            ch2 = input2.read();
            if(ch != ch2)
                return false;
        }

        ch2 = input2.read();
        return ch2 == -1;
    }

    public static boolean contentEquals(Reader input1, Reader input2)
        throws IOException
    {
        input1 = toBufferedReader(input1);
        input2 = toBufferedReader(input2);
        int ch2;
        for(int ch = input1.read(); -1 != ch; ch = input1.read())
        {
            ch2 = input2.read();
            if(ch != ch2)
                return false;
        }

        ch2 = input2.read();
        return ch2 == -1;
    }

    public static boolean contentEqualsIgnoreEOL(Reader input1, Reader input2)
        throws IOException
    {
        BufferedReader br1 = toBufferedReader(input1);
        BufferedReader br2 = toBufferedReader(input2);
        String line1 = br1.readLine();
        String line2;
        for(line2 = br2.readLine(); line1 != null && line2 != null && line1.equals(line2); line2 = br2.readLine())
            line1 = br1.readLine();

        return line1 != null ? line1.equals(line2) : line2 == null;
    }

    public static long skip(InputStream input, long toSkip)
        throws IOException
    {
        if(toSkip < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append("Skip count must be non-negative, actual: ").append(toSkip).toString());
        if(SKIP_BYTE_BUFFER == null)
            SKIP_BYTE_BUFFER = new byte[2048];
        long remain = toSkip;
        do
        {
            if(remain <= 0L)
                break;
            long n = input.read(SKIP_BYTE_BUFFER, 0, (int)Math.min(remain, 2048L));
            if(n < 0L)
                break;
            remain -= n;
        } while(true);
        return toSkip - remain;
    }

    public static long skip(Reader input, long toSkip)
        throws IOException
    {
        if(toSkip < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append("Skip count must be non-negative, actual: ").append(toSkip).toString());
        if(SKIP_CHAR_BUFFER == null)
            SKIP_CHAR_BUFFER = new char[2048];
        long remain = toSkip;
        do
        {
            if(remain <= 0L)
                break;
            long n = input.read(SKIP_CHAR_BUFFER, 0, (int)Math.min(remain, 2048L));
            if(n < 0L)
                break;
            remain -= n;
        } while(true);
        return toSkip - remain;
    }

    public static void skipFully(InputStream input, long toSkip)
        throws IOException
    {
        if(toSkip < 0L)
            throw new IllegalArgumentException((new StringBuilder()).append("Bytes to skip must not be negative: ").append(toSkip).toString());
        long skipped = skip(input, toSkip);
        if(skipped != toSkip)
            throw new EOFException((new StringBuilder()).append("Bytes to skip: ").append(toSkip).append(" actual: ").append(skipped).toString());
        else
            return;
    }

    public static void skipFully(Reader input, long toSkip)
        throws IOException
    {
        long skipped = skip(input, toSkip);
        if(skipped != toSkip)
            throw new EOFException((new StringBuilder()).append("Chars to skip: ").append(toSkip).append(" actual: ").append(skipped).toString());
        else
            return;
    }

    public static int read(Reader input, char buffer[], int offset, int length)
        throws IOException
    {
        if(length < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Length must not be negative: ").append(length).toString());
        int remaining = length;
        do
        {
            if(remaining <= 0)
                break;
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if(-1 == count)
                break;
            remaining -= count;
        } while(true);
        return length - remaining;
    }

    public static int read(Reader input, char buffer[])
        throws IOException
    {
        return read(input, buffer, 0, buffer.length);
    }

    public static int read(InputStream input, byte buffer[], int offset, int length)
        throws IOException
    {
        if(length < 0)
            throw new IllegalArgumentException((new StringBuilder()).append("Length must not be negative: ").append(length).toString());
        int remaining = length;
        do
        {
            if(remaining <= 0)
                break;
            int location = length - remaining;
            int count = input.read(buffer, offset + location, remaining);
            if(-1 == count)
                break;
            remaining -= count;
        } while(true);
        return length - remaining;
    }

    public static int read(InputStream input, byte buffer[])
        throws IOException
    {
        return read(input, buffer, 0, buffer.length);
    }

    public static void readFully(Reader input, char buffer[], int offset, int length)
        throws IOException
    {
        int actual = read(input, buffer, offset, length);
        if(actual != length)
            throw new EOFException((new StringBuilder()).append("Length to read: ").append(length).append(" actual: ").append(actual).toString());
        else
            return;
    }

    public static void readFully(Reader input, char buffer[])
        throws IOException
    {
        readFully(input, buffer, 0, buffer.length);
    }

    public static void readFully(InputStream input, byte buffer[], int offset, int length)
        throws IOException
    {
        int actual = read(input, buffer, offset, length);
        if(actual != length)
            throw new EOFException((new StringBuilder()).append("Length to read: ").append(length).append(" actual: ").append(actual).toString());
        else
            return;
    }

    public static void readFully(InputStream input, byte buffer[])
        throws IOException
    {
        readFully(input, buffer, 0, buffer.length);
    }

    private static final int EOF = -1;
    public static final char DIR_SEPARATOR_UNIX = 47;
    public static final char DIR_SEPARATOR_WINDOWS = 92;
    public static final char DIR_SEPARATOR;
    public static final String LINE_SEPARATOR_UNIX = "\n";
    public static final String LINE_SEPARATOR_WINDOWS = "\r\n";
    public static final String LINE_SEPARATOR;
    private static final int DEFAULT_BUFFER_SIZE = 4096;
    private static final int SKIP_BUFFER_SIZE = 2048;
    private static char SKIP_CHAR_BUFFER[];
    private static byte SKIP_BYTE_BUFFER[];

    static 
    {
        DIR_SEPARATOR = File.separatorChar;
        StringBuilderWriter buf = new StringBuilderWriter(4);
        PrintWriter out = new PrintWriter(buf);
        out.println();
        LINE_SEPARATOR = buf.toString();
        out.close();
    }
}
