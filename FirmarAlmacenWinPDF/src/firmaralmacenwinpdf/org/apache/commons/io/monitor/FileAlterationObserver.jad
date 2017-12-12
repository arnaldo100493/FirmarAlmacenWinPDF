// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileAlterationObserver.java

package org.apache.commons.io.monitor;

import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOCase;
import org.apache.commons.io.comparator.NameFileComparator;

// Referenced classes of package org.apache.commons.io.monitor:
//            FileEntry, FileAlterationListener

public class FileAlterationObserver
    implements Serializable
{

    public FileAlterationObserver(String directoryName)
    {
        this(new File(directoryName));
    }

    public FileAlterationObserver(String directoryName, FileFilter fileFilter)
    {
        this(new File(directoryName), fileFilter);
    }

    public FileAlterationObserver(String directoryName, FileFilter fileFilter, IOCase caseSensitivity)
    {
        this(new File(directoryName), fileFilter, caseSensitivity);
    }

    public FileAlterationObserver(File directory)
    {
        this(directory, (FileFilter)null);
    }

    public FileAlterationObserver(File directory, FileFilter fileFilter)
    {
        this(directory, fileFilter, (IOCase)null);
    }

    public FileAlterationObserver(File directory, FileFilter fileFilter, IOCase caseSensitivity)
    {
        this(new FileEntry(directory), fileFilter, caseSensitivity);
    }

    protected FileAlterationObserver(FileEntry rootEntry, FileFilter fileFilter, IOCase caseSensitivity)
    {
        listeners = new CopyOnWriteArrayList();
        if(rootEntry == null)
            throw new IllegalArgumentException("Root entry is missing");
        if(rootEntry.getFile() == null)
            throw new IllegalArgumentException("Root directory is missing");
        this.rootEntry = rootEntry;
        this.fileFilter = fileFilter;
        if(caseSensitivity == null || caseSensitivity.equals(IOCase.SYSTEM))
            comparator = NameFileComparator.NAME_SYSTEM_COMPARATOR;
        else
        if(caseSensitivity.equals(IOCase.INSENSITIVE))
            comparator = NameFileComparator.NAME_INSENSITIVE_COMPARATOR;
        else
            comparator = NameFileComparator.NAME_COMPARATOR;
    }

    public File getDirectory()
    {
        return rootEntry.getFile();
    }

    public FileFilter getFileFilter()
    {
        return fileFilter;
    }

    public void addListener(FileAlterationListener listener)
    {
        if(listener != null)
            listeners.add(listener);
    }

    public void removeListener(FileAlterationListener listener)
    {
        if(listener != null)
            while(listeners.remove(listener)) ;
    }

    public Iterable getListeners()
    {
        return listeners;
    }

    public void initialize()
        throws Exception
    {
        rootEntry.refresh(rootEntry.getFile());
        File files[] = listFiles(rootEntry.getFile());
        FileEntry children[] = files.length <= 0 ? FileEntry.EMPTY_ENTRIES : new FileEntry[files.length];
        for(int i = 0; i < files.length; i++)
            children[i] = createFileEntry(rootEntry, files[i]);

        rootEntry.setChildren(children);
    }

    public void destroy()
        throws Exception
    {
    }

    public void checkAndNotify()
    {
        FileAlterationListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.onStart(this))
            listener = (FileAlterationListener)i$.next();

        File rootFile = rootEntry.getFile();
        if(rootFile.exists())
            checkAndNotify(rootEntry, rootEntry.getChildren(), listFiles(rootFile));
        else
        if(rootEntry.isExists())
            checkAndNotify(rootEntry, rootEntry.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
        FileAlterationListener listener;
        for(Iterator i$ = listeners.iterator(); i$.hasNext(); listener.onStop(this))
            listener = (FileAlterationListener)i$.next();

    }

    private void checkAndNotify(FileEntry parent, FileEntry previous[], File files[])
    {
        int c = 0;
        FileEntry current[] = files.length <= 0 ? FileEntry.EMPTY_ENTRIES : new FileEntry[files.length];
        FileEntry arr$[] = previous;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            FileEntry entry;
            for(entry = arr$[i$]; c < files.length && comparator.compare(entry.getFile(), files[c]) > 0; c++)
            {
                current[c] = createFileEntry(parent, files[c]);
                doCreate(current[c]);
            }

            if(c < files.length && comparator.compare(entry.getFile(), files[c]) == 0)
            {
                doMatch(entry, files[c]);
                checkAndNotify(entry, entry.getChildren(), listFiles(files[c]));
                current[c] = entry;
                c++;
            } else
            {
                checkAndNotify(entry, entry.getChildren(), FileUtils.EMPTY_FILE_ARRAY);
                doDelete(entry);
            }
        }

        for(; c < files.length; c++)
        {
            current[c] = createFileEntry(parent, files[c]);
            doCreate(current[c]);
        }

        parent.setChildren(current);
    }

    private FileEntry createFileEntry(FileEntry parent, File file)
    {
        FileEntry entry = parent.newChildInstance(file);
        entry.refresh(file);
        File files[] = listFiles(file);
        FileEntry children[] = files.length <= 0 ? FileEntry.EMPTY_ENTRIES : new FileEntry[files.length];
        for(int i = 0; i < files.length; i++)
            children[i] = createFileEntry(entry, files[i]);

        entry.setChildren(children);
        return entry;
    }

    private void doCreate(FileEntry entry)
    {
        for(Iterator i$ = listeners.iterator(); i$.hasNext();)
        {
            FileAlterationListener listener = (FileAlterationListener)i$.next();
            if(entry.isDirectory())
                listener.onDirectoryCreate(entry.getFile());
            else
                listener.onFileCreate(entry.getFile());
        }

        FileEntry children[] = entry.getChildren();
        FileEntry arr$[] = children;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            FileEntry aChildren = arr$[i$];
            doCreate(aChildren);
        }

    }

    private void doMatch(FileEntry entry, File file)
    {
        if(entry.refresh(file))
        {
            for(Iterator i$ = listeners.iterator(); i$.hasNext();)
            {
                FileAlterationListener listener = (FileAlterationListener)i$.next();
                if(entry.isDirectory())
                    listener.onDirectoryChange(file);
                else
                    listener.onFileChange(file);
            }

        }
    }

    private void doDelete(FileEntry entry)
    {
        for(Iterator i$ = listeners.iterator(); i$.hasNext();)
        {
            FileAlterationListener listener = (FileAlterationListener)i$.next();
            if(entry.isDirectory())
                listener.onDirectoryDelete(entry.getFile());
            else
                listener.onFileDelete(entry.getFile());
        }

    }

    private File[] listFiles(File file)
    {
        File children[] = null;
        if(file.isDirectory())
            children = fileFilter != null ? file.listFiles(fileFilter) : file.listFiles();
        if(children == null)
            children = FileUtils.EMPTY_FILE_ARRAY;
        if(comparator != null && children.length > 1)
            Arrays.sort(children, comparator);
        return children;
    }

    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getSimpleName());
        builder.append("[file='");
        builder.append(getDirectory().getPath());
        builder.append('\'');
        if(fileFilter != null)
        {
            builder.append(", ");
            builder.append(fileFilter.toString());
        }
        builder.append(", listeners=");
        builder.append(listeners.size());
        builder.append("]");
        return builder.toString();
    }

    private final List listeners;
    private final FileEntry rootEntry;
    private final FileFilter fileFilter;
    private final Comparator comparator;
}
