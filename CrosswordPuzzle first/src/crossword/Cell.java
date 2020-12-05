/*
CrosswordPuzzle
https://github.com/hencoappel/CrosswordPuzzle/tree/master/src/crossword
Copyright 2013 Henco Appel / 영문 한글화, 단어의 문자 칸수에 따른 점수표기, 십자말풀이 순서변경, 십자말풀이 게임 조작설명
*/

package crossword;

class Cell {

	private String c;// current char
	private String answer;// correct char
	private String clueNum; // only if first character
	private Clue acrossClue, downClue; // references to clues this cell is part
										// of

	public Cell(char answer, Clue acrossClue, Clue downClue) {
		this(' ', answer, acrossClue, downClue);
	}

	public Cell(char c, char answer, Clue acrossClue, Clue downClue) {
		this.c = Character.toString(c);
		this.answer = Character.toString(answer);
		this.acrossClue = acrossClue;
		this.downClue = downClue;
	}

	protected boolean hasAcross() {
		return acrossClue != null;
	}

	protected boolean hasDown() {
		return downClue != null;
	}

	protected boolean isStart() {
		return clueNum != null;
	}

	public String getC() {
		return c;
	}

	public void setC(String c) {
		this.c = c;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Clue getAcrossClue() {
		return acrossClue;
	}

	public void setAcrossClue(Clue acrossClue) {
		this.acrossClue = acrossClue;
	}

	public Clue getDownClue() {
		return downClue;
	}

	public void setDownClue(Clue downClue) {
		this.downClue = downClue;
	}

	public String getClueNum() {
		return clueNum;
	}

	public void setClueNum(String clueNum) {
		this.clueNum = clueNum;
	}
}