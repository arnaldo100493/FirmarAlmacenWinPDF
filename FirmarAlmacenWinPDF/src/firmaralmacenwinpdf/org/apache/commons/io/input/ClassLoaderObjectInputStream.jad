// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   ClassLoaderObjectInputStream.java

package org.apache.commons.io.input;

import java.io.*;
import java.lang.reflect.Proxy;

public class ClassLoaderObjectInputStream extends ObjectInputStream
{

    public ClassLoaderObjectInputStream(ClassLoader classLoader, InputStream inputStream)
        throws IOException, StreamCorruptedException
    {
        super(inputStream);
        this.classLoader = classLoader;
    }

    protected Class resolveClass(ObjectStreamClass objectStreamClass)
        throws IOException, ClassNotFoundException
    {
        Class clazz = Class.forName(objectStreamClass.getName(), false, classLoader);
        if(clazz != null)
            return clazz;
        else
            return super.resolveClass(objectStreamClass);
    }

    protected Class resolveProxyClass(String interfaces[])
        throws IOException, ClassNotFoundException
    {
        Class interfaceClasses[] = new Class[interfaces.length];
        for(int i = 0; i < interfaces.length; i++)
            interfaceClasses[i] = Class.forName(interfaces[i], false, classLoader);

        try
        {
            return Proxy.getProxyClass(classLoader, interfaceClasses);
        }
        catch(IllegalArgumentException e)
        {
            return super.resolveProxyClass(interfaces);
        }
    }

    private final ClassLoader classLoader;
}
