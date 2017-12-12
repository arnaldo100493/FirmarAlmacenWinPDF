// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPUtil.java

package co.org.bouncy.openpgp.operator;

import co.org.bouncy.bcpg.HashAlgorithmTags;
import co.org.bouncy.bcpg.S2K;
import co.org.bouncy.openpgp.PGPException;
import co.org.bouncy.util.Strings;
import java.io.IOException;
import java.io.OutputStream;

// Referenced classes of package co.org.bouncy.openpgp.operator:
//            PGPDigestCalculator, PGPDigestCalculatorProvider

class PGPUtil
    implements HashAlgorithmTags
{

    PGPUtil()
    {
    }

    static byte[] makeKeyFromPassPhrase(PGPDigestCalculator digestCalculator, int algorithm, S2K s2k, char passPhrase[])
        throws PGPException
    {
        String algName = null;
        int keySize = 0;
        switch(algorithm)
        {
        case 2: // '\002'
            keySize = 192;
            algName = "DES_EDE";
            break;

        case 1: // '\001'
            keySize = 128;
            algName = "IDEA";
            break;

        case 3: // '\003'
            keySize = 128;
            algName = "CAST5";
            break;

        case 4: // '\004'
            keySize = 128;
            algName = "Blowfish";
            break;

        case 5: // '\005'
            keySize = 128;
            algName = "SAFER";
            break;

        case 6: // '\006'
            keySize = 64;
            algName = "DES";
            break;

        case 7: // '\007'
            keySize = 128;
            algName = "AES";
            break;

        case 8: // '\b'
            keySize = 192;
            algName = "AES";
            break;

        case 9: // '\t'
            keySize = 256;
            algName = "AES";
            break;

        case 10: // '\n'
            keySize = 256;
            algName = "Twofish";
            break;

        default:
            throw new PGPException((new StringBuilder()).append("unknown symmetric algorithm: ").append(algorithm).toString());
        }
        byte pBytes[] = Strings.toUTF8ByteArray(passPhrase);
        byte keyBytes[] = new byte[(keySize + 7) / 8];
        int generatedBytes = 0;
        int loopCount = 0;
        if(s2k != null)
        {
            if(s2k.getHashAlgorithm() != digestCalculator.getAlgorithm())
                throw new PGPException("s2k/digestCalculator mismatch");
        } else
        if(digestCalculator.getAlgorithm() != 1)
            throw new PGPException("digestCalculator not for MD5");
        OutputStream dOut = digestCalculator.getOutputStream();
        try
        {
            while(generatedBytes < keyBytes.length) 
            {
                if(s2k != null)
                {
                    for(int i = 0; i != loopCount; i++)
                        dOut.write(0);

                    byte iv[] = s2k.getIV();
                    switch(s2k.getType())
                    {
                    case 0: // '\0'
                        dOut.write(pBytes);
                        break;

                    case 1: // '\001'
                        dOut.write(iv);
                        dOut.write(pBytes);
                        break;

                    case 3: // '\003'
                        long count = s2k.getIterationCount();
                        dOut.write(iv);
                        dOut.write(pBytes);
                        count -= iv.length + pBytes.length;
                        do
                        {
                            if(count <= 0L)
                                break;
                            if(count < (long)iv.length)
                            {
                                dOut.write(iv, 0, (int)count);
                                break;
                            }
                            dOut.write(iv);
                            count -= iv.length;
                            if(count < (long)pBytes.length)
                            {
                                dOut.write(pBytes, 0, (int)count);
                                count = 0L;
                            } else
                            {
                                dOut.write(pBytes);
                                count -= pBytes.length;
                            }
                        } while(true);
                        break;

                    case 2: // '\002'
                    default:
                        throw new PGPException((new StringBuilder()).append("unknown S2K type: ").append(s2k.getType()).toString());
                    }
                } else
                {
                    for(int i = 0; i != loopCount; i++)
                        dOut.write(0);

                    dOut.write(pBytes);
                }
                dOut.close();
                byte dig[] = digestCalculator.getDigest();
                if(dig.length > keyBytes.length - generatedBytes)
                    System.arraycopy(dig, 0, keyBytes, generatedBytes, keyBytes.length - generatedBytes);
                else
                    System.arraycopy(dig, 0, keyBytes, generatedBytes, dig.length);
                generatedBytes += dig.length;
                loopCount++;
            }
        }
        catch(IOException e)
        {
            throw new PGPException((new StringBuilder()).append("exception calculating digest: ").append(e.getMessage()).toString(), e);
        }
        for(int i = 0; i != pBytes.length; i++)
            pBytes[i] = 0;

        return keyBytes;
    }

    public static byte[] makeKeyFromPassPhrase(PGPDigestCalculatorProvider digCalcProvider, int algorithm, S2K s2k, char passPhrase[])
        throws PGPException
    {
        PGPDigestCalculator digestCalculator;
        if(s2k != null)
            digestCalculator = digCalcProvider.get(s2k.getHashAlgorithm());
        else
            digestCalculator = digCalcProvider.get(1);
        return makeKeyFromPassPhrase(digestCalculator, algorithm, s2k, passPhrase);
    }
}
