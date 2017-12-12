// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileFilterUtils.java

package org.apache.commons.io.filefilter;

import java.io.*;
import java.util.*;
import org.apache.commons.io.IOCase;

// Referenced classes of package org.apache.commons.io.filefilter:
//            PrefixFileFilter, SuffixFileFilter, NameFileFilter, AndFileFilter, 
//            OrFileFilter, NotFileFilter, DelegateFileFilter, AgeFileFilter, 
//            SizeFileFilter, MagicNumberFileFilter, IOFileFilter, DirectoryFileFilter, 
//            FileFileFilter, TrueFileFilter, FalseFileFilter

public class FileFilterUtils
{

    public FileFilterUtils()
    {
    }

    public static transient File[] filter(IOFileFilter filter, File files[])
    {
        if(filter == null)
            throw new IllegalArgumentException("file filter is null");
        if(files == null)
            return new File[0];
        List acceptedFiles = new ArrayList();
        File arr$[] = files;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            File file = arr$[i$];
            if(file == null)
                throw new IllegalArgumentException("file array contains null");
            if(filter.accept(file))
                acceptedFiles.add(file);
        }

        return (File[])acceptedFiles.toArray(new File[acceptedFiles.size()]);
    }

    public static File[] filter(IOFileFilter filter, Iterable files)
    {
        List acceptedFiles = filterList(filter, files);
        return (File[])acceptedFiles.toArray(new File[acceptedFiles.size()]);
    }

    public static List filterList(IOFileFilter filter, Iterable files)
    {
        return (List)filter(filter, files, new ArrayList());
    }

    public static transient List filterList(IOFileFilter filter, File files[])
    {
        File acceptedFiles[] = filter(filter, files);
        return Arrays.asList(acceptedFiles);
    }

    public static transient Set filterSet(IOFileFilter filter, File files[])
    {
        File acceptedFiles[] = filter(filter, files);
        return new HashSet(Arrays.asList(acceptedFiles));
    }

    public static Set filterSet(IOFileFilter filter, Iterable files)
    {
        return (Set)filter(filter, files, new HashSet());
    }

    private static Collection filter(IOFileFilter filter, Iterable files, Collection acceptedFiles)
    {
        if(filter == null)
            throw new IllegalArgumentException("file filter is null");
        if(files != null)
        {
            Iterator i$ = files.iterator();
            do
            {
                if(!i$.hasNext())
                    break;
                File file = (File)i$.next();
                if(file == null)
                    throw new IllegalArgumentException("file collection contains null");
                if(filter.accept(file))
                    acceptedFiles.add(file);
            } while(true);
        }
        return acceptedFiles;
    }

    public static IOFileFilter prefixFileFilter(String prefix)
    {
        return new PrefixFileFilter(prefix);
    }

    public static IOFileFilter prefixFileFilter(String prefix, IOCase caseSensitivity)
    {
        return new PrefixFileFilter(prefix, caseSensitivity);
    }

    public static IOFileFilter suffixFileFilter(String suffix)
    {
        return new SuffixFileFilter(suffix);
    }

    public static IOFileFilter suffixFileFilter(String suffix, IOCase caseSensitivity)
    {
        return new SuffixFileFilter(suffix, caseSensitivity);
    }

    public static IOFileFilter nameFileFilter(String name)
    {
        return new NameFileFilter(name);
    }

    public static IOFileFilter nameFileFilter(String name, IOCase caseSensitivity)
    {
        return new NameFileFilter(name, caseSensitivity);
    }

    public static IOFileFilter directoryFileFilter()
    {
        return DirectoryFileFilter.DIRECTORY;
    }

    public static IOFileFilter fileFileFilter()
    {
        return FileFileFilter.FILE;
    }

    /**
     * @deprecated Method andFileFilter is deprecated
     */

    public static IOFileFilter andFileFilter(IOFileFilter filter1, IOFileFilter filter2)
    {
        return new AndFileFilter(filter1, filter2);
    }

    /**
     * @deprecated Method orFileFilter is deprecated
     */

    public static IOFileFilter orFileFilter(IOFileFilter filter1, IOFileFilter filter2)
    {
        return new OrFileFilter(filter1, filter2);
    }

    public static transient IOFileFilter and(IOFileFilter filters[])
    {
        return new AndFileFilter(toList(filters));
    }

    public static transient IOFileFilter or(IOFileFilter filters[])
    {
        return new OrFileFilter(toList(filters));
    }

    public static transient List toList(IOFileFilter filters[])
    {
        if(filters == null)
            throw new IllegalArgumentException("The filters must not be null");
        List list = new ArrayList(filters.length);
        for(int i = 0; i < filters.length; i++)
        {
            if(filters[i] == null)
                throw new IllegalArgumentException((new StringBuilder()).append("The filter[").append(i).append("] is null").toString());
            list.add(filters[i]);
        }

        return list;
    }

    public static IOFileFilter notFileFilter(IOFileFilter filter)
    {
        return new NotFileFilter(filter);
    }

    public static IOFileFilter trueFileFilter()
    {
        return TrueFileFilter.TRUE;
    }

    public static IOFileFilter falseFileFilter()
    {
        return FalseFileFilter.FALSE;
    }

    public static IOFileFilter asFileFilter(FileFilter filter)
    {
        return new DelegateFileFilter(filter);
    }

    public static IOFileFilter asFileFilter(FilenameFilter filter)
    {
        return new DelegateFileFilter(filter);
    }

    public static IOFileFilter ageFileFilter(long cutoff)
    {
        return new AgeFileFilter(cutoff);
    }

    public static IOFileFilter ageFileFilter(long cutoff, boolean acceptOlder)
    {
        return new AgeFileFilter(cutoff, acceptOlder);
    }

    public static IOFileFilter ageFileFilter(Date cutoffDate)
    {
        return new AgeFileFilter(cutoffDate);
    }

    public static IOFileFilter ageFileFilter(Date cutoffDate, boolean acceptOlder)
    {
        return new AgeFileFilter(cutoffDate, acceptOlder);
    }

    public static IOFileFilter ageFileFilter(File cutoffReference)
    {
        return new AgeFileFilter(cutoffReference);
    }

    public static IOFileFilter ageFileFilter(File cutoffReference, boolean acceptOlder)
    {
        return new AgeFileFilter(cutoffReference, acceptOlder);
    }

    public static IOFileFilter sizeFileFilter(long threshold)
    {
        return new SizeFileFilter(threshold);
    }

    public static IOFileFilter sizeFileFilter(long threshold, boolean acceptLarger)
    {
        return new SizeFileFilter(threshold, acceptLarger);
    }

    public static IOFileFilter sizeRangeFileFilter(long minSizeInclusive, long maxSizeInclusive)
    {
        IOFileFilter minimumFilter = new SizeFileFilter(minSizeInclusive, true);
        IOFileFilter maximumFilter = new SizeFileFilter(maxSizeInclusive + 1L, false);
        return new AndFileFilter(minimumFilter, maximumFilter);
    }

    public static IOFileFilter magicNumberFileFilter(String magicNumber)
    {
        return new MagicNumberFileFilter(magicNumber);
    }

    public static IOFileFilter magicNumberFileFilter(String magicNumber, long offset)
    {
        return new MagicNumberFileFilter(magicNumber, offset);
    }

    public static IOFileFilter magicNumberFileFilter(byte magicNumber[])
    {
        return new MagicNumberFileFilter(magicNumber);
    }

    public static IOFileFilter magicNumberFileFilter(byte magicNumber[], long offset)
    {
        return new MagicNumberFileFilter(magicNumber, offset);
    }

    public static IOFileFilter makeCVSAware(IOFileFilter filter)
    {
        if(filter == null)
            return cvsFilter;
        else
            return and(new IOFileFilter[] {
                filter, cvsFilter
            });
    }

    public static IOFileFilter makeSVNAware(IOFileFilter filter)
    {
        if(filter == null)
            return svnFilter;
        else
            return and(new IOFileFilter[] {
                filter, svnFilter
            });
    }

    public static IOFileFilter makeDirectoryOnly(IOFileFilter filter)
    {
        if(filter == null)
            return DirectoryFileFilter.DIRECTORY;
        else
            return new AndFileFilter(DirectoryFileFilter.DIRECTORY, filter);
    }

    public static IOFileFilter makeFileOnly(IOFileFilter filter)
    {
        if(filter == null)
            return FileFileFilter.FILE;
        else
            return new AndFileFilter(FileFileFilter.FILE, filter);
    }

    private static final IOFileFilter cvsFilter = notFileFilter(and(new IOFileFilter[] {
        directoryFileFilter(), nameFileFilter("CVS")
    }));
    private static final IOFileFilter svnFilter = notFileFilter(and(new IOFileFilter[] {
        directoryFileFilter(), nameFileFilter(".svn")
    }));

}
