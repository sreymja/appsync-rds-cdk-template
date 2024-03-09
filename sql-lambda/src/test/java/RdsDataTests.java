import com.amazon.rdsdata.client.RdsData;
import com.google.gson.Gson;
import lombok.Data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.services.rdsdata.RdsDataClient;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class RdsDataTests {
    private static String databaseStackOutput = """
    {
    "ClusterArn": "arn:aws:rds:us-east-1:445112775305:cluster:database-stack-appsynccluster1f863a01-zl3mnos1vm2f",
    "SecretArn": "arn:aws:secretsmanager:us-east-1:445112775305:secret:AppsyncDbCredentials-lleeJ3",
    "ExportsOutputRefAppSyncPasswordAttachment1EC4941B06A6BED0": "arn:aws:secretsmanager:us-east-1:445112775305:secret:AppsyncDbCredentials-lleeJ3",
    "ExportsOutputRefAppSyncPassword574D7E10EDEB03F5": "arn:aws:secretsmanager:us-east-1:445112775305:secret:AppsyncDbCredentials-lleeJ3",
    "ExportsOutputRefAppSyncCluster1F863A01E3BF7490": "database-stack-appsynccluster1f863a01-zl3mnos1vm2f",
    "DbName": "AppSyncDb",
    "ExportsOutputFnGetAttAppSyncClusterSecurityGroupFB4318D0GroupId91D87737": "sg-0ebccb8caed19771c"
  }
""";

    private static final Gson gson = new Gson();
    private static  RdsData client;

    @BeforeAll
    public static void setup() {
        DatabaseStack stack = gson.fromJson(databaseStackOutput, DatabaseStack.class);
       client = RdsData.builder()
                .sdkClient(RdsDataClient.builder().build())
                .database(stack.dbName)
                .resourceArn(stack.clusterArn)
                .secretArn(stack.secretArn)
                .build();
    }

    @Test
    public void insertIntoTable(){
        String sqlStmt = """
                insert into tickets (subject,
                                     markdown,
                                     status_id,
                                     priority_id,
                                     user_id,
                                     category_id)
                values ('insertIntoTable test ticket', '# Title', 1, 1, 1, 1);
                """;
        var executionResult = client.forSql(sqlStmt).execute();

        assertThat(executionResult.getNumberOfRecordsUpdated()).isEqualTo(1);
    }

    @Data
    public class DatabaseStack{
        private String clusterArn;
        private String secretArn;
        private String dbName;
    }
}
