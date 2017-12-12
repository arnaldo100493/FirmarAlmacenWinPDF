// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Base64.java

package co.com.pdf.text.pdf.codec;

import co.com.pdf.text.error_messages.MessageLocalization;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Base64
{
    public static class OutputStream extends FilterOutputStream
    {

        public void write(int theByte)
            throws IOException
        {
            if(suspendEncoding)
            {
                super.out.write(theByte);
                return;
            }
            if(encode)
            {
                buffer[position++] = (byte)theByte;
                if(position >= bufferLength)
                {
                    out.write(Base64.encode3to4(b4, buffer, bufferLength, options));
                    lineLength += 4;
                    if(breakLines && lineLength >= 76)
                    {
                        out.write(10);
                        lineLength = 0;
                    }
                    position = 0;
                }
            } else
            if(decodabet[theByte & 0x7f] > -5)
            {
                buffer[position++] = (byte)theByte;
                if(position >= bufferLength)
                {
                    int len = Base64.decode4to3(buffer, 0, b4, 0, options);
                    out.write(b4, 0, len);
                    position = 0;
                }
            } else
            if(decodabet[theByte & 0x7f] != -5)
                throw new IOException(MessageLocalization.getComposedMessage("invalid.character.in.base64.data", new Object[0]));
        }

        public void write(byte theBytes[], int off, int len)
            throws IOException
        {
            if(suspendEncoding)
            {
                super.out.write(theBytes, off, len);
                return;
            }
            for(int i = 0; i < len; i++)
                write(theBytes[off + i]);

        }

        public void flushBase64()
            throws IOException
        {
            if(position > 0)
                if(encode)
                {
                    out.write(Base64.encode3to4(b4, buffer, position, options));
                    position = 0;
                } else
                {
                    throw new IOException(MessageLocalization.getComposedMessage("base64.input.not.properly.padded", new Object[0]));
                }
        }

        public void close()
            throws IOException
        {
            flushBase64();
            super.close();
            buffer = null;
            out = null;
        }

        public void suspendEncoding()
            throws IOException
        {
            flushBase64();
            suspendEncoding = true;
        }

        public void resumeEncoding()
        {
            suspendEncoding = false;
        }

        private boolean encode;
        private int position;
        private byte buffer[];
        private int bufferLength;
        private int lineLength;
        private boolean breakLines;
        private byte b4[];
        private boolean suspendEncoding;
        private int options;
        private byte alphabet[];
        private byte decodabet[];

        public OutputStream(java.io.OutputStream out)
        {
            this(out, 1);
        }

        public OutputStream(java.io.OutputStream out, int options)
        {
            super(out);
            breakLines = (options & 8) != 8;
            encode = (options & 1) == 1;
            bufferLength = encode ? 3 : 4;
            buffer = new byte[bufferLength];
            position = 0;
            lineLength = 0;
            suspendEncoding = false;
            b4 = new byte[4];
            this.options = options;
            alphabet = Base64.getAlphabet(options);
            decodabet = Base64.getDecodabet(options);
        }
    }

    public static class InputStream extends FilterInputStream
    {

        public int read()
            throws IOException
        {
            if(position < 0)
                if(encode)
                {
                    byte b3[] = new byte[3];
                    int numBinaryBytes = 0;
                    for(int i = 0; i < 3; i++)
                        try
                        {
                            int b = in.read();
                            if(b >= 0)
                            {
                                b3[i] = (byte)b;
                                numBinaryBytes++;
                            }
                            continue;
                        }
                        catch(IOException e)
                        {
                            if(i == 0)
                                throw e;
                        }

                    if(numBinaryBytes > 0)
                    {
                        Base64.encode3to4(b3, 0, numBinaryBytes, buffer, 0, options);
                        position = 0;
                        numSigBytes = 4;
                    } else
                    {
                        return -1;
                    }
                } else
                {
                    byte b4[] = new byte[4];
                    int i = 0;
                    i = 0;
                    do
                    {
                        if(i >= 4)
                            break;
                        int b = 0;
                        do
                            b = in.read();
                        while(b >= 0 && decodabet[b & 0x7f] <= -5);
                        if(b < 0)
                            break;
                        b4[i] = (byte)b;
                        i++;
                    } while(true);
                    if(i == 4)
                    {
                        numSigBytes = Base64.decode4to3(b4, 0, buffer, 0, options);
                        position = 0;
                    } else
                    if(i == 0)
                        return -1;
                    else
                        throw new IOException(MessageLocalization.getComposedMessage("improperly.padded.base64.input", new Object[0]));
                }
            if(position >= 0)
            {
                if(position >= numSigBytes)
                    return -1;
                if(encode && breakLines && lineLength >= 76)
                {
                    lineLength = 0;
                    return 10;
                }
                lineLength++;
                int b = buffer[position++];
                if(position >= bufferLength)
                    position = -1;
                return b & 0xff;
            } else
            {
                throw new IOException(MessageLocalization.getComposedMessage("error.in.base64.code.reading.stream", new Object[0]));
            }
        }

        public int read(byte dest[], int off, int len)
            throws IOException
        {
            int i = 0;
            do
            {
                if(i >= len)
                    break;
                int b = read();
                if(b >= 0)
                {
                    dest[off + i] = (byte)b;
                } else
                {
                    if(i == 0)
                        return -1;
                    break;
                }
                i++;
            } while(true);
            return i;
        }

        private boolean encode;
        private int position;
        private byte buffer[];
        private int bufferLength;
        private int numSigBytes;
        private int lineLength;
        private boolean breakLines;
        private int options;
        private byte alphabet[];
        private byte decodabet[];

        public InputStream(java.io.InputStream in)
        {
            this(in, 0);
        }

        public InputStream(java.io.InputStream in, int options)
        {
            super(in);
            breakLines = (options & 8) != 8;
            encode = (options & 1) == 1;
            bufferLength = encode ? 4 : 3;
            buffer = new byte[bufferLength];
            position = -1;
            lineLength = 0;
            this.options = options;
            alphabet = Base64.getAlphabet(options);
            decodabet = Base64.getDecodabet(options);
        }
    }


    private static final byte[] getAlphabet(int options)
    {
        if((options & 0x10) == 16)
            return _URL_SAFE_ALPHABET;
        if((options & 0x20) == 32)
            return _ORDERED_ALPHABET;
        else
            return _STANDARD_ALPHABET;
    }

    private static final byte[] getDecodabet(int options)
    {
        if((options & 0x10) == 16)
            return _URL_SAFE_DECODABET;
        if((options & 0x20) == 32)
            return _ORDERED_DECODABET;
        else
            return _STANDARD_DECODABET;
    }

    private Base64()
    {
    }

    private static final void usage(String msg)
    {
        System.err.println(msg);
        System.err.println("Usage: java Base64 -e|-d inputfile outputfile");
    }

    private static byte[] encode3to4(byte b4[], byte threeBytes[], int numSigBytes, int options)
    {
        encode3to4(threeBytes, 0, numSigBytes, b4, 0, options);
        return b4;
    }

    private static byte[] encode3to4(byte source[], int srcOffset, int numSigBytes, byte destination[], int destOffset, int options)
    {
        byte ALPHABET[] = getAlphabet(options);
        int inBuff = (numSigBytes <= 0 ? 0 : (source[srcOffset] << 24) >>> 8) | (numSigBytes <= 1 ? 0 : (source[srcOffset + 1] << 24) >>> 16) | (numSigBytes <= 2 ? 0 : (source[srcOffset + 2] << 24) >>> 24);
        switch(numSigBytes)
        {
        case 3: // '\003'
            destination[destOffset] = ALPHABET[inBuff >>> 18];
            destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3f];
            destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3f];
            destination[destOffset + 3] = ALPHABET[inBuff & 0x3f];
            return destination;

        case 2: // '\002'
            destination[destOffset] = ALPHABET[inBuff >>> 18];
            destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3f];
            destination[destOffset + 2] = ALPHABET[inBuff >>> 6 & 0x3f];
            destination[destOffset + 3] = 61;
            return destination;

        case 1: // '\001'
            destination[destOffset] = ALPHABET[inBuff >>> 18];
            destination[destOffset + 1] = ALPHABET[inBuff >>> 12 & 0x3f];
            destination[destOffset + 2] = 61;
            destination[destOffset + 3] = 61;
            return destination;
        }
        return destination;
    }

    public static String encodeObject(Serializable serializableObject)
    {
        return encodeObject(serializableObject, 0);
    }

    public static String encodeObject(Serializable serializableObject, int options)
    {
        ByteArrayOutputStream baos;
        java.io.OutputStream b64os;
        ObjectOutputStream oos;
        GZIPOutputStream gzos;
        int gzip;
        baos = null;
        b64os = null;
        oos = null;
        gzos = null;
        gzip = options & 2;
        int dontBreakLines = options & 8;
        baos = new ByteArrayOutputStream();
        b64os = new OutputStream(baos, 1 | options);
        if(gzip == 2)
        {
            gzos = new GZIPOutputStream(b64os);
            oos = new ObjectOutputStream(gzos);
        } else
        {
            oos = new ObjectOutputStream(b64os);
        }
        oos.writeObject(serializableObject);
        Exception e;
        try
        {
            oos.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            gzos.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            b64os.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            baos.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_222;
        e;
        String s;
        e.printStackTrace();
        s = null;
        try
        {
            oos.close();
        }
        catch(Exception e) { }
        try
        {
            gzos.close();
        }
        catch(Exception e) { }
        try
        {
            b64os.close();
        }
        catch(Exception e) { }
        try
        {
            baos.close();
        }
        catch(Exception e) { }
        return s;
        Exception exception;
        exception;
        try
        {
            oos.close();
        }
        catch(Exception e) { }
        try
        {
            gzos.close();
        }
        catch(Exception e) { }
        try
        {
            b64os.close();
        }
        catch(Exception e) { }
        try
        {
            baos.close();
        }
        catch(Exception e) { }
        throw exception;
        try
        {
            return new String(baos.toByteArray(), "UTF-8");
        }
        catch(UnsupportedEncodingException uue)
        {
            return new String(baos.toByteArray());
        }
    }

    public static String encodeBytes(byte source[])
    {
        return encodeBytes(source, 0, source.length, 0);
    }

    public static String encodeBytes(byte source[], int options)
    {
        return encodeBytes(source, 0, source.length, options);
    }

    public static String encodeBytes(byte source[], int off, int len)
    {
        return encodeBytes(source, off, len, 0);
    }

    public static String encodeBytes(byte source[], int off, int len, int options)
    {
        int dontBreakLines;
        ByteArrayOutputStream baos;
        GZIPOutputStream gzos;
        OutputStream b64os;
        dontBreakLines = options & 8;
        int gzip = options & 2;
        if(gzip != 2)
            break MISSING_BLOCK_LABEL_214;
        baos = null;
        gzos = null;
        b64os = null;
        baos = new ByteArrayOutputStream();
        b64os = new OutputStream(baos, 1 | options);
        gzos = new GZIPOutputStream(b64os);
        gzos.write(source, off, len);
        gzos.close();
        Exception e;
        try
        {
            gzos.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            b64os.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            baos.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_184;
        e;
        String s;
        e.printStackTrace();
        s = null;
        try
        {
            gzos.close();
        }
        catch(Exception e) { }
        try
        {
            b64os.close();
        }
        catch(Exception e) { }
        try
        {
            baos.close();
        }
        catch(Exception e) { }
        return s;
        Exception exception;
        exception;
        try
        {
            gzos.close();
        }
        catch(Exception e) { }
        try
        {
            b64os.close();
        }
        catch(Exception e) { }
        try
        {
            baos.close();
        }
        catch(Exception e) { }
        throw exception;
        try
        {
            return new String(baos.toByteArray(), "UTF-8");
        }
        catch(UnsupportedEncodingException uue)
        {
            return new String(baos.toByteArray());
        }
        boolean breakLines = dontBreakLines == 0;
        int len43 = (len * 4) / 3;
        byte outBuff[] = new byte[len43 + (len % 3 <= 0 ? 0 : 4) + (breakLines ? len43 / 76 : 0)];
        int d = 0;
        int e = 0;
        int len2 = len - 2;
        int lineLength = 0;
        while(d < len2) 
        {
            encode3to4(source, d + off, 3, outBuff, e, options);
            lineLength += 4;
            if(breakLines && lineLength == 76)
            {
                outBuff[e + 4] = 10;
                e++;
                lineLength = 0;
            }
            d += 3;
            e += 4;
        }
        if(d < len)
        {
            encode3to4(source, d + off, len - d, outBuff, e, options);
            e += 4;
        }
        try
        {
            return new String(outBuff, 0, e, "UTF-8");
        }
        catch(UnsupportedEncodingException uue)
        {
            return new String(outBuff, 0, e);
        }
    }

    private static int decode4to3(byte source[], int srcOffset, byte destination[], int destOffset, int options)
    {
        byte DECODABET[] = getDecodabet(options);
        if(source[srcOffset + 2] == 61)
        {
            int outBuff = (DECODABET[source[srcOffset]] & 0xff) << 18 | (DECODABET[source[srcOffset + 1]] & 0xff) << 12;
            destination[destOffset] = (byte)(outBuff >>> 16);
            return 1;
        }
        if(source[srcOffset + 3] == 61)
        {
            int outBuff = (DECODABET[source[srcOffset]] & 0xff) << 18 | (DECODABET[source[srcOffset + 1]] & 0xff) << 12 | (DECODABET[source[srcOffset + 2]] & 0xff) << 6;
            destination[destOffset] = (byte)(outBuff >>> 16);
            destination[destOffset + 1] = (byte)(outBuff >>> 8);
            return 2;
        }
        try
        {
            int outBuff = (DECODABET[source[srcOffset]] & 0xff) << 18 | (DECODABET[source[srcOffset + 1]] & 0xff) << 12 | (DECODABET[source[srcOffset + 2]] & 0xff) << 6 | DECODABET[source[srcOffset + 3]] & 0xff;
            destination[destOffset] = (byte)(outBuff >> 16);
            destination[destOffset + 1] = (byte)(outBuff >> 8);
            destination[destOffset + 2] = (byte)outBuff;
            return 3;
        }
        catch(Exception e)
        {
            System.out.println((new StringBuilder()).append("").append(source[srcOffset]).append(": ").append(DECODABET[source[srcOffset]]).toString());
        }
        System.out.println((new StringBuilder()).append("").append(source[srcOffset + 1]).append(": ").append(DECODABET[source[srcOffset + 1]]).toString());
        System.out.println((new StringBuilder()).append("").append(source[srcOffset + 2]).append(": ").append(DECODABET[source[srcOffset + 2]]).toString());
        System.out.println((new StringBuilder()).append("").append(source[srcOffset + 3]).append(": ").append(DECODABET[source[srcOffset + 3]]).toString());
        return -1;
    }

    public static byte[] decode(byte source[], int off, int len, int options)
    {
        byte DECODABET[] = getDecodabet(options);
        int len34 = (len * 3) / 4;
        byte outBuff[] = new byte[len34];
        int outBuffPosn = 0;
        byte b4[] = new byte[4];
        int b4Posn = 0;
        int i = 0;
        byte sbiCrop = 0;
        byte sbiDecode = 0;
        for(i = off; i < off + len; i++)
        {
            sbiCrop = (byte)(source[i] & 0x7f);
            sbiDecode = DECODABET[sbiCrop];
            if(sbiDecode >= -5)
            {
                if(sbiDecode < -1)
                    continue;
                b4[b4Posn++] = sbiCrop;
                if(b4Posn <= 3)
                    continue;
                outBuffPosn += decode4to3(b4, 0, outBuff, outBuffPosn, options);
                b4Posn = 0;
                if(sbiCrop == 61)
                    break;
            } else
            {
                System.err.println((new StringBuilder()).append("Bad Base64 input character at ").append(i).append(": ").append(source[i]).append("(decimal)").toString());
                return null;
            }
        }

        byte out[] = new byte[outBuffPosn];
        System.arraycopy(outBuff, 0, out, 0, outBuffPosn);
        return out;
    }

    public static byte[] decode(String s)
    {
        return decode(s, 0);
    }

    public static byte[] decode(String s, int options)
    {
        byte bytes[];
        ByteArrayInputStream bais;
        GZIPInputStream gzis;
        ByteArrayOutputStream baos;
        byte buffer[];
        try
        {
            bytes = s.getBytes("UTF-8");
        }
        catch(UnsupportedEncodingException uee)
        {
            bytes = s.getBytes();
        }
        bytes = decode(bytes, 0, bytes.length, options);
        if(bytes == null || bytes.length < 4)
            break MISSING_BLOCK_LABEL_243;
        int head = bytes[0] & 0xff | bytes[1] << 8 & 0xff00;
        if(35615 != head)
            break MISSING_BLOCK_LABEL_243;
        bais = null;
        gzis = null;
        baos = null;
        buffer = new byte[2048];
        int length = 0;
        try
        {
            baos = new ByteArrayOutputStream();
            bais = new ByteArrayInputStream(bytes);
            gzis = new GZIPInputStream(bais);
            int length;
            while((length = gzis.read(buffer)) >= 0) 
                baos.write(buffer, 0, length);
            bytes = baos.toByteArray();
        }
        catch(IOException e)
        {
            try
            {
                baos.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException e) { }
            try
            {
                gzis.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException e) { }
            try
            {
                bais.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException e) { }
            break MISSING_BLOCK_LABEL_243;
        }
        try
        {
            baos.close();
        }
        catch(Exception e) { }
        try
        {
            gzis.close();
        }
        catch(Exception e) { }
        try
        {
            bais.close();
        }
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_243;
        Exception exception;
        exception;
        try
        {
            baos.close();
        }
        catch(Exception e) { }
        try
        {
            gzis.close();
        }
        catch(Exception e) { }
        try
        {
            bais.close();
        }
        catch(Exception e) { }
        throw exception;
        return bytes;
    }

    public static Object decodeToObject(String encodedObject)
    {
        byte objBytes[];
        ByteArrayInputStream bais;
        ObjectInputStream ois;
        Object obj;
        objBytes = decode(encodedObject);
        bais = null;
        ois = null;
        obj = null;
        bais = new ByteArrayInputStream(objBytes);
        ois = new ObjectInputStream(bais);
        obj = ois.readObject();
        Exception e;
        try
        {
            bais.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            ois.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_136;
        e;
        e.printStackTrace();
        try
        {
            bais.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            ois.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_136;
        e;
        e.printStackTrace();
        try
        {
            bais.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        try
        {
            ois.close();
        }
        // Misplaced declaration of an exception variable
        catch(Exception e) { }
        break MISSING_BLOCK_LABEL_136;
        Exception exception;
        exception;
        try
        {
            bais.close();
        }
        catch(Exception e) { }
        try
        {
            ois.close();
        }
        catch(Exception e) { }
        throw exception;
        return obj;
    }

    public static boolean encodeToFile(byte dataToEncode[], String filename)
    {
        boolean success;
        OutputStream bos;
        success = false;
        bos = null;
        bos = new OutputStream(new FileOutputStream(filename), 1);
        bos.write(dataToEncode);
        success = true;
        IOException e;
        try
        {
            bos.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e) { }
        break MISSING_BLOCK_LABEL_70;
        e;
        success = false;
        try
        {
            bos.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e) { }
        break MISSING_BLOCK_LABEL_70;
        Exception exception;
        exception;
        try
        {
            bos.close();
        }
        catch(Exception e) { }
        throw exception;
        return success;
    }

    public static boolean decodeToFile(String dataToDecode, String filename)
    {
        boolean success;
        OutputStream bos;
        success = false;
        bos = null;
        bos = new OutputStream(new FileOutputStream(filename), 0);
        bos.write(dataToDecode.getBytes("UTF-8"));
        success = true;
        IOException e;
        try
        {
            bos.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e) { }
        break MISSING_BLOCK_LABEL_75;
        e;
        success = false;
        try
        {
            bos.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e) { }
        break MISSING_BLOCK_LABEL_75;
        Exception exception;
        exception;
        try
        {
            bos.close();
        }
        catch(Exception e) { }
        throw exception;
        return success;
    }

    public static byte[] decodeFromFile(String filename)
    {
        byte decodedData[];
        InputStream bis;
        decodedData = null;
        bis = null;
        File file;
        int length;
        byte abyte0[];
        file = new File(filename);
        byte buffer[] = null;
        length = 0;
        int numBytes = 0;
        if(file.length() <= 0x7fffffffL)
            break MISSING_BLOCK_LABEL_86;
        System.err.println((new StringBuilder()).append("File is too big for this convenience method (").append(file.length()).append(" bytes).").toString());
        abyte0 = null;
        if(null != bis)
            try
            {
                bis.close();
            }
            catch(Exception e) { }
        return abyte0;
        byte buffer[] = new byte[(int)file.length()];
        bis = new InputStream(new BufferedInputStream(new FileInputStream(file)), 0);
        int numBytes;
        while((numBytes = bis.read(buffer, length, 4096)) >= 0) 
            length += numBytes;
        decodedData = new byte[length];
        System.arraycopy(buffer, 0, decodedData, 0, length);
        IOException e;
        if(null != bis)
            try
            {
                bis.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException e) { }
        break MISSING_BLOCK_LABEL_238;
        e;
        System.err.println((new StringBuilder()).append("Error decoding from file ").append(filename).toString());
        if(null != bis)
            try
            {
                bis.close();
            }
            // Misplaced declaration of an exception variable
            catch(IOException e) { }
        break MISSING_BLOCK_LABEL_238;
        Exception exception;
        exception;
        if(null != bis)
            try
            {
                bis.close();
            }
            catch(Exception e) { }
        throw exception;
        return decodedData;
    }

    public static String encodeFromFile(String filename)
    {
        String encodedData;
        InputStream bis;
        encodedData = null;
        bis = null;
        File file = new File(filename);
        byte buffer[] = new byte[Math.max((int)((double)file.length() * 1.3999999999999999D), 40)];
        int length = 0;
        int numBytes = 0;
        bis = new InputStream(new BufferedInputStream(new FileInputStream(file)), 1);
        while((numBytes = bis.read(buffer, length, 4096)) >= 0) 
            length += numBytes;
        encodedData = new String(buffer, 0, length, "UTF-8");
        IOException e;
        try
        {
            bis.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e) { }
        break MISSING_BLOCK_LABEL_166;
        e;
        System.err.println((new StringBuilder()).append("Error encoding from file ").append(filename).toString());
        try
        {
            bis.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException e) { }
        break MISSING_BLOCK_LABEL_166;
        Exception exception;
        exception;
        try
        {
            bis.close();
        }
        catch(Exception e) { }
        throw exception;
        return encodedData;
    }

    public static void encodeFileToFile(String infile, String outfile)
    {
        String encoded;
        java.io.OutputStream out;
        encoded = encodeFromFile(infile);
        out = null;
        out = new BufferedOutputStream(new FileOutputStream(outfile));
        out.write(encoded.getBytes("US-ASCII"));
        IOException ex;
        try
        {
            out.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_78;
        ex;
        ex.printStackTrace();
        try
        {
            out.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_78;
        Exception exception;
        exception;
        try
        {
            out.close();
        }
        catch(Exception ex) { }
        throw exception;
    }

    public static void decodeFileToFile(String infile, String outfile)
    {
        byte decoded[];
        java.io.OutputStream out;
        decoded = decodeFromFile(infile);
        out = null;
        out = new BufferedOutputStream(new FileOutputStream(outfile));
        out.write(decoded);
        IOException ex;
        try
        {
            out.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_73;
        ex;
        ex.printStackTrace();
        try
        {
            out.close();
        }
        // Misplaced declaration of an exception variable
        catch(IOException ex) { }
        break MISSING_BLOCK_LABEL_73;
        Exception exception;
        exception;
        try
        {
            out.close();
        }
        catch(Exception ex) { }
        throw exception;
    }

    public static final int NO_OPTIONS = 0;
    public static final int ENCODE = 1;
    public static final int DECODE = 0;
    public static final int GZIP = 2;
    public static final int DONT_BREAK_LINES = 8;
    public static final int URL_SAFE = 16;
    public static final int ORDERED = 32;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte EQUALS_SIGN = 61;
    private static final byte NEW_LINE = 10;
    private static final String PREFERRED_ENCODING = "UTF-8";
    private static final byte WHITE_SPACE_ENC = -5;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final byte _STANDARD_ALPHABET[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 43, 47
    };
    private static final byte _STANDARD_DECODABET[] = {
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
        -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, 62, -9, -9, -9, 63, 52, 53, 
        54, 55, 56, 57, 58, 59, 60, 61, -9, -9, 
        -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
        25, -9, -9, -9, -9, -9, -9, 26, 27, 28, 
        29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
        39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
        49, 50, 51, -9, -9, -9, -9
    };
    private static final byte _URL_SAFE_ALPHABET[] = {
        65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 
        75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 
        85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 
        101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 
        111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 
        121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 
        56, 57, 45, 95
    };
    private static final byte _URL_SAFE_DECODABET[] = {
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
        -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, 62, -9, -9, 52, 53, 
        54, 55, 56, 57, 58, 59, 60, 61, -9, -9, 
        -9, -1, -9, -9, -9, 0, 1, 2, 3, 4, 
        5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 
        15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 
        25, -9, -9, -9, -9, 63, -9, 26, 27, 28, 
        29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 
        39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 
        49, 50, 51, -9, -9, -9, -9
    };
    private static final byte _ORDERED_ALPHABET[] = {
        45, 48, 49, 50, 51, 52, 53, 54, 55, 56, 
        57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 
        74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 
        84, 85, 86, 87, 88, 89, 90, 95, 97, 98, 
        99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 
        109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 
        119, 120, 121, 122
    };
    private static final byte _ORDERED_DECODABET[] = {
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -5, 
        -5, -9, -9, -5, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -5, -9, -9, -9, -9, -9, -9, -9, 
        -9, -9, -9, -9, -9, 0, -9, -9, 1, 2, 
        3, 4, 5, 6, 7, 8, 9, 10, -9, -9, 
        -9, -1, -9, -9, -9, 11, 12, 13, 14, 15, 
        16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 
        26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 
        36, -9, -9, -9, -9, 37, -9, 38, 39, 40, 
        41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 
        51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 
        61, 62, 63, -9, -9, -9, -9
    };






}
