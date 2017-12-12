// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   PdfEncryption.java

package co.com.pdf.text.pdf;

import co.com.pdf.text.DocWriter;
import co.com.pdf.text.ExceptionConverter;
import co.com.pdf.text.error_messages.MessageLocalization;
import co.com.pdf.text.exceptions.BadPasswordException;
import co.com.pdf.text.pdf.crypto.AESCipherCBCnoPad;
import co.com.pdf.text.pdf.crypto.ARCFOUREncryption;
import co.com.pdf.text.pdf.crypto.IVGenerator;
import java.io.*;
import java.security.MessageDigest;
import java.security.cert.Certificate;

// Referenced classes of package co.com.pdf.text.pdf:
//            PdfPublicKeySecurityHandler, ByteBuffer, PdfLiteral, PdfDictionary, 
//            PdfNumber, OutputStreamEncryption, StandardDecryption, PdfPublicKeyRecipient, 
//            PdfArray, PdfName, PdfObject, PdfBoolean, 
//            PdfContentByte

public class PdfEncryption
{

    public PdfEncryption()
    {
        mkey = new byte[0];
        extra = new byte[5];
        ownerKey = new byte[32];
        userKey = new byte[32];
        publicKeyHandler = null;
        arcfour = new ARCFOUREncryption();
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        publicKeyHandler = new PdfPublicKeySecurityHandler();
    }

    public PdfEncryption(PdfEncryption enc)
    {
        this();
        if(enc.key != null)
            key = (byte[])(byte[])enc.key.clone();
        keySize = enc.keySize;
        mkey = (byte[])(byte[])enc.mkey.clone();
        ownerKey = (byte[])(byte[])enc.ownerKey.clone();
        userKey = (byte[])(byte[])enc.userKey.clone();
        permissions = enc.permissions;
        if(enc.documentID != null)
            documentID = (byte[])(byte[])enc.documentID.clone();
        revision = enc.revision;
        keyLength = enc.keyLength;
        encryptMetadata = enc.encryptMetadata;
        embeddedFilesOnly = enc.embeddedFilesOnly;
        publicKeyHandler = enc.publicKeyHandler;
    }

    public void setCryptoMode(int mode, int kl)
    {
        cryptoMode = mode;
        encryptMetadata = (mode & 8) != 8;
        embeddedFilesOnly = (mode & 0x18) == 24;
        mode &= 7;
        switch(mode)
        {
        case 0: // '\0'
            encryptMetadata = true;
            embeddedFilesOnly = false;
            keyLength = 40;
            revision = 2;
            break;

        case 1: // '\001'
            embeddedFilesOnly = false;
            if(kl > 0)
                keyLength = kl;
            else
                keyLength = 128;
            revision = 3;
            break;

        case 2: // '\002'
            keyLength = 128;
            revision = 4;
            break;

        case 3: // '\003'
            keyLength = 256;
            keySize = 32;
            revision = 5;
            break;

        default:
            throw new IllegalArgumentException(MessageLocalization.getComposedMessage("no.valid.encryption.mode", new Object[0]));
        }
    }

    public int getCryptoMode()
    {
        return cryptoMode;
    }

    public boolean isMetadataEncrypted()
    {
        return encryptMetadata;
    }

    public int getPermissions()
    {
        return permissions;
    }

    public boolean isEmbeddedFilesOnly()
    {
        return embeddedFilesOnly;
    }

    private byte[] padPassword(byte userPassword[])
    {
        byte userPad[] = new byte[32];
        if(userPassword == null)
        {
            System.arraycopy(pad, 0, userPad, 0, 32);
        } else
        {
            System.arraycopy(userPassword, 0, userPad, 0, Math.min(userPassword.length, 32));
            if(userPassword.length < 32)
                System.arraycopy(pad, 0, userPad, userPassword.length, 32 - userPassword.length);
        }
        return userPad;
    }

    private byte[] computeOwnerKey(byte userPad[], byte ownerPad[])
    {
        byte ownerKey[] = new byte[32];
        byte digest[] = md5.digest(ownerPad);
        if(revision == 3 || revision == 4)
        {
            byte mkey[] = new byte[keyLength / 8];
            for(int k = 0; k < 50; k++)
            {
                md5.update(digest, 0, mkey.length);
                System.arraycopy(md5.digest(), 0, digest, 0, mkey.length);
            }

            System.arraycopy(userPad, 0, ownerKey, 0, 32);
            for(int i = 0; i < 20; i++)
            {
                for(int j = 0; j < mkey.length; j++)
                    mkey[j] = (byte)(digest[j] ^ i);

                arcfour.prepareARCFOURKey(mkey);
                arcfour.encryptARCFOUR(ownerKey);
            }

        } else
        {
            arcfour.prepareARCFOURKey(digest, 0, 5);
            arcfour.encryptARCFOUR(userPad, ownerKey);
        }
        return ownerKey;
    }

