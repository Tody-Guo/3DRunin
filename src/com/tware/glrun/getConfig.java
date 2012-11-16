package com.tware.glrun;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import android.util.Log;

class getConfig{

	public static String TAG = "getconfig";
	
	static int RuninTime = 0;
	static int CPUTIME = 0;

	public static int getRuninTime()
	{
		return RuninTime;
	}

	public static int getCPUTestTime()
	{
		return CPUTIME;
	}
	
	public static boolean init()
	{
		File f = new File("/mnt/sdcard/runin.cfg");
		if (!f.exists())
		{
			f = new File("/mnt/sdcard/external_sdcard/runin.cfg");
		}
    
		if (f.exists() && f.isFile())
		{
			Log.i(TAG, f.getAbsoluteFile()+ " found");
			try {
				BufferedReader fr = new BufferedReader(new FileReader(f));
				String str = fr.readLine();
				do{
					if (!str.startsWith("#") && str.trim().length()>= 11 )
					{
						String [] strSplit = new String[20];
						strSplit = str.split("=");
						if (strSplit != null && strSplit[0].equalsIgnoreCase("RuninTime"))
						{
							Log.i(TAG, "RuninTime defined: " + strSplit[1]);
							RuninTime = Integer.parseInt(strSplit[1].trim());
						}else if (strSplit != null && strSplit[0].equalsIgnoreCase("CPUTestTime"))
						{
							Log.i(TAG, "CPULOAD defined: " + strSplit[1]);
							CPUTIME = Integer.parseInt(strSplit[1].trim());							
						}
					}	
				}while((str = fr.readLine())!= null);

				fr.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return false;
			}catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
}