import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.models.RowMutation;
import com.google.cloud.bigtable.data.v2.models.Row;
import com.google.cloud.bigtable.data.v2.models.Query;

public class BigtableExample {
    public static void main(String[] args) throws Exception {
        String projectId = "project-id";
        String instanceId = "instance-id";
        String tableId = "table-id";

        try (BigtableDataClient dataClient = BigtableDataClient.create(projectId, instanceId)) {
            // Write data to Bigtable
            RowMutation mutation = RowMutation.create(tableId, "row-key")
                    .setCell("cf1", "column1", "Hello, Bigtable!");
            dataClient.mutateRow(mutation);
            System.out.println("Data written successfully!");

            // Read data from Bigtable
            Query query = Query.create(tableId).rowKey("row-key");
            Row row = dataClient.readRow(query);
            if (row != null) {
                System.out.println("Read data: " + row.getCells().get(0).getValue().toStringUtf8());
            } else {
                System.out.println("No data found.");
            }
        }
    }
}