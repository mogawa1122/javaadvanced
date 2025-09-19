package tests;

public class DisplayAnswers {

	public static final String[] QUESTIONS =
		{
			"1:総合的な満足度",
			"2:総合的な満足度：満足傾向",
			"3:総合的な満足：不満足傾向"
		};
	
	public static final String[] SINGLE_CHOICES =
		{	"1:満足",
			"2:どちらかというと満足",
			"3:どちらともいえない",
			"4:どちらかというと不満足",
			"5:不満足"
		};
	
	public static final String[] MULTI_CHOICES_GOOD =
		{	
			"1:学習内容や授業が良かった",
			"2:教材が良かった",
			"3:担当教員が良かった",
			"4:試験結果・テスト結果が良かった",
			"5:実践的なスキルが身に付いた",
			"6:行事やイベントが良かった",
			"7:友人関係や学校生活が良かった"
		};
	public static final String[] MULTI_CHOICES_BAD =
		{	
			"1:学習内容や授業が良くなかった",
			"2:教材が良くなかった",
			"3:担当教員が良くなかった",
			"4:試験結果・テスト結果が良くなかった",
			"5:実践的なスキルが身に付かなかった",
			"6:行事やイベントが良くなかった",
			"7:友人関係や学校生活が良くなかった"
		};
	
    public void displayAnswers(int resultArray[][]) {
    	
   		System.out.println("■アンケート集計結果\n");
    	for (int i=0; i<QUESTIONS.length; i++) {
    		System.out.println(QUESTIONS[i]);
    		
    		switch(i) {
    		case 0:
    			for (int j=0; j<SINGLE_CHOICES.length; j++) {
    				System.out.println("\t" + SINGLE_CHOICES[j] + "\t\t" + resultArray[i+1][j+1] + "件");
    			};
    			System.out.println();
    			break;
    		case 1:
    			for (int j=0; j<MULTI_CHOICES_GOOD.length; j++) {
    				System.out.println("\t" + MULTI_CHOICES_GOOD[j] + "\t\t" + resultArray[i+1][j+1] + "件");
    			};
    			System.out.println();
    			break;
    		case 2:
    			for (int j=0; j<MULTI_CHOICES_BAD.length; j++) {
    				System.out.println("\t" + MULTI_CHOICES_BAD[j] + "\t\t" + resultArray[i+1][j+1] + "件");
    			};
    			System.out.println();
    		}
    	}
    	System.out.println("\n以上");

    }
}
