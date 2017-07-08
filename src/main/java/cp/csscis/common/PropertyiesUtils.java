package cp.csscis.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

public class PropertyiesUtils {
	Properties pps = null;
	AtomicBoolean load = new AtomicBoolean(false);
	public boolean init() {
		try {
			if (!load.get()) {
				pps = new Properties();
				InputStream in = new FileInputStream(this.getClass()
						.getResource("/testsend.properties").getPath());
				pps.load(in);
				in.close();
				load.set(true);
			}
		} catch (IOException e) {
			pps = null;
			load.set(false);
		}
		return load.get();
	}

	public boolean set(String key, String value) {
		if(!init())return false;
		try {
			pps.setProperty(key, value);
			OutputStream fos = new FileOutputStream(this.getClass()
					.getResource("/testsend.properties").getPath());
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pps.store(fos, df.format(new Date()));
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Object get(String key) {
		if(!init())return null;
		if (pps.containsKey(key)) {
			return pps.get(key);
		} else {
			return null;
		}
	}
}
