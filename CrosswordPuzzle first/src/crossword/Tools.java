/*
CrosswordPuzzle
https://github.com/hencoappel/CrosswordPuzzle/tree/master/src/crossword
Copyright 2013 Henco Appel / 영문 한글화, 단어의 문자 칸수에 따른 점수표기, 십자말풀이 순서변경, 십자말풀이 게임 조작설명
*/

package crossword;
import java.text.SimpleDateFormat;
import java.util.Date;

class Tools {

	public static String getTime() {
		Date now = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		return formatter.format(now);
	}
}