// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   FileUtils.java

package org.apache.commons.io;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.*;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.output.NullOutputStream;

// Referenced classes of package org.apache.commons.io:
//            FileExistsException, IOUtils, FilenameUtils, Charsets, 
//            LineIterator

public class FileUtils
{

    public FileUtils()
    {
    }

    public static transient File getFile(File directory, String names[])
    {
        if(directory == null)
            throw new NullPointerException("directorydirectory must not be null");
        if(names == null)
            throw new NullPointerException("names must not be null");
        File file = directory;
        String arr$[] = names;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String name = arr$[i$];
            file = new File(file, name);
        }

        return file;
    }

    public static transient File getFile(String names[])
    {
        if(names == null)
            throw new NullPointerException("names must not be null");
        File file = null;
        String arr$[] = names;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            String name = arr$[i$];
            if(file == null)
                file = new File(name);
            else
                file = new File(file, name);
        }

        return file;
    }

    public static String getTempDirectoryPath()
    {
        return System.getProperty("java.io.tmpdir");
    }

    public static File getTempDirectory()
    {
        return new File(getTempDirectoryPath());
    }

    public static String getUserDirectoryPath()
    {
        return System.getProperty("user.home");
    }

    public static File getUserDirectory()
    {
        return new File(getUserDirectoryPath());
    }

    public static FileInputStream openInputStream(File file)
        throws IOException
    {
        if(file.exists())
        {
            if(file.isDirectory())
                throw new IOException((new StringBuilder()).append("File '").append(file).append("' exists but is a directory").toString());
            if(!file.canRead())
                throw new IOException((new StringBuilder()).append("File '").append(file).append("' cannot be read").toString());
            else
                return new FileInputStream(file);
        } else
        {
            throw new FileNotFoundException((new StringBuilder()).append("File '").append(file).append("' does not exist").toString());
        }
    }

    public static FileOutputStream openOutputStream(File file)
        throws IOException
    {
        return openOutputStream(file, false);
    }

    public static FileOutputStream openOutputStream(File file, boolean append)
        throws IOException
    {
        if(file.exists())
        {
            if(file.isDirectory())
                throw new IOException((new StringBuilder()).append("File '").append(file).append("' exists but is a directory").toString());
            if(!file.canWrite())
                throw new IOException((new StringBuilder()).append("File '").append(file).append("' cannot be written to").toString());
        } else
        {
            File parent = file.getParentFile();
            if(parent != null && !parent.mkdirs() && !parent.isDirectory())
                throw new IOException((new StringBuilder()).append("Directory '").append(parent).append("' could not be created").toString());
        }
        return new FileOutputStream(file, append);
    }

    public static String byteCountToDisplaySize(BigInteger size)
    {
        String displaySize;
        if(size.divide(ONE_EB_BI).compareTo(BigInteger.ZERO) > 0)
            displaySize = (new StringBuilder()).append(String.valueOf(size.divide(ONE_EB_BI))).append(" EB").toString();
        else
        if(size.divide(ONE_PB_BI).compareTo(BigInteger.ZERO) > 0)
            displaySize = (new StringBuilder()).append(String.valueOf(size.divide(ONE_PB_BI))).append(" PB").toString();
        else
        if(size.divide(ONE_TB_BI).compareTo(BigInteger.ZERO) > 0)
            displaySize = (new StringBuilder()).append(String.valueOf(size.divide(ONE_TB_BI))).append(" TB").toString();
        else
        if(size.divide(ONE_GB_BI).compareTo(BigInteger.ZERO) > 0)
            displaySize = (new StringBuilder()).append(String.valueOf(size.divide(ONE_GB_BI))).append(" GB").toString();
        else
        if(size.divide(ONE_MB_BI).compareTo(BigInteger.ZERO) > 0)
            displaySize = (new StringBuilder()).append(String.valueOf(size.divide(ONE_MB_BI))).append(" MB").toString();
        else
        if(size.divide(ONE_KB_BI).compareTo(BigInteger.ZERO) > 0)
            displaySize = (new StringBuilder()).append(String.valueOf(size.divide(ONE_KB_BI))).append(" KB").toString();
        else
            displaySize = (new StringBuilder()).append(String.valueOf(size)).append(" bytes").toString();
        return displaySize;
    }

    public static String byteCountToDisplaySize(long size)
    {
        return byteCountToDisplaySize(BigInteger.valueOf(size));
    }

    public static void touch(File file)
        throws IOException
    {
        if(!file.exists())
        {
            OutputStream out = openOutputStream(file);
            IOUtils.closeQuietly(out);
        }
        boolean success = file.setLastModified(System.currentTimeMillis());
        if(!success)
            throw new IOException((new StringBuilder()).append("Unable to set the last modification time for ").append(file).toString());
        else
            return;
    }

    public static File[] convertFileCollectionToFileArray(Collection files)
    {
        return (File[])files.toArray(new File[files.size()]);
    }

    private static void innerListFiles(Collection files, File directory, IOFileFilter filter, boolean includeSubDirectories)
    {
        File found[] = directory.listFiles(filter);
        if(found != null)
        {
            File arr$[] = found;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                File file = arr$[i$];
                if(file.isDirectory())
                {
                    if(includeSubDirectories)
                        files.add(file);
                    innerListFiles(files, file, filter, includeSubDirectories);
                } else
                {
                    files.add(file);
                }
            }

        }
    }

    public static Collection listFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter)
    {
        validateListFilesParameters(directory, fileFilter);
        IOFileFilter effFileFilter = setUpEffectiveFileFilter(fileFilter);
        IOFileFilter effDirFilter = setUpEffectiveDirFilter(dirFilter);
        Collection files = new LinkedList();
        innerListFiles(files, directory, FileFilterUtils.or(new IOFileFilter[] {
            effFileFilter, effDirFilter
        }), false);
        return files;
    }

    private static void validateListFilesParameters(File directory, IOFileFilter fileFilter)
    {
        if(!directory.isDirectory())
            throw new IllegalArgumentException("Parameter 'directory' is not a directory");
        if(fileFilter == null)
            throw new NullPointerException("Parameter 'fileFilter' is null");
        else
            return;
    }

    private static IOFileFilter setUpEffectiveFileFilter(IOFileFilter fileFilter)
    {
        return FileFilterUtils.and(new IOFileFilter[] {
            fileFilter, FileFilterUtils.notFileFilter(DirectoryFileFilter.INSTANCE)
        });
    }

    private static IOFileFilter setUpEffectiveDirFilter(IOFileFilter dirFilter)
    {
        return dirFilter != null ? FileFilterUtils.and(new IOFileFilter[] {
            dirFilter, DirectoryFileFilter.INSTANCE
        }) : FalseFileFilter.INSTANCE;
    }

    public static Collection listFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter)
    {
        validateListFilesParameters(directory, fileFilter);
        IOFileFilter effFileFilter = setUpEffectiveFileFilter(fileFilter);
        IOFileFilter effDirFilter = setUpEffectiveDirFilter(dirFilter);
        Collection files = new LinkedList();
        if(directory.isDirectory())
            files.add(directory);
        innerListFiles(files, directory, FileFilterUtils.or(new IOFileFilter[] {
            effFileFilter, effDirFilter
        }), true);
        return files;
    }

    public static Iterator iterateFiles(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter)
    {
        return listFiles(directory, fileFilter, dirFilter).iterator();
    }

    public static Iterator iterateFilesAndDirs(File directory, IOFileFilter fileFilter, IOFileFilter dirFilter)
    {
        return listFilesAndDirs(directory, fileFilter, dirFilter).iterator();
    }

    private static String[] toSuffixes(String extensions[])
    {
        String suffixes[] = new String[extensions.length];
        for(int i = 0; i < extensions.length; i++)
            suffixes[i] = (new StringBuilder()).append(".").append(extensions[i]).toString();

        return suffixes;
    }

    public static Collection listFiles(File directory, String extensions[], boolean recursive)
    {
        IOFileFilter filter;
        if(extensions == null)
        {
            filter = TrueFileFilter.INSTANCE;
        } else
        {
            String suffixes[] = toSuffixes(extensions);
            filter = new SuffixFileFilter(suffixes);
        }
        return listFiles(directory, filter, recursive ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE);
    }

    public static Iterator iterateFiles(File directory, String extensions[], boolean recursive)
    {
        return listFiles(directory, extensions, recursive).iterator();
    }

    public static boolean contentEquals(File file1, File file2)
        throws IOException
    {
        InputStream input1;
        InputStream input2;
        boolean file1Exists = file1.exists();
        if(file1Exists != file2.exists())
            return false;
        if(!file1Exists)
            return true;
        if(file1.isDirectory() || file2.isDirectory())
            throw new IOException("Can't compare directories, only files");
        if(file1.length() != file2.length())
            return false;
        if(file1.getCanonicalFile().equals(file2.getCanonicalFile()))
            return true;
        input1 = null;
        input2 = null;
        boolean flag;
        input1 = new FileInputStream(file1);
        input2 = new FileInputStream(file2);
        flag = IOUtils.contentEquals(input1, input2);
        IOUtils.closeQuietly(input1);
        IOUtils.closeQuietly(input2);
        return flag;
        Exception exception;
        exception;
        IOUtils.closeQuietly(input1);
        IOUtils.closeQuietly(input2);
        throw exception;
    }

    public static boolean contentEqualsIgnoreEOL(File file1, File file2, String charsetName)
        throws IOException
    {
        Reader input1;
        Reader input2;
        boolean file1Exists = file1.exists();
        if(file1Exists != file2.exists())
            return false;
        if(!file1Exists)
            return true;
        if(file1.isDirectory() || file2.isDirectory())
            throw new IOException("Can't compare directories, only files");
        if(file1.getCanonicalFile().equals(file2.getCanonicalFile()))
            return true;
        input1 = null;
        input2 = null;
        boolean flag;
        if(charsetName == null)
        {
            input1 = new InputStreamReader(new FileInputStream(file1));
            input2 = new InputStreamReader(new FileInputStream(file2));
        } else
        {
            input1 = new InputStreamReader(new FileInputStream(file1), charsetName);
            input2 = new InputStreamReader(new FileInputStream(file2), charsetName);
        }
        flag = IOUtils.contentEqualsIgnoreEOL(input1, input2);
        IOUtils.closeQuietly(input1);
        IOUtils.closeQuietly(input2);
        return flag;
        Exception exception;
        exception;
        IOUtils.closeQuietly(input1);
        IOUtils.closeQuietly(input2);
        throw exception;
    }

    public static File toFile(URL url)
    {
        if(url == null || !"file".equalsIgnoreCase(url.getProtocol()))
        {
            return null;
        } else
        {
            String filename = url.getFile().replace('/', File.separatorChar);
            filename = decodeUrl(filename);
            return new File(filename);
        }
    }

    static String decodeUrl(String url)
    {
        String decoded;
        int n;
        StringBuffer buffer;
        ByteBuffer bytes;
        int i;
        decoded = url;
        if(url == null || url.indexOf('%') < 0)
            break MISSING_BLOCK_LABEL_245;
        n = url.length();
        buffer = new StringBuffer();
        bytes = ByteBuffer.allocate(n);
        i = 0;
_L2:
        if(i >= n)
            break; /* Loop/switch isn't completed */
        if(url.charAt(i) != '%')
            break MISSING_BLOCK_LABEL_223;
        try
        {
            do
            {
                byte octet = (byte)Integer.parseInt(url.substring(i + 1, i + 3), 16);
                bytes.put(octet);
            } while((i += 3) < n && url.charAt(i) == '%');
        }
        catch(RuntimeException e)
        {
            if(bytes.position() > 0)
            {
                bytes.flip();
                buffer.append(UTF8.decode(bytes).toString());
                bytes.clear();
            }
            break MISSING_BLOCK_LABEL_223;
        }
        if(bytes.position() > 0)
        {
            bytes.flip();
            buffer.append(UTF8.decode(bytes).toString());
            bytes.clear();
        }
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        if(bytes.position() > 0)
        {
            bytes.flip();
            buffer.append(UTF8.decode(bytes).toString());
            bytes.clear();
        }
        throw exception;
        buffer.append(url.charAt(i++));
        if(true) goto _L2; else goto _L1
_L1:
        decoded = buffer.toString();
        return decoded;
    }

    public static File[] toFiles(URL urls[])
    {
        if(urls == null || urls.length == 0)
            return EMPTY_FILE_ARRAY;
        File files[] = new File[urls.length];
        for(int i = 0; i < urls.length; i++)
        {
            URL url = urls[i];
            if(url == null)
                continue;
            if(!url.getProtocol().equals("file"))
                throw new IllegalArgumentException((new StringBuilder()).append("URL could not be converted to a File: ").append(url).toString());
            files[i] = toFile(url);
        }

        return files;
    }

    public static URL[] toURLs(File files[])
        throws IOException
    {
        URL urls[] = new URL[files.length];
        for(int i = 0; i < urls.length; i++)
            urls[i] = files[i].toURI().toURL();

        return urls;
    }

    public static void copyFileToDirectory(File srcFile, File destDir)
        throws IOException
    {
        copyFileToDirectory(srcFile, destDir, true);
    }

    public static void copyFileToDirectory(File srcFile, File destDir, boolean preserveFileDate)
        throws IOException
    {
        if(destDir == null)
            throw new NullPointerException("Destination must not be null");
        if(destDir.exists() && !destDir.isDirectory())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Destination '").append(destDir).append("' is not a directory").toString());
        } else
        {
            File destFile = new File(destDir, srcFile.getName());
            copyFile(srcFile, destFile, preserveFileDate);
            return;
        }
    }

    public static void copyFile(File srcFile, File destFile)
        throws IOException
    {
        copyFile(srcFile, destFile, true);
    }

    public static void copyFile(File srcFile, File destFile, boolean preserveFileDate)
        throws IOException
    {
        if(srcFile == null)
            throw new NullPointerException("Source must not be null");
        if(destFile == null)
            throw new NullPointerException("Destination must not be null");
        if(!srcFile.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Source '").append(srcFile).append("' does not exist").toString());
        if(srcFile.isDirectory())
            throw new IOException((new StringBuilder()).append("Source '").append(srcFile).append("' exists but is a directory").toString());
        if(srcFile.getCanonicalPath().equals(destFile.getCanonicalPath()))
            throw new IOException((new StringBuilder()).append("Source '").append(srcFile).append("' and destination '").append(destFile).append("' are the same").toString());
        File parentFile = destFile.getParentFile();
        if(parentFile != null && !parentFile.mkdirs() && !parentFile.isDirectory())
            throw new IOException((new StringBuilder()).append("Destination '").append(parentFile).append("' directory cannot be created").toString());
        if(destFile.exists() && !destFile.canWrite())
        {
            throw new IOException((new StringBuilder()).append("Destination '").append(destFile).append("' exists but is read-only").toString());
        } else
        {
            doCopyFile(srcFile, destFile, preserveFileDate);
            return;
        }
    }

    public static long copyFile(File input, OutputStream output)
        throws IOException
    {
        FileInputStream fis = new FileInputStream(input);
        long l = IOUtils.copyLarge(fis, output);
        fis.close();
        return l;
        Exception exception;
        exception;
        fis.close();
        throw exception;
    }

    private static void doCopyFile(File srcFile, File destFile, boolean preserveFileDate)
        throws IOException
    {
        FileInputStream fis;
        FileOutputStream fos;
        FileChannel input;
        FileChannel output;
        if(destFile.exists() && destFile.isDirectory())
            throw new IOException((new StringBuilder()).append("Destination '").append(destFile).append("' exists but is a directory").toString());
        fis = null;
        fos = null;
        input = null;
        output = null;
        fis = new FileInputStream(srcFile);
        fos = new FileOutputStream(destFile);
        input = fis.getChannel();
        output = fos.getChannel();
        long size = input.size();
        long pos = 0L;
        long count = 0L;
        for(; pos < size; pos += output.transferFrom(input, pos, count))
            count = size - pos <= 0x1e00000L ? size - pos : 0x1e00000L;

        IOUtils.closeQuietly(output);
        IOUtils.closeQuietly(fos);
        IOUtils.closeQuietly(input);
        IOUtils.closeQuietly(fis);
        break MISSING_BLOCK_LABEL_200;
        Exception exception;
        exception;
        IOUtils.closeQuietly(output);
        IOUtils.closeQuietly(fos);
        IOUtils.closeQuietly(input);
        IOUtils.closeQuietly(fis);
        throw exception;
        if(srcFile.length() != destFile.length())
            throw new IOException((new StringBuilder()).append("Failed to copy full contents from '").append(srcFile).append("' to '").append(destFile).append("'").toString());
        if(preserveFileDate)
            destFile.setLastModified(srcFile.lastModified());
        return;
    }

    public static void copyDirectoryToDirectory(File srcDir, File destDir)
        throws IOException
    {
        if(srcDir == null)
            throw new NullPointerException("Source must not be null");
        if(srcDir.exists() && !srcDir.isDirectory())
            throw new IllegalArgumentException((new StringBuilder()).append("Source '").append(destDir).append("' is not a directory").toString());
        if(destDir == null)
            throw new NullPointerException("Destination must not be null");
        if(destDir.exists() && !destDir.isDirectory())
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Destination '").append(destDir).append("' is not a directory").toString());
        } else
        {
            copyDirectory(srcDir, new File(destDir, srcDir.getName()), true);
            return;
        }
    }

    public static void copyDirectory(File srcDir, File destDir)
        throws IOException
    {
        copyDirectory(srcDir, destDir, true);
    }

    public static void copyDirectory(File srcDir, File destDir, boolean preserveFileDate)
        throws IOException
    {
        copyDirectory(srcDir, destDir, null, preserveFileDate);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter)
        throws IOException
    {
        copyDirectory(srcDir, destDir, filter, true);
    }

    public static void copyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate)
        throws IOException
    {
        if(srcDir == null)
            throw new NullPointerException("Source must not be null");
        if(destDir == null)
            throw new NullPointerException("Destination must not be null");
        if(!srcDir.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Source '").append(srcDir).append("' does not exist").toString());
        if(!srcDir.isDirectory())
            throw new IOException((new StringBuilder()).append("Source '").append(srcDir).append("' exists but is not a directory").toString());
        if(srcDir.getCanonicalPath().equals(destDir.getCanonicalPath()))
            throw new IOException((new StringBuilder()).append("Source '").append(srcDir).append("' and destination '").append(destDir).append("' are the same").toString());
        List exclusionList = null;
        if(destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath()))
        {
            File srcFiles[] = filter != null ? srcDir.listFiles(filter) : srcDir.listFiles();
            if(srcFiles != null && srcFiles.length > 0)
            {
                exclusionList = new ArrayList(srcFiles.length);
                File arr$[] = srcFiles;
                int len$ = arr$.length;
                for(int i$ = 0; i$ < len$; i$++)
                {
                    File srcFile = arr$[i$];
                    File copiedFile = new File(destDir, srcFile.getName());
                    exclusionList.add(copiedFile.getCanonicalPath());
                }

            }
        }
        doCopyDirectory(srcDir, destDir, filter, preserveFileDate, exclusionList);
    }

    private static void doCopyDirectory(File srcDir, File destDir, FileFilter filter, boolean preserveFileDate, List exclusionList)
        throws IOException
    {
        File srcFiles[] = filter != null ? srcDir.listFiles(filter) : srcDir.listFiles();
        if(srcFiles == null)
            throw new IOException((new StringBuilder()).append("Failed to list contents of ").append(srcDir).toString());
        if(destDir.exists())
        {
            if(!destDir.isDirectory())
                throw new IOException((new StringBuilder()).append("Destination '").append(destDir).append("' exists but is not a directory").toString());
        } else
        if(!destDir.mkdirs() && !destDir.isDirectory())
            throw new IOException((new StringBuilder()).append("Destination '").append(destDir).append("' directory cannot be created").toString());
        if(!destDir.canWrite())
            throw new IOException((new StringBuilder()).append("Destination '").append(destDir).append("' cannot be written to").toString());
        File arr$[] = srcFiles;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            File srcFile = arr$[i$];
            File dstFile = new File(destDir, srcFile.getName());
            if(exclusionList != null && exclusionList.contains(srcFile.getCanonicalPath()))
                continue;
            if(srcFile.isDirectory())
                doCopyDirectory(srcFile, dstFile, filter, preserveFileDate, exclusionList);
            else
                doCopyFile(srcFile, dstFile, preserveFileDate);
        }

        if(preserveFileDate)
            destDir.setLastModified(srcDir.lastModified());
    }

    public static void copyURLToFile(URL source, File destination)
        throws IOException
    {
        InputStream input = source.openStream();
        copyInputStreamToFile(input, destination);
    }

    public static void copyURLToFile(URL source, File destination, int connectionTimeout, int readTimeout)
        throws IOException
    {
        URLConnection connection = source.openConnection();
        connection.setConnectTimeout(connectionTimeout);
        connection.setReadTimeout(readTimeout);
        InputStream input = connection.getInputStream();
        copyInputStreamToFile(input, destination);
    }

    public static void copyInputStreamToFile(InputStream source, File destination)
        throws IOException
    {
        FileOutputStream output = openOutputStream(destination);
        IOUtils.copy(source, output);
        output.close();
        IOUtils.closeQuietly(output);
        break MISSING_BLOCK_LABEL_29;
        Exception exception;
        exception;
        IOUtils.closeQuietly(output);
        throw exception;
        IOUtils.closeQuietly(source);
        break MISSING_BLOCK_LABEL_45;
        Exception exception1;
        exception1;
        IOUtils.closeQuietly(source);
        throw exception1;
    }

    public static void deleteDirectory(File directory)
        throws IOException
    {
        if(!directory.exists())
            return;
        if(!isSymlink(directory))
            cleanDirectory(directory);
        if(!directory.delete())
        {
            String message = (new StringBuilder()).append("Unable to delete directory ").append(directory).append(".").toString();
            throw new IOException(message);
        } else
        {
            return;
        }
    }

    public static boolean deleteQuietly(File file)
    {
        if(file == null)
            return false;
        try
        {
            if(file.isDirectory())
                cleanDirectory(file);
        }
        catch(Exception ignored) { }
        try
        {
            return file.delete();
        }
        catch(Exception ignored)
        {
            return false;
        }
    }

    public static boolean directoryContains(File directory, File child)
        throws IOException
    {
        if(directory == null)
            throw new IllegalArgumentException("Directory must not be null");
        if(!directory.isDirectory())
            throw new IllegalArgumentException((new StringBuilder()).append("Not a directory: ").append(directory).toString());
        if(child == null)
            return false;
        if(!directory.exists() || !child.exists())
        {
            return false;
        } else
        {
            String canonicalParent = directory.getCanonicalPath();
            String canonicalChild = child.getCanonicalPath();
            return FilenameUtils.directoryContains(canonicalParent, canonicalChild);
        }
    }

    public static void cleanDirectory(File directory)
        throws IOException
    {
        if(!directory.exists())
        {
            String message = (new StringBuilder()).append(directory).append(" does not exist").toString();
            throw new IllegalArgumentException(message);
        }
        if(!directory.isDirectory())
        {
            String message = (new StringBuilder()).append(directory).append(" is not a directory").toString();
            throw new IllegalArgumentException(message);
        }
        File files[] = directory.listFiles();
        if(files == null)
            throw new IOException((new StringBuilder()).append("Failed to list contents of ").append(directory).toString());
        IOException exception = null;
        File arr$[] = files;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            File file = arr$[i$];
            try
            {
                forceDelete(file);
            }
            catch(IOException ioe)
            {
                exception = ioe;
            }
        }

        if(null != exception)
            throw exception;
        else
            return;
    }

    public static boolean waitFor(File file, int seconds)
    {
        int timeout = 0;
        int tick = 0;
        do
        {
            if(file.exists())
                break;
            if(tick++ >= 10)
            {
                tick = 0;
                if(timeout++ > seconds)
                    return false;
            }
            try
            {
                Thread.sleep(100L);
                continue;
            }
            catch(InterruptedException ignore)
            {
                continue;
            }
            catch(Exception ex) { }
            break;
        } while(true);
        return true;
    }

    public static String readFileToString(File file, Charset encoding)
        throws IOException
    {
        InputStream in = null;
        String s;
        in = openInputStream(file);
        s = IOUtils.toString(in, Charsets.toCharset(encoding));
        IOUtils.closeQuietly(in);
        return s;
        Exception exception;
        exception;
        IOUtils.closeQuietly(in);
        throw exception;
    }

    public static String readFileToString(File file, String encoding)
        throws IOException
    {
        return readFileToString(file, Charsets.toCharset(encoding));
    }

    public static String readFileToString(File file)
        throws IOException
    {
        return readFileToString(file, Charset.defaultCharset());
    }

    public static byte[] readFileToByteArray(File file)
        throws IOException
    {
        InputStream in = null;
        byte abyte0[];
        in = openInputStream(file);
        abyte0 = IOUtils.toByteArray(in, file.length());
        IOUtils.closeQuietly(in);
        return abyte0;
        Exception exception;
        exception;
        IOUtils.closeQuietly(in);
        throw exception;
    }

    public static List readLines(File file, Charset encoding)
        throws IOException
    {
        InputStream in = null;
        List list;
        in = openInputStream(file);
        list = IOUtils.readLines(in, Charsets.toCharset(encoding));
        IOUtils.closeQuietly(in);
        return list;
        Exception exception;
        exception;
        IOUtils.closeQuietly(in);
        throw exception;
    }

    public static List readLines(File file, String encoding)
        throws IOException
    {
        return readLines(file, Charsets.toCharset(encoding));
    }

    public static List readLines(File file)
        throws IOException
    {
        return readLines(file, Charset.defaultCharset());
    }

    public static LineIterator lineIterator(File file, String encoding)
        throws IOException
    {
        InputStream in = null;
        try
        {
            in = openInputStream(file);
            return IOUtils.lineIterator(in, encoding);
        }
        catch(IOException ex)
        {
            IOUtils.closeQuietly(in);
            throw ex;
        }
        catch(RuntimeException ex)
        {
            IOUtils.closeQuietly(in);
            throw ex;
        }
    }

    public static LineIterator lineIterator(File file)
        throws IOException
    {
        return lineIterator(file, null);
    }

    public static void writeStringToFile(File file, String data, Charset encoding)
        throws IOException
    {
        writeStringToFile(file, data, encoding, false);
    }

    public static void writeStringToFile(File file, String data, String encoding)
        throws IOException
    {
        writeStringToFile(file, data, encoding, false);
    }

    public static void writeStringToFile(File file, String data, Charset encoding, boolean append)
        throws IOException
    {
        OutputStream out = null;
        out = openOutputStream(file, append);
        IOUtils.write(data, out, encoding);
        out.close();
        IOUtils.closeQuietly(out);
        break MISSING_BLOCK_LABEL_40;
        Exception exception;
        exception;
        IOUtils.closeQuietly(out);
        throw exception;
    }

    public static void writeStringToFile(File file, String data, String encoding, boolean append)
        throws IOException
    {
        writeStringToFile(file, data, Charsets.toCharset(encoding), append);
    }

    public static void writeStringToFile(File file, String data)
        throws IOException
    {
        writeStringToFile(file, data, Charset.defaultCharset(), false);
    }

    public static void writeStringToFile(File file, String data, boolean append)
        throws IOException
    {
        writeStringToFile(file, data, Charset.defaultCharset(), append);
    }

    public static void write(File file, CharSequence data)
        throws IOException
    {
        write(file, data, Charset.defaultCharset(), false);
    }

    public static void write(File file, CharSequence data, boolean append)
        throws IOException
    {
        write(file, data, Charset.defaultCharset(), append);
    }

    public static void write(File file, CharSequence data, Charset encoding)
        throws IOException
    {
        write(file, data, encoding, false);
    }

    public static void write(File file, CharSequence data, String encoding)
        throws IOException
    {
        write(file, data, encoding, false);
    }

    public static void write(File file, CharSequence data, Charset encoding, boolean append)
        throws IOException
    {
        String str = data != null ? data.toString() : null;
        writeStringToFile(file, str, encoding, append);
    }

    public static void write(File file, CharSequence data, String encoding, boolean append)
        throws IOException
    {
        write(file, data, Charsets.toCharset(encoding), append);
    }

    public static void writeByteArrayToFile(File file, byte data[])
        throws IOException
    {
        writeByteArrayToFile(file, data, false);
    }

    public static void writeByteArrayToFile(File file, byte data[], boolean append)
        throws IOException
    {
        OutputStream out = null;
        out = openOutputStream(file, append);
        out.write(data);
        out.close();
        IOUtils.closeQuietly(out);
        break MISSING_BLOCK_LABEL_33;
        Exception exception;
        exception;
        IOUtils.closeQuietly(out);
        throw exception;
    }

    public static void writeLines(File file, String encoding, Collection lines)
        throws IOException
    {
        writeLines(file, encoding, lines, null, false);
    }

    public static void writeLines(File file, String encoding, Collection lines, boolean append)
        throws IOException
    {
        writeLines(file, encoding, lines, null, append);
    }

    public static void writeLines(File file, Collection lines)
        throws IOException
    {
        writeLines(file, null, lines, null, false);
    }

    public static void writeLines(File file, Collection lines, boolean append)
        throws IOException
    {
        writeLines(file, null, lines, null, append);
    }

    public static void writeLines(File file, String encoding, Collection lines, String lineEnding)
        throws IOException
    {
        writeLines(file, encoding, lines, lineEnding, false);
    }

    public static void writeLines(File file, String encoding, Collection lines, String lineEnding, boolean append)
        throws IOException
    {
        FileOutputStream out = null;
        out = openOutputStream(file, append);
        BufferedOutputStream buffer = new BufferedOutputStream(out);
        IOUtils.writeLines(lines, lineEnding, buffer, encoding);
        buffer.flush();
        out.close();
        IOUtils.closeQuietly(out);
        break MISSING_BLOCK_LABEL_58;
        Exception exception;
        exception;
        IOUtils.closeQuietly(out);
        throw exception;
    }

    public static void writeLines(File file, Collection lines, String lineEnding)
        throws IOException
    {
        writeLines(file, null, lines, lineEnding, false);
    }

    public static void writeLines(File file, Collection lines, String lineEnding, boolean append)
        throws IOException
    {
        writeLines(file, null, lines, lineEnding, append);
    }

    public static void forceDelete(File file)
        throws IOException
    {
        if(file.isDirectory())
        {
            deleteDirectory(file);
        } else
        {
            boolean filePresent = file.exists();
            if(!file.delete())
                if(!filePresent)
                {
                    throw new FileNotFoundException((new StringBuilder()).append("File does not exist: ").append(file).toString());
                } else
                {
                    String message = (new StringBuilder()).append("Unable to delete file: ").append(file).toString();
                    throw new IOException(message);
                }
        }
    }

    public static void forceDeleteOnExit(File file)
        throws IOException
    {
        if(file.isDirectory())
            deleteDirectoryOnExit(file);
        else
            file.deleteOnExit();
    }

    private static void deleteDirectoryOnExit(File directory)
        throws IOException
    {
        if(!directory.exists())
            return;
        directory.deleteOnExit();
        if(!isSymlink(directory))
            cleanDirectoryOnExit(directory);
    }

    private static void cleanDirectoryOnExit(File directory)
        throws IOException
    {
        if(!directory.exists())
        {
            String message = (new StringBuilder()).append(directory).append(" does not exist").toString();
            throw new IllegalArgumentException(message);
        }
        if(!directory.isDirectory())
        {
            String message = (new StringBuilder()).append(directory).append(" is not a directory").toString();
            throw new IllegalArgumentException(message);
        }
        File files[] = directory.listFiles();
        if(files == null)
            throw new IOException((new StringBuilder()).append("Failed to list contents of ").append(directory).toString());
        IOException exception = null;
        File arr$[] = files;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            File file = arr$[i$];
            try
            {
                forceDeleteOnExit(file);
            }
            catch(IOException ioe)
            {
                exception = ioe;
            }
        }

        if(null != exception)
            throw exception;
        else
            return;
    }

    public static void forceMkdir(File directory)
        throws IOException
    {
        if(directory.exists())
        {
            if(!directory.isDirectory())
            {
                String message = (new StringBuilder()).append("File ").append(directory).append(" exists and is ").append("not a directory. Unable to create directory.").toString();
                throw new IOException(message);
            }
        } else
        if(!directory.mkdirs() && !directory.isDirectory())
        {
            String message = (new StringBuilder()).append("Unable to create directory ").append(directory).toString();
            throw new IOException(message);
        }
    }

    public static long sizeOf(File file)
    {
        if(!file.exists())
        {
            String message = (new StringBuilder()).append(file).append(" does not exist").toString();
            throw new IllegalArgumentException(message);
        }
        if(file.isDirectory())
            return sizeOfDirectory(file);
        else
            return file.length();
    }

    public static BigInteger sizeOfAsBigInteger(File file)
    {
        if(!file.exists())
        {
            String message = (new StringBuilder()).append(file).append(" does not exist").toString();
            throw new IllegalArgumentException(message);
        }
        if(file.isDirectory())
            return sizeOfDirectoryAsBigInteger(file);
        else
            return BigInteger.valueOf(file.length());
    }

    public static long sizeOfDirectory(File directory)
    {
        checkDirectory(directory);
        File files[] = directory.listFiles();
        if(files == null)
            return 0L;
        long size = 0L;
        File arr$[] = files;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            File file = arr$[i$];
            try
            {
                if(isSymlink(file))
                    continue;
                size += sizeOf(file);
                if(size < 0L)
                    break;
            }
            catch(IOException ioe) { }
        }

        return size;
    }

    public static BigInteger sizeOfDirectoryAsBigInteger(File directory)
    {
        checkDirectory(directory);
        File files[] = directory.listFiles();
        if(files == null)
            return BigInteger.ZERO;
        BigInteger size = BigInteger.ZERO;
        File arr$[] = files;
        int len$ = arr$.length;
        for(int i$ = 0; i$ < len$; i$++)
        {
            File file = arr$[i$];
            try
            {
                if(!isSymlink(file))
                    size = size.add(BigInteger.valueOf(sizeOf(file)));
            }
            catch(IOException ioe) { }
        }

        return size;
    }

    private static void checkDirectory(File directory)
    {
        if(!directory.exists())
            throw new IllegalArgumentException((new StringBuilder()).append(directory).append(" does not exist").toString());
        if(!directory.isDirectory())
            throw new IllegalArgumentException((new StringBuilder()).append(directory).append(" is not a directory").toString());
        else
            return;
    }

    public static boolean isFileNewer(File file, File reference)
    {
        if(reference == null)
            throw new IllegalArgumentException("No specified reference file");
        if(!reference.exists())
            throw new IllegalArgumentException((new StringBuilder()).append("The reference file '").append(reference).append("' doesn't exist").toString());
        else
            return isFileNewer(file, reference.lastModified());
    }

    public static boolean isFileNewer(File file, Date date)
    {
        if(date == null)
            throw new IllegalArgumentException("No specified date");
        else
            return isFileNewer(file, date.getTime());
    }

    public static boolean isFileNewer(File file, long timeMillis)
    {
        if(file == null)
            throw new IllegalArgumentException("No specified file");
        if(!file.exists())
            return false;
        else
            return file.lastModified() > timeMillis;
    }

    public static boolean isFileOlder(File file, File reference)
    {
        if(reference == null)
            throw new IllegalArgumentException("No specified reference file");
        if(!reference.exists())
            throw new IllegalArgumentException((new StringBuilder()).append("The reference file '").append(reference).append("' doesn't exist").toString());
        else
            return isFileOlder(file, reference.lastModified());
    }

    public static boolean isFileOlder(File file, Date date)
    {
        if(date == null)
            throw new IllegalArgumentException("No specified date");
        else
            return isFileOlder(file, date.getTime());
    }

    public static boolean isFileOlder(File file, long timeMillis)
    {
        if(file == null)
            throw new IllegalArgumentException("No specified file");
        if(!file.exists())
            return false;
        else
            return file.lastModified() < timeMillis;
    }

    public static long checksumCRC32(File file)
        throws IOException
    {
        CRC32 crc = new CRC32();
        checksum(file, crc);
        return crc.getValue();
    }

    public static Checksum checksum(File file, Checksum checksum)
        throws IOException
    {
        InputStream in;
        if(file.isDirectory())
            throw new IllegalArgumentException("Checksums can't be computed on directories");
        in = null;
        in = new CheckedInputStream(new FileInputStream(file), checksum);
        IOUtils.copy(in, new NullOutputStream());
        IOUtils.closeQuietly(in);
        break MISSING_BLOCK_LABEL_62;
        Exception exception;
        exception;
        IOUtils.closeQuietly(in);
        throw exception;
        return checksum;
    }

    public static void moveDirectory(File srcDir, File destDir)
        throws IOException
    {
        if(srcDir == null)
            throw new NullPointerException("Source must not be null");
        if(destDir == null)
            throw new NullPointerException("Destination must not be null");
        if(!srcDir.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Source '").append(srcDir).append("' does not exist").toString());
        if(!srcDir.isDirectory())
            throw new IOException((new StringBuilder()).append("Source '").append(srcDir).append("' is not a directory").toString());
        if(destDir.exists())
            throw new FileExistsException((new StringBuilder()).append("Destination '").append(destDir).append("' already exists").toString());
        boolean rename = srcDir.renameTo(destDir);
        if(!rename)
        {
            if(destDir.getCanonicalPath().startsWith(srcDir.getCanonicalPath()))
                throw new IOException((new StringBuilder()).append("Cannot move directory: ").append(srcDir).append(" to a subdirectory of itself: ").append(destDir).toString());
            copyDirectory(srcDir, destDir);
            deleteDirectory(srcDir);
            if(srcDir.exists())
                throw new IOException((new StringBuilder()).append("Failed to delete original directory '").append(srcDir).append("' after copy to '").append(destDir).append("'").toString());
        }
    }

    public static void moveDirectoryToDirectory(File src, File destDir, boolean createDestDir)
        throws IOException
    {
        if(src == null)
            throw new NullPointerException("Source must not be null");
        if(destDir == null)
            throw new NullPointerException("Destination directory must not be null");
        if(!destDir.exists() && createDestDir)
            destDir.mkdirs();
        if(!destDir.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Destination directory '").append(destDir).append("' does not exist [createDestDir=").append(createDestDir).append("]").toString());
        if(!destDir.isDirectory())
        {
            throw new IOException((new StringBuilder()).append("Destination '").append(destDir).append("' is not a directory").toString());
        } else
        {
            moveDirectory(src, new File(destDir, src.getName()));
            return;
        }
    }

    public static void moveFile(File srcFile, File destFile)
        throws IOException
    {
        if(srcFile == null)
            throw new NullPointerException("Source must not be null");
        if(destFile == null)
            throw new NullPointerException("Destination must not be null");
        if(!srcFile.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Source '").append(srcFile).append("' does not exist").toString());
        if(srcFile.isDirectory())
            throw new IOException((new StringBuilder()).append("Source '").append(srcFile).append("' is a directory").toString());
        if(destFile.exists())
            throw new FileExistsException((new StringBuilder()).append("Destination '").append(destFile).append("' already exists").toString());
        if(destFile.isDirectory())
            throw new IOException((new StringBuilder()).append("Destination '").append(destFile).append("' is a directory").toString());
        boolean rename = srcFile.renameTo(destFile);
        if(!rename)
        {
            copyFile(srcFile, destFile);
            if(!srcFile.delete())
            {
                deleteQuietly(destFile);
                throw new IOException((new StringBuilder()).append("Failed to delete original file '").append(srcFile).append("' after copy to '").append(destFile).append("'").toString());
            }
        }
    }

    public static void moveFileToDirectory(File srcFile, File destDir, boolean createDestDir)
        throws IOException
    {
        if(srcFile == null)
            throw new NullPointerException("Source must not be null");
        if(destDir == null)
            throw new NullPointerException("Destination directory must not be null");
        if(!destDir.exists() && createDestDir)
            destDir.mkdirs();
        if(!destDir.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Destination directory '").append(destDir).append("' does not exist [createDestDir=").append(createDestDir).append("]").toString());
        if(!destDir.isDirectory())
        {
            throw new IOException((new StringBuilder()).append("Destination '").append(destDir).append("' is not a directory").toString());
        } else
        {
            moveFile(srcFile, new File(destDir, srcFile.getName()));
            return;
        }
    }

    public static void moveToDirectory(File src, File destDir, boolean createDestDir)
        throws IOException
    {
        if(src == null)
            throw new NullPointerException("Source must not be null");
        if(destDir == null)
            throw new NullPointerException("Destination must not be null");
        if(!src.exists())
            throw new FileNotFoundException((new StringBuilder()).append("Source '").append(src).append("' does not exist").toString());
        if(src.isDirectory())
            moveDirectoryToDirectory(src, destDir, createDestDir);
        else
            moveFileToDirectory(src, destDir, createDestDir);
    }

    public static boolean isSymlink(File file)
        throws IOException
    {
        if(file == null)
            throw new NullPointerException("File must not be null");
        if(FilenameUtils.isSystemWindows())
            return false;
        File fileInCanonicalDir = null;
        if(file.getParent() == null)
        {
            fileInCanonicalDir = file;
        } else
        {
            File canonicalDir = file.getParentFile().getCanonicalFile();
            fileInCanonicalDir = new File(canonicalDir, file.getName());
        }
        return !fileInCanonicalDir.getCanonicalFile().equals(fileInCanonicalDir.getAbsoluteFile());
    }

    public static final long ONE_KB = 1024L;
    public static final BigInteger ONE_KB_BI;
    public static final long ONE_MB = 0x100000L;
    public static final BigInteger ONE_MB_BI;
    private static final long FILE_COPY_BUFFER_SIZE = 0x1e00000L;
    public static final long ONE_GB = 0x40000000L;
    public static final BigInteger ONE_GB_BI;
    public static final long ONE_TB = 0x10000000000L;
    public static final BigInteger ONE_TB_BI;
    public static final long ONE_PB = 0x4000000000000L;
    public static final BigInteger ONE_PB_BI;
    public static final long ONE_EB = 0x1000000000000000L;
    public static final BigInteger ONE_EB_BI;
    public static final BigInteger ONE_ZB;
    public static final BigInteger ONE_YB;
    public static final File EMPTY_FILE_ARRAY[] = new File[0];
    private static final Charset UTF8 = Charset.forName("UTF-8");

    static 
    {
        ONE_KB_BI = BigInteger.valueOf(1024L);
        ONE_MB_BI = ONE_KB_BI.multiply(ONE_KB_BI);
        ONE_GB_BI = ONE_KB_BI.multiply(ONE_MB_BI);
        ONE_TB_BI = ONE_KB_BI.multiply(ONE_GB_BI);
        ONE_PB_BI = ONE_KB_BI.multiply(ONE_TB_BI);
        ONE_EB_BI = ONE_KB_BI.multiply(ONE_PB_BI);
        ONE_ZB = BigInteger.valueOf(1024L).multiply(BigInteger.valueOf(0x1000000000000000L));
        ONE_YB = ONE_KB_BI.multiply(ONE_ZB);
    }
}
