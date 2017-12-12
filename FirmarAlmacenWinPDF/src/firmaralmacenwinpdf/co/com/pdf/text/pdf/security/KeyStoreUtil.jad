// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KeyStoreUtil.java

package co.com.pdf.text.pdf.security;

import co.com.pdf.text.ExceptionConverter;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class KeyStoreUtil
{

    public KeyStoreUtil()
    {
    }

    public static KeyStore loadCacertsKeyStore(String provider)
    {
        File file;
        FileInputStream fin;
        file = new File(System.getProperty("java.home"), "lib");
        file = new File(file, "security");
        file = new File(file, "cacerts");
        fin = null;
        KeyStore keystore;
        try
        {
            fin = new FileInputStream(file);
            KeyStore k;
            if(provider == null)
                k = KeyStore.getInstance("JKS");
            else
                k = KeyStore.getInstance("JKS", provider);
            k.load(fin, null);
            keystore = k;
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        try
        {
            if(fin != null)
                fin.close();
        }
        catch(Exception ex) { }
        return keystore;
        Exception exception;
        exception;
        try
        {
            if(fin != null)
                fin.close();
        }
        catch(Exception ex) { }
        throw exception;
    }

    public static KeyStore loadCacertsKeyStore()
    {
        return loadCacertsKeyStore(null);
    }
}
