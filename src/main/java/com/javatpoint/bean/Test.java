package com.javatpoint.bean;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.hibernate.Session;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class Test {

	public static void main(String[] args) {
//		Session session;
//		session = HibernateUtils.getSessionFactory().openSession();
//		session.beginTransaction();
//		Employee employee1 = new Employee();
//		employee1.setEmpName("Sandip");
//		employee1.setEmpEmail("Sandip@gmail.com");
//		employee1.setEmpSalary(16800.3);
//
//		Employee employee2 = new Employee();
//		employee2.setEmpName("Rahul");
//		employee2.setEmpEmail("Rahul@gmail.com");
//		employee2.setEmpSalary(12800.3);
//
//		session.save(employee1);
//		session.save(employee2);
//		session.getTransaction().commit();

		final String ACCOUNT_SID = "ACea3f638b850352e8d996638e86afee10";
		final String AUTH_TOKEN = "e5459dcc746c18b5cef20707dd6e04f8";

		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

		Message message = Message.creator(new PhoneNumber("+919370362910"), new PhoneNumber("+18136448713"),
				"Ahoy from Twilio!").create();

		System.out.println(message.getSid());
		String str = sendSms();
		System.out.println(str);

	}

	public static String sendSms() {
		try {
			// Construct data
			String apiKey = "apikey=" + "a9qfXOz5Z0E-xrJ0dqctGcfjAyJlAMknrPbxRHqAoH";
			String message = "&message=" + "Prashnat Check This message";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "919325763656";

			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();

			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS " + e);
			return "Error " + e;
		}
	}
}
