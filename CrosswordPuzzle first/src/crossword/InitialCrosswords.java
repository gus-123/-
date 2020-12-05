/*
CrosswordPuzzle
https://github.com/hencoappel/CrosswordPuzzle/tree/master/src/crossword
Copyright 2013 Henco Appel / ���� �ѱ�ȭ, �ܾ��� ���� ĭ���� ���� ����ǥ��, ���ڸ�Ǯ�� ��������, ���ڸ�Ǯ�� ���� ���ۼ���
*/

package crossword;

import java.util.ArrayList;

public class InitialCrosswords {

	public static Crossword getCrossword1() {
		ArrayList<Clue> ��������Ʈ = new ArrayList<Clue>();
		ArrayList<Clue> ��������Ʈ = new ArrayList<Clue>();

		��������Ʈ.add(new Clue(1, 1, 0, "�����ϴ�", "OSTENTATIOUS"));
		��������Ʈ.add(new Clue(9, 0, 2, "������", "ARMED"));
		��������Ʈ.add(new Clue(10, 6, 2, "�����", "OCEANIC"));
		��������Ʈ.add(new Clue(11, 0, 4, "������", "ACHE"));
		��������Ʈ.add(new Clue(12, 5, 4, "�ż��� ����", "BEHOLDEN"));
		��������Ʈ.add(new Clue(14, 0, 6, "��½�Ÿ���", "TAWDRY"));
		��������Ʈ.add(new Clue(15, 7, 6, "��ź", "LAMENT"));
		��������Ʈ.add(new Clue(18, 0, 8, "�ٸ� ����", "OPPOSITE"));
		��������Ʈ.add(new Clue(20, 9, 8, "¡��", "OMEN"));
		��������Ʈ.add(new Clue(22, 0, 10, "������ ��", "HOTHEAD"));
		��������Ʈ.add(new Clue(23, 8, 10, "���۰�", "IBSEN"));
		��������Ʈ.add(new Clue(24, 0, 12, "����", "COLD-SHOULDER"));

		��������Ʈ.add(new Clue(2, 2, 0, "��������", "SOMEHOW"));
		��������Ʈ.add(new Clue(3, 4, 0, "����", "EDDY"));
		��������Ʈ.add(new Clue(4, 6, 0, "Ʈ��", "TROWEL"));
		��������Ʈ.add(new Clue(5, 8, 0, "�ڶ�", "THE KORAN"));
		��������Ʈ.add(new Clue(6, 10, 0, "������", "OWNED"));
		��������Ʈ.add(new Clue(7, 12, 0, "��°��", "SECOND TO NONE"));
		��������Ʈ.add(new Clue(8, 0, 1, "����", "CATASTROPHIC"));
		��������Ʈ.add(new Clue(13, 4, 5, "���", "BRUSSELS"));
		��������Ʈ.add(new Clue(16, 10, 6, "��� �Բ�", "EN MASSE"));
		��������Ʈ.add(new Clue(17, 6, 7, "��Ʃ���", "STUDIO"));
		��������Ʈ.add(new Clue(19, 2, 8, "�ر� ������", "PETAL"));
		��������Ʈ.add(new Clue(21, 8, 9, "��", "MILL"));

		return new Crossword("���ڸ�Ǯ�� (��)", 13, ��������Ʈ, ��������Ʈ);
	}

	public static Crossword getCrossword2() {
		ArrayList<Clue> ��������Ʈ = new ArrayList<Clue>();
		ArrayList<Clue> ��������Ʈ = new ArrayList<Clue>();

		��������Ʈ.add(new Clue(1, 1, 0, "����", "enthusiasm"));
		��������Ʈ.add(new Clue(8, 0, 2, "��", "river"));
		��������Ʈ.add(new Clue(9, 6, 2, "�Ծ��ϴ�", "adopt"));
		��������Ʈ.add(new Clue(10, 0, 4, "����", "golf"));
		��������Ʈ.add(new Clue(12, 5, 4, "����", "sentry"));
		��������Ʈ.add(new Clue(14, 0, 6, "����", "speech"));
		��������Ʈ.add(new Clue(17, 7, 6, "�ڵ�", "plum"));
		��������Ʈ.add(new Clue(21, 0, 8, "�Դٰ�", "extra"));
		��������Ʈ.add(new Clue(22, 6, 8, "����", "limit"));
		��������Ʈ.add(new Clue(23, 0, 10, "�濵", "management"));

		��������Ʈ.add(new Clue(2, 2, 0, "�ر�", "naval"));
		��������Ʈ.add(new Clue(3, 4, 0, "�����", "hard"));
		��������Ʈ.add(new Clue(4, 6, 0, "����", "share"));
		��������Ʈ.add(new Clue(5, 8, 0, "����", "about"));
		��������Ʈ.add(new Clue(6, 10, 0, "å�� �����ϴ� ���", "matey"));
		��������Ʈ.add(new Clue(7, 0, 1, "�ڶ��ϴ�", "brag"));
		��������Ʈ.add(new Clue(11, 3, 4, "��", "foe"));
		��������Ʈ.add(new Clue(13, 7, 4, "����", "nap"));
		��������Ʈ.add(new Clue(14, 0, 6, "����", "steam"));
		��������Ʈ.add(new Clue(15, 2, 6, "�Ծ���", "eaten"));
		��������Ʈ.add(new Clue(16, 4, 6, "û����", "clang"));
		��������Ʈ.add(new Clue(18, 8, 6, "����", "lemon"));
		��������Ʈ.add(new Clue(19, 10, 6, "�Ǵ�", "mutt"));
		��������Ʈ.add(new Clue(20, 6, 7, "���� ġ��", "slam"));

		return new Crossword("���ڸ�Ǯ�� (��)", 11, ��������Ʈ, ��������Ʈ);		
	}

}
