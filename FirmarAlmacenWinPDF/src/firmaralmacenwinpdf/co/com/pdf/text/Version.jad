// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   Version.java

package co.com.pdf.text;

import java.lang.reflect.Method;

public final class Version
{

    public Version()
    {
        iText = "iText\256";
        release = "5.4.4";
        key = null;
        iTextVersion = (new StringBuilder()).append(iText).append(" ").append(release).append(" \2512000-2013 1T3XT BVBA").toString();
    }

    public static Version getInstance()
    {
        if(version != null) goto _L2; else goto _L1
_L1:
        version = new Version();
        String info[];
        Class klass = Class.forName("com.itextpdf.license.LicenseKey");
        Method m = klass.getMethod("getLicenseeInfo", new Class[0]);
        info = (String[])(String[])m.invoke(klass.newInstance(), new Object[0]);
        if(info[3] == null || info[3].trim().length() <= 0) goto _L4; else goto _L3
_L3:
        version.key = info[3];
          goto _L5
_L4:
        version.key = "Trial version ";
        if(info[5] != null) goto _L7; else goto _L6
_L6:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        key;
        append();
        "unauthorised";
        append();
        toString();
        key;
          goto _L5
_L7:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        key;
        append();
        info[5];
        append();
        toString();
        key;
_L5:
        if(info[4] == null || info[4].trim().length() <= 0) goto _L9; else goto _L8
_L8:
        version.iTextVersion = info[4];
          goto _L2
_L9:
        if(info[2] == null || info[2].trim().length() <= 0) goto _L11; else goto _L10
_L10:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        " (";
        append();
        info[2];
        append();
        toString();
        iTextVersion;
        if(version.key.toLowerCase().startsWith("trial")) goto _L13; else goto _L12
_L12:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        "; licensed version)";
        append();
        toString();
        iTextVersion;
          goto _L2
_L13:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        "; ";
        append();
        version.key;
        append();
        ")";
        append();
        toString();
        iTextVersion;
          goto _L2
_L11:
        if(info[0] == null || info[0].trim().length() <= 0) goto _L15; else goto _L14
_L14:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        " (";
        append();
        info[0];
        append();
        toString();
        iTextVersion;
        if(version.key.toLowerCase().startsWith("trial")) goto _L17; else goto _L16
_L16:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        "; licensed version)";
        append();
        toString();
        iTextVersion;
          goto _L2
_L17:
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        "; ";
        append();
        version.key;
        append();
        ")";
        append();
        toString();
        iTextVersion;
          goto _L2
_L15:
        throw new Exception();
        Exception e;
        e;
        new StringBuilder();
        version;
        JVM INSTR dup_x1 ;
        iTextVersion;
        append();
        " (AGPL-version)";
        append();
        toString();
        iTextVersion;
_L2:
        return version;
    }

    public String getProduct()
    {
        return iText;
    }

    public String getRelease()
    {
        return release;
    }

    public String getVersion()
    {
        return iTextVersion;
    }

    public String getKey()
    {
        return key;
    }

    private String iText;
    private String release;
    private String key;
    private String iTextVersion;
    private static Version version = null;

}
