package com.will.performanceapp2.net;

import com.will.performanceapp2.PerformanceApp;

import java.security.cert.CertificateException;

import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.fastjson.FastJsonConverterFactory;

public class RetrofitNewsUtils {
    private static final APIService API_SERVICE;

    public static APIService getApiService() {
        return API_SERVICE;
    }

    public static final String HTTP_SPORTSNBA_QQ_COM = "https://pixabay.com/api/";

    //定义一个信任所有证书的TrustManager
    private static final X509TrustManager trustAllCert = new X509TrustManager() {
        @Override
        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
            return new java.security.cert.X509Certificate[]{};
        }
    };




    static {
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        //client.sslSocketFactory(new SSL(trustAllCert),trustAllCert);
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLog());
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        Cache cache = new Cache(PerformanceApp.getApplication().getCacheDir(),10*1024*1024);
        client.
                eventListenerFactory(OkHttpEventListener.FACTORY).
                dns(OkHttpDNS.getIns(PerformanceApp.getApplication())).
                addInterceptor(new NoNetInterceptor()).
                addInterceptor(logging)
        .sslSocketFactory(SSL.getSSLSocketFactory())
        .hostnameVerifier(SSL.getHostnameVerifier());

        final Retrofit RETROFIT = new Retrofit.Builder()
                .baseUrl(HTTP_SPORTSNBA_QQ_COM)
                .addConverterFactory(FastJsonConverterFactory.create())
                .client(client.build())
                .build();
        API_SERVICE = RETROFIT.create(APIService.class);
    }




}