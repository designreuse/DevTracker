package com.tmt.common;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;


public class DistanceCalculator {
	public static void main(String[] args) throws java.lang.Exception {
		System.out.println(distance(13.0826802, 80.2707184, 11.0168445, 76.9558321,"M") + " Miles\n");
		System.out.println(distance(13.0826802, 80.2707184, 11.0168445, 76.9558321,"K") + " Kilometers\n");
		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506,"N") + " Nautical Miles\n");
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))	+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2))	* Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
	
	
	
	public static String calculateRoute(String addressFrom, String addressTo) {
		String outputResult = "";
		String totalDistance="";
		String urlString = "http://maps.googleapis.com/maps/api/directions/xml?sensor=true&origin="
				+ addressFrom + "&destination=" + addressTo;
		System.out.println(urlString);

		try {
			URL urlGoogleDirService = new URL(urlString);

			HttpURLConnection urlGoogleDirCon = (HttpURLConnection) urlGoogleDirService
					.openConnection();

			urlGoogleDirCon.setAllowUserInteraction(false);
			urlGoogleDirCon.setDoInput(true);
			urlGoogleDirCon.setDoOutput(false);
			urlGoogleDirCon.setUseCaches(true);
			urlGoogleDirCon.setRequestMethod("GET");
			urlGoogleDirCon.connect();

			try {
				OutputStream output = new OutputStream() {
					private StringBuilder string = new StringBuilder();

					@Override
					public void write(int b) throws IOException {
						this.string.append((char) b);
					}

					@Override
					public String toString() {
						return this.string.toString();
					}
				};

				byte buf[] = new byte[1024];
				int len;

				while ((len = urlGoogleDirCon.getInputStream().read(buf)) > 0) {
					output.write(buf, 0, len);
				}
				output.close();
				urlGoogleDirCon.getInputStream().close();
				outputResult = output.toString();

				DocumentBuilderFactory dbf = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder db = dbf.newDocumentBuilder();
				InputSource is = new InputSource();
				is.setCharacterStream(new StringReader(outputResult));

				Document doc = db.parse(is);

				XPath xPath = XPathFactory.newInstance().newXPath();
				XPathExpression expr = xPath
						.compile("//DirectionsResponse/route/leg/duration/text/text()");

				Object durationNodeList = expr.evaluate(doc,
						XPathConstants.NODESET);
				NodeList durationNodes = (NodeList) durationNodeList;
				int durationLength = durationNodes.getLength();
				for (int i = 0; i < durationLength; i++) {
					System.out.println("Duration:"
							+ durationNodes.item(i).getNodeValue());
				}

				XPath xPath1 = XPathFactory.newInstance().newXPath();
				XPathExpression expr1 = xPath1
						.compile("//DirectionsResponse/route/leg/distance/text/text()");

				Object distanceNodeList = expr1.evaluate(doc,
						XPathConstants.NODESET);
				NodeList distanceNodes = (NodeList) distanceNodeList;
				int distanceLenght = distanceNodes.getLength();
				for (int i = 0; i < distanceLenght; i++) {
					System.out.println("Distance:"
							+ distanceNodes.item(i).getNodeValue());
					
				totalDistance = distanceNodes.item(i).getNodeValue();
				}

				System.out.println("Done successfully");
			} catch (IOException e) {
				e.printStackTrace();
			}

			urlGoogleDirCon.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return totalDistance;
		}
	
	
	
}