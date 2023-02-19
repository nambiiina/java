//package com.arkeup.lencify.gestion_brevets_mcs.commun.config;
//
//import java.net.InetAddress;
//import java.net.UnknownHostException;
//
//import org.elasticsearch.client.Client;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.TransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
//
//@Configuration
//@EnableElasticsearchRepositories(basePackages = "com.arkeup.lencify.gestion_brevets_mcs.service.repository.es")
//public class ElasticsearchConfig {
//
//	@Value("${es.host}")
//	private String esHost;
//
//	@Value("${es.port}")
//	private int esPort;
//
//	@Value("${elasticsearch.clustername}")
//	private String esClusterName;
//
//	@SuppressWarnings("resource")
//	@Bean
//	public Client client() throws UnknownHostException {
//		final Settings esSettings = Settings.builder().put("cluster.name", esClusterName).build();
//
//		TransportClient client = new PreBuiltTransportClient(esSettings)
//				.addTransportAddress(new TransportAddress(InetAddress.getByName(esHost), esPort));
//
//		return client;
//	}
//}
