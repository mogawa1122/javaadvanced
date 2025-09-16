package util.bkup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ExeJson {

    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();

        // ルートJSONオブジェクトを作成
        ObjectNode rootNode = objectMapper.createObjectNode();

        // "name"フィールドを追加
//        rootNode.put("qIndex", "0");

        // "address"オブジェクトを作成
        ObjectNode questionNode = objectMapper.createObjectNode();
        questionNode.put("text", "質問だよ");
        questionNode.put("next", "5");

        // "address"オブジェクトをルートオブジェクトに追加
        rootNode.set("0", questionNode);

//        // "phones"配列を作成
//        ArrayNode phonesNode = objectMapper.createArrayNode();
//        phonesNode.add("123-456-7890");
//        phonesNode.add("090-1111-2222");
//
//        // "phones"配列をルートオブジェクトに追加
//        rootNode.set("phones", phonesNode);
//
//        // "details"オブジェクトを作成
//        ObjectNode detailsNode = objectMapper.createObjectNode();
//        detailsNode.put("age", 30);
//
//        // "details"オブジェクトを"address"オブジェクトに追加
//        addressNode.set("details", detailsNode);
//        

        // JSON文字列に変換
        String jsonString = null;
        try {
            jsonString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 結果のJSON出力
        System.out.println(jsonString);
    }
}