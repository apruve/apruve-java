package com.apruve;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation.Builder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status.Family;

import org.glassfish.jersey.jackson.JacksonFeature;

/**
 * A convenient class for integrating to the Apruve API.
 * 
 * Initialize with: ApruveClient.init(apiKey, Environment);
 * 
 * More documentation at https://docs.apruve.com and
 * https://github.com/apruve/apruve-java
 * 
 * @author https://github.com/nealtovsen
 * 
 */
public class ApruveClient {
	private static final Logger log = Logger.getLogger(ApruveClient.class.getName());;
	private static final LoggingFilter filter = new LoggingFilter(log, true, "Apruve-Api-Key");

	private ApruveEnvironment env;

	private String apiKey;

	/**
	 * @param apiKey
	 *            The API Key for your merchant account. Create one for your test
	 *            account on https://test.apruve.com, or create one
	 *            for live transactions at https://app.apruve.com. 
	 * @param env
	 *            com.apruve.ApruveClient.Environment.PROD or
	 *            com.apruve.ApruveClient.Environment.TEST, as appropriate.
	 */
	public ApruveClient(String apiKey, ApruveEnvironment env) {
		if (apiKey == null)
			throw new ApruveException("apiKey cannot be null");
		if (env == null)
			throw new ApruveException("env cannot be null");
		this.apiKey = apiKey;
		this.env = env;
	}

	/**
	 * @return
	 */
	public String getApiKey() {
		return this.apiKey;
	}

	/**
	 * @return
	 */
	public ApruveEnvironment getEnvironment() {
		return this.env;
	}

	protected Builder restRequest(String path) {
		return restRequest(path, null);
	}
	
	protected Builder restRequest(String path, Map<String, Object> queryParams) {
		Client client = ClientBuilder.newBuilder()
				.register(JSONObjectMapperProvider.class)
				.register(JacksonFeature.class)
				.register(filter)
				.build();
		WebTarget target = client.target(getEnvironment().getApiV4Url() + path);
		if (queryParams != null) {
			for (String paramName : queryParams.keySet()) {
				target = target.queryParam(paramName, queryParams.get(paramName));
			}
		}
		Builder builder = target.request(MediaType.APPLICATION_JSON).header("Apruve-Api-Key", getApiKey());

		return builder;
	}

	private <T> ApruveResponse<T> processResponse(Response response, Class<T> resultType) {
		T responseObject = null;
		ApruveResponse<T> result;
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			responseObject = response.readEntity(resultType);
			result = new ApruveResponse<T>(response.getStatus(), responseObject);
		} else {
			result = new ApruveResponse<T>(response.getStatus(), null, response.readEntity(String.class));
		}

		return result;
	}

	public <T> ApruveResponse<List<T>> index(String path, GenericType<List<T>> resultType) {
		return index(path, resultType, null);
	}
	
	/**
	 * As get(), but specialized to return lists of objects for REST index
	 * operations
	 * 
	 * @param path
	 *            The path to issue the GET against
	 * @param resultType
	 *            A {@link GenericType} instance that determines the type of
	 *            list returned
	 * @return List of objects of a type as determined by resultType
	 */
	public <T> ApruveResponse<List<T>> index(String path, GenericType<List<T>> resultType,
			Map<String, Object> queryParams) {
		Response response = restRequest(path, queryParams).get();
		List<T> responseObject = null;
		ApruveResponse<List<T>> result;
		if (response.getStatusInfo().getFamily() == Family.SUCCESSFUL) {
			responseObject = response.readEntity(resultType);
			result = new ApruveResponse<List<T>>(response.getStatus(), responseObject);
		} else {
			result = new ApruveResponse<List<T>>(response.getStatus(), null, response.readEntity(String.class));
		}
		return result;
	}

	/**
	 * Issues a GET request against to the Apruve REST API, using the specified
	 * path.
	 * 
	 * @param path
	 *            The path to issue the GET against
	 * @param resultType
	 *            The type of the response
	 * @return A single object of type resultType
	 */
	public <T> ApruveResponse<T> get(String path, Class<T> resultType) {
		Response response = restRequest(path).get();
		return processResponse(response, resultType);
	}

	public <T, RT> ApruveResponse<RT> post(String path, T payload, Class<RT> responseType) {
		Response response = restRequest(path).post(Entity.json(payload));
		log.info(Entity.json(payload).toString());
		return processResponse(response, responseType);
	}

	public <T, RT> ApruveResponse<RT> put(String path, T payload, Class<RT> responseType) {
		Response response = restRequest(path).put(Entity.json(payload));
		log.info(Entity.json(payload).toString());
		return processResponse(response, responseType);
	}

	public <T> ApruveResponse<T> delete(String path, Class<T> responseType) {
		Response response = restRequest(path).delete();
		return processResponse(response, responseType);
	}
}
