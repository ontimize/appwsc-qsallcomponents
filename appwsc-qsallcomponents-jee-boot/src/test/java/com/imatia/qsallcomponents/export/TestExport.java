package com.imatia.qsallcomponents.export;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Paths;

import org.springframework.util.ResourceUtils;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

public class TestExport {

	private static BasicCookieStore cookieStore;

	public TestExport() {
		cookieStore = new BasicCookieStore();
	}

	public static void main(String[] args) throws IOException, URISyntaxException, JSONException {
		cleanOutputFolder();
		String token = login();

		File f = ResourceUtils.getFile(TestExport.class.getResource("/"));
		FileUtils.listFiles(f, new String[] { "json" }, false).forEach(t -> {
			String fileName = FilenameUtils.getBaseName(t.getName());
			try {
				getExcelFile(token, fileName + ".xlsx");
			} catch (URISyntaxException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		});

	}

	private static void cleanOutputFolder() {
		File folder = Paths.get("./output").toFile();
		folder.mkdirs();
		for (File file : folder.listFiles()) {
			file.delete();
		}
	}

	public static void downloadFile(HttpEntity entity, OutputStream target)
			throws ClientProtocolException, IOException {
		if (entity != null) {
			InputStream inputStream = entity.getContent();
			copy(inputStream, target);
		}
	}

	private static void copy(final InputStream in, final OutputStream out) throws IOException {
		final byte[] b = new byte[8192];
		for (int r; (r = in.read(b)) != -1;) {
			out.write(b, 0, r);
		}
	}

	public static void downloadAndSaveToFile(HttpEntity entity, File targetFile) throws IOException {
		OutputStream outputStream = new FileOutputStream(targetFile);
		downloadFile(entity, outputStream);
		outputStream.close();
	}

	private static String login() throws URISyntaxException, IOException {
		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

		HttpUriRequest login = RequestBuilder.post().setUri(new URI("http://localhost:8080/qsallcomponents-jee/users/login"))
				.addHeader("Host", "localhost:8080").addHeader("Connection", "keep-alive")
				.addHeader("Content-Length", "0").addHeader("Accept", "application/json, text/plain, */*")
				.addHeader("Authorization", "Basic ZGVtbzpkZW1vdXNlcg==")
				.addHeader("Accept-Encoding", "gzip, deflate, br")
				.addHeader("Accept-Language", "es-419,es;q=0.9,en;q=0.8").build();

		CloseableHttpResponse response = httpclient.execute(login);
		try {
			HttpEntity entity = response.getEntity();
			EntityUtils.consume(entity);
			return response.getHeaders("X-Auth-Token")[0].getValue();
		} finally {
			response.close();
		}
	}

	private static void getExcelFile(String token, String outputFileName)
			throws IOException, URISyntaxException, JSONException {

		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();

		HttpUriRequest request = RequestBuilder.post().setUri(new URI("http://localhost:8080/qsallcomponents-jee/export/xlsx"))
				.addHeader("Access-Control-Allow-Origin", "*").addHeader("Authorization", "Bearer " + token)
				.addHeader("Content-Type", "application/json;charset=UTF-8")
				.setEntity(EntityBuilder.create().setContentType(ContentType.APPLICATION_JSON)
						.setFile(new File(TestExport.class.getClassLoader().getResource("example0.json").getFile()))
						.build())
				.build();
		CloseableHttpResponse response = httpclient.execute(request);

		try {
			File folder = Paths.get("./output").toFile();
			folder.mkdirs();
			File file = Paths.get(folder.getPath() + File.separator + outputFileName).toAbsolutePath().normalize()
					.toFile();
			if (!file.exists()) {
				file.createNewFile();
			}
			downloadAndSaveToFile(response.getEntity(), file);
		} finally {
			response.close();
		}

	}

}
