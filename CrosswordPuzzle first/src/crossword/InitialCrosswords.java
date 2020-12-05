/*
CrosswordPuzzle
https://github.com/hencoappel/CrosswordPuzzle/tree/master/src/crossword
Copyright 2013 Henco Appel / 영문 한글화, 단어의 문자 칸수에 따른 점수표기, 십자말풀이 순서변경, 십자말풀이 게임 조작설명
*/

package crossword;

import java.util.ArrayList;

public class InitialCrosswords {

	public static Crossword getCrossword1() {
		ArrayList<Clue> 가로줄힌트 = new ArrayList<Clue>();
		ArrayList<Clue> 세로줄힌트 = new ArrayList<Clue>();

		가로줄힌트.add(new Clue(1, 1, 0, "과시하는", "OSTENTATIOUS"));
		가로줄힌트.add(new Clue(9, 0, 2, "무장한", "ARMED"));
		가로줄힌트.add(new Clue(10, 6, 2, "대양의", "OCEANIC"));
		가로줄힌트.add(new Clue(11, 0, 4, "아프다", "ACHE"));
		가로줄힌트.add(new Clue(12, 5, 4, "신세를 지는", "BEHOLDEN"));
		가로줄힌트.add(new Clue(14, 0, 6, "번쩍거리는", "TAWDRY"));
		가로줄힌트.add(new Clue(15, 7, 6, "한탄", "LAMENT"));
		가로줄힌트.add(new Clue(18, 0, 8, "다른 편의", "OPPOSITE"));
		가로줄힌트.add(new Clue(20, 9, 8, "징조", "OMEN"));
		가로줄힌트.add(new Clue(22, 0, 10, "성급한 자", "HOTHEAD"));
		가로줄힌트.add(new Clue(23, 8, 10, "극작가", "IBSEN"));
		가로줄힌트.add(new Clue(24, 0, 12, "무시", "COLD-SHOULDER"));

		세로줄힌트.add(new Clue(2, 2, 0, "웬일인지", "SOMEHOW"));
		세로줄힌트.add(new Clue(3, 4, 0, "에디", "EDDY"));
		세로줄힌트.add(new Clue(4, 6, 0, "트롤", "TROWEL"));
		세로줄힌트.add(new Clue(5, 8, 0, "코란", "THE KORAN"));
		세로줄힌트.add(new Clue(6, 10, 0, "소유의", "OWNED"));
		세로줄힌트.add(new Clue(7, 12, 0, "둘째로", "SECOND TO NONE"));
		세로줄힌트.add(new Clue(8, 0, 1, "참사", "CATASTROPHIC"));
		세로줄힌트.add(new Clue(13, 4, 5, "브뤼셀", "BRUSSELS"));
		세로줄힌트.add(new Clue(16, 10, 6, "모두 함께", "EN MASSE"));
		세로줄힌트.add(new Clue(17, 6, 7, "스튜디오", "STUDIO"));
		세로줄힌트.add(new Clue(19, 2, 8, "해군 정보부", "PETAL"));
		세로줄힌트.add(new Clue(21, 8, 9, "밀", "MILL"));

		return new Crossword("십자말풀이 (중)", 13, 가로줄힌트, 세로줄힌트);
	}

	public static Crossword getCrossword2() {
		ArrayList<Clue> 가로줄힌트 = new ArrayList<Clue>();
		ArrayList<Clue> 세로줄힌트 = new ArrayList<Clue>();

		가로줄힌트.add(new Clue(1, 1, 0, "열정", "enthusiasm"));
		가로줄힌트.add(new Clue(8, 0, 2, "강", "river"));
		가로줄힌트.add(new Clue(9, 6, 2, "입양하다", "adopt"));
		가로줄힌트.add(new Clue(10, 0, 4, "골프", "golf"));
		가로줄힌트.add(new Clue(12, 5, 4, "보초", "sentry"));
		가로줄힌트.add(new Clue(14, 0, 6, "연설", "speech"));
		가로줄힌트.add(new Clue(17, 7, 6, "자두", "plum"));
		가로줄힌트.add(new Clue(21, 0, 8, "게다가", "extra"));
		가로줄힌트.add(new Clue(22, 6, 8, "제한", "limit"));
		가로줄힌트.add(new Clue(23, 0, 10, "경영", "management"));

		세로줄힌트.add(new Clue(2, 2, 0, "해군", "naval"));
		세로줄힌트.add(new Clue(3, 4, 0, "어려운", "hard"));
		세로줄힌트.add(new Clue(4, 6, 0, "공유", "share"));
		세로줄힌트.add(new Clue(5, 8, 0, "거의", "about"));
		세로줄힌트.add(new Clue(6, 10, 0, "책을 좋아하는 사람", "matey"));
		세로줄힌트.add(new Clue(7, 0, 1, "자랑하다", "brag"));
		세로줄힌트.add(new Clue(11, 3, 4, "적", "foe"));
		세로줄힌트.add(new Clue(13, 7, 4, "낮잠", "nap"));
		세로줄힌트.add(new Clue(14, 0, 6, "증기", "steam"));
		세로줄힌트.add(new Clue(15, 2, 6, "먹었다", "eaten"));
		세로줄힌트.add(new Clue(16, 4, 6, "청명한", "clang"));
		세로줄힌트.add(new Clue(18, 8, 6, "레몬", "lemon"));
		세로줄힌트.add(new Clue(19, 10, 6, "되다", "mutt"));
		세로줄힌트.add(new Clue(20, 6, 7, "쾅쾅 치다", "slam"));

		return new Crossword("십자말풀이 (하)", 11, 가로줄힌트, 세로줄힌트);		
	}

}
