package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ConcadenateWavs {
	static String routeSox = "C:\\Program Files (x86)\\sox-14-4-2\\sox.exe";
	static File routeFiles = new File("C:\\Users\\------\\Desktop\\------");
	static HashMap<String, String> listFilePath = new HashMap<>();
	
	
	public ConcadenateWavs() {
		listFiles(routeFiles);
		concatenateWavs(listFilePath);
	}
	
	private void listFiles(File route){
		for (File file : route.listFiles()){
			if (file.isDirectory()){
				listFiles(file);
			}else{
				String [] fileSplit = file.getName().split("_");
				String [] subName = fileSplit[3].toString().split("\\.");
				String auxFileName = fileSplit[2] + "_" + subName[1];
				
				if (listFilePath.containsKey(auxFileName)){
					String newPath = listFilePath.get(auxFileName).concat(" "+file.getAbsolutePath());
					listFilePath.put(auxFileName, newPath);
					
				}else{
					listFilePath.put(auxFileName, file.getAbsolutePath());
				}
			}
		}
	}
	
	private static void concatenateWavs(HashMap<String, String> listPath){
		String command = "";
		
		for (Map.Entry<String, String> entry : listPath.entrySet()){
			System.out.println("Procesing file: " + entry.getKey() + ".wav");
			command = routeSox + " " + entry.getValue() + " " + routeFiles + "\\" + entry.getKey() + ".wav";
			
			runCommand(command);
		}
		
		System.out.println("Archivos guardados en: " + routeFiles);
	}
	
	private static void runCommand(String command) {
		try {
			String line;
			Process p = Runtime.getRuntime().exec(command);

			BufferedReader bri = new BufferedReader(new InputStreamReader(p.getInputStream()));
			BufferedReader bre = new BufferedReader(new InputStreamReader(p.getErrorStream()));
			while ((line = bri.readLine()) != null) {
				System.out.println(line);
			}
			bri.close();
			while ((line = bre.readLine()) != null) {
				System.out.println(line);
			}
			bre.close();
			p.waitFor();
			System.out.println("Done");
		} catch (Exception err) {
			err.printStackTrace();
		}
	}
}