package firmaralmacenwinpdf;

import co.com.andesscd.pki.clases.CMS;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DoSign
  implements HttpHandler
{
  public static String dataReturn = "";
  public static String detalleCert = "";
  public static String errorMsg = "";
  public Signature signer = null;
  public KeyStore ks = null;
  
  public void handle(HttpExchange he)
    throws IOException
  {
    try
    {
      Map<String, Object> params = (Map)he.getAttribute("parameters");
      
      String accion = params.get("accion").toString();
      boolean result = false;
      DoSign dosign = new DoSign();
      switch (accion)
      {
      case "winmy": 
        if (params.containsKey("firmar")) {
          result = dosign.firmar(params);
        } else {
          result = dosign.listSlot(params);
        }
        break;
      }
      String response;
      if (result) {
        response = dataReturn;
      } else {
        response = "Error: " + errorMsg;
      }
      FirmarAlmacenWinPDF.setHeaders(he.getResponseHeaders());
      int responseLength = response.getBytes("UTF-8").length;
      he.sendResponseHeaders(200, responseLength);
      try
      {
        OutputStream os = he.getResponseBody();Throwable localThrowable4 = null;
        try
        {
          os.write(response.getBytes("UTF-8"));
        }
        catch (Throwable localThrowable2)
        {
          localThrowable4 = localThrowable2;throw localThrowable2;
        }
        finally
        {
          if (os != null) {
            if (localThrowable4 != null) {
              try
              {
                os.close();
              }
              catch (Throwable localThrowable3)
              {
                localThrowable4.addSuppressed(localThrowable3);
              }
            } else {
              os.close();
            }
          }
        }
      }
      catch (Exception E)
      {
        String message = FirmarAlmacenWinPDF.textLog.getText();
        message = message + "\nmensaje " + E.getMessage();
        FirmarAlmacenWinPDF.textLog.setText(message);
      }
    }
    catch (Throwable ex)
    {
      String message = FirmarAlmacenWinPDF.textLog.getText();
      message = message + "\nhandle" + ex;
      FirmarAlmacenWinPDF.textLog.setText(message);
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public boolean firmar(Map<String, Object> params)
  {
    boolean resultado = true;
    String digestAlgorithm = "SHA1";
    String signingAlgorithm = "SHA1withRSA";
    PrivateKey priv = null;
    X509Certificate x509 = null;
    BASE64Decoder decode = new BASE64Decoder();
    String hashdocumento = params.get("hashdocumento").toString();
    try
    {
      byte[] dataToSign = decode.decodeBuffer(hashdocumento);
     
      
      this.ks = loadKeyStore(params);
      String alias = "";
      if (params.get("accion").toString().equalsIgnoreCase("p12"))
      {
        Enumeration e = this.ks.aliases();
        alias = (String)e.nextElement();
      }
      else
      {
        alias = params.get("alias").toString();
      }
      String password = params.get("password").toString();
      String firmaTSA = params.get("firmaTSA").toString();
      String tsa = params.get("tsa").toString();
      priv = (PrivateKey)this.ks.getKey(alias, password.toCharArray());
      
      x509 = (X509Certificate)this.ks.getCertificate(alias);
      
      ByteArrayOutputStream baos = new ByteArrayOutputStream();
      ByteArrayInputStream bais = new ByteArrayInputStream(dataToSign);
      /**/
      FirmarPDF p1 = new FirmarPDF();
      p1.firmarPDF(x509, priv, this.ks.getProvider(), bais, baos, tsa, firmaTSA);
      
     
     // miCms.firmar(k,alias,"",baos);
     //String resultado2 = CMS.firmar(hashdocumento, alias, "");
    
      
      BASE64Encoder base = new BASE64Encoder();
      dataReturn = base.encode(baos.toByteArray());
      //dataReturn = resultado2; 
    }
    catch (NoSuchAlgorithmException ex)
    {
      resultado = false;
      errorMsg = "1" + ex.getMessage();
      Logger.getLogger(FirmarAlmacenWinPDF.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (KeyStoreException ex)
    {
      resultado = false;
      errorMsg = "2" + ex.getMessage();
      Logger.getLogger(FirmarAlmacenWinPDF.class.getName()).log(Level.SEVERE, null, ex);
      resultado = false;
    }
    catch (UnrecoverableKeyException ex)
    {
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
      resultado = false;
      errorMsg = "4" + ex.getMessage();
    }
    catch (IOException ex)
    {
      resultado = false;
      errorMsg = "7" + ex.getMessage();
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (UnknownError ex)
    {
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (Exception ex)
    {
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    return resultado;
  }
  
  public boolean listSlot(Map<String, Object> params)
  {
    Map<String, Object> cert = new TreeMap();
    String detalle = "";
    String dnName = "";
    detalleCert = "";
    boolean resultado = true;
    try
    {
      this.ks = loadKeyStore(params);
      Enumeration<String> aliasesEnum = this.ks.aliases();
      while (aliasesEnum.hasMoreElements())
      {
        String print = (String)aliasesEnum.nextElement();
        X509Certificate certf = (X509Certificate)this.ks.getCertificate(print);
        LdapName ln = new LdapName(certf.getSubjectDN().toString());
        dnName = "";
        String cn = "";String email = "";
        for (Rdn rdn : ln.getRdns())
        {
          String name = rdn.getType().toLowerCase();
          switch (name)
          {
          case "cn": 
            cn = "<b>CN= </b>" + rdn.getValue();
            break;
          case "emailaddress": 
            email = "<b>EMAILADDRESS=</b> " + rdn.getValue();
          }
        }
        dnName = certf.getSubjectDN().toString();
        if (detalle.isEmpty()) {
          detalle = dnName + "|" + print;
        } else {
          detalle = detalle + ";" + dnName + "|" + print;
        }
      }
      cert.put("certificado", detalle);
      cert.put("detalle", detalleCert);
      dataReturn = Json.serialize(cert);
    }
    catch (KeyStoreException ex)
    {
      resultado = false;
      errorMsg = "10" + ex.getMessage();
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (InvalidNameException ex)
    {
      resultado = false;
      errorMsg = "11" + ex.getMessage();
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (Exception ex)
    {
      resultado = false;
      errorMsg = "Pin Incorrecto";
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    return resultado;
  }
  
  public KeyStore loadKeyStore(Map<String, Object> params)
  {
    try
    {
      String accion = params.get("accion").toString();
      String password = params.get("password").toString();
      switch (accion)
      {
      case "winmy": 
        this.ks = KeyStore.getInstance("Windows-MY");
        this.ks.load(null, null);
      }
    }
    catch (KeyStoreException ex)
    {
      errorMsg = "12" + ex.getMessage();
      System.out.println(errorMsg);
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (IOException ex)
    {
      errorMsg = "13" + ex.getMessage();
      System.out.println(errorMsg);
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (NoSuchAlgorithmException ex)
    {
      errorMsg = "14" + ex.getMessage();
      System.out.println(errorMsg);
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    catch (CertificateException ex)
    {
      errorMsg = "15" + ex.getMessage();
      System.out.println(errorMsg);
      Logger.getLogger(DoSign.class.getName()).log(Level.SEVERE, null, ex);
    }
    return this.ks;
  }
}
