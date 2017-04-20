/*******************************************************************************
 * Copyright (C) 2017 AT&T Intellectual Property. All rights reserved. This code is licensed under the Apache License, Version 2.0
 *******************************************************************************/

package com.woorea.openstack.connector;

/**
 * <h2>Revisions</h2>
 * <dl>
 * <dt>8/5/2015</dt>
 * <dd>Change design of connector to lazy initialize an instance on first access, so that the properties and
 * configuration of the client can be inspected to set configuration options for the connector</dd>
 * </dl>
 */
public class OpenStack {

    // public static Client CLIENT;
    //
    // public static ObjectMapper DEFAULT_MAPPER;
    //
    // public static ObjectMapper WRAPPED_MAPPER;
    //
    // static {
    // initialize();
    // }
    //
    // private static void initialize() {
    //
    // /*
    // //class MyX509TrustManager implements X509TrustManager
    // TrustManager mytm[] = null;
    // KeyManager mykm[] = null;
    //
    // try {
    // mytm = new TrustManager[]{new MyX509TrustManager("./truststore_client", "asdfgh".toCharArray())};
    // mykm = new KeyManager[]{new MyX509KeyManager("./keystore_client", "asdfgh".toCharArray())};
    // } catch (Exception ex) {
    //
    // }
    //
    // SSLContext context = null;
    // context = SSLContext.getInstance("SSL");
    // context.init(mykm, mytm, null);
    //
    // */
    //
    // try {
    //
    // SSLContext context = null;
    // context = SSLContext.getInstance("SSL");
    // context.init(null, null, null);
    //
    // SslConfigurator sslConfig = SslConfigurator.newInstance();
    // /*
    // .trustStoreFile("./truststore_client")
    // .trustStorePassword("asdfgh")
    //
    // .keyStoreFile("./keystore_client")
    // .keyPassword("asdfgh");
    // */
    // //old: CLIENT.property(ClientProperties.SSL_CONFIG, new SslConfig(context));
    //
    // CLIENT = ClientBuilder.newBuilder().sslContext(sslConfig.createSSLContext()).build();
    //
    // DEFAULT_MAPPER = new ObjectMapper();
    //
    // DEFAULT_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
    // DEFAULT_MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    // DEFAULT_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    // DEFAULT_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    // DEFAULT_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    //
    // WRAPPED_MAPPER = new ObjectMapper();
    //
    // WRAPPED_MAPPER.setSerializationInclusion(Inclusion.NON_NULL);
    // WRAPPED_MAPPER.enable(SerializationConfig.Feature.INDENT_OUTPUT);
    // WRAPPED_MAPPER.enable(SerializationConfig.Feature.WRAP_ROOT_VALUE);
    // WRAPPED_MAPPER.enable(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE);
    // WRAPPED_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
    // WRAPPED_MAPPER.disable(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES);
    // WRAPPED_MAPPER.enable(DeserializationConfig.Feature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    //
    // CLIENT.register(new JacksonFeature()).register(new ContextResolver<ObjectMapper>() {
    //
    // public ObjectMapper getContext(Class<?> type) {
    // return type.getAnnotation(JsonRootName.class) == null ? DEFAULT_MAPPER : WRAPPED_MAPPER;
    // }
    //
    // });
    //
    // CLIENT.register(new ClientRequestFilter() {
    //
    // public void filter(ClientRequestContext requestContext) throws IOException {
    // requestContext.getHeaders().remove("Content-Language");
    // requestContext.getHeaders().remove("Content-Encoding");
    // }
    // });
    //
    // } catch(Exception e) {
    // throw new RuntimeException(e.getMessage(), e);
    // }
    //
    // }
    //
}
