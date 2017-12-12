// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPUtil.java

package co.org.bouncy.openpgp;

import co.org.bouncy.asn1.*;
import co.org.bouncy.bcpg.*;
import co.org.bouncy.util.encoders.Base64;
import java.io.*;
import java.security.*;
import java.util.Date;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPException, PGPLiteralDataGenerator

public class PGPUtil
    implements HashAlgorithmTags
{
    static class BufferedInputStreamExt extends BufferedInputStream
    {

        public synchronized int available()
            throws IOException
        {
            int result = super.available();
            if(result < 0)
                result = 0x7fffffff;
            return result;
        }

        BufferedInputStreamExt(InputStream input)
        {
            super(input);
        }
    }


    public PGPUtil()
    {
    }

    public static String getDefaultProvider()
    {
        return defProvider;
    }

    public static void setDefaultProvider(String provider)
    {
        defProvider = provider;
    }

    static MPInteger[] dsaSigToMpi(byte encoding[])
        throws PGPException
    {
        ASN1InputStream aIn = new ASN1InputStream(encoding);
        DERInteger i1;
        DERInteger i2;
        try
        {
            ASN1Sequence s = (ASN1Sequence)aIn.readObject();
            i1 = (DERInteger)s.getObjectAt(0);
            i2 = (DERInteger)s.getObjectAt(1);
        }
        catch(IOException e)
        {
            throw new PGPException("exception encoding signature", e);
        }
        MPInteger values[] = new MPInteger[2];
        values[0] = new MPInteger(i1.getValue());
        values[1] = new MPInteger(i2.getValue());
        return values;
    }

    static String getDigestName(int hashAlgorithm)
        throws PGPException
    {
        switch(hashAlgorithm)
        {
        case 2: // '\002'
            return "SHA1";

        case 5: // '\005'
            return "MD2";

        case 1: // '\001'
            return "MD5";

        case 3: // '\003'
            return "RIPEMD160";

        case 8: // '\b'
            return "SHA256";

        case 9: // '\t'
            return "SHA384";

        case 10: // '\n'
            return "SHA512";

        case 11: // '\013'
            return "SHA224";

        case 4: // '\004'
        case 6: // '\006'
        case 7: // '\007'
        default:
            throw new PGPException((new StringBuilder()).append("unknown hash algorithm tag in getDigestName: ").append(hashAlgorithm).toString());
        }
    }

    static String getSignatureName(int keyAlgorithm, int hashAlgorithm)
        throws PGPException
    {
        String encAlg;
        switch(keyAlgorithm)
        {
        case 1: // '\001'
        case 3: // '\003'
            encAlg = "RSA";
            break;

        case 17: // '\021'
            encAlg = "DSA";
            break;

        case 16: // '\020'
        case 20: // '\024'
            encAlg = "ElGamal";
            break;

        default:
            throw new PGPException((new StringBuilder()).append("unknown algorithm tag in signature:").append(keyAlgorithm).toString());
        }
        return (new StringBuilder()).append(getDigestName(hashAlgorithm)).append("with").append(encAlg).toString();
    }

    public static byte[] makeRandomKey(int algorithm, SecureRandom random)
        throws PGPException
    {
        int keySize = 0;
        switch(algorithm)
        {
        case 2: // '\002'
            keySize = 192;
            break;

        case 1: // '\001'
            keySize = 128;
            break;

        case 3: // '\003'
            keySize = 128;
            break;

        case 4: // '\004'
            keySize = 128;
            break;

        case 5: // '\005'
            keySize = 128;
            break;

        case 6: // '\006'
            keySize = 64;
            break;

        case 7: // '\007'
            keySize = 128;
            break;

        case 8: // '\b'
            keySize = 192;
            break;

        case 9: // '\t'
            keySize = 256;
            break;

        case 10: // '\n'
            keySize = 256;
            break;

        default:
            throw new PGPException((new StringBuilder()).append("unknown symmetric algorithm: ").append(algorithm).toString());
        }
        byte keyBytes[] = new byte[(keySize + 7) / 8];
        random.nextBytes(keyBytes);
        return keyBytes;
    }

    public static void writeFileToLiteralData(OutputStream out, char fileType, File file)
        throws IOException
    {
        PGPLiteralDataGenerator lData = new PGPLiteralDataGenerator();
        OutputStream pOut = lData.open(out, fileType, file.getName(), file.length(), new Date(file.lastModified()));
        pipeFileContents(file, pOut, 4096);
    }

    public static void writeFileToLiteralData(OutputStream out, char fileType, File file, byte buffer[])
        throws IOException
    {
        PGPLiteralDataGenerator lData = new PGPLiteralDataGenerator();
        OutputStream pOut = lData.open(out, fileType, file.getName(), new Date(file.lastModified()), buffer);
        pipeFileContents(file, pOut, buffer.length);
    }

    private static void pipeFileContents(File file, OutputStream pOut, int bufSize)
        throws IOException
    {
        FileInputStream in = new FileInputStream(file);
        byte buf[] = new byte[bufSize];
        int len;
        while((len = in.read(buf)) > 0) 
            pOut.write(buf, 0, len);
        pOut.close();
        in.close();
    }

    private static boolean isPossiblyBase64(int ch)
    {
        return ch >= 65 && ch <= 90 || ch >= 97 && ch <= 122 || ch >= 48 && ch <= 57 || ch == 43 || ch == 47 || ch == 13 || ch == 10;
    }

    public static InputStream getDecoderStream(InputStream in)
        throws IOException
    {
        if(!in.markSupported())
            in = new BufferedInputStreamExt(in);
        in.mark(60);
        int ch = in.read();
        if((ch & 0x80) != 0)
        {
            in.reset();
            return in;
        }
        if(!isPossiblyBase64(ch))
        {
            in.reset();
            return new ArmoredInputStream(in);
        }
        byte buf[] = new byte[60];
        int count = 1;
        int index = 1;
        buf[0] = (byte)ch;
        for(; count != 60 && (ch = in.read()) >= 0; count++)
        {
            if(!isPossiblyBase64(ch))
            {
                in.reset();
                return new ArmoredInputStream(in);
            }
            if(ch != 10 && ch != 13)
                buf[index++] = (byte)ch;
        }

        in.reset();
        if(count < 4)
            return new ArmoredInputStream(in);
        byte firstBlock[] = new byte[8];
        System.arraycopy(buf, 0, firstBlock, 0, firstBlock.length);
        byte decoded[] = Base64.decode(firstBlock);
        if((decoded[0] & 0x80) != 0)
            return new ArmoredInputStream(in, false);
        else
            return new ArmoredInputStream(in);
    }

    static Provider getProvider(String providerName)
        throws NoSuchProviderException
    {
        Provider prov = Security.getProvider(providerName);
        if(prov == null)
            throw new NoSuchProviderException((new StringBuilder()).append("provider ").append(providerName).append(" not found.").toString());
        else
            return prov;
    }

    private static String defProvider = "BC";
    private static final int READ_AHEAD = 60;

}