    private void setupGlobalEncryptionKey(byte documentID[], byte userPad[], byte ownerKey[], int permissions)
    {
        this.documentID = documentID;
        this.ownerKey = ownerKey;
        this.permissions = permissions;
        mkey = new byte[keyLength / 8];
        md5.reset();
        md5.update(userPad);
        md5.update(ownerKey);
        byte ext[] = new byte[4];
        ext[0] = (byte)permissions;
        ext[1] = (byte)(permissions >> 8);
        ext[2] = (byte)(permissions >> 16);
        ext[3] = (byte)(permissions >> 24);
        md5.update(ext, 0, 4);
        if(documentID != null)
            md5.update(documentID);
        if(!encryptMetadata)
            md5.update(metadataPad);
        byte digest[] = new byte[mkey.length];
        System.arraycopy(md5.digest(), 0, digest, 0, mkey.length);
        if(revision == 3 || revision == 4)
        {
            for(int k = 0; k < 50; k++)
                System.arraycopy(md5.digest(digest), 0, digest, 0, mkey.length);

        }
        System.arraycopy(digest, 0, mkey, 0, mkey.length);
    }

    private void setupUserKey()
    {
        if(revision == 3 || revision == 4)
        {
            md5.update(pad);
            byte digest[] = md5.digest(documentID);
            System.arraycopy(digest, 0, userKey, 0, 16);
            for(int k = 16; k < 32; k++)
                userKey[k] = 0;

            for(int i = 0; i < 20; i++)
            {
                for(int j = 0; j < mkey.length; j++)
                    digest[j] = (byte)(mkey[j] ^ i);

                arcfour.prepareARCFOURKey(digest, 0, mkey.length);
                arcfour.encryptARCFOUR(userKey, 0, 16);
            }

        } else
        {
            arcfour.prepareARCFOURKey(mkey);
            arcfour.encryptARCFOUR(pad, userKey);
        }
    }

    public void setupAllKeys(byte userPassword[], byte ownerPassword[], int permissions)
    {
        if(ownerPassword == null || ownerPassword.length == 0)
            ownerPassword = md5.digest(createDocumentId());
        permissions |= revision != 3 && revision != 4 && revision != 5 ? -64 : 0xfffff0c0;
        permissions &= -4;
        this.permissions = permissions;
        if(revision == 5)
        {
            try
            {
                if(userPassword == null)
                    userPassword = new byte[0];
                documentID = createDocumentId();
                byte uvs[] = IVGenerator.getIV(8);
                byte uks[] = IVGenerator.getIV(8);
                key = IVGenerator.getIV(32);
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                md.update(userPassword, 0, Math.min(userPassword.length, 127));
                md.update(uvs);
                userKey = new byte[48];
                md.digest(userKey, 0, 32);
                System.arraycopy(uvs, 0, userKey, 32, 8);
                System.arraycopy(uks, 0, userKey, 40, 8);
                md.update(userPassword, 0, Math.min(userPassword.length, 127));
                md.update(uks);
                AESCipherCBCnoPad ac = new AESCipherCBCnoPad(true, md.digest());
                ueKey = ac.processBlock(key, 0, key.length);
                byte ovs[] = IVGenerator.getIV(8);
                byte oks[] = IVGenerator.getIV(8);
                md.update(ownerPassword, 0, Math.min(ownerPassword.length, 127));
                md.update(ovs);
                md.update(userKey);
                ownerKey = new byte[48];
                md.digest(ownerKey, 0, 32);
                System.arraycopy(ovs, 0, ownerKey, 32, 8);
                System.arraycopy(oks, 0, ownerKey, 40, 8);
                md.update(ownerPassword, 0, Math.min(ownerPassword.length, 127));
                md.update(oks);
                md.update(userKey);
                ac = new AESCipherCBCnoPad(true, md.digest());
                oeKey = ac.processBlock(key, 0, key.length);
                byte permsp[] = IVGenerator.getIV(16);
                permsp[0] = (byte)permissions;
                permsp[1] = (byte)(permissions >> 8);
                permsp[2] = (byte)(permissions >> 16);
                permsp[3] = (byte)(permissions >> 24);
                permsp[4] = -1;
                permsp[5] = -1;
                permsp[6] = -1;
                permsp[7] = -1;
                permsp[8] = ((byte)(encryptMetadata ? 84 : 70));
                permsp[9] = 97;
                permsp[10] = 100;
                permsp[11] = 98;
                ac = new AESCipherCBCnoPad(true, key);
                perms = ac.processBlock(permsp, 0, permsp.length);
            }
            catch(Exception ex)
            {
                throw new ExceptionConverter(ex);
            }
        } else
        {
            byte userPad[] = padPassword(userPassword);
            byte ownerPad[] = padPassword(ownerPassword);
            ownerKey = computeOwnerKey(userPad, ownerPad);
            documentID = createDocumentId();
            setupByUserPad(documentID, userPad, ownerKey, permissions);
        }
    }

