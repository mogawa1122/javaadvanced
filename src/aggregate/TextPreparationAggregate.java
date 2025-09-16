package aggregate;

import java.util.ArrayList;
import java.util.List;

/**
 * アンケート分析用の文書を準備するクラス
 */
public class TextPreparationAggregate {
    private List<String> contentList = new ArrayList<>();

    public TextPreparationAggregate() {
        /* 一人目の回答者 */
        this.contentList.add(
            "{item}1{/item/}\n"
            + "{singleAnswer}1{/singleAnswer/}\n"
            + "{item}2{/item/}\n"
            + "{multiAnswer}1 2 3{/multiAnswer/}\n"
            + "{EOF}\n"
        );

        /* 二人目の回答者 */
        this.contentList.add(
            "{item}1{/item/}\n"
            + "{singleAnswer}3{/singleAnswer/}\n"
            + "{EOF}\n"
        );

        /* 三人目の回答者 */
        this.contentList.add(
            "{item}1{/item/}\n"
            + "{singleAnswer}4{/singleAnswer/}\n"
            + "{item}3{/item/}\n"
            + "{multiAnswer}1 2 3{/multiAnswer/}\n"
            + "{EOF}\n"
        );

        /* 四人目の回答者 */
        this.contentList.add(
            "{item}1{/item/}\n"
            + "{singleAnswer}1{/singleAnswer/}\n"
            + "{item}2{/item/}\n"
            + "{multiAnswer}1 3 5{/multiAnswer/}\n"
            + "{EOF}\n"
        );

    }

    /**
     * this.contentListの内容を返す
     * @param i index番号
     * @return
     */
    public String getContent(int i) {
        return this.contentList.get(i);
    }

    /**
     * this.contentListのサイズを返す
     * @return contentListのサイズ
     */
    public int getSize() {
        return this.contentList.size();
    }

}
