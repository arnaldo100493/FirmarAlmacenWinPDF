// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PGPPublicKeyRingCollection.java

package co.org.bouncy.openpgp;

import co.org.bouncy.bcpg.BCPGOutputStream;
import co.org.bouncy.util.Strings;
import java.io.*;
import java.util.*;

// Referenced classes of package co.org.bouncy.openpgp:
//            PGPObjectFactory, PGPPublicKeyRing, PGPException, PGPPublicKey

public class PGPPublicKeyRingCollection
{

    private PGPPublicKeyRingCollection(Map pubRings, List order)
    {
        this.pubRings = new HashMap();
        this.order = new ArrayList();
        this.pubRings = pubRings;
        this.order = order;
    }

    public PGPPublicKeyRingCollection(byte encoding[])
        throws IOException, PGPException
    {
        this(((InputStream) (new ByteArrayInputStream(encoding))));
    }

    public PGPPublicKeyRingCollection(InputStream in)
        throws IOException, PGPException
    {
        pubRings = new HashMap();
        order = new ArrayList();
        PGPObjectFactory pgpFact = new PGPObjectFactory(in);
        Object obj;
        while((obj = pgpFact.nextObject()) != null) 
        {
            if(!(obj instanceof PGPPublicKeyRing))
                throw new PGPException((new StringBuilder()).append(obj.getClass().getName()).append(" found where PGPPublicKeyRing expected").toString());
            PGPPublicKeyRing pgpPub = (PGPPublicKeyRing)obj;
            Long key = new Long(pgpPub.getPublicKey().getKeyID());
            pubRings.put(key, pgpPub);
            order.add(key);
        }
    }

    public PGPPublicKeyRingCollection(Collection collection)
        throws IOException, PGPException
    {
        pubRings = new HashMap();
        order = new ArrayList();
        Long key;
        for(Iterator it = collection.iterator(); it.hasNext(); order.add(key))
        {
            PGPPublicKeyRing pgpPub = (PGPPublicKeyRing)it.next();
            key = new Long(pgpPub.getPublicKey().getKeyID());
            pubRings.put(key, pgpPub);
        }

    }

    public int size()
    {
        return order.size();
    }

    public Iterator getKeyRings()
    {
        return pubRings.values().iterator();
    }

    public Iterator getKeyRings(String userID)
        throws PGPException
    {
        return getKeyRings(userID, false, false);
    }

    public Iterator getKeyRings(String userID, boolean matchPartial)
        throws PGPException
    {
        return getKeyRings(userID, matchPartial, false);
    }

    public Iterator getKeyRings(String userID, boolean matchPartial, boolean ignoreCase)
        throws PGPException
    {
        Iterator it = getKeyRings();
        List rings = new ArrayList();
        if(ignoreCase)
            userID = Strings.toLowerCase(userID);
        while(it.hasNext()) 
        {
            PGPPublicKeyRing pubRing = (PGPPublicKeyRing)it.next();
            Iterator uIt = pubRing.getPublicKey().getUserIDs();
            while(uIt.hasNext()) 
            {
                String next = (String)uIt.next();
                if(ignoreCase)
                    next = Strings.toLowerCase(next);
                if(matchPartial)
                {
                    if(next.indexOf(userID) > -1)
                        rings.add(pubRing);
                } else
                if(next.equals(userID))
                    rings.add(pubRing);
            }
        }
        return rings.iterator();
    }

    public PGPPublicKey getPublicKey(long keyID)
        throws PGPException
    {
        for(Iterator it = getKeyRings(); it.hasNext();)
        {
            PGPPublicKeyRing pubRing = (PGPPublicKeyRing)it.next();
            PGPPublicKey pub = pubRing.getPublicKey(keyID);
            if(pub != null)
                return pub;
        }

        return null;
    }

    public PGPPublicKeyRing getPublicKeyRing(long keyID)
        throws PGPException
    {
        Long id = new Long(keyID);
        if(pubRings.containsKey(id))
            return (PGPPublicKeyRing)pubRings.get(id);
        for(Iterator it = getKeyRings(); it.hasNext();)
        {
            PGPPublicKeyRing pubRing = (PGPPublicKeyRing)it.next();
            PGPPublicKey pub = pubRing.getPublicKey(keyID);
            if(pub != null)
                return pubRing;
        }

        return null;
    }

    public boolean contains(long keyID)
        throws PGPException
    {
        return getPublicKey(keyID) != null;
    }

    public byte[] getEncoded()
        throws IOException
    {
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        encode(bOut);
        return bOut.toByteArray();
    }

    public void encode(OutputStream outStream)
        throws IOException
    {
        BCPGOutputStream out;
        if(outStream instanceof BCPGOutputStream)
            out = (BCPGOutputStream)outStream;
        else
            out = new BCPGOutputStream(outStream);
        PGPPublicKeyRing sr;
        for(Iterator it = order.iterator(); it.hasNext(); sr.encode(out))
            sr = (PGPPublicKeyRing)pubRings.get(it.next());

    }

    public static PGPPublicKeyRingCollection addPublicKeyRing(PGPPublicKeyRingCollection ringCollection, PGPPublicKeyRing publicKeyRing)
    {
        Long key = new Long(publicKeyRing.getPublicKey().getKeyID());
        if(ringCollection.pubRings.containsKey(key))
        {
            throw new IllegalArgumentException("Collection already contains a key with a keyID for the passed in ring.");
        } else
        {
            Map newPubRings = new HashMap(ringCollection.pubRings);
            List newOrder = new ArrayList(ringCollection.order);
            newPubRings.put(key, publicKeyRing);
            newOrder.add(key);
            return new PGPPublicKeyRingCollection(newPubRings, newOrder);
        }
    }

    public static PGPPublicKeyRingCollection removePublicKeyRing(PGPPublicKeyRingCollection ringCollection, PGPPublicKeyRing publicKeyRing)
    {
        Long key = new Long(publicKeyRing.getPublicKey().getKeyID());
        if(!ringCollection.pubRings.containsKey(key))
            throw new IllegalArgumentException("Collection does not contain a key with a keyID for the passed in ring.");
        Map newPubRings = new HashMap(ringCollection.pubRings);
        List newOrder = new ArrayList(ringCollection.order);
        newPubRings.remove(key);
        int i = 0;
        do
        {
            if(i >= newOrder.size())
                break;
            Long r = (Long)newOrder.get(i);
            if(r.longValue() == key.longValue())
            {
                newOrder.remove(i);
                break;
            }
            i++;
        } while(true);
        return new PGPPublicKeyRingCollection(newPubRings, newOrder);
    }

    private Map pubRings;
    private List order;
}
