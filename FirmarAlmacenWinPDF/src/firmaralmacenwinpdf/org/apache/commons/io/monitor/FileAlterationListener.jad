// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileAlterationListener.java

package org.apache.commons.io.monitor;

import java.io.File;

// Referenced classes of package org.apache.commons.io.monitor:
//            FileAlterationObserver

public interface FileAlterationListener
{

    public abstract void onStart(FileAlterationObserver filealterationobserver);

    public abstract void onDirectoryCreate(File file);

    public abstract void onDirectoryChange(File file);

    public abstract void onDirectoryDelete(File file);

    public abstract void onFileCreate(File file);

    public abstract void onFileChange(File file);

    public abstract void onFileDelete(File file);

    public abstract void onStop(FileAlterationObserver filealterationobserver);
}
