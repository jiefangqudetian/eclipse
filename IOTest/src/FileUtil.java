import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class FileUtil {
	int i = 0;
	public void showFiles(String string) {
		
		File file = new File(string);
		String str = stringBlank(i);
		if (file.isFile()) {
			System.out.println(str+file.getAbsolutePath());
		} else {
			System.out.println(str+"[]"+file.getAbsolutePath());
			i++;
			File[] files = file.listFiles();
			for (File fil : files) {
				showFiles(fil.getAbsolutePath());
			}
		}
	}
	
	public void writeFiles(String string,String path) {
		
		
		File file = new File(string);
		File oFile = new File(path);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(oFile);
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
			
			subWriteFiles(file, outputStreamWriter,i);
			
			outputStreamWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void subWriteFiles(File file,OutputStreamWriter outputStreamWriter,int i) throws IOException {
		String str = stringBlank(i);
		if (file.isFile()) {
			outputStreamWriter.write(str+file.getName());
			outputStreamWriter.write("\r\n");
		} else {
			outputStreamWriter.write(str+"[]"+file.getName());
			outputStreamWriter.write("\r\n");
			i++;
			File[] files = file.listFiles();
			for (File fil : files) {
				subWriteFiles(fil, outputStreamWriter,i);
			}
		}
	}
	
	public String stringBlank (int i) {
		
		StringBuffer stringBuffer = new StringBuffer();
		String blank = "--";
		for (int j = 0; j < i; j++) {
			stringBuffer.append(blank);
		}
		return stringBuffer.toString();
	}
	
	
}
