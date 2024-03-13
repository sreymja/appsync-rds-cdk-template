import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.util.List;

import static com.pennyhill.gen.Resolvers.requestTemplate;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class ResolversTest {

    @Test
    public void requestTemplateResolverStringAsExpected() {
        var actual = requestTemplate(false, List.of(
                "update statuses set id = '$ctx.args.status.id', name = '$ctx.args.status.name' where id = '$ctx.args.id';",
                "select * from statuses where id = :ID"), List.of(
                "\":ID\": $util.toJson($ctx.args.id)"));

        assertThat(actual).isEqualTo("""
                {
                    "version": "2018-05-29",
                    "statements": [
                                "update statuses set id = '$ctx.args.status.id', name = '$ctx.args.status.name' where id = '$ctx.args.id';",
                        "select * from statuses where id = :ID"
                    ],
                    "variableMap": {
                        ":ID": $util.toJson($ctx.args.id)
                    }
                }
                """);
    }

//    @Test
//    public void requestTemplateResolverStringAsExpected_Relation() throws JSONException {
//        var actual = requestTemplate(false, List.of(
//                "select * from audits where ticket_id = :ID" ), List.of(
//                "\":ID\":$util.toJson($ctx.args.id)" ));
//        var expected = """
//                {
//                    "version": "2018-05-29",
//                    "statements": [
//                            "select * from audits where ticket_id = :ID"
//                    ],
//                    "variableMap": {
//                        ":ID": $util.toJson($ctx.args.id)
//                    }
//                }
//                """;
//        JSONAssert.assertEquals(expected
//                , actual, JSONCompareMode.LENIENT);
//
//    }

//        @Test
//    public void requestTemplateResolverStringAsExpected_Relation() throws JSONException {
//        var actual = requestTemplate(false, List.of(
//                "select * from audits where ticket_id = :ID" ), List.of(
//                """
//                        ":ID":$util.toJson($ctx.args.id)
//                        },
//                        "variableTypeHintMap": {
//                                ":id": "DECIMAL"
//                        """
//                 ));
////        var expected = "";
////        JSONAssert.assertEquals(expected
////                , actual, JSONCompareMode.LENIENT);
//            assertThat(actual).isEqualTo("");
//
//    }

}