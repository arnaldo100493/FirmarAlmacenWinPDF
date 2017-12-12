// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   BcDefaultDigestProvider.java

package co.org.bouncy.operator.bc;

import co.org.bouncy.asn1.cryptopro.CryptoProObjectIdentifiers;
import co.org.bouncy.asn1.nist.NISTObjectIdentifiers;
import co.org.bouncy.asn1.oiw.OIWObjectIdentifiers;
import co.org.bouncy.asn1.pkcs.PKCSObjectIdentifiers;
import co.org.bouncy.asn1.teletrust.TeleTrusTObjectIdentifiers;
import co.org.bouncy.asn1.x509.AlgorithmIdentifier;
import co.org.bouncy.crypto.ExtendedDigest;
import co.org.bouncy.crypto.digests.*;
import co.org.bouncy.operator.OperatorCreationException;
import java.util.*;

// Referenced classes of package co.org.bouncy.operator.bc:
//            BcDigestProvider

public class BcDefaultDigestProvider
    implements BcDigestProvider
{

    private static Map createTable()
    {
        Map table = new HashMap();
        table.put(OIWObjectIdentifiers.idSHA1, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA1Digest();
            }

        }
);
        table.put(NISTObjectIdentifiers.id_sha224, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA224Digest();
            }

        }
);
        table.put(NISTObjectIdentifiers.id_sha256, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA256Digest();
            }

        }
);
        table.put(NISTObjectIdentifiers.id_sha384, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA384Digest();
            }

        }
);
        table.put(NISTObjectIdentifiers.id_sha512, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new SHA512Digest();
            }

        }
);
        table.put(PKCSObjectIdentifiers.md5, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new MD5Digest();
            }

        }
);
        table.put(PKCSObjectIdentifiers.md4, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new MD4Digest();
            }

        }
);
        table.put(PKCSObjectIdentifiers.md2, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new MD2Digest();
            }

        }
);
        table.put(CryptoProObjectIdentifiers.gostR3411, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new GOST3411Digest();
            }

        }
);
        table.put(TeleTrusTObjectIdentifiers.ripemd128, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new RIPEMD128Digest();
            }

        }
);
        table.put(TeleTrusTObjectIdentifiers.ripemd160, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new RIPEMD160Digest();
            }

        }
);
        table.put(TeleTrusTObjectIdentifiers.ripemd256, new BcDigestProvider() {

            public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
            {
                return new RIPEMD256Digest();
            }

        }
);
        return Collections.unmodifiableMap(table);
    }

    private BcDefaultDigestProvider()
    {
    }

    public ExtendedDigest get(AlgorithmIdentifier digestAlgorithmIdentifier)
        throws OperatorCreationException
    {
        BcDigestProvider extProv = (BcDigestProvider)lookup.get(digestAlgorithmIdentifier.getAlgorithm());
        if(extProv == null)
            throw new OperatorCreationException("cannot recognise digest");
        else
            return extProv.get(digestAlgorithmIdentifier);
    }

    private static final Map lookup = createTable();
    public static final BcDigestProvider INSTANCE = new BcDefaultDigestProvider();

}
