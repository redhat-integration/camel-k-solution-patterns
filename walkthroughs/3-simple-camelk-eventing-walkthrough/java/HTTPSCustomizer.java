import org.apache.camel.BindToRegistry;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;
import java.security.cert.CertificateException;
import org.apache.camel.support.jsse.SSLContextParameters;
import org.apache.camel.support.jsse.TrustManagersParameters;

public class HTTPSCustomizer {
    @BindToRegistry("allowAllHostnameVerifier")
    public AllowAllHostnameVerifier verifier(){
        AllowAllHostnameVerifier allowAllHostnameVerifier = new AllowAllHostnameVerifier();
        System.out.println("allowAllHostnameVerifier:["+allowAllHostnameVerifier+"]");
        return allowAllHostnameVerifier;
    }

    @BindToRegistry("mySSLContextParameters")
    public SSLContextParameters sslContext() throws Exception{
        SSLContextParameters sslContextParameters = new SSLContextParameters();
        TrustManagersParameters tmp = new TrustManagersParameters();
        tmp.setTrustManager(new TrustALLManager());
        sslContextParameters.setTrustManagers(tmp);
        System.out.println("mySslContext:["+sslContextParameters+"]");
        return sslContextParameters; 
    }

    class AllowAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String s, SSLSession sslSession) {
            return true;
        }
    }

    // Create a trust manager that does not validate certificate chains
    class TrustALLManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException { }
        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }
}
