package operationPack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadObject {
	Properties p=new Properties();
	//what is this public mean i think its method because its has return value;
	public Properties getObjectRepository() throws IOException {
		File f=new File(System.getProperty("user.dir")+"\\src\\objectsPack\\property2");
		FileInputStream fis=new FileInputStream(f);
		p.load(fis);
		return p;
	}

}
