package com.tware.glrun;

import android.util.Log;

class cpuLoader{
	static boolean isCPUStop = false;
	static double Pi1 =  3.14 ;
	
	public static void CpuTest(){
		new Thread ()
		{
			public void run()
			{
				Log.i("CPULoader", "CPU Loading Test Start!");
				while(!isCPUStop){
					Pi1 = Pi1*Pi1;
					if (Pi1 > 1024*1024*1024)
						Pi1 = 3.14;
				}
				Log.e("CPULoader", "CPU Loading Test Quit!");
			}		
		}.start();
	}
	
	public static void setStop()
	{
		isCPUStop = true;
	}
}