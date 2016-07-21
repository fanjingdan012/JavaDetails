/**
 * Project Sudoku Next!
 * @author pRobE
 * @last update 2010-1-4
 */
package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.swing.JOptionPane;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.XMLStreamWriter;

import core.SudokuNext;
import core.SudokuSettings;

/**
 * XMLFactory�� 
 * ��XML�ļ�����������ã�����
 * @version 0.2
 */
public class XMLFactory {
	
	/**
	 * ���ļ���ȡ����
	 * @param _settings ��Ҫ���ĵ�������
	 * @param _filename XML�ĵ�������
	 */
	public static void getSudokuSettings(SudokuSettings _settings, String _filename)
	{
		XMLInputFactory factory = XMLInputFactory.newInstance();
		try {
			XMLStreamReader reader = factory.createXMLStreamReader(new FileInputStream(new File(_filename)));
			
			while (reader.hasNext())
			{
				int currentPointer=reader.next();
				if (currentPointer==XMLStreamConstants.START_ELEMENT)
				{
					if (reader.getLocalName().equals("FullScreen"))
					{
						_settings.setFullScreen(Boolean.parseBoolean(reader.getAttributeValue(0)));
						continue;
					}else if (reader.getLocalName().equals("ThemepackFilename"))
					{
						_settings.setThemepackFilename(reader.getAttributeValue(0));
						continue;
					}else if (reader.getLocalName().equals("BGMVolume"))
					{
						_settings.setBGMVolume(Integer.parseInt(reader.getAttributeValue(0)));
						continue;					
					}else if (reader.getLocalName().equals("SEVolume"))
					{
						_settings.setSEVolume((Integer.parseInt(reader.getAttributeValue(0))));
						continue;					
					}
				}
			}
			reader.close();

		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "��ȡ��Ϸ���ó���!\nSudoku Next!  �������������ã�����������SudokuNext!", "Sudoku Next!", JOptionPane.ERROR_MESSAGE);
			SudokuNext.setSettings(new SudokuSettings());
			SudokuSettings.update();
			System.exit(0);
		}
		
	}
	
	
	/**
	 * �����ñ��浽�ļ�
	 * @param _settings ��Ҫ�����������
	 * @param _filename XML�ĵ�������
	 */
	public static void setSudokuSettings(SudokuSettings _settings, String _filename)
	{
		XMLOutputFactory factory = XMLOutputFactory.newInstance();
		try {
			XMLStreamWriter writer = factory.createXMLStreamWriter(new FileOutputStream(new File(_filename)));
			writer.writeStartDocument();
			
			writer.writeStartElement("General");
			writer.writeEmptyElement("FullScreen");
			writer.writeAttribute("Value", Boolean.toString(_settings.isFullScreen()));
			writer.writeEmptyElement("ThemepackFilename");
			writer.writeAttribute("Value", _settings.getThemepackFilename());
			writer.writeEmptyElement("BGMVolume");
			writer.writeAttribute("Value", Integer.toString(_settings.getBGMVolume()));
			writer.writeEmptyElement("SEVolume");
			writer.writeAttribute("Value", Integer.toString(_settings.getSEVolume()));
			writer.writeEndElement();
			
			writer.writeEndDocument();
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
