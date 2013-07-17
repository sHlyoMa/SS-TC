package service;

import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;

@Service
public class StringSplitter {

	public String splitter(String string, Integer splitLenght) {
		Iterable<String> tempString = Splitter.on(" ").split(string);
		String returnedString = "";
		for (String fullWordsArray : tempString) {
			if (fullWordsArray.length() > splitLenght) {
				Iterable<String> itString = Splitter.fixedLength(splitLenght).split(fullWordsArray);
				for (String smallWordsArray : itString) {
					returnedString += smallWordsArray + " ";
				}
			} else returnedString += fullWordsArray + " ";
		}
		return returnedString;
	}

}
