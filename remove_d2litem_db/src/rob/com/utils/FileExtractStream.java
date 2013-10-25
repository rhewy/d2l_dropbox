package rob.com.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import net.sf.sevenzipjbinding.ISequentialOutStream;
import net.sf.sevenzipjbinding.SevenZipException;

public class FileExtractStream implements ISequentialOutStream
{
	private File fileOut;
	private FileOutputStream out;
	
	FileExtractStream(File fout)
	{
		try
		{
			this.fileOut = fout;
			fout.getParentFile().mkdirs();
			out = new FileOutputStream(this.fileOut);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public int write(byte[] data) throws SevenZipException
	{
		int bytesWritten = 0;
		try
		{
			this.out.write(data);
			this.out.close();
			bytesWritten = data.length;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return bytesWritten;
	}
}