    public boolean readKey(PdfDictionary enc, byte password[])
        throws BadPasswordException
    {
        try
        {
            if(password == null)
                password = new byte[0];
            byte oValue[] = DocWriter.getISOBytes(enc.get(PdfName.O).toString());
            byte uValue[] = DocWriter.getISOBytes(enc.get(PdfName.U).toString());
            byte oeValue[] = DocWriter.getISOBytes(enc.get(PdfName.OE).toString());
            byte ueValue[] = DocWriter.getISOBytes(enc.get(PdfName.UE).toString());
            byte perms[] = DocWriter.getISOBytes(enc.get(PdfName.PERMS).toString());
            boolean isUserPass = false;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password, 0, Math.min(password.length, 127));
            md.update(oValue, 32, 8);
            md.update(uValue, 0, 48);
            byte hash[] = md.digest();
            boolean isOwnerPass = compareArray(hash, oValue, 32);
            AESCipherCBCnoPad ac;
            if(isOwnerPass)
            {
                md.update(password, 0, Math.min(password.length, 127));
                md.update(oValue, 40, 8);
                md.update(uValue, 0, 48);
                hash = md.digest();
                ac = new AESCipherCBCnoPad(false, hash);
                key = ac.processBlock(oeValue, 0, oeValue.length);
            } else
            {
                md.update(password, 0, Math.min(password.length, 127));
                md.update(uValue, 32, 8);
                hash = md.digest();
                isUserPass = compareArray(hash, uValue, 32);
                if(!isUserPass)
                    throw new BadPasswordException(MessageLocalization.getComposedMessage("bad.user.password", new Object[0]));
                md.update(password, 0, Math.min(password.length, 127));
                md.update(uValue, 40, 8);
                hash = md.digest();
                ac = new AESCipherCBCnoPad(false, hash);
                key = ac.processBlock(ueValue, 0, ueValue.length);
            }
            ac = new AESCipherCBCnoPad(false, key);
            byte decPerms[] = ac.processBlock(perms, 0, perms.length);
            if(decPerms[9] != 97 || decPerms[10] != 100 || decPerms[11] != 98)
            {
                throw new BadPasswordException(MessageLocalization.getComposedMessage("bad.user.password", new Object[0]));
            } else
            {
                permissions = decPerms[0] & 0xff | (decPerms[1] & 0xff) << 8 | (decPerms[2] & 0xff) << 16 | (decPerms[2] & 0xff) << 24;
                encryptMetadata = decPerms[8] == 84;
                return isOwnerPass;
            }
        }
        catch(BadPasswordException ex)
        {
            throw ex;
        }
        catch(Exception ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    private static boolean compareArray(byte a[], byte b[], int len)
    {
        for(int k = 0; k < len; k++)
            if(a[k] != b[k])
                return false;

        return true;
    }

    public static byte[] createDocumentId()
    {
        MessageDigest md5;
        try
        {
            md5 = MessageDigest.getInstance("MD5");
        }
        catch(Exception e)
        {
            throw new ExceptionConverter(e);
        }
        long time = System.currentTimeMillis();
        long mem = Runtime.getRuntime().freeMemory();
        String s = (new StringBuilder()).append(time).append("+").append(mem).append("+").append(seq++).toString();
        return md5.digest(s.getBytes());
    }

    public void setupByUserPassword(byte documentID[], byte userPassword[], byte ownerKey[], int permissions)
    {
        setupByUserPad(documentID, padPassword(userPassword), ownerKey, permissions);
    }

    private void setupByUserPad(byte documentID[], byte userPad[], byte ownerKey[], int permissions)
    {
        setupGlobalEncryptionKey(documentID, userPad, ownerKey, permissions);
        setupUserKey();
    }

    public void setupByOwnerPassword(byte documentID[], byte ownerPassword[], byte userKey[], byte ownerKey[], int permissions)
    {
        setupByOwnerPad(documentID, padPassword(ownerPassword), userKey, ownerKey, permissions);
    }

    private void setupByOwnerPad(byte documentID[], byte ownerPad[], byte userKey[], byte ownerKey[], int permissions)
    {
        byte userPad[] = computeOwnerKey(ownerKey, ownerPad);
        setupGlobalEncryptionKey(documentID, userPad, ownerKey, permissions);
        setupUserKey();
    }

    public void setKey(byte key[])
    {
        this.key = key;
    }

    public void setupByEncryptionKey(byte key[], int keylength)
    {
        mkey = new byte[keylength / 8];
        System.arraycopy(key, 0, mkey, 0, mkey.length);
    }

    public void setHashKey(int number, int generation)
    {
        if(revision == 5)
            return;
        md5.reset();
        extra[0] = (byte)number;
        extra[1] = (byte)(number >> 8);
        extra[2] = (byte)(number >> 16);
        extra[3] = (byte)generation;
        extra[4] = (byte)(generation >> 8);
        md5.update(mkey);
        md5.update(extra);
        if(revision == 4)
            md5.update(salt);
        key = md5.digest();
        keySize = mkey.length + 5;
        if(keySize > 16)
            keySize = 16;
    }

    public static PdfObject createInfoId(byte id[])
        throws IOException
    {
        ByteBuffer buf = new ByteBuffer(90);
        buf.append('[').append('<');
        if(id.length != 16)
            id = createDocumentId();
        for(int k = 0; k < 16; k++)
            buf.appendHex(id[k]);

        buf.append('>').append('<');
        id = createDocumentId();
        for(int k = 0; k < 16; k++)
            buf.appendHex(id[k]);

        buf.append('>').append(']');
        buf.close();
        return new PdfLiteral(buf.toByteArray());
    }

    public PdfDictionary getEncryptionDictionary()
    {
        PdfDictionary dic = new PdfDictionary();
        if(publicKeyHandler.getRecipientsSize() > 0)
        {
            PdfArray recipients = null;
            dic.put(PdfName.FILTER, PdfName.PUBSEC);
            dic.put(PdfName.R, new PdfNumber(revision));
            try
            {
                recipients = publicKeyHandler.getEncodedRecipients();
            }
            catch(Exception f)
            {
                throw new ExceptionConverter(f);
            }
            if(revision == 2)
            {
                dic.put(PdfName.V, new PdfNumber(1));
                dic.put(PdfName.SUBFILTER, PdfName.ADBE_PKCS7_S4);
                dic.put(PdfName.RECIPIENTS, recipients);
            } else
            if(revision == 3 && encryptMetadata)
            {
                dic.put(PdfName.V, new PdfNumber(2));
                dic.put(PdfName.LENGTH, new PdfNumber(128));
                dic.put(PdfName.SUBFILTER, PdfName.ADBE_PKCS7_S4);
                dic.put(PdfName.RECIPIENTS, recipients);
            } else
            {
                if(revision == 5)
                {
                    dic.put(PdfName.R, new PdfNumber(5));
                    dic.put(PdfName.V, new PdfNumber(5));
                } else
                {
                    dic.put(PdfName.R, new PdfNumber(4));
                    dic.put(PdfName.V, new PdfNumber(4));
                }
                dic.put(PdfName.SUBFILTER, PdfName.ADBE_PKCS7_S5);
                PdfDictionary stdcf = new PdfDictionary();
                stdcf.put(PdfName.RECIPIENTS, recipients);
                if(!encryptMetadata)
                    stdcf.put(PdfName.ENCRYPTMETADATA, PdfBoolean.PDFFALSE);
                if(revision == 4)
                {
                    stdcf.put(PdfName.CFM, PdfName.AESV2);
                    stdcf.put(PdfName.LENGTH, new PdfNumber(128));
                } else
                if(revision == 5)
                {
                    stdcf.put(PdfName.CFM, PdfName.AESV3);
                    stdcf.put(PdfName.LENGTH, new PdfNumber(256));
                } else
                {
                    stdcf.put(PdfName.CFM, PdfName.V2);
                }
                PdfDictionary cf = new PdfDictionary();
                cf.put(PdfName.DEFAULTCRYPTFILTER, stdcf);
                dic.put(PdfName.CF, cf);
                if(embeddedFilesOnly)
                {
                    dic.put(PdfName.EFF, PdfName.DEFAULTCRYPTFILTER);
                    dic.put(PdfName.STRF, PdfName.IDENTITY);
                    dic.put(PdfName.STMF, PdfName.IDENTITY);
                } else
                {
                    dic.put(PdfName.STRF, PdfName.DEFAULTCRYPTFILTER);
                    dic.put(PdfName.STMF, PdfName.DEFAULTCRYPTFILTER);
                }
            }
            MessageDigest md = null;
            byte encodedRecipient[] = null;
            try
            {
                if(revision == 5)
                    md = MessageDigest.getInstance("SHA-256");
                else
                    md = MessageDigest.getInstance("SHA-1");
                md.update(publicKeyHandler.getSeed());
                for(int i = 0; i < publicKeyHandler.getRecipientsSize(); i++)
                {
                    encodedRecipient = publicKeyHandler.getEncodedRecipient(i);
                    md.update(encodedRecipient);
                }

                if(!encryptMetadata)
                    md.update(new byte[] {
                        -1, -1, -1, -1
                    });
            }
            catch(Exception f)
            {
                throw new ExceptionConverter(f);
            }
            byte mdResult[] = md.digest();
            if(revision == 5)
                key = mdResult;
            else
                setupByEncryptionKey(mdResult, keyLength);
        } else
        {
            dic.put(PdfName.FILTER, PdfName.STANDARD);
            dic.put(PdfName.O, new PdfLiteral(PdfContentByte.escapeString(ownerKey)));
            dic.put(PdfName.U, new PdfLiteral(PdfContentByte.escapeString(userKey)));
            dic.put(PdfName.P, new PdfNumber(permissions));
            dic.put(PdfName.R, new PdfNumber(revision));
            if(revision == 2)
                dic.put(PdfName.V, new PdfNumber(1));
            else
            if(revision == 3 && encryptMetadata)
            {
                dic.put(PdfName.V, new PdfNumber(2));
                dic.put(PdfName.LENGTH, new PdfNumber(128));
            } else
            if(revision == 5)
            {
                if(!encryptMetadata)
                    dic.put(PdfName.ENCRYPTMETADATA, PdfBoolean.PDFFALSE);
                dic.put(PdfName.OE, new PdfLiteral(PdfContentByte.escapeString(oeKey)));
                dic.put(PdfName.UE, new PdfLiteral(PdfContentByte.escapeString(ueKey)));
                dic.put(PdfName.PERMS, new PdfLiteral(PdfContentByte.escapeString(perms)));
                dic.put(PdfName.V, new PdfNumber(revision));
                dic.put(PdfName.LENGTH, new PdfNumber(256));
                PdfDictionary stdcf = new PdfDictionary();
                stdcf.put(PdfName.LENGTH, new PdfNumber(32));
                if(embeddedFilesOnly)
                {
                    stdcf.put(PdfName.AUTHEVENT, PdfName.EFOPEN);
                    dic.put(PdfName.EFF, PdfName.STDCF);
                    dic.put(PdfName.STRF, PdfName.IDENTITY);
                    dic.put(PdfName.STMF, PdfName.IDENTITY);
                } else
                {
                    stdcf.put(PdfName.AUTHEVENT, PdfName.DOCOPEN);
                    dic.put(PdfName.STRF, PdfName.STDCF);
                    dic.put(PdfName.STMF, PdfName.STDCF);
                }
                stdcf.put(PdfName.CFM, PdfName.AESV3);
                PdfDictionary cf = new PdfDictionary();
                cf.put(PdfName.STDCF, stdcf);
                dic.put(PdfName.CF, cf);
            } else
            {
                if(!encryptMetadata)
                    dic.put(PdfName.ENCRYPTMETADATA, PdfBoolean.PDFFALSE);
                dic.put(PdfName.R, new PdfNumber(4));
                dic.put(PdfName.V, new PdfNumber(4));
                dic.put(PdfName.LENGTH, new PdfNumber(128));
                PdfDictionary stdcf = new PdfDictionary();
                stdcf.put(PdfName.LENGTH, new PdfNumber(16));
                if(embeddedFilesOnly)
                {
                    stdcf.put(PdfName.AUTHEVENT, PdfName.EFOPEN);
                    dic.put(PdfName.EFF, PdfName.STDCF);
                    dic.put(PdfName.STRF, PdfName.IDENTITY);
                    dic.put(PdfName.STMF, PdfName.IDENTITY);
                } else
                {
                    stdcf.put(PdfName.AUTHEVENT, PdfName.DOCOPEN);
                    dic.put(PdfName.STRF, PdfName.STDCF);
                    dic.put(PdfName.STMF, PdfName.STDCF);
                }
                if(revision == 4)
                    stdcf.put(PdfName.CFM, PdfName.AESV2);
                else
                    stdcf.put(PdfName.CFM, PdfName.V2);
                PdfDictionary cf = new PdfDictionary();
                cf.put(PdfName.STDCF, stdcf);
                dic.put(PdfName.CF, cf);
            }
        }
        return dic;
    }

    public PdfObject getFileID()
        throws IOException
    {
        return createInfoId(documentID);
    }

    public OutputStreamEncryption getEncryptionStream(OutputStream os)
    {
        return new OutputStreamEncryption(os, key, 0, keySize, revision);
    }

    public int calculateStreamSize(int n)
    {
        if(revision == 4 || revision == 5)
            return (n & 0x7ffffff0) + 32;
        else
            return n;
    }

    public byte[] encryptByteArray(byte b[])
    {
        try
        {
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            OutputStreamEncryption os2 = getEncryptionStream(ba);
            os2.write(b);
            os2.finish();
            return ba.toByteArray();
        }
        catch(IOException ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    public StandardDecryption getDecryptor()
    {
        return new StandardDecryption(key, 0, keySize, revision);
    }

    public byte[] decryptByteArray(byte b[])
    {
        try
        {
            ByteArrayOutputStream ba = new ByteArrayOutputStream();
            StandardDecryption dec = getDecryptor();
            byte b2[] = dec.update(b, 0, b.length);
            if(b2 != null)
                ba.write(b2);
            b2 = dec.finish();
            if(b2 != null)
                ba.write(b2);
            return ba.toByteArray();
        }
        catch(IOException ex)
        {
            throw new ExceptionConverter(ex);
        }
    }

    public void addRecipient(Certificate cert, int permission)
    {
        documentID = createDocumentId();
        publicKeyHandler.addRecipient(new PdfPublicKeyRecipient(cert, permission));
    }

    public byte[] computeUserPassword(byte ownerPassword[])
    {
        byte userPad[] = computeOwnerKey(ownerKey, padPassword(ownerPassword));
        for(int i = 0; i < userPad.length; i++)
        {
            boolean match = true;
            int j = 0;
            do
            {
                if(j >= userPad.length - i)
                    break;
                if(userPad[i + j] != pad[j])
                {
                    match = false;
                    break;
                }
                j++;
            } while(true);
            if(match)
            {
                byte userPassword[] = new byte[i];
                System.arraycopy(userPad, 0, userPassword, 0, i);
                return userPassword;
            }
        }

        return userPad;
    }

    public static final int STANDARD_ENCRYPTION_40 = 2;
    public static final int STANDARD_ENCRYPTION_128 = 3;
    public static final int AES_128 = 4;
    public static final int AES_256 = 5;
    private static final byte pad[] = {
        40, -65, 78, 94, 78, 117, -118, 65, 100, 0, 
        78, 86, -1, -6, 1, 8, 46, 46, 0, -74, 
        -48, 104, 62, -128, 47, 12, -87, -2, 100, 83, 
        105, 122
    };
    private static final byte salt[] = {
        115, 65, 108, 84
    };
    private static final byte metadataPad[] = {
        -1, -1, -1, -1
    };
    byte key[];
    int keySize;
    byte mkey[];
    byte extra[];
    MessageDigest md5;
    byte ownerKey[];
    byte userKey[];
    byte oeKey[];
    byte ueKey[];
    byte perms[];
    protected PdfPublicKeySecurityHandler publicKeyHandler;
    int permissions;
    byte documentID[];
    static long seq = System.currentTimeMillis();
    private int revision;
    private ARCFOUREncryption arcfour;
    private int keyLength;
    private boolean encryptMetadata;
    private boolean embeddedFilesOnly;
    private int cryptoMode;
    private static final int VALIDATION_SALT_OFFSET = 32;
    private static final int KEY_SALT_OFFSET = 40;
    private static final int SALT_LENGHT = 8;
    private static final int OU_LENGHT = 48;

}
