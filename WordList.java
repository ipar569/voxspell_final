

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Random;


//Written by Injae Park
public class WordList {
	
	private ArrayList<String> _wordList=new ArrayList<String>();
	
	private ArrayList<Integer> pos = new ArrayList<Integer>();
	private LinkedHashMap<String,Integer> position = new LinkedHashMap<String, Integer>();
	protected boolean _failed = false;
	
	//This constructor is for normal quiz.
	public WordList(String file) throws IOException{
		
			BufferedReader wordlist = new BufferedReader(new FileReader(file));
			
			String line;
			//Reading word where and adding it to arrayList if it is not an empty line
			while ((line = wordlist.readLine()) != null){
				if(!"".equals(line.trim())){
					
					String[] lines = line.split("");
					if(lines[0].equals("%")){
						if(_wordList.size()<1){
							pos.add(0);
							position.put(line, 0);
						}
						else{
							pos.add(_wordList.size());
							position.put(line,_wordList.size());
						}
					}
					_wordList.add(line);
				}
			}
	}
	
	/*
	 * This constructor is for making wordlist for review function.
	 */
	public WordList(String file, int level) throws IOException{
		
		BufferedReader wordlist = new BufferedReader(new FileReader("."+File.separator+file+level));
		
		String line;
		int a=0;
		//Reading word where and adding it to arrayList if it is not an empty line
		while ((line = wordlist.readLine()) != null){
			if(!"".equals(line.trim())){
				_wordList.add(line);
			}
		}
		_failed = true;
}
	
	//Return wordCount for each level.
	public int getWordCount(String level){
		level = "%"+level;
		if(_failed){
			LinkedHashSet<String> temp = new LinkedHashSet<String>(_wordList);
			return temp.size();
		}
		ArrayList<String> key = new ArrayList<String>(position.keySet());
		if (level.equals(key.get(key.size()-1))) {
			return _wordList.size() -1 - position.get(level);
		} 
		int poss = key.indexOf(level)+1;
		return position.get(key.get(poss))-1-position.get(level);
		
	}
	
	//Get random word from the arrayList
	public String getRandomWord(String level){
		Random r = new Random();
		LinkedHashSet<String> temp = new LinkedHashSet<String>(_wordList);
		ArrayList<String> testList = new ArrayList<String>();
		testList.addAll(temp);
		//Getting random position
		
		if(_failed){
			int rand = Math.abs(r.nextInt()) % testList.size();
			return testList.get(rand);
		}
		int rand = Math.abs(r.nextInt()) % this.getWordCount(level);
		return _wordList.get(rand+position.get("%"+level)+1);
	}
	
	//Sort the list and return the word at the position specified.
	public String getSortedWord(int a){
		Collections.sort(_wordList);
		return _wordList.get(a);
	}
	
	public String[] getCategories(){
		String[] categories = new String[pos.size()];
		for(int i=0;i<pos.size();i++){
			String category = _wordList.get(pos.get(i)).replace("%", "");
			categories[i]= category;
		}
		return categories;
		
	}
	
	//Create a list of words to be tested.
	public ArrayList<String> createTestList(String level, int num){
		//Store only unique values.
		LinkedHashSet<String> list = new LinkedHashSet<String>();
		int n = getWordCount(level);
		while(list.size()<num&&list.size()<n){
			list.add(getRandomWord(level));
			if(list.size()==n)
				break;
		}
		
		ArrayList<String> testList = new ArrayList<String>();
		testList.addAll(list);
		return testList;
	}
	
}