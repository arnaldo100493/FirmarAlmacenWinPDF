// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   CMapCache.java

package co.com.pdf.text.pdf.fonts.cmaps;

import java.io.IOException;
import java.util.HashMap;

// Referenced classes of package co.com.pdf.text.pdf.fonts.cmaps:
//            CMapUniCid, CidResource, CMapCidUni, CMapCidByte, 
//            CMapByteCid, CMapParserEx

public class CMapCache
{

    public CMapCache()
    {
    }

    public static CMapUniCid getCachedCMapUniCid(String name)
        throws IOException
    {
        CMapUniCid cmap = null;
        synchronized(cacheUniCid)
        {
            cmap = (CMapUniCid)cacheUniCid.get(name);
        }
        if(cmap == null)
        {
            cmap = new CMapUniCid();
            CMapParserEx.parseCid(name, cmap, new CidResource());
            synchronized(cacheUniCid)
            {
                cacheUniCid.put(name, cmap);
            }
        }
        return cmap;
    }

    public static CMapCidUni getCachedCMapCidUni(String name)
        throws IOException
    {
        CMapCidUni cmap = null;
        synchronized(cacheCidUni)
        {
            cmap = (CMapCidUni)cacheCidUni.get(name);
        }
        if(cmap == null)
        {
            cmap = new CMapCidUni();
            CMapParserEx.parseCid(name, cmap, new CidResource());
            synchronized(cacheCidUni)
            {
                cacheCidUni.put(name, cmap);
            }
        }
        return cmap;
    }

    public static CMapCidByte getCachedCMapCidByte(String name)
        throws IOException
    {
        CMapCidByte cmap = null;
        synchronized(cacheCidByte)
        {
            cmap = (CMapCidByte)cacheCidByte.get(name);
        }
        if(cmap == null)
        {
            cmap = new CMapCidByte();
            CMapParserEx.parseCid(name, cmap, new CidResource());
            synchronized(cacheCidByte)
            {
                cacheCidByte.put(name, cmap);
            }
        }
        return cmap;
    }

    public static CMapByteCid getCachedCMapByteCid(String name)
        throws IOException
    {
        CMapByteCid cmap = null;
        synchronized(cacheByteCid)
        {
            cmap = (CMapByteCid)cacheByteCid.get(name);
        }
        if(cmap == null)
        {
            cmap = new CMapByteCid();
            CMapParserEx.parseCid(name, cmap, new CidResource());
            synchronized(cacheByteCid)
            {
                cacheByteCid.put(name, cmap);
            }
        }
        return cmap;
    }

    private static final HashMap cacheUniCid = new HashMap();
    private static final HashMap cacheCidUni = new HashMap();
    private static final HashMap cacheCidByte = new HashMap();
    private static final HashMap cacheByteCid = new HashMap();

}
