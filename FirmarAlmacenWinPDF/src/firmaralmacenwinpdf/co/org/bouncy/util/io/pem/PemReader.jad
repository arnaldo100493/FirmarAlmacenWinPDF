// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PemReader.java

package co.org.bouncy.util.io.pem;

import co.org.bouncy.util.encoders.Base64;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package co.org.bouncy.util.io.pem:
//            PemHeader, PemObject

public class PemReader extends BufferedReader
{

    public PemReader(Reader reader)
    {
        super(reader);
    }

    public PemObject readPemObject()
        throws IOException
    {
        String line;
        for(line = readLine(); line != null && !line.startsWith("-----BEGIN "); line = readLine());
        if(line != null)
        {
            line = line.substring("-----BEGIN ".length());
            int index = line.indexOf('-');
            String type = line.substring(0, index);
            if(index > 0)
                return loadObject(type);
        }
        return null;
    }

    private PemObject loadObject(String type)
        throws IOException
    {
        String endMarker = (new StringBuilder()).append("-----END ").append(type).toString();
        StringBuffer buf = new StringBuffer();
        List headers = new ArrayList();
        String line;
        do
        {
            if((line = readLine()) == null)
                break;
            if(line.indexOf(":") >= 0)
            {
                int index = line.indexOf(':');
                String hdr = line.substring(0, index);
                String value = line.substring(index + 1).trim();
                headers.add(new PemHeader(hdr, value));
                continue;
            }
            if(line.indexOf(endMarker) != -1)
                break;
            buf.append(line.trim());
        } while(true);
        if(line == null)
            throw new IOException((new StringBuilder()).append(endMarker).append(" not found").toString());
        else
            return new PemObject(type, headers, Base64.decode(buf.toString()));
    }

    private static final String BEGIN = "-----BEGIN ";
    private static final String END = "-----END ";
}
